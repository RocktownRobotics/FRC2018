package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.commands.SetHeightWithEncoder;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveBackward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveBackwardsAndLift extends CommandGroup {

	private double distance;
	private double height;
	private double tolerance;
	
	public MoveBackwardsAndLift(double distance, double height, double tolerance) {
		requires(Robot.kDriveTrain);
		requires(Robot.kClaw);
		this.distance = distance;
		this.height = height;
		this.tolerance = tolerance;
		
		// Drive forward and set the height...
				addSequential(new DriveBackward(distance));
				addParallel(new SetHeightByGuesstimate(height));
	}
	
	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */
	
}
