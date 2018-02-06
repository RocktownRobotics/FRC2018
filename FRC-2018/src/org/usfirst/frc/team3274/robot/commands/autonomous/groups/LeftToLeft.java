package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftToLeft extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public LeftToLeft() {
		
		System.out.println("Robot en route to Left side Startpoint");
		addSequential(new DriveForward(8));
		System.out.println("Robot arrived at Left side Startpoint");
	}
}
