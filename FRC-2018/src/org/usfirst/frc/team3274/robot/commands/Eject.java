package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;

/**
 * An example command. You can replace me with your own command. I did....
 */

public class Eject extends Command {

	public static enum Speed {
		LOW, MEDIUM, HIGH
	};

	public static final double HIGH_SPEED = .8;
	public static final double MEDIUM_SPEED = .6;
	public static final double SLOW_SPEED = .4;

	double launchPower;
	double ejectStartTime;
	double thrustTime;

	public Eject() {
		this(Speed.HIGH);
	}

	public Eject(Speed speed) {
		switch (speed) {
		case HIGH:
			this.launchPower = HIGH_SPEED;
			break;
		case MEDIUM:
			this.launchPower = MEDIUM_SPEED;
			break;
		case LOW:
			this.launchPower = SLOW_SPEED;
			break;
		default:
			this.launchPower = HIGH_SPEED;
		}

		requires(Robot.kClawIntake);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		this.thrustTime = 0.5;
		this.ejectStartTime = Robot.getTime();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.kClawIntake.setCubeManipulatorMotors(launchPower);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {

		// if(ejectStartTime + thrustTime <= Timer.getMatchTime()){
		// return false;
		// }
		// else{
		// System.out.println("Robot has ejected it's cube. Robot has nothing. Robot is
		// lonely, and wants another cube to throw at something.");
		// return true;

		// }
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.kClawIntake.setCubeManipulatorMotors(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		System.out.println("Failed: Interruption");
	}
}
