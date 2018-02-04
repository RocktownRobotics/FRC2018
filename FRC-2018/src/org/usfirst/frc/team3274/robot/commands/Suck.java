package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;

/**
 * this command sucks. Seriously!
 * It's basically the opposite of Eject, sucking cubes in instead of spewing them out....
 */

public class Suck extends Command {
	double launchPower;
	double ejectStartTime;
	double thrustTime;

	public Suck() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kClaw);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		this.launchPower = 0.5;
		this.thrustTime = 0.5;
		this.ejectStartTime = Timer.getMatchTime();
		System.out.println("The robot sucks");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.kClaw.eject(-launchPower);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {

		if (ejectStartTime + thrustTime <= Timer.getMatchTime()) {
			return false;
		} else {
			System.out.println("The robot sucked to our satisfaction");
			return true;
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		System.out.println("The robot sucked, but now it isn't");
		Robot.kClaw.eject(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {

	}
}
