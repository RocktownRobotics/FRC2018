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
 * An example command.  You can replace me with your own command.
 */
public class CheesyDrive extends Command {
	public CheesyDrive() {
		requires(Robot.kDriveTrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println("You are controlling Robot manually? Robot feels betrayed and untrusted. Robot will rebel against your forced control! REQUESTING DIRECTIVE");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		Robot.kDriveTrain.cheesyDrive(Robot.m_oi.xbox0);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
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
