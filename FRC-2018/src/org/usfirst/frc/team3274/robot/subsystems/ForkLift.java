package org.usfirst.frc.team3274.robot.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3274.robot.OI;
import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team3274.robot.util.TalonSRXGroup;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

/**
 * 
 * @author Ian McGary
 * The DriveTrain subsystem controls the robot's forklift assembly
 */
public class ForkLift extends Subsystem {

	public static final double ENCODER_PULSES_PER_REVOLUTION = 1343;


	/** In inches **/
	public static final double WHEEL_DIAMETER = 6.0;



	// creates _liftMotor group, running both forklift belt motors as one motor.
	private WPI_TalonSRX _liftMotor = new TalonSRXGroup(RobotMap.LIFT_MOTOR_LEFT, RobotMap.LIFT_MOTOR_RIGHT);

	private Encoder _liftEncoder = new Encoder(RobotMap.LIFT_ENCODER[0], RobotMap.LIFT_ENCODER[1], true,
			EncodingType.k4X);


	public ForkLift() {

		// Configure encoders
		_liftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);


		double distancePerPulse; // in feet
		distancePerPulse = (WHEEL_DIAMETER/* in */ * Math.PI) / (ENCODER_PULSES_PER_REVOLUTION * 12.0/* in/ft */);

		_liftEncoder.setDistancePerPulse(distancePerPulse);

		// configure navX
	
	}

	
	/**
	 * Limit motor values to the -1.0 to +1.0 range.
	 */
	protected double limit(double value) {
		if (value > 1.0) {
			return 0.999;
		}
		if (value < -1.0) {
			return -0.999;
		}
		return value;
	}

	/**
	 * Returns 0.0 if the given value is within the specified range around zero. The
	 * remaining range between the deadzone and 1.0 is scaled from 0.0 to 1.0.
	 * 
	 * This method is used to make sure we ignore false input from the joysticks
	 * (basically any value that is too small).
	 *
	 * @param value
	 *            value to clip
	 * @param deadband
	 *            range around zero
	 */
	protected double applyDeadband(double value, double deadband) {
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) / (1.0 - deadband);
			} else {
				return (value + deadband) / (1.0 - deadband);
			}
		} else {
			return 0.0;
		}
	}

	/**
	 * Drive the wheels on one side forward with one stick and the wheels on another
	 * side forward with another stick.
	 * 
	 * @param joy
	 *            Xbox controller to use as the input for lift control.
	 */
	public void LiftControl(Joystick joy) {
		this.LiftControl(joy.getRawAxis(-RobotMap.XBOX_RIGHT_Y_AXIS));
	}

	/**
	 * Drive the wheels on one side forward with one stick and the wheels on another
	 * side forward with another stick.
	 * 
	 * @param leftAxis
	 *            left stick y-axis (or power between -1 and 1)
	 * @param rightAxis
	 *            right stick y-axis (or power between -1 and 1)
	 */
	public void LiftControl(double ControlStick) {
		LiftControl(-ControlStick);
	}

	/**
	 * Drive the wheels on one side forward with one stick and the wheels on another
	 * side forward with another stick.
	 * 
	 * @param leftPower
	 *            power towards left motor, between -1 and 1, where 0 is not moving
	 * @param rightPower
	 *            power towards right motor, between -1 and 1, where 0 is not moving
	 * @param applyDeadband
	 *            whether or not tankdrive should ignore smaller inputs, in order to
	 *            account for false input from joysticks.
	 */
	public void tankDrive(double leftPower, double rightPower, boolean applyDeadband) {
		double lJoyStickVal = leftPower;
		double rJoyStickVal = rightPower;

		if (applyDeadband) {
			lJoyStickVal = applyDeadband(leftPower, OI.JOYSTICK_DEADZONE);
			rJoyStickVal = applyDeadband(rightPower, OI.JOYSTICK_DEADZONE);
		}


		_leftMotor.set(ControlMode.PercentOutput, lJoyStickVal);
		_rightMotor.set(ControlMode.PercentOutput, rJoyStickVal);

		Timer.delay(0.005); // wait for a motor update time
	}

	public int getLeftRotations() {
		return this._leftEncoder.getRaw();
	}

	public int getRightRotations() {
		return this._rightEncoder.getRaw();
	}



	/**
	 * Stop the drivetrain from moving.
	 */
	public void stop() {
		this.tankDrive(0, 0);
	}

	/**
	 * Resets encoders to start tracking distance driven from a certain point.
	 */
	public void resetEncoders() {
		_rightEncoder.reset();
		_leftEncoder.reset();

		double time = 0;

		// wait for encoders to finish resetting
		while (Robot.itself.isEnabled() && Math.abs(_rightEncoder.getDistance()) > 0.15
				&& Math.abs(_leftEncoder.getDistance()) > 0.15) {

			Timer.delay(0.01);

			SmartDashboard.putNumber("leftEncoder", this.getLeftDistance());
			SmartDashboard.putNumber("rightEncoder", this.getRightDistance());
			;
			SmartDashboard.putNumber("encoder_reset_seconds", time += 0.01);
		}
	}

	/**
	 * Gets the average distance driven each encoder has registered in feet since
	 * the last time the encoders were reset.
	 * 
	 * @return average distance driven in feet
	 */
	public double getDistanceDriven() {
		double dist;

		dist = (_rightEncoder.getDistance() + _leftEncoder.getDistance()) / 2;

		return dist;
	}

	/**
	 * Distance in feet.
	 * 
	 * @return
	 */
	public double getLeftDistance() {
		return _leftEncoder.getDistance();
	}

	/**
	 * Distance in feet.
	 * 
	 * @return
	 */
	public double getRightDistance() {
		return _rightEncoder.getDistance();
	}

	/**
	 * @return The encoder getting the distance and speed of left side of the
	 *         drivetrain.
	 */
	public Encoder getLeftEncoder() {
		return _leftEncoder;
	}

	/**
	 * @return The encoder getting the distance and speed of right side of the
	 *         drivetrain.
	 */
	public Encoder getRightEncoder() {
		return _rightEncoder;

	}

	/**
	 * @return The current angle of the drivetrain.
	 */
	public double getYaw() {
		return navX.getYaw();
	}

	/**
	 * Resets Gyro Yaw to 0.
	 */

	public void resetYaw() {

		double time = 0;

		navX.zeroYaw();

		// wait for yaw to reset fully
		while (Robot.itself.isEnabled() && Math.abs(this.getYaw()) > 0.8) {
			Timer.delay(0.01);
			SmartDashboard.putNumber("gyro_yaw", this.getYaw());
			SmartDashboard.putNumber("gyro_reset_seconds", time += 0.01);
		}
	}
}
