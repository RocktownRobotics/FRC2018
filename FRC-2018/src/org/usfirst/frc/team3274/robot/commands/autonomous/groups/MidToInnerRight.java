package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.commands.ArmLock;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveBackward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidToInnerRight extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public MidToInnerRight() {

		addParallel(new ArmLock());
		// addSequential(new DriveBackward(2));
		// addSequential(new TurnRobot(180));
		System.out.println("Robot moving to Left side Startpoint");
		addSequential(new DriveForward(5));
		addSequential(new TurnRobot(90));
		addSequential(new DriveForward(6));
		addSequential(new TurnRobot(-90));
		addSequential(new DriveForward(5));
		addSequential(new Interrupt(Robot.kClawArm));
		System.out.println("Robot arrived at Left side Startpoint");
	}
}
