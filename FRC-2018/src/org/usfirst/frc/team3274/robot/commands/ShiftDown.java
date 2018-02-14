package org.usfirst.frc.team3274.robot.commands;

import org.usfirst.frc.team3274.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftDown extends Command {
	public ShiftDown() {
		//requires(Robot.kDrivePneumatics);
	}

	@Override
	protected void initialize() {
		//Robot.kDrivePneumatics.StartLowGear();
		System.out.println("Shifting down");
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		System.out.println("No longer shifting down");
		//Robot.kDrivePneumatics.stop();
	}

}
