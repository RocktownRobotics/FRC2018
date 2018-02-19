package org.usfirst.frc.team3274.robot.subsystems;

import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.util.StoppableSubsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DrivePneumatics extends StoppableSubsystem {
	private Solenoid shifterLowGear = new Solenoid(RobotMap.SHIFTER_LOW_GEAR);
	private Solenoid shifterHighGear = new Solenoid(RobotMap.SHIFTER_HIGH_GEAR);

	// DoubleSolenoid gearShifter = new DoubleSolenoid(RobotMap.shifterForward,
	// RobotMap.shifterReverse);
	//
	// DoubleSolenoid.Value off = DoubleSolenoid.Value.kOff;
	// // May need to switch lowGear and HighGear values (kForward/kReverse)
	// DoubleSolenoid.Value lowGear = DoubleSolenoid.Value.kForward;
	// DoubleSolenoid.Value highGear = DoubleSolenoid.Value.kReverse;

	public DrivePneumatics() {
		// LiveWindow.addActuator("DrivePnumatics", "GearShifter", gearShifter);
	}

	public void StartLowGear() {
		shifterLowGear.set(true);
		// gearShifter.set(lowGear);
	}

	public void StartHighGear() {
		shifterHighGear.set(true);
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
		shifterHighGear.set(false);
		shifterLowGear.set(false);

		// gearShifter.set(off);
	}

	/**
	 * 
	 * @return -1 for low and 1 for high
	 */
	public boolean getCurrentGear() {
		return shifterHighGear.get() == true;
	}
}
