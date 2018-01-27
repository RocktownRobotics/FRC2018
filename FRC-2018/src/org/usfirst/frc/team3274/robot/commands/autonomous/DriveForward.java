package org.usfirst.frc.team3274.robot.commands.autonomous;

import org.usfirst.frc.team3274.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @Author Ian McGary A command. That drives. Forward. Hence the name
 *         DriveForward.
 */

public class DriveForward extends Command {

	public static final double NORMAL_SPEED = 0.2;
	public static final double SLOW_SPEED = 0.13;

	public static final double SLOW_DISTANCE = 1; // offset in ft

	private double targetDistance;

	/**
	 * creates a new DriveForward command that drives the targetDistance.
	 * 
	 * @param targetDistance
	 *            Distance to be traveled, in feet... hopefully....
	 */
	public DriveForward(double targetDistance) {
		requires(Robot.kDriveTrain);
		this.targetDistance = targetDistance;
		Robot.kDriveTrain.resetEncoders();
	}

	@Override
	protected void initialize() {
		Robot.kDriveTrain.resetYaw();
	}

	@Override
	protected void execute() {

		double determinedSpeed;

		if (Robot.kDriveTrain.getDistanceDriven() < targetDistance - SLOW_DISTANCE) {
			determinedSpeed = NORMAL_SPEED;

		} else {
			determinedSpeed = SLOW_SPEED;
		}

		// no offset needed in error because it is determined at beginning.
		double gyroError = Robot.kDriveTrain.getYaw();
		double gyroTurn = gyroError * Robot.kDriveTrain.Gyro_KP;

		double leftPower = determinedSpeed - gyroTurn;
		double rightPower = determinedSpeed + gyroTurn;

		SmartDashboard.putNumber("leftRaw", leftPower);
		SmartDashboard.putNumber("rightRaw", rightPower);

		Robot.kDriveTrain.tankDrive(-leftPower, rightPower, false);
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
