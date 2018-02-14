/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.subsystems.ForkLift;

/**
 * An example command. You can replace me with your own command.
 */
public class TestCommand extends Command {

	public enum Action {
		FORK_UP, FORK_DOWN
	};

	private Action action;

	public TestCommand(Action action) {
		switch (action) {
		case FORK_UP:
			//requires(Robot.kForkLift);
			break;
		case FORK_DOWN:
			//requires(Robot.kForkLift);
			break;
		default:
			//requires(Robot.kForkLift);
		}

		this.action = action;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		switch (action) {
		case FORK_UP:
			// move fork motor up
			//Robot.kForkLift.setLiftPower(.1);
			break;
		case FORK_DOWN:
			// move fork motor down
			//Robot.kForkLift.setLiftPower(-.1);
			break;
		default:
			// do nothing
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
