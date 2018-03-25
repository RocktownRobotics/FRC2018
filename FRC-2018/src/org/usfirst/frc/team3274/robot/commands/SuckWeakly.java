package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.subsystems.ClawIntake;

/**
 * this command sucks. Seriously! It's basically the opposite of Eject, sucking
 * cubes in instead of spewing them out....
 */

public class SuckWeakly extends Command {

	public static final double HIGH_SPEED = .5;
	public static final double NORMAL_SPEED = .25;

	public static final double HIGH_POWER_TIME = 1.5; // in seconds

	private double startTime;

	public SuckWeakly() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kClawIntake);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		this.startTime = Robot.getTime();
		System.out.println("The robot sucks");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		double power;

		if (Robot.getTime() - this.startTime >= HIGH_POWER_TIME) {
			power = NORMAL_SPEED;
		} else {
			power = HIGH_SPEED;
		}

		if (Robot.kClawIntake.isClawLoaded()) {
			Robot.kClawIntake.setCubeManipulatorMotors(-power);
		} else {
			this.startTime = Robot.getTime();
			Robot.kClawIntake.setCubeManipulatorMotors(0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {

		// return Robot.kClawIntake.isClawLoaded();
		return false;
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
