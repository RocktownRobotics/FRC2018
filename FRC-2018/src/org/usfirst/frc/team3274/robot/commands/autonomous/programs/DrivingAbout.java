package org.usfirst.frc.team3274.robot.commands.autonomous.programs;

import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.Eject;
import org.usfirst.frc.team3274.robot.commands.MoveClawTo;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DrivingAbout extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees. Except... NOPE
	 */
	public DrivingAbout() {
		System.out.println("Robot will now do something that achieves nothing...");
		addSequential(new DriveForward(5));
		addSequential(new TurnRobot(180));
		addSequential(new DriveForward(5));
		System.out.println("Will now simultaneously raise and deploy the claw");
		addSequential(new SetHeightByGuesstimate(35));
		addParallel(new MoveClawTo(45));
		addSequential(new Eject(), RobotMap.Autonomous.EJECT_DURATION);
	}
}
