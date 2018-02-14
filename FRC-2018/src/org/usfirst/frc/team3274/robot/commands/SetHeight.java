
package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command. Just did...
 * 
 * @author UnimpressiveGoo
 */

//is called with a targetHeight (in inches), and a tolerable proximity in inches, the range of acceptable error

/**
 * @param targetHeight
 *            the intended forklift height, in inches
 * 
 * @param tolerableProximity
 *            The acceptable range of error on the forklift height, in inches
 */
public class SetHeight extends Command {

	public static final double MAX_HEIGHT = 42;

	public static final double NORMAL_SPEED = .3;
	public static final double SLOW_SPEED = .1;

	public SetHeight(double targetHeight, double tolerableProximity) {
		//requires(Robot.kForkLift);
		this.targetHeight = targetHeight;
		this.tolerableProximity = tolerableProximity;
	}

	private double targetHeight;
	private double tolerableProximity;

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println("Setting Forklift height");
		if (targetHeight > MAX_HEIGHT) {
			targetHeight = MAX_HEIGHT;
			System.out.println(
					"Robot had to change the target height because it was too big. It's all the driver's fault...");

		}

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
/*
		// needs to move down
		if (Robot.kForkLift.getLiftHeight() > targetHeight) {
			if (Robot.kForkLift.getLiftHeight() > targetHeight + tolerableProximity * 2) {
				Robot.kForkLift.setLiftPower(-NORMAL_SPEED);
			} else {
				Robot.kForkLift.setLiftPower(-SLOW_SPEED);
			}
		}
		// needs to move up
		if (Robot.kForkLift.getLiftHeight() < targetHeight) {
			if (Robot.kForkLift.getLiftHeight() < targetHeight - tolerableProximity * 2) {
				Robot.kForkLift.setLiftPower(NORMAL_SPEED);
			} else {
				Robot.kForkLift.setLiftPower(SLOW_SPEED);
			}
		}
*/
	}

	// If forklift is within tolerableProximity of the target height, end.
	@Override
	protected boolean isFinished() {

		/*if (targetHeight < 0) {
			System.out.println("Failed: negative target height value");
			return true;
		}

		if (Robot.kForkLift.getLiftHeight() > targetHeight + tolerableProximity) {
			return false;
		} else {
			if (Robot.kForkLift.getLiftHeight() < targetHeight - tolerableProximity) {
				return false;
			} else {
				System.out.println(
						"Forklift is at desired height. Well, close enough. Anyway, SetHeightcommand ending succesfully...");

				return true;
			}
		}*/

		return false
				;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		//Robot.kForkLift.setLiftPower(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		System.out.println("The robot's attempts to move the forklift were interrupted.");
	}
}
