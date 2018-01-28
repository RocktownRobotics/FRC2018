package org.usfirst.frc.team3274.robot.subsystems;

import org.usfirst.frc.team3274.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawPistons extends Subsystem {
	private Solenoid reverseShifter = new Solenoid(RobotMap.shifterReverse);
	private Solenoid forwardShifter = new Solenoid(RobotMap.shifterForward);

	// DoubleSolenoid gearShifter = new DoubleSolenoid(RobotMap.shifterForward,
	// RobotMap.shifterReverse);
	//
	// DoubleSolenoid.Value off = DoubleSolenoid.Value.kOff;
	// // May need to switch lowGear and HighGear values (kForward/kReverse)
	// DoubleSolenoid.Value lowGear = DoubleSolenoid.Value.kForward;
	// DoubleSolenoid.Value highGear = DoubleSolenoid.Value.kReverse;

	public ClawPistons() {
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
