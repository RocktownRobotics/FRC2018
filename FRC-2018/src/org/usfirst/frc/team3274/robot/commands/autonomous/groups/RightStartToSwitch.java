package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.Eject;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.ResetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightStartToSwitch extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public RightStartToSwitch() {
		
		System.out.println("Robot moving to Switch");
	//	addSequential(new DriveForward(1));
		addParallel(new SetHeightByGuesstimate(RobotMap.Autonomous.SWITCH_RAISE_HEIGHT));
		addSequential(new TurnRobot(-90), 3.5);
		addSequential(new DriveForward(RobotMap.Autonomous.SIDE_DISTANCE_TO_SWITCH_FROM_STARTPOINT, true),
				RobotMap.Autonomous.POS_TO_SWITCH_TIMEOUT);
		addSequential(new Eject(Eject.Speed.LOW), RobotMap.Autonomous.EJECT_DURATION);
		addSequential(new Interrupt(Robot.kForkLift));
		System.out.println("Robot has siezed the Switch, and is happy.");
		
	}
}
