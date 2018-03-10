package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.commands.DeployClawArm;
import org.usfirst.frc.team3274.robot.commands.SetHeightWithEncoder;
import org.usfirst.frc.team3274.robot.commands.Suck;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveBackward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.EjectAutonomous;
import org.usfirst.frc.team3274.robot.commands.autonomous.ResetHeight;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftScaleToCubeToSwitch extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees. NOPE
	 */
	public LeftScaleToCubeToSwitch() {
		
		addSequential(new TurnRobot(45));
		addParallel(new SetHeightWithEncoder(3, 2));
		addParallel(new ResetClawArm());
		addSequential(new DriveForward(1));
		addSequential(new TurnRobot(90));
		addSequential(new DriveForward(7));
		addParallel(new Suck());
		addSequential(new DriveBackward(1));
		addSequential(new SetHeightWithEncoder(20, 3));
		addSequential(new DriveForward(1));
		addSequential(new EjectAutonomous());
		System.out.println("Robot has seized both Scale and Switch, and is joyous. Hopefullynot joyously delusional...");
	}
}
