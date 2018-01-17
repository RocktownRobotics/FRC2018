package org.usfirst.frc.team3274.robot.commands;

import org.usfirst.frc.team3274.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Compress extends Command {
	private boolean started;

	public Compress() {
		requires(Robot.kCompressor);
	}

	@Override
	protected void initialize() {
		started = false;
	}

	private void startCompressor() {
		Robot.kCompressor.start();
		started = true;
	}

	private void stopCompressor() {
		Robot.kCompressor.stop();
		started = false;
	}

	@Override
	protected void execute() {
		if (started == false) {
			startCompressor();
		}
		/*
		 * The code below is one way to prevent the compressor from running at the same
		 * time as other systems.
		 */
		// else if (Robot.winch.isWinchRunning() == true && started == true)
		// {
		// stopCompressor();
		// }
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		Robot.kCompressor.stop();
	}
}