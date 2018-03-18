package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.Suck;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightScaleToCube extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees. NOPE
	 */
	public RightScaleToCube() {
		//W/o any kind of switch or lift encoder, this DOES NOT WORK
		addSequential(new TurnRobot(-45));
		addSequential(new DriveForward(1));
		addSequential(new TurnRobot(-90));
		addSequential(new DriveForward(7));
		addSequential(new Suck(), RobotMap.Autonomous.SUCK_DURATION);
	}
}
