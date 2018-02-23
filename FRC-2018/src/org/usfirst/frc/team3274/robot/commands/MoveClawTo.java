package org.usfirst.frc.team3274.robot.commands;

import org.usfirst.frc.team3274.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Moves the claw to the given location and locks it.
 * 
 * @author AJ Snarr
 */
public class MoveClawTo extends CommandGroup {

	/**
	 * Create new command that moves claw to given position. Given position is in
	 * degrees, based on position given by ClawArm.getAngle().
	 * 
	 * Immediately locks arm after moving.
	 * 
	 * @param angle
	 *            in degrees. Based on position given by ClawArm.getAngle().
	 */
	public MoveClawTo(double angle) {
		addSequential(new MoveClawTo(angle));
		addParallel(new ArmLock());
	}

	/**
	 * 
	 * 
	 * @author AJ Snarr
	 */
	static class _MoveClawTo extends Command {
		private static final double UP_POWER = RaiseClaw.POWER;
		private static final double DOWN_POWER = LowerClaw.POWER;

		private double goalAngle;
		private boolean movingUp;

		/**
		 * Create new command that moves claw to given position. Given position is in
		 * degrees, based on position given by ClawArm.getAngle().
		 * 
		 * @param angle
		 *            in degrees. Based on position given by ClawArm.getAngle().
		 */
		public _MoveClawTo(double angle) {
			this.goalAngle = angle;
			this.movingUp = angle > Robot.kClawArm.getAngle();
		}

		@Override
		protected void initialize() {

		}

		@Override
		protected void execute() {
			if (movingUp) {
				Robot.kClawArm.setPower(UP_POWER);
			} else {
				Robot.kClawArm.setPower(-DOWN_POWER);
			}
		}

		@Override
		protected boolean isFinished() {
			if (true == movingUp && Robot.kClawArm.getAngle() >= goalAngle) {
				return true;
			} else if (false == movingUp && Robot.kClawArm.getAngle() <= goalAngle) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		protected void end() {
			Robot.kClawArm.setPower(0);
		}
	}
}
