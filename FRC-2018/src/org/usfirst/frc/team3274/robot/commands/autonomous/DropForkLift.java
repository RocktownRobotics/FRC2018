/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;

/**
 * An example command.  You can replace me with your own command. Already done
 */
public class DropForkLift extends Command {
	
	private double LiftPower;
	
	public DropForkLift() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kForkLift);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println("Robot wonders where the Claw is. Robot will drop the forklift until it finds the claw. Robot wishes for an encoder...");
		this.LiftPower = -0.5;
	
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		Robot.kForkLift.setLiftPower(this.LiftPower);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
if(Robot.kForkLift.isLiftNotAtMinHeight()) {
	return false;
}
else {
	System.out.println("Robot has dropped the lift and found the claw. Robot is happy");
	return true;
}
	
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
