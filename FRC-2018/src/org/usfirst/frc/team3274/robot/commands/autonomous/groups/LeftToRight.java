package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftToRight extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public LeftToRight() {
		
		System.out.println("Robot moving to Right side Startpoint");
		addSequential(new DriveForward(4));
		addSequential(new TurnRobot(-90));
		addSequential(new DriveForward(21));
		addSequential(new TurnRobot(90));
		addSequential(new DriveForward(4));
		System.out.println("Robot arriving at Right side Startpoint");
	}
}
