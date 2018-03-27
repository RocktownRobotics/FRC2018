package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.ArmLock;
import org.usfirst.frc.team3274.robot.commands.Eject;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveBackward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DropClawForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.ResetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightStartToLeftScale extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public RightStartToLeftScale() {
		
		System.out.println("Robot moving to Left side Scale");
		addSequential(new DropClawForTime(0.3));
		addParallel(new ArmLock());
		addSequential(new DriveForward(3));//before turning
		addSequential(new TurnRobot(-90));
		addSequential(new DriveForward(RobotMap.Autonomous.SIDE_DISTANCE_TO_SCALE));
		addSequential(new TurnRobot(90));
		addSequential(new SetHeightByGuesstimate(RobotMap.Autonomous.SCALE_RAISE_HEIGHT), 2);
		addSequential(new DriveForward(RobotMap.Autonomous.CROSS_FORWARD_DISTANCE_TO_SCALE));//to scale
		addSequential(new Interrupt(Robot.kDriveTrain));
		addSequential(new Eject(), RobotMap.Autonomous.EJECT_DURATION);
		addSequential(new DriveBackward(RobotMap.Autonomous.SCALE_SAFE_BACKUP_DISTANCE), 1);
		addSequential(new ResetHeightByGuesstimate(), 4);
		addSequential(new Interrupt(Robot.kClawArm));
		
		System.out.println("Robot believes it has captured the scale. Robot is happy. Hopefully, Robot is not delusional");
		
		
	}
}
