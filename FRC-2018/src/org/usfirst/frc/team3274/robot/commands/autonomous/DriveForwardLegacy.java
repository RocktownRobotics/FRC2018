package org.usfirst.frc.team3274.robot.commands.autonomous;

import org.usfirst.frc.team3274.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

@Deprecated
public class DriveForwardLegacy extends Command {

	private double targetDistance;

	public DriveForwardLegacy(double targetDistance) {
		requires(Robot.kDriveTrain);

		this.targetDistance = targetDistance;

		Robot.kDriveTrain.resetEncoders();
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.kDriveTrain.tankDrive(.2, .2, false);
	}

	@Override
	protected void end() {
		Robot.kDriveTrain.tankDrive(0, 0, false);
	}

	@Override
	protected boolean isFinished() {
		return Robot.kDriveTrain.getDistanceDriven() >= targetDistance;
	}

}
