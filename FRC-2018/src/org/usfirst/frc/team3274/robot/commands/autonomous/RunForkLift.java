/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;

/**
 * An example command. You can replace me with your own command.
 */
public class RunForkLift extends Command {

	
	public static final double BASE_LIFT_POWER = .55;

	public RunForkLift() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kForkLift);
		

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		double joystickVal = Robot.m_oi.getXbox1().getRawAxis(RobotMap.XBOX_LEFT_Y_AXIS);
		
		double addedPower;
		
		if (joystickVal > 0) {
			// add more power going down
			addedPower = joystickVal - (-BASE_LIFT_POWER) - .35;
		} else {
			addedPower = joystickVal;
		}
		
		Robot.kForkLift.setLiftPowerWithJoystick((-BASE_LIFT_POWER) + addedPower);

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {

	return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.kForkLift.setLiftPower(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
