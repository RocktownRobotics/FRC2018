package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.subsystems.ClawIntake;

/**
 * this command sucks. Seriously!
 * It's basically the opposite of Eject, sucking cubes in instead of spewing them out....
 */

public class Suck extends Command {
	double howMuchWeSuck;
	

	public Suck() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kClawIntake);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		this.howMuchWeSuck = 0.99;
		System.out.println("The robot sucks");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.kClawIntake.setCubeManipulatorMotors(-howMuchWeSuck);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {

		return Robot.kClawIntake.isClawLoaded();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		System.out.println("The robot was sucking, but no longer");
		Robot.kClawIntake.setCubeManipulatorMotors(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
			System.out.println("Something interfered with our suckitude");
	}
}
