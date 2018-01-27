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
	public static final double SLOW_SPEED = 0.1;

	public static final double SLOW_DISTANCE = 1; // offset in ft

	public static double offsetAngle = 0;

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
		this.offsetAngle = Robot.kDriveTrain.getYaw();
	}

	@Override
	protected void execute() {

		double gyroError = Robot.kDriveTrain.getYaw() - offsetAngle;
		double gyroTurn = gyroError * Robot.kDriveTrain.Gyro_KP;

		gyroTurn = equalize(gyroTurn);

		double leftPowerRaw = NORMAL_SPEED - gyroTurn;
		double rightPowerRaw = NORMAL_SPEED + gyroTurn;

		double leftPower;
		double rightPower;

		leftPower = -leftPowerRaw;
		rightPower = rightPowerRaw;

		SmartDashboard.putNumber("leftRaw", leftPowerRaw);
		SmartDashboard.putNumber("rightRaw", rightPowerRaw);

		Robot.kDriveTrain.tankDrive(leftPower, rightPower, false);
	}

	/**
	 * Takes a number in degrees and converts it to power.
	 * 
	 * @param rawPower
	 * @return
	 */
	private double equalize(double rawPower) {
		return ((rawPower % 360) + 360) % 360;
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
