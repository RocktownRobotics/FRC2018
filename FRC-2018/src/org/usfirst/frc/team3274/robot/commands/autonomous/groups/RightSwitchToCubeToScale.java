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

public class RightSwitchToCubeToScale extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees. NOPE
	 */
	public RightSwitchToCubeToScale() {
		
		addSequential(new TurnRobot(90));
		addSequential(new DriveForward(2.5));
		addParallel(new ResetHeight());
		addParallel(new ResetClawArm());
		addSequential(new  TurnRobot(-90));
		addSequential(new DriveForward(1));
		System.out.println("Robot is now attempting to retrieve a cube");
		addParallel(new Suck());
		addSequential(new DriveBackward(1));
		addSequential(new TurnRobot(90));
		addSequential(new DriveForward(7.5));
		addParallel(new SetHeightByGuesstimate(40));
		addSequential(new TurnRobot(-45));
		addSequential(new EjectAutonomous());
		System.out.println("Robot believes it has captured the switch and scale. Robot is delighted. Hopefully, Robot is not delusional");
		
		
	}
}
