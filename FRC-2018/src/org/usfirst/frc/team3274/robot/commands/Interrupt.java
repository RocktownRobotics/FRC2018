/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.util.StoppableSubsystem;

/**
 * An example command. You can replace me with your own command.
 */
public class Interrupt extends Command {

	/** In seconds **/
	public static final double WAIT_TIME = .2;

	private double timeToReach;
	
	StoppableSubsystem sub;

	public Interrupt(StoppableSubsystem sub) {
		requires(sub);
		this.sub = sub;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		this.sub.stop();
		
		this.timeToReach = Timer.getMatchTime() + WAIT_TIME;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if (Timer.getMatchTime() >= this.timeToReach) {
			return true;
		}
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
