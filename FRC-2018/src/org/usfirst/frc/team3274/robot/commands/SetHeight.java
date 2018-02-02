
package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;

/**
 * An example command.  You can replace me with your own command. Just did...
 * 
 * @author UnimpressiveGoo
 */

//is called with a targetHeight (in inches), and a tolerable proximity in inches, the range of acceptable error

/**
 * @param targetHeight
 * 		the intended forklift height, in inches
 * 
 * @param tolerableProximity
 * 		The acceptable range of error on the forklift height, in inches
 */
public class SetHeight extends Command {
	public SetHeight(double targetHeight, double tolerableProximity) {
		requires(Robot.kForkLift);
		this.targetHeight = targetHeight;
		this.tolerableProximity = tolerableProximity;
	}
	
	private double targetHeight;
	private double tolerableProximity;


	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
		if(targetHeight < 0) {
			this.end();
			
		if(targetHeight > 42) {
			targetHeight = 42;
		}
		}
	
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		if(Robot.kForkLift.getLiftHeight() > targetHeight) {
			if(Robot.kForkLift.getLiftHeight() > targetHeight + tolerableProximity * 2) {
			Robot.kForkLift.setLiftPower(-0.3);
			}
			else {
				Robot.kForkLift.setLiftPower(-0.1);
			}
		}
		else {
			if(Robot.kForkLift.getLiftHeight() < targetHeight - tolerableProximity * 2) {
			Robot.kForkLift.setLiftPower(0.3);
			}
			else {
				Robot.kForkLift.setLiftPower(0.1);
			}
		}
		
	}

	//If forklift is within tolerableProximity of the target height, end.
	@Override
	protected boolean isFinished() {
		if(Robot.kForkLift.getLiftHeight() > targetHeight + tolerableProximity) {
		return false;
		}
		else {
			if(Robot.kForkLift.getLiftHeight() < targetHeight - tolerableProximity) {
				return false;
			}
			else {
				return true;
			}
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
