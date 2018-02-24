package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.commands.MoveClawTo;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveBackward;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.EjectAutonomous;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MidToExchange extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees. Umm... No
	 */
	public MidToExchange() {
		
		System.out.println("Robot moving to Exchange");
		addSequential(new DriveForward(1));
		addParallel(new MoveClawTo(90));
		addSequential(new TurnRobot(-90));
		addSequential(new DriveForward(4));
		addSequential(new TurnRobot(-90));
		addSequential(new DriveForward(0.5));	
		System.out.println("Robot exchanging P-Cube");
		System.out.println("Robot moving to Auto Line");
		addSequential(new EjectAutonomous());
		addSequential(new DriveBackward(7));
		System.out.println("Robot has arrived");
		
	}
}
