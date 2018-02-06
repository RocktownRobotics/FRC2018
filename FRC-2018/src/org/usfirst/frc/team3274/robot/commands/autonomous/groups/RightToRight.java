package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightToRight extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public RightToRight() {
		
		System.out.println("Robot moving to Right side Startpoint");
		addSequential(new DriveForward(8));
		System.out.println("Robot arrived at Right side Startpoint");
		
	}
}
