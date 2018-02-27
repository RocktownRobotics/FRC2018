package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command. That drives. Forward. Hence the name DriveForward.
 * 
 * @author Ian McGary
 * @author AJ Snarr
 */

public class CrossingTheLine extends Command {

	public static final double FAST_SPEED = 0.5;
	public static final double NORMAL_SPEED = 0.2;
	public static final double SLOW_SPEED = 0.1;

	public static final double SLOW_DISTANCE = 1; // offset in ft
	public static final double MID_DISTANCE = 2; // offset in ft

	public static final double SLOWER_STARTING_TIME = 0.5; // in seconds

	private double targetDistance;
	private double startTime;

	/**
	 * creates a new DriveForward command that drives the targetDistance.
	 * 
	 * @param targetDistance
	 *            Distance to be traveled, in feet... hopefully....
	 */
	public CrossingTheLine() {
		requires(Robot.kDriveTrain);
	}

	@Override
	protected void initialize() {
		Robot.kDriveTrain.resetYaw();
		Robot.kDriveTrain.resetEncoders();
		DriverStation.reportWarning("initDriveForward", false);
		System.out.println("There is a Line. 'The Colossus' is going to cross it.");
		this.targetDistance = 8;
		this.startTime = Robot.getTime();
	}

	@Override
	protected void execute() {

		double determinedSpeed;
		if (Robot.kDriveTrain.isRobotTipping()) {
			determinedSpeed = SLOW_SPEED;
			System.out.println(
					"Robot is almost tipping over. Robot is scared. Robot will move slowly and carefully until it is on safer ground...");
		} else {
			if (Robot.kDriveTrain.getDistanceDriven() < targetDistance - MID_DISTANCE) {

				if (this.startTime + SLOWER_STARTING_TIME <= Robot.getTime()) {
					determinedSpeed = FAST_SPEED;
				} else {
					determinedSpeed = NORMAL_SPEED;
				}

			} else if (Robot.kDriveTrain.getDistanceDriven() < targetDistance - SLOW_DISTANCE) {
				determinedSpeed = SLOW_SPEED;
			} else {
				determinedSpeed = NORMAL_SPEED;
			}
		}

		// no offset needed in error because it is determined at beginning.
		double gyroError = Robot.kDriveTrain.getYaw();
		double gyroTurn = gyroError * Robot.kDriveTrain.Gyro_KP;

		double leftPower = determinedSpeed - gyroTurn;
		double rightPower = determinedSpeed + gyroTurn;

		Robot.kDriveTrain.tankDrive(leftPower, rightPower, false);
	}

	@Override
	protected void end() {
		Robot.kDriveTrain.tankDrive(0, 0, false);
		Robot.kDriveTrain.resetYaw();
		System.out.println("'The Colossus' has crossed a line, from which it can never return... during this Autonomous period");
	}

	@Override
	protected boolean isFinished() {
		return Robot.kDriveTrain.getDistanceDriven() >= targetDistance;
	}

}
