package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAuto extends CommandGroup {

	/**
	 * WE DIDN'T BREAK ANYTHING
	 */
	public TestAuto() {

		addSequential(new DriveForward(22));
		addSequential(new TurnRobot(90));
		addSequential(new DriveForward(3));
		addSequential(new TurnRobot(-90));
	}
}
