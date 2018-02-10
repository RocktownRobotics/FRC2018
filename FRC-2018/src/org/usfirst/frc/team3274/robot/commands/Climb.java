package org.usfirst.frc.team3274.robot.commands;

import org.usfirst.frc.team3274.robot.commands.autonomous.ResetHeight;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Climb extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public Climb() {
		
		addSequential(new SetHeight(14,1));
		addParallel(new DropClaw());
		addSequential(new SetHeight(42,1));
		addParallel(new Eject());
		addSequential(new ResetHeight());
		
	}
}
