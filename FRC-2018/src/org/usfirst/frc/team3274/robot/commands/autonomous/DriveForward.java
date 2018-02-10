package org.usfirst.frc.team3274.robot.commands.autonomous;

import org.usfirst.frc.team3274.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @Author Ian McGary A command. That drives. Forward. Hence the name
 *         DriveForward.
 */

public class DriveForward extends Command {

	public static final double FAST_SPEED = 0.5;
	public static final double NORMAL_SPEED = 0.2;
	public static final double SLOW_SPEED = 0.1;

	public static final double SLOW_DISTANCE = 1; // offset in ft
	private static final double MID_DISTANCE = 2;

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
	}

	@Override
	protected void initialize() {
		Robot.kDriveTrain.resetYaw();
		Robot.kDriveTrain.resetEncoders();
		DriverStation.reportWarning("initDriveForward", false);
		System.out.println("Robot is now driving forwards");
	}

	@Override
	protected void execute() {

		System.out.println("exe_driveforward");
		
		double determinedSpeed;

		if (Robot.kDriveTrain.getDistanceDriven() < targetDistance - MID_DISTANCE) {
			determinedSpeed = FAST_SPEED;

		} else if(Robot.kDriveTrain.getDistanceDriven() < targetDistance - SLOW_DISTANCE){
			determinedSpeed = SLOW_SPEED;
		}
		else {
			determinedSpeed = NORMAL_SPEED;
		}

		// no offset needed in error because it is determined at beginning.
		double gyroError = Robot.kDriveTrain.getYaw();
		double gyroTurn = gyroError * Robot.kDriveTrain.Gyro_KP;

		double leftPower = determinedSpeed - gyroTurn;
		double rightPower = determinedSpeed + gyroTurn;

		Robot.kDriveTrain.tankDrive(-leftPower, rightPower, false);
	}

	@Override
	protected void end() {
		Robot.kDriveTrain.tankDrive(0, 0, false);
		Robot.kDriveTrain.resetYaw();
		DriverStation.reportWarning("endDriveForward", false);
		System.out.println("No longer driving forwards");
	}

	@Override
	protected boolean isFinished() {
		return Robot.kDriveTrain.getDistanceDriven() >= targetDistance;
	}

}
