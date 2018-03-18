package org.usfirst.frc.team3274.robot.commands.autonomous.programs;

import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A command. That drives. Forward. Hence the name DriveForward.
 * 
 * @author Ian McGary
 * @author AJ Snarr
 */

public class CrossingTheLine extends CommandGroup {

	public CrossingTheLine() {
		addSequential(new DriveForward(RobotMap.Autonomous.CROSS_THE_LINE));
	}
}
