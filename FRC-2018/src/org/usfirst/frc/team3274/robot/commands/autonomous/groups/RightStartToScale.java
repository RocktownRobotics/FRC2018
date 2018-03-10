package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.commands.DeployClawArm;
import org.usfirst.frc.team3274.robot.commands.MoveClawTo;
import org.usfirst.frc.team3274.robot.commands.SetHeightWithEncoder;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.EjectAutonomous;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightStartToScale extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	public RightStartToScale() {

		System.out.println("Robot moving to Scale");
		addParallel(new ResetClawArm());
		addSequential(new DriveForward(13));
		addParallel(new SetHeightWithEncoder(40, 3));
		addSequential(new TurnRobot(-45));
		addSequential(new EjectAutonomous());
		System.out.println("Robot believes it has captured the scale. Robot is happy. Hopefully, Robot is not delusional");
		
		
	}
}
