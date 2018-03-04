/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;

/**
 * An example command. You can replace me with your own command.
 */
public class DeployClawArm extends Command {
	public DeployClawArm() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kClawArm);
	}

	public static double power = -0.5;

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println("Deploying Claw Arm");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		Robot.kClawArm.setPower(this.power);

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if(Robot.kClawArm.isClawRetracted() == true) {
		return false;
		}
		else {
			System.out.println("Claw Arm Deployed");
			return true;
					}
		
		}

	

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.kClawArm.resetEncoder();

		Robot.kClawArm.setPower(0.2);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
