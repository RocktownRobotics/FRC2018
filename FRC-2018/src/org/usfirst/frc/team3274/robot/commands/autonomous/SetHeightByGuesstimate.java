package org.usfirst.frc.team3274.robot.commands.autonomous;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetHeightByGuesstimate extends CommandGroup {

	private double height;
	
	/**
	 * 
	 * @param targetHeight inches
	 */
	public SetHeightByGuesstimate(double targetHeight) {
		
		this.height = targetHeight;
		
		//addSequential(new DropForkLift());

		addSequential(new SetHeightFromBottom(this.height));
		addSequential(new Interrupt(Robot.kForkLift));
		addSequential(new RunForkLift());
	}
}
