package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;



/**
 * An example command.  You can replace me with your own command. I did....
 */

public class Eject extends Command {
	 double launchPower;
	 double ejectStartTime;
	 double thrustTime;

	public Eject() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kClaw);
	}


	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		this.launchPower = 0.5;
		this.ejectStartTime = Timer.getMatchTime();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.kClaw.eject(launchPower);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {

	if(ejectStartTime + thrustTime <= Timer.getMatchTime()){
		return false;
	}
		else{
		return true;
	}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.kClaw.eject(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		
	}
}
