package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3274.robot.OI;
import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.subsystems.DriveTrain;;

/**
 * This command allows xbox joystick to drive the robot. It is always running
 * except when interrupted by another command.
 */
public class DriveWithJoystick extends Command {
	public DriveWithJoystick() {
		requires(Robot.kDriveTrain);
	}

	/**
	 * Drive using joystick input.
	 * 
	 * @see edu.wpi.first.wpilibj.command.Command#execute()
	 */
	@Override
	protected void execute() {
		Robot.kDriveTrain.tankDrive(Robot.m_oi.getXbox01());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.kDriveTrain.stop();
	}
}