package org.usfirst.frc.team3274.robot.commands.autonomous.programs;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftScaleToCube;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftStartToScale;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.MidToLeft;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.MidToRight;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.RightScaleToCube;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.RightStartToScale;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleFromMid extends CommandGroup {

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

	public ScaleFromMid() {
		// make sure robot is in low gear
		addSequential(new ShiftDownForTime());

		if (this.scaleIsRight()) {
			// mid to right scale
			addSequential(new MidToRight());
			addSequential(new RightStartToScale());
			addSequential(new RightScaleToCube());
		} else {
			// mid to left scale
			addSequential(new MidToLeft());
			addSequential(new LeftStartToScale());
			addSequential(new LeftScaleToCube());
		}
	}
}
