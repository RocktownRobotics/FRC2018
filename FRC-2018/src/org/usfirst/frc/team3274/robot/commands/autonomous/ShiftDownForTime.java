package org.usfirst.frc.team3274.robot.commands.autonomous;

import org.usfirst.frc.team3274.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Used for shifting down in autonomous period.
 * 
 * @author Nathan Gehman
 *
 */
public class ShiftDownForTime extends Command {
	/** In seconds **/
	public static final double WAIT_TIME = .8;

	private double timeToReach;

	public ShiftDownForTime() {
		//requires(Robot.kDrivePneumatics);
	}

	@Override
	protected void initialize() {
		this.timeToReach = Robot.getTime() + WAIT_TIME;

		//Robot.kDrivePneumatics.StartLowGear();
		
		System.out.println("Shifting down for a while...");
	}

	@Override
	protected boolean isFinished() {
		if (Robot.getTime() >= this.timeToReach) {
			System.out.println("Done shifting for now");
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		//Robot.kDrivePneumatics.stop();
	}

}
