package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.ArmLock;
import org.usfirst.frc.team3274.robot.commands.Eject;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveBackward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DropClawForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftStartToScale extends CommandGroup {

	
	public LeftStartToScale() {
		
		System.out.println("Robot moving to Scale");
		addSequential(new DropClawForTime(0.3));
		addParallel(new ArmLock());
		addSequential(new DriveForward(RobotMap.Autonomous.FORWARD_DISTANCE_TO_SCALE_FROM_STARTPOINT));
		//addParallel(new ResetClawArm());
		addSequential(new TurnRobot(90), 2);
		addSequential(new Interrupt(Robot.kDriveTrain));
		addSequential(new DriveBackward(0.75), 1);
		addSequential(new SetHeightByGuesstimate(RobotMap.Autonomous.SCALE_RAISE_HEIGHT), 2);
		addSequential(new Eject(), RobotMap.Autonomous.EJECT_DURATION);
		addSequential(new Interrupt(Robot.kForkLift));
		System.out.println("Robot has captured the Scale! Robot is happy. With luck, Robot knows what it is talking about");
	}
}
