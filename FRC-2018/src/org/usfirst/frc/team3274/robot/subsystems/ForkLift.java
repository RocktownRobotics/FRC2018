/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot.subsystems;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.util.TalonSRXGroup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls all DC motors and encoders for the forklift assembly
 */
public class ForkLift extends Subsystem {
	
	private DigitalInput _lowerLimitSwitch = new DigitalInput(RobotMap.LOWER_LIMIT_SWITCH);
	private DigitalInput _upperLimitSwitch = new DigitalInput(RobotMap.UPPER_LIMIT_SWITCH);
	
	public static final double ENCODER_PULSES_PER_REVOLUTION = 1343;
	public static final double WHEEL_DIAMETER = 4.0; // in inches

	private PWMTalonSRX liftMotor1 = new PWMTalonSRX(RobotMap.LIFT_MOTOR_LEFT);
	private PWMTalonSRX liftMotor2 = new PWMTalonSRX(RobotMap.LIFT_MOTOR_RIGHT);
	
	// sets the left and right forklift motors to be together...
	private SpeedController _liftMotors = new SpeedControllerGroup(this.liftMotor1, this.liftMotor2);

	private Encoder _liftEncoder = new Encoder(RobotMap.LIFT_ENCODER[0], RobotMap.LIFT_ENCODER[1], true,
			EncodingType.k4X);
	
	public ForkLift() {
		double distancePerPulse; // in feet
		distancePerPulse = (WHEEL_DIAMETER/* in */ * Math.PI) / (ENCODER_PULSES_PER_REVOLUTION * 12.0/* in/ft */);

		_liftEncoder.setDistancePerPulse(distancePerPulse);
	}

	public void setStartPositions() {
		_liftEncoder.reset();
	}
	public double getLiftHeight() {
		return _liftEncoder.getDistance();
	}
	
	public void resetLiftEncoders() {
		_liftEncoder.reset();
	}
	
	public boolean isLiftNotAtMaxHeight() {
		if(this._upperLimitSwitch.get()) {
			return false;
		}
		else{
			return true;
		}
	}
	
	public boolean isLiftNotAtMinHeight() {
		if(this._lowerLimitSwitch.get()) {
			return false;
		}
		else{
			return true;
		}
	}
	
	
	/**
	 * 
	 * @param power
	 * 		desired motor power
	 */
	public void setLiftPower(double power) {
		if(power > 0) {
			if(this.isLiftNotAtMaxHeight()) {
		_liftMotors.set(power);
			}
			else {
				System.out.println("Robot is unhappy, because someone told it to blow itself up. "
						+ "Robot does not like blowing up. "
						+ "Therefore, Robot refuses to raise the lift high enough to blow itself up. "
						+ "You should be happy that Robot is smart enough to do that");
				_liftMotors.set(0);
			}
		}
		else {
			if(this.isLiftNotAtMinHeight()) {
				_liftMotors.set(power);
			}
			else {
				System.out.println("'somebody' just ordered Robot to implode. Robot is"
						+ " unhappy, and refuses to lower the lift below the frame....");
				_liftMotors.set(0);
			}
		}
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
