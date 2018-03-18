package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.Eject;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.Suck;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveBackward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.ResetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftScaleToCubeToSwitch extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees. NOPE
	 */
	public LeftScaleToCubeToSwitch() {
		
		addSequential(new TurnRobot(45));
		addParallel(new SetHeightByGuesstimate(RobotMap.Autonomous.SWITCH_RAISE_HEIGHT));
		//addParallel(new ResetClawArm());
		addSequential(new DriveForward(1));
		addSequential(new TurnRobot(90));
		addSequential(new DriveForward(7));
		addSequential(new Suck(), RobotMap.Autonomous.SUCK_DURATION);
		addSequential(new DriveBackward(1));
		addParallel(new ResetHeightByGuesstimate());	
		addSequential(new SetHeightByGuesstimate(RobotMap.Autonomous.SWITCH_RAISE_HEIGHT));
		addSequential(new DriveForward(1));
		addSequential(new Eject(), RobotMap.Autonomous.EJECT_DURATION);
		addSequential(new Interrupt(Robot.kForkLift));
		System.out.println("Robot has seized both Scale and Switch, and is joyous. Hopefullynot joyously delusional...");
	}
}
