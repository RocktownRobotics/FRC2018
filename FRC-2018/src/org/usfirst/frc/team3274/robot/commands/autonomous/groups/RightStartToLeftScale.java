package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.ArmLock;
import org.usfirst.frc.team3274.robot.commands.Eject;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveBackward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
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
	//	addSequential(new DriveBackward(2));
	//	addSequential(new TurnRobot(180));
		addSequential(new DriveForward(3));//before turning
		addSequential(new TurnRobot(40, true), 0.5);
		addSequential(new DriveForward(RobotMap.Autonomous.SIDE_DISTANCE_TO_STARTPOINT));
		addSequential(new TurnRobot(-40, true), 0.5);
		addSequential(new SetHeightByGuesstimate(RobotMap.Autonomous.SCALE_RAISE_HEIGHT), 0.85);
		addSequential(new DriveForward(5));//to scale
		addSequential(new Interrupt(Robot.kDriveTrain));
		addSequential(new Eject(), RobotMap.Autonomous.EJECT_DURATION);
		addSequential(new DriveBackward(0.2), 1);
		addSequential(new SetHeightByGuesstimate(0));
		addSequential(new Interrupt(Robot.kForkLift));
		System.out.println("Robot believes it has captured the scale. Robot is happy. Hopefully, Robot is not delusional");
	}
}
