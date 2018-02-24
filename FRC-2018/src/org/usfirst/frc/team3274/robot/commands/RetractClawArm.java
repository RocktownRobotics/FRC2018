package org.usfirst.frc.team3274.robot.commands;

import org.usfirst.frc.team3274.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves claw arm to retracted position and then resets encoder value based on
 * that position.
 * 
 * @author AJ Snarr
 */
public class RetractClawArm extends Command {

	private static final double POWER = RaiseClaw.POWER;

	public RetractClawArm() {
		requires(Robot.kClawArm);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		Robot.kClawArm.setPower(POWER);
	}

	@Override
	protected boolean isFinished() {
		// wait until limit switch is hit
		if (Robot.kClawArm.isClawRetracted()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		Robot.kClawArm.setPower(0);
		Robot.kClawArm.resetEncoder();
	}
}
