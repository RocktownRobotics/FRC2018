package org.usfirst.frc.team3274.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;

/**
 * An example command. You can replace me with your own command. I did....
 */

public class EjectAutonomous extends Command {
	
	public static final double THRUST_TIME = .3;
	
	private double launchPower;
	private double ejectStartTime;

	public EjectAutonomous() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kClawIntake);
		System.out.println("Ejecting Cube");
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		this.launchPower = 0.0;
		this.ejectStartTime = Timer.getMatchTime();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.kClawIntake.setCubeManipulatorMotors(launchPower);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {

		if (ejectStartTime + THRUST_TIME <= Timer.getMatchTime()) {
			return false;
		} else {
			System.out.println(
					"Robot has ejected it's cube. Robot has nothing. Robot is lonely, and wants another cube to throw at something.");
			return true;
		}
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
