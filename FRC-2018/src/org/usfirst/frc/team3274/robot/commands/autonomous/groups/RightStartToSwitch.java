package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.commands.ArmLock;
import org.usfirst.frc.team3274.robot.commands.DeployClawArm;
import org.usfirst.frc.team3274.robot.commands.MoveClawTo;
import org.usfirst.frc.team3274.robot.commands.SetHeightWithEncoder;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveBackward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.EjectAutonomous;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightStartToSwitch extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public RightStartToSwitch() {
		
		System.out.println("Robot moving to Switch");
	//	addParallel(new ArmLock());
	//	addSequential(new DriveBackward(2));
	//	addSequential(new TurnRobot(180));
		addSequential(new DriveForward(3));
	//	addParallel(new ResetClawArm());
	//	addParallel(new SetHeightByGuesstimate(20));
		addSequential(new TurnRobot(-90));
		addSequential(new EjectAutonomous());
		System.out.println("Robot has siezed the Switch, and is happy.");
		
	}
}
