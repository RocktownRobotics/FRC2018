/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot.subsystems;

import org.usfirst.frc.team3274.robot.OI;
import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.autonomous.RunForkLift;
import org.usfirst.frc.team3274.robot.util.StoppableSubsystem;
import org.usfirst.frc.team3274.robot.util.TalonSRXGroup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls all DC motors and encoders for the forklift assembly
 */
public class ForkLift extends StoppableSubsystem {

	private DigitalInput _lowerLimitSwitch = new DigitalInput(RobotMap.LOWER_LIMIT_SWITCH);
	//private DigitalInput _upperLimitSwitch = new DigitalInput(RobotMap.UPPER_LIMIT_SWITCH);

	public static final double ENCODER_PULSES_PER_REVOLUTION = 1343;
	public static final double WHEEL_DIAMETER = 4.0; // in inches

	private Spark liftMotor1 = new Spark(RobotMap.LIFT_MOTOR_LEFT);
	private Spark liftMotor2 = new Spark(RobotMap.LIFT_MOTOR_RIGHT);

	// sets the left and right forklift motors to be together...
	private SpeedController _liftMotors = new SpeedControllerGroup(this.liftMotor1, this.liftMotor2);

	// private Encoder _liftEncoder = new Encoder(RobotMap.LIFT_ENCODER[0],
	// RobotMap.LIFT_ENCODER[1],
	// true, EncodingType.k4X);

	public ForkLift() {

		// _liftEncoder.setDistancePerPulse(distancePerPulse);
	}

	public void setStartPositions() {
		// _liftEncoder.reset();
	}

	public double getLiftHeight() {
		// return _liftEncoder.getDistance();
		DriverStation.reportError("Tried to get lift height using nonexistant encoder", true);
		return 0;
	}

	public void resetLiftEncoders() {
		// _liftEncoder.reset();
	}

	public boolean isLiftNotAtMaxHeight() {
//		if (this._upperLimitSwitch.get()) {
			return true;
//		} else {
//			return false;
//		}
	}

	public boolean isLiftNotAtMinHeight() {
		// if (this._lowerLimitSwitch.get()) {
		return true;
		// } else {
		// return false;
	}
	// }

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
				return (value - deadband) * (value - deadband);
			} else {
				return -1 * (value + deadband) * (value + deadband);
			}
		} else {
			return 0.0;
		}
	}

	/**
	 * 
	 * @param power
	 *            desired motor power
	 * 
	 *            public void tankDrive(Joystick joy) {
	 *            this.tankDrive(joy.getRawAxis(RobotMap.XBOX_LEFT_Y_AXIS),
	 *            -joy.getRawAxis(RobotMap.XBOX_RIGHT_Y_AXIS), true); }
	 * 
	 *            /** Drive the wheels on one side forward with one stick and the
	 *            wheels on another side forward with another stick.
	 * 
	 * @param leftPower
	 *            power towards left motor, between -1 and 1, where 0 is not moving
	 * @param rightPower
	 *            power towards right motor, between -1 and 1, where 0 is not moving
	 * @param applyDeadband
	 *            whether or not tankdrive should ignore smaller inputs, in order to
	 *            account for false input from joysticks. Will also assume two
	 *            inputs that are really close to each other should actually be the
	 *            same input.
	 */
	public void setLiftPowerWithJoystick(double power) {
		double realPower = -power;

		// apply deadband
		realPower = applyDeadband(realPower, OI.JOYSTICK_DEADZONE);

		setLiftPower(realPower);
	}

	public void setLiftPower(double power) {

		double realPower = -power;

		 _liftMotors.set(realPower);
//		
//		if (power > 0) {
//			if (this.isLiftNotAtMaxHeight()) {
//				_liftMotors.set(realPower);
//			} else {
//				System.out.println("Robot is unhappy, because someone told it to blow itself up. "
//						+ "Robot does not like blowing up. "
//						+ "Therefore, Robot refuses to raise the lift high enough to blow itself up. "
//						+ "You should be happy that Robot is smart enough to do that");
//				_liftMotors.set(0);
//			}
//		} else {
//			if (this.isLiftNotAtMinHeight()) {
//			_liftMotors.set(realPower);
//			 } else {
//			 System.out.println("'somebody' just ordered Robot to implode. Robot is"
//			 + " unhappy, and refuses to lower the lift below the frame....");
//			 _liftMotors.set(0);
//			 }
//		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new RunForkLift());
	}

	@Override
	public void stop() {
		setLiftPower(0);
	}
}
