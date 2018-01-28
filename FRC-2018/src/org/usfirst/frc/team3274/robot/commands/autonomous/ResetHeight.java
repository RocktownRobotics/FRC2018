package org.usfirst.frc.team3274.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class ResetHeight extends Command {
	public ResetHeight() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kForkLift);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Robot.kForkLift.getLiftHeight() >= 0) {
		if(Robot.kForkLift.getLiftHeight() > 1) {
		Robot.kForkLift.setLiftPower(0.3);
		}
		else {
			Robot.kForkLift.setLiftPower(0.1);
		}
		}
		else {
			this.end();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if(Robot.kForkLift.getLiftHeight() >= 0) {
		return false;
		}
		else {
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
