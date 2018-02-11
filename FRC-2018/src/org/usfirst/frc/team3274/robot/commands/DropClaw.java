/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;

/**
 * An example command. You can replace me with your own command.
 */
public class DropClaw extends Command {
	public DropClaw() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kClaw);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		System.out.println("Drop Claw");

		if (Robot.kClaw.getDeployAngle() < 160) {
			Robot.kClaw.deploy(0.1);
		} else {
			System.out.println("Failed: Claw already dropped");
		}

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if (Robot.kForkLift.getLiftHeight() > 12) {
			if (Robot.kClaw.getDeployAngle() <= 80) {
				return false;
			} else {
				System.out.println("Claw Dropped Successfully");
				return true;
			}
		} else {
			System.out.println("Robot did not drop the claw, because the forklift was too low. Try again later.");
			return true;
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {

		Robot.kClaw.deploy(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		System.out.println("Claw Drop interrupted");
	}
}