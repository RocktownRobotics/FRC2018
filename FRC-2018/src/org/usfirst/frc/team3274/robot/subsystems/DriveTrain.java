package org.usfirst.frc.team3274.robot.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3274.robot.OI;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team3274.robot.util.TalonSRXGroup;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The DriveTrain subsystem controls the robot's chassis and reads in
 * information about it's speed and position.
 */
public class DriveTrain extends Subsystem {

	public static final int ENCODER_PULSES_PER_REVOLUTION = 256;

	/**
	 * Normal power is multiplied by this value when in enter sniper mode. Greater
	 * than 0 and less than 1.
	 */
	private static final double SNIPER_MODE_MULTIPLIER = 0.5;

	/** In inches **/
	public static final double WHEEL_DIAMETER = 4.0;

	/** PID Controller coefficients. **/
	public static final double P_VAL = 1;
	public static final double I_VAL = 1;
	public static final double D_VAL = 1;

	private boolean isSniperMode;

	// "rightMotor" and "leftMotor" are
	// actually all three motors on the side but should act as one
	private WPI_TalonSRX _rightMotor = new TalonSRXGroup(RobotMap.REAR_RIGHT_MOTOR,
			RobotMap.FRONT_RIGHT_MOTOR, RobotMap.RIGHT_MOTOR);
	private WPI_TalonSRX _leftMotor = new TalonSRXGroup(RobotMap.FRONT_LEFT_MOTOR,
			RobotMap.REAR_LEFT_MOTOR, RobotMap.LEFT_MOTOR);

	private Encoder _rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER[0],
			RobotMap.RIGHT_ENCODER[1], true, EncodingType.k4X);
	private Encoder _leftEncoder = new Encoder(RobotMap.LEFT_ENCODER[0], RobotMap.LEFT_ENCODER[1],
			true, EncodingType.k4X);

	// Right side PID Loop motor control
	private PIDController _rightPIDLoop = new PIDController(P_VAL, I_VAL, D_VAL, _rightEncoder,
			_rightMotor);

	// Left side PID motor control
	private PIDController _leftPIDLoop = new PIDController(P_VAL, I_VAL, D_VAL, _leftEncoder,
			_leftMotor);

	// Creates Setpoints for the PID Loops. See Line 276-277. Note that 1 is likely
	// not the desired value.
	double rightSetPoint = 1;
	double leftSetPoint = 1;

	public DriveTrain() {

		// Configure encoders
		_rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		_leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);

		isSniperMode = true;

		double distancePerPulse; // in feet
		distancePerPulse = (WHEEL_DIAMETER/* in */ * Math.PI)
				/ (ENCODER_PULSES_PER_REVOLUTION * 12.0/* in/ft */);

		_rightEncoder.setDistancePerPulse(distancePerPulse);
		_leftEncoder.setDistancePerPulse(distancePerPulse);
	}

	/**
	 * When other commands aren't using the drivetrain, allow tank drive with the
	 * joystick.
	 */
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
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
	 *            Xbox controller to use as the input for tank drive.
	 */
	public void tankDrive(Joystick joy) {
		this.tankDrive(joy.getRawAxis(-RobotMap.XBOX_LEFT_Y_AXIS),
				joy.getRawAxis(-RobotMap.XBOX_RIGHT_X_AXIS));
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
	public void tankDrive(double leftAxis, double rightAxis) {
		tankDrive(-leftAxis, -rightAxis, true);
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
		double lJoyStickVal = 0.0;
		double rJoyStickVal = 0.0;

		if (applyDeadband) {
			lJoyStickVal = applyDeadband(leftPower, OI.JOYSTICK_DEADZONE);
			rJoyStickVal = applyDeadband(rightPower, OI.JOYSTICK_DEADZONE);
		}

		// used for fine movements
		if (isSniperMode) {
			lJoyStickVal *= SNIPER_MODE_MULTIPLIER;
			rJoyStickVal *= SNIPER_MODE_MULTIPLIER;
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
	 * Used to enable or disable sniper mode. Sniper mode makes the robot more
	 * sensitive to actions from the driver.
	 * 
	 * @param isEnabled
	 *            whether or not sniper mode is enabled
	 */
	public void setSniperMode(boolean isEnabled) {
		this.isSniperMode = isEnabled;
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
	 * Enables the PID Loop motor controls.
	 */
	public void enablePID() {
		_leftPIDLoop.enable();
		_rightPIDLoop.enable();
		_rightPIDLoop.setSetpoint(rightSetPoint);
		_leftPIDLoop.setSetpoint(leftSetPoint);

	}

	/**
	 * Disables the PID Loop motor controls.
	 */

	public void disablePID() {
		_leftPIDLoop.disable();
		_rightPIDLoop.disable();
	}

	//
	// /**
	// * @return The current angle of the drivetrain.
	// */
	// public double getAngle() {
	// return gyro.getAngle();
	// }
}
