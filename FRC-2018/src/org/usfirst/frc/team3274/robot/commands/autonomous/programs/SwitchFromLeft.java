package org.usfirst.frc.team3274.robot.commands.autonomous.programs;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.commands.ArmLock;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftStartToSwitch;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftToLeft;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftToLeftSwitch;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftToRight;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.RightStartToSwitch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchFromLeft extends CommandGroup {

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees.
	 */

	public boolean switchIsRight() {
		// check if string has 3 characters
		if (Robot.gameData.length() >= 3) {
			// check if first character in string is 'R'
			if (Robot.gameData.toUpperCase().charAt(0) == 'R') {
				return true;
			} else {
				return false;
			}
		} else {
			DriverStation.reportError("Failed to read a correct FMS message: " + Robot.gameData, true);
			System.out.println(
					"Failed: Field said something unintelligable... Robot hasn't a clue what it should do... Left is always the answer");
			return false;
		}
	}


	

	public SwitchFromLeft() {
		// make sure robot is in low gear
		addParallel(new ArmLock());
//		addSequential(new ShiftDownForTime());
		if (this.switchIsRight()) {
			addSequential(new LeftToLeft());//Will need to remove when add the far scale code in
//			// left to right switch
//			addSequential(new LeftToRight());
//			addSequential(new RightStartToSwitch());
		} else {
			// going from left to left switch
			addSequential(new LeftToLeftSwitch());
			addSequential(new LeftStartToSwitch());
		}
		addSequential(new Interrupt(Robot.kClawArm));
	}
}