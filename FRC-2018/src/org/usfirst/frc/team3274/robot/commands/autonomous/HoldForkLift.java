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

/**
 * An example command. You can replace me with your own command.
 */
public class HoldForkLift extends Command {

	
	private double liftPower;

	public HoldForkLift() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kForkLift);
		

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		
		this.liftPower = -0.1;

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		Robot.kForkLift.setLiftPower(this.liftPower);

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
