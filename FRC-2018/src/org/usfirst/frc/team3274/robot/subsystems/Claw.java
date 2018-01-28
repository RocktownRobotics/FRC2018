package org.usfirst.frc.team3274.robot.subsystems;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.util.TalonSRXGroup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {
	private Solenoid reverseShifter = new Solenoid(RobotMap.shifterReverse);
	private Solenoid forwardShifter = new Solenoid(RobotMap.shifterForward);

	private boolean clawClosed;

	private WPI_TalonSRX leftClaw = new WPI_TalonSRX(RobotMap.CLAW_MOTOR_LEFT);
	private WPI_TalonSRX rightClaw = new WPI_TalonSRX(RobotMap.CLAW_MOTOR_RIGHT);

	// sets the left and right forklift motors to be together...
	private WPI_TalonSRX _liftMotors = new TalonSRXGroup(RobotMap.LIFT_MOTOR_LEFT, RobotMap.LIFT_MOTOR_RIGHT);

	
	
	/**
	 * @param ejectSpeed
	 *            the motor power at which the cube should be ejected. Between 0 and
	 *            1, the negatives, if any, will be handled in the eject code.
	 * 
	 * @param thrustTime
	 *            the time to run the ejection motors, in seconds
	 */

	public void eject(double ejectSpeed) {
		if (Robot.kClaw.isClawClosed() == true) {
			this.leftClaw.set(ejectSpeed);
			this.rightClaw.set(ejectSpeed);
			
		}

		else {
			System.out.println("Ejection failed due to open claw");
		}
	}
	// DoubleSolenoid gearShifter = new DoubleSolenoid(RobotMap.shifterForward,
	// RobotMap.shifterReverse);
	//
	// DoubleSolenoid.Value off = DoubleSolenoid.Value.kOff;
	// // May need to switch lowGear and HighGear values (kForward/kReverse)
	// DoubleSolenoid.Value lowGear = DoubleSolenoid.Value.kForward;
	// DoubleSolenoid.Value highGear = DoubleSolenoid.Value.kReverse;

	public Solenoid getReverseShifter() {
		return reverseShifter;
	}

	public Solenoid getForwardShifter() {
		return forwardShifter;
	}

	public boolean isClawClosed() {
		return clawClosed;
	}

	public Claw() {
		// LiveWindow.addActuator("DrivePnumatics", "GearShifter", gearShifter);
	}

	public void StartLowGear() {
		reverseShifter.set(true);
		// gearShifter.set(lowGear);
	}

	public void StartHighGear() {
		forwardShifter.set(true);
		// gearShifter.set(highGear);
	}

	public void ShiftGears() {
		// if (gearShifter.get() == lowGear)
		// {
		// StartHighGear();
		// } else if (gearShifter.get() == highGear)
		// {
		// StartLowGear();
		// }
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void stop() {
		forwardShifter.set(false);
		reverseShifter.set(false);

		// gearShifter.set(off);
	}

	/**
	 * 
	 * @return -1 for low and 1 for high
	 */
	public boolean getCurrentGear() {
		return forwardShifter.get() == true;
	}
}
