package org.usfirst.frc.team3274.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ExampleAutonomousCommand extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public ExampleAutonomousCommand() {
		addSequential(new ShiftDownForTime());
	}
}
