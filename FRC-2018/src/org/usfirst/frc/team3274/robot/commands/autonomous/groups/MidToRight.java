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

public class MidToRight extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public MidToRight() {

		System.out.println("Robot moving to right Startpoint");
		// addSequential(new DriveBackward(2));
		// addSequential(new TurnRobot(180));
		addSequential(new DriveForward(RobotMap.Autonomous.INITIAL_FORWARD_DISTANCE_TO_STARTPOINT));
		addSequential(new TurnRobot(90));
		addSequential(new DriveForward(RobotMap.Autonomous.MID_DISTANCE_TO_STARTPOINT));
		addSequential(new TurnRobot(-90));
		addSequential(new DriveForward(RobotMap.Autonomous.SECOND_FORWARD_DISTANCE_TO_STARTPOINT));
		System.out.println("Robot arrived at Right Startpoint");
	}
}
