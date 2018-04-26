package org.usfirst.frc.team3274.robot.commands.autonomous;

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

public class DriveForwardScale extends Command {

//	public static final double FAST_SPEED = 0.4;//.45
//	public static final double NORMAL_SPEED = 0.3;
//	public static final double SLOW_SPEED = 0.2;
	
	public static final double SCALE_FAST_POWER = 0.85;
	public static final double SCALE_NORMAL_POWER = 0.50;
	public static final double SCALE_SLOW_POWER = 0.30;

	public static final double SLOW_DISTANCE = 1; // offset in ft
	public static final double MID_DISTANCE = 3; // offset in ft

	public static final double SLOWER_STARTING_TIME = 0.2; // in seconds

	private double targetDistance;
	private double startTime;
	
	private boolean ignorePrecisionForSpeed;

	public DriveForwardScale(double targetDistance, boolean ignorePrecisionForSpeed) {
		this.ignorePrecisionForSpeed = ignorePrecisionForSpeed;
		
		requires(Robot.kDriveTrain);

		Robot.kDriveTrain.resetEncoders();
		this.targetDistance = targetDistance;
	}
	
	/**
	 * creates a new DriveForward command that drives the targetDistance.
	 * 
	 * @param targetDistance
	 *            Distance to be traveled, in feet... hopefully....
	 */
	public DriveForwardScale(double targetDistance) {
		this(targetDistance, false);
	}

	@Override
	protected void initialize() {
		Robot.kDriveTrain.resetYaw();
		Robot.kDriveTrain.resetEncoders();
//		DriverStation.reportWarning("initDriveForward", false);
		System.out.println("Robot is now driving forwards :)");

		this.startTime = Robot.getTime();
	}

	@Override
	protected void execute() {

		double determinedSpeed;
		if (Robot.kDriveTrain.isRobotTipping()) {
			determinedSpeed = SCALE_SLOW_POWER;
			System.out.println(
					"Robot is almost tipping over. Robot is scared. Robot will move slowly and carefully until it is on safer ground...");
		} else {
			if (this.ignorePrecisionForSpeed == false) {
				if (Robot.kDriveTrain.getDistanceDriven() < targetDistance - MID_DISTANCE) {
	
					if (this.startTime + SLOWER_STARTING_TIME <= Robot.getTime()) {
						determinedSpeed = SCALE_FAST_POWER;
					} else {
						determinedSpeed = SCALE_NORMAL_POWER;
					}
	
				} else if (Robot.kDriveTrain.getDistanceDriven() < targetDistance - SLOW_DISTANCE) {
					determinedSpeed = SCALE_SLOW_POWER;
				} else {
					determinedSpeed = SCALE_NORMAL_POWER;
				}
			} else {
				determinedSpeed = SCALE_FAST_POWER;
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
		//THIS IS FOR TROUBLESHOOTING YOU SHOULD DISABLE PROBABLY
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//******
		Robot.kDriveTrain.tankDrive(0, 0, false);
		Robot.kDriveTrain.resetYaw();
		System.out.println("No longer driving forwards");
		Robot.kDriveTrain.tankDrive(0,0, false);
	}

	@Override
	protected boolean isFinished() {
		return Robot.kDriveTrain.getDistanceDriven() >= targetDistance;
	}

}
