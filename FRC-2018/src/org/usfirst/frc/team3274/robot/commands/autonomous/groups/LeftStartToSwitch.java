package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.commands.DeployClaw;
import org.usfirst.frc.team3274.robot.commands.Eject;
import org.usfirst.frc.team3274.robot.commands.SetHeight;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftStartToSwitch extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public LeftStartToSwitch() {
		
		System.out.println("Robot moving to Switch");
		addSequential(new DriveForward(3));
		addParallel(new DeployClaw());
		addParallel(new SetHeight(20, 5));
		addSequential(new TurnRobot(90));
		addSequential(new Eject());
		System.out.println("Robot has captured the Switch! Robot is happy.");

	}
}
