package org.usfirst.frc.team3274.robot.commands.autonomous.programs;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.commands.ArmLock;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftStartToScale;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftStartToSwitch;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftToLeft;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftToLeftScale;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftToLeftSwitch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CloseFromLeft_PrioritizingScale extends CommandGroup {

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

	public boolean scaleIsRight() {
		// check if string has 3 characters
		if (Robot.gameData.length() >= 3) {
			// check if second character in string is 'R'
			if (Robot.gameData.toUpperCase().charAt(1) == 'R') {
				return true;
			} else {
				return false;
			}
		} else {
			DriverStation.reportError("Failed to read a correct FMS message", true);
			System.out.println(
					"Failed: Field said something unintelligable... Robot hasn't a clue what it should do... Left is always the answer");

			return false;
		}
	}

	

	public CloseFromLeft_PrioritizingScale() {
		// make sure robot is in low gear
		addParallel(new ArmLock());
//		addSequential(new ShiftDownForTime());
		if (!this.scaleIsRight()) {
			// left to right switch
			addSequential(new LeftToLeftScale());
			addSequential(new LeftStartToScale());
		} else if (!this.switchIsRight()) {
			addSequential(new LeftToLeftSwitch());
			addSequential(new LeftStartToSwitch());
		} else {
			addSequential(new LeftToLeft());
		}

		addSequential(new Interrupt(Robot.kClawArm));
	}
}
