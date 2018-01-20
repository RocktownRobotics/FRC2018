package org.usfirst.frc.team3274.robot.commands.autonomous;

import org.usfirst.frc.team3274.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveForward extends Command {

	private double targetDistance;

	public DriveForward(double targetDistance) {
		requires(Robot.kDriveTrain);

	}

	@Override
	protected void initialize() {
		// enable PID loop and run the motors to drive forward.
		Robot.kDriveTrain.enablePID();
	}

	@Override
	protected void execute() {
		// PIDLoop runs everything in the background on a separate thread, thus this is
		// superfluous and empty.
	}

	@Override
	protected void end() {
		// turn everything off.
		Robot.kDriveTrain.disablePID();
	}

	@Override
	protected boolean isFinished() {
		// Ends the command after target distance is achieved.
		if (targetDistance <= Robot.kDriveTrain.getDistanceDriven())
			return false;
		else
			return true;

		/*
		 * Alternatively, simply return "targetDistance >=
		 * Robot.kDriveTrain.getDistanceDriven()", since targetDistance >=
		 * Robot.kDriveTrain.getDistanceDriven() is a boolean. Either works, the one
		 * used is easier to read.
		 */
	}

}
