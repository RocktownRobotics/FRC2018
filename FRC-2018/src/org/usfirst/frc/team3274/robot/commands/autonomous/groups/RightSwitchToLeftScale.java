package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.Eject;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.Suck;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.ResetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightSwitchToLeftScale extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees. NOT ANY MORE!!!!
	 */
	public RightSwitchToLeftScale() {
		
		addSequential(new TurnRobot(-90));
		addSequential(new DriveForward(2.5, false));
		addParallel(new ResetHeightByGuesstimate());
		addSequential(new  TurnRobot(90));
		addSequential(new DriveForward(21, false));
		System.out.println("Robot is now attempting to retrieve a cube");
		addSequential(new Suck(), RobotMap.Autonomous.SUCK_DURATION);
		addSequential(new TurnRobot(-90));
		addSequential(new DriveForward(7.5, false));
		addParallel(new SetHeightByGuesstimate(RobotMap.Autonomous.SCALE_RAISE_HEIGHT));
		addSequential(new TurnRobot(45));
		addSequential(new Eject(), RobotMap.Autonomous.EJECT_DURATION);
		addSequential(new Interrupt(Robot.kForkLift));
		System.out.println("Robot believes it has captured the switch and scale. Robot is delighted. Hopefully, Robot is not delusional");
		
		
	}
}
