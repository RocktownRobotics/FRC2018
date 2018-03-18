package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.Eject;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightStartToScale extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public RightStartToScale() {

		System.out.println("Robot moving to Scale");
		addSequential(new DriveForward(RobotMap.Autonomous.FORWARD_DISTANCE_TO_SCALE_FROM_STARTPOINT));
		addParallel(new SetHeightByGuesstimate(RobotMap.Autonomous.SCALE_RAISE_HEIGHT));
		addSequential(new TurnRobot(-45));
		addSequential(new Eject(), RobotMap.Autonomous.EJECT_DURATION);
		addSequential(new Interrupt(Robot.kForkLift));
		System.out.println("Robot believes it has captured the scale. Robot is happy. Hopefully, Robot is not delusional");
		
		
	}
}
