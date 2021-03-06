package org.usfirst.frc.team3274.robot.commands.autonomous.programs;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.commands.ArmLock;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftScaleToCubeToSwitch;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftStartToScale;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.RightScaleToCubeToSwitch;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.RightStartToScale;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.RightToLeft;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.RightToRight;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoCubeLeftScaleFirst extends CommandGroup {

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

	public TwoCubeLeftScaleFirst() {
		// make sure robot is in low gear
//		addSequential(new ShiftDownForTime());
		addParallel(new ArmLock());
		
		if (this.scaleIsRight() && this.switchIsRight()) {
			addSequential(new RightToRight());
			addSequential(new RightStartToScale());
			addSequential(new RightScaleToCubeToSwitch());
		} else if (this.scaleIsRight() && this.switchIsRight() == false) {
			addSequential(new RightToLeft());
			addSequential(new LeftStartToScale());
			addSequential(new LeftScaleToCubeToSwitch());
		} else {
			addSequential(new ScaleFromRight());
		}
		addSequential(new Interrupt(Robot.kClawArm));
	}
}