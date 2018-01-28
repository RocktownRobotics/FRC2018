package org.usfirst.frc.team3274.robot.commands.autonomous;

import org.usfirst.frc.team3274.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command that turns the robot.
 * 
 * @author Ian McGary
 */
public class TurnRobot extends Command {

	public static final double TURN_POWER = 0.3;

	public static final double SLOW_TURN_POWER = 0.15;
	public static final double SLOW_ANGLE = 35;

	private double turnAngle;

	/**
	 * creates a new AutoTurn command that turns the desired angle.
	 * 
	 * @param targetAngle
	 *            The angle to be turned, in degrees. Positive numbers will turn
	 *            right, negatives left. MUST BE <= 180 DEGREES.
	 */
	public TurnRobot(double targetAngle) {
		requires(Robot.kDriveTrain);
		this.turnAngle = targetAngle;
		Robot.kDriveTrain.resetYaw();
	}

	@Override
	protected void initialize() {
		DriverStation.reportWarning("init", false);
		Robot.kDriveTrain.resetYaw();
	}

	/**
	 * Turns, left or right based on the turnAngle variable, and ends the command if
	 * the desired angle is reached(or exceeded)
	 */
	@Override
	protected void execute() {

		double unscaledPower;

		System.out.println("exe");
		if (Math.abs(Robot.kDriveTrain.getYaw()) >= (Math.abs(turnAngle) - SLOW_ANGLE)) {
			unscaledPower = SLOW_TURN_POWER;
		} else {
			unscaledPower = TURN_POWER;
		}

		double leftPower;
		double rightPower;

		if (turnAngle > 0) {
			leftPower = unscaledPower;
			rightPower = -unscaledPower;
		} else {
			leftPower = -unscaledPower;
			rightPower = unscaledPower;
		}

		SmartDashboard.putNumber("leftRaw2", leftPower);
		SmartDashboard.putNumber("rightRaw2", rightPower);
		SmartDashboard.putNumber("turnGoal", turnAngle);

		Robot.kDriveTrain.tankDrive(-leftPower, rightPower, false);
	}

	@Override
	protected void end() {
		Robot.kDriveTrain.tankDrive(0, 0, false);
		DriverStation.reportWarning("end", false);
	}

	@Override
	protected boolean isFinished() {
		if (turnAngle < 0) {
			return Robot.kDriveTrain.getYaw() <= turnAngle;
		} else {
			return Robot.kDriveTrain.getYaw() >= turnAngle;
		}
	}

}
