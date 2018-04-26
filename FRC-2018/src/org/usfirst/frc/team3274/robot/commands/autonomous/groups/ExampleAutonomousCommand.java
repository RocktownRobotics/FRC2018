package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ExampleAutonomousCommand extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public ExampleAutonomousCommand() {
		// make sure robot is in low gear
//		addSequential(new ShiftDownForTime());

		// addSequential(new DriveForward(3));
		// addSequential(new TurnRobot(-90));
//		addSequential(new DriveForward(2));
//		addSequential(new DriveForward(4));
//		addSequential(new DriveForward(6));
		addSequential(new DriveForward(27));
	}
}
