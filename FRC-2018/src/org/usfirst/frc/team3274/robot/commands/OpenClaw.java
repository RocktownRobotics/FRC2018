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
public class OpenClaw extends Command {
	public OpenClaw() {
		requires(Robot.kClaw);
	}

	private boolean clawClosed;

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		this.clawClosed = Robot.kClaw.isClawClosed();
		if (this.clawClosed = false) {
			System.out.println("Claw already open");
			this.end();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.kClaw.OpenClaw();
		this.clawClosed = false;
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if (this.clawClosed = true) {
			return false;
		} else {
			return true;
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
