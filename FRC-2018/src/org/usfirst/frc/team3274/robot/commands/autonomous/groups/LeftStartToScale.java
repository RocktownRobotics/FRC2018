package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.commands.MoveClawTo;
import org.usfirst.frc.team3274.robot.commands.SetHeightWithEncoder;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.EjectAutonomous;
import org.usfirst.frc.team3274.robot.commands.autonomous.SetHeightByGuesstimate;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftStartToScale extends CommandGroup {

	
	public LeftStartToScale() {
		
		System.out.println("Robot moving to Scale");
		addSequential(new DriveForward(13));
		addParallel(new MoveClawTo(90));
		addParallel(new SetHeightByGuesstimate(40));
		addSequential(new TurnRobot(45));
		addSequential(new EjectAutonomous());
		System.out.println("Robot has captured the Scale! Robot is happy. With luck, Robot knows what it is talking about");
	}
}
