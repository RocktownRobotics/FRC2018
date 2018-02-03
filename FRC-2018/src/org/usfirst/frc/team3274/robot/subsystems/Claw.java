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
	private Solenoid clawPistons = new Solenoid(RobotMap.clawPiston);

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

	public Solenoid getclawPiston() {
		return clawPistons;
	}

	public boolean isClawClosed() {
		return clawClosed;
	}

	public Claw() {
		// LiveWindow.addActuator("DrivePnumatics", "GearShifter", gearShifter);
	}

	public void OpenClaw() {
		clawPistons.set(false);
}

	public void CloseClaw() {
		clawPistons.set(true);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void stop() {
		clawPistons.set(false);

		// gearShifter.set(off);
	}

	/**
	 * 
	 * @return -1 for low and 1 for high
	 */

}
