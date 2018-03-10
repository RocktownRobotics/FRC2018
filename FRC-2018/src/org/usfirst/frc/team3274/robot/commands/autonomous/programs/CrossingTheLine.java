package org.usfirst.frc.team3274.robot.commands.autonomous.programs;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveBackward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command. That drives. Forward. Hence the name DriveForward.
 * 
 * @author Ian McGary
 * @author AJ Snarr
 */

public class CrossingTheLine extends CommandGroup {

	public CrossingTheLine() {
		addSequential(new DriveForward(11));
	}
}
