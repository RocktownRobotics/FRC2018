package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.ArmLock;
import org.usfirst.frc.team3274.robot.commands.Eject;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveBackward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveBackwardScale;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForwardScale;
import org.usfirst.frc.team3274.robot.commands.autonomous.DropClawForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftStartToScale extends CommandGroup {
	public LeftStartToScale() {
		System.out.println("Robot moving to Scale");
		addSequential(new Interrupt(Robot.kDriveTrain));
		System.out.println("Drivetrain Interrupted");
		addSequential(new DropClawForTime(0.3));
		addParallel(new ArmLock());
		addSequential(new DriveForward(RobotMap.Autonomous.FORWARD_DISTANCE_TO_SCALE_FROM_STARTPOINT, true));
		addSequential(new TurnRobot(40), 1.5);  //is actually 90 degrees
		addSequential(new Interrupt(Robot.kDriveTrain));
		addSequential(new DriveBackward(1.5), 1.2);
		addSequential(new SetHeightByGuesstimate(RobotMap.Autonomous.SCALE_RAISE_HEIGHT), 0.85);
		addSequential(new Eject(), RobotMap.Autonomous.EJECT_DURATION);
		addSequential(new Interrupt(Robot.kForkLift));
		System.out.println("Robot has captured the Scale! Robot is happy. With luck, Robot knows what it is talking about");
	}
}