package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.commands.DeployClaw;
import org.usfirst.frc.team3274.robot.commands.SetHeight;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.EjectAutonomous;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DoingSomethingUseless extends CommandGroup {

	/**
	 * why? because reasons....
	 */
	public DoingSomethingUseless() {
		// make sure robot is in low gear
		addSequential(new ShiftDownForTime());
		addParallel(new DeployClaw());
		addSequential(new DriveForward(10));
		addParallel(new SetHeight(25, 5));
		addSequential(new EjectAutonomous());
	}
}
