package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.commands.DeployClawArm;
import org.usfirst.frc.team3274.robot.commands.RetractClawArm;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ResetClawArm extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public ResetClawArm() {

		if(Robot.kClawArm.isClawRetracted() == true) {
			addSequential(new DeployClawArm());
		}
		else {
			addSequential(new RetractClawArm());
		}
		
	}
}
