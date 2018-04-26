package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.ArmLock;
import org.usfirst.frc.team3274.robot.commands.Eject;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DropClawForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftStartToSwitch extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public LeftStartToSwitch() {

		System.out.println("Robot moving to Switch");
//		addSequential(new DriveForward(1));
		addParallel(new SetHeightByGuesstimate(RobotMap.Autonomous.SWITCH_RAISE_HEIGHT));
		addSequential(new DropClawForTime(0.3));
		addParallel(new ArmLock());
		addSequential(new TurnRobot(80), 1.5);
		addSequential(new Interrupt(Robot.kDriveTrain));
		addSequential(new DriveForward(RobotMap.Autonomous.SIDE_DISTANCE_TO_SWITCH_FROM_STARTPOINT, false),
				RobotMap.Autonomous.POS_TO_SWITCH_TIMEOUT);
		addSequential(new Eject(Eject.Speed.LOW), RobotMap.Autonomous.EJECT_DURATION);
		addSequential(new Interrupt(Robot.kForkLift));
		System.out.println("Robot has captured the Switch! Robot is happy.");

	}
}
