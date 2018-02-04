/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;

/**
 * An example command. You can replace me with your own command.
 */
public class RetractClaw extends Command {
	public RetractClaw() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kClaw);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		System.out.println("Retracting Claw");

		if (Robot.kClaw.getDeployAngle() > 5) {
			Robot.kClaw.retract(0.1);
		} else {
			System.out.println("Failed: Claw already retracted");
		}

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if (Robot.kClaw.getDeployAngle() > 5) {
			return false;
		} else {
			System.out.println("Claw Retracted Successfully");

			return true;
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.kClaw.retract(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
