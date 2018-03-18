package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.ArmLock;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveBackward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightToLeft extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public RightToLeft() {

		System.out.println("Robot moving to Left Startpoint");
		// addSequential(new DriveBackward(2));
		// addSequential(new TurnRobot(180));
		addSequential(new DriveForward(RobotMap.Autonomous.INITIAL_FORWARD_DISTANCE_TO_STARTPOINT, false));
		addSequential(new TurnRobot(-90));
		addSequential(new DriveForward(RobotMap.Autonomous.SIDE_DISTANCE_TO_STARTPOINT, false));
		addSequential(new TurnRobot(90));
		addSequential(new DriveForward(RobotMap.Autonomous.SECOND_FORWARD_DISTANCE_TO_STARTPOINT, false));
		System.out.println("Robot at Left Starpoint.");

	}
}
