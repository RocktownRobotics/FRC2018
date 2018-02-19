package org.usfirst.frc.team3274.robot.util;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class StoppableSubsystem extends Subsystem {

	/**
	 * Stops the motors/etc of the current subsystem.
	 */
	public abstract void stop();

}
