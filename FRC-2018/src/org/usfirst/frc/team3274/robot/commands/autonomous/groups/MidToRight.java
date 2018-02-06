package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidToRight extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public MidToRight() {
		
		System.out.println("Robot moving to right Startpoint");
		addSequential(new DriveForward(4));
		addSequential(new TurnRobot(90));
		addSequential(new DriveForward(10));
		addSequential(new TurnRobot(-90));
		addSequential(new DriveForward(4));
		System.out.println("Robot arrived at Right Startpoint");
	}
}
