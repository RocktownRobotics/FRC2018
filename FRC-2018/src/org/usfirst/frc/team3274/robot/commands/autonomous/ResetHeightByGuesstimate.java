package org.usfirst.frc.team3274.robot.commands.autonomous;

import org.usfirst.frc.team3274.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Lowers lift based on time.
 *
 */
public class ResetHeightByGuesstimate extends Command {
	
	/**
	 * This is a percentage of the speed used to keep lift in place.
	 */
	public static final double PERCENT_SPEED_DOWN = .6;
	
	public static final double TIME_TO_LOWER = 4; // in seconds
	
	private double timeToReach;
	
	/**
	 * Slowly lowers the lift based on timing
	 */
	public ResetHeightByGuesstimate() {
		requires(Robot.kForkLift);
	}
	
	@Override
	protected void initialize() {
		this.timeToReach = Robot.getTime() + TIME_TO_LOWER;
	}
	
	@Override
	protected void execute() {
		Robot.kForkLift.setLiftPower(RunForkLift.BASE_LIFT_POWER * PERCENT_SPEED_DOWN);
	}
	
	@Override
	protected boolean isFinished() {
		if (Robot.getTime() >= this.timeToReach) {
			return true;
		}
		return false;
	}
	
	@Override
	protected void end() {
		Robot.kForkLift.setLiftPower(0);
	}
}
