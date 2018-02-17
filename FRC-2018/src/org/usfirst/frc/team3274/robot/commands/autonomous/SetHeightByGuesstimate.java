package org.usfirst.frc.team3274.robot.commands.autonomous;

import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetHeightByGuesstimate extends CommandGroup {

	private double height;
	
	public SetHeightByGuesstimate(double targetHeight) {
		
		this.height = targetHeight;
		
		addSequential(new DropForkLift());
		addSequential(new SetHeightFromBottom(this.height));
		
	}
}
