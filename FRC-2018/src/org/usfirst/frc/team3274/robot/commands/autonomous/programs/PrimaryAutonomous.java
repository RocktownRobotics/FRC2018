package org.usfirst.frc.team3274.robot.commands.autonomous.programs;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.commands.Delay;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftStartToScale;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftStartToSwitch;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftSwitchToCubeToScale;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftToLeft;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.LeftToRight;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.MidToExchange;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.MidToLeft;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.MidToRight;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.RightStartToScale;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.RightStartToSwitch;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.RightSwitchToCubeToScale;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.RightToLeft;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.RightToRight;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PrimaryAutonomous extends CommandGroup {

	private StartPosition startPosition;
	private ScoringMethod scoringStrategy;
	private double initialDelay;
	private boolean isTwoCubeAuto;
	
	//Enums for choosing options in SmartDashboard
	public static enum StartPosition{MIDDLE, LEFT, RIGHT};
	public static enum ScoringMethod{SWITCH, SCALE, NONE, EXCHANGE};

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

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees. No, no it is not....
	 */

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * 
	 * All of the if(BLAH.equals("BLEH") needs to become if(BLAH .equals "BLEH")
	 * 
	 * This is a very complicated bit of code, with many, many stacked if
	 * statements, not written for easy readability. You have been warned...
	 * 
	 * Additionally, if you are using my EditBox settings, your eyes will be
	 * bleeding by the end of it all...
	 */
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Runs autonomous with given starting parameters and goals. Enabling
	 * tryTwoCubeAuto will only make the robot grab two cubes if both the switch and
	 * scale are on the same side.
	 * 
	 * @param startDelay
	 *            in seconds
	 * @param scoreSelection
	 * @param startPos
	 * @param tryTwoCubeAuto
	 */
	public PrimaryAutonomous(double startDelay, ScoringMethod scoreSelection, StartPosition startPos,
			boolean tryTwoCubeAuto) {

		System.out.println("before added initial sequentials");
		
		addSequential(new ShiftDownForTime());

		this.initialDelay = startDelay;
		this.scoringStrategy = scoreSelection;
		this.startPosition = startPos;
		
		System.out.println("after added initial sequential");

		boolean switchAndScaleOnSameSide = switchIsRight() == scaleIsRight();

		this.isTwoCubeAuto = switchAndScaleOnSameSide && tryTwoCubeAuto;

		System.out.println("after checked switch side");
		if(this.initialDelay > 0) {
		addSequential(new Delay(this.initialDelay));
		}
		System.out.println("after added initial delayF");

		if (this.isTwoCubeAuto == false) {
			if (this.scoringStrategy == ScoringMethod.SWITCH) {

				goToSwitchFromStart();
			}

			else if (this.scoringStrategy == ScoringMethod.SCALE) {

				System.out.println("before going to scale from start");
				goToScaleFromStart();
				System.out.println("started going to scale from start");
			}

			else if (this.scoringStrategy == ScoringMethod.EXCHANGE) {

				if (this.startPosition == StartPosition.LEFT) {
					System.out.println(
							"Failed: Cannot move to exchange from Left Start Position. However, Robot is smart and will still cross the auto line");
					addSequential(new LeftToLeft());
				} else if (this.startPosition == StartPosition.MIDDLE) {
					// mid to exchange to auto line
					addSequential(new MidToExchange());
				} else {
					System.out.println(
							"Failed: Cannot move to exchange from Right Start. Robot is sad, but will still cross the auto line");
					addSequential(new RightToRight());
				}

			}
			// Cross the Auto Line.
			else if (this.startPosition == StartPosition.LEFT) {
				addSequential(new LeftToLeft());
			} else {
				addSequential(new RightToRight());
			}
			
			System.out.println("done with primary auto if statements");
		} else {
			// two cube auto
			goToSwitchFromStart();
			if (this.scaleIsRight() == true) {
				addSequential(new RightSwitchToCubeToScale());
			} else {
				addSequential(new LeftSwitchToCubeToScale());

			}
		}
		
		System.out.println("end of primary auto setup");
	}

	private void goToSwitchFromStart() {
		if (this.startPosition == StartPosition.LEFT) {
			if (this.switchIsRight()) {
				// left to right switch
				addSequential(new LeftToRight());
				addSequential(new RightStartToSwitch());
			} else {

				// going from left to left switch
				addSequential(new LeftToLeft());
				addSequential(new LeftStartToSwitch());
			}
		} else if (this.startPosition == StartPosition.MIDDLE) {

			if (this.switchIsRight()) {
				// mid to Right Switch
				addSequential(new MidToRight());
				addSequential(new RightStartToSwitch());
			} else {
				// Mid to Left Switch
				addSequential(new MidToLeft());
				addSequential(new LeftStartToSwitch());
			}
		} else {
			if (this.switchIsRight()) {
				// left to right switch
				addSequential(new RightToRight());
				addSequential(new RightStartToSwitch());
			} else {
				// right to Left Switch
				addSequential(new RightToLeft());
				addSequential(new LeftStartToSwitch());
			}
		}
	}

	private void goToScaleFromStart() {
		if (this.startPosition == StartPosition.LEFT) {
			if (this.scaleIsRight()) {
				// left to right scale
				addSequential(new LeftToRight());
				addSequential(new RightStartToScale());
			} else {
				// left to left scale
				addSequential(new LeftToLeft());
				addSequential(new LeftStartToScale());
			}

		} else if (this.startPosition == StartPosition.MIDDLE) {
			if (this.scaleIsRight()) {
				// mid to right scale
				addSequential(new MidToRight());
				addSequential(new RightStartToScale());
			} else {
				// mid to left scale
				addSequential(new MidToLeft());
				addSequential(new LeftStartToScale());
			}
		} else {
			if (this.scaleIsRight()) {
				// right to right scale
				addSequential(new RightToRight());
				addSequential(new RightStartToScale());
			} else {
				// right to left scale
				addSequential(new RightToLeft());
				addSequential(new LeftStartToScale());
			}
		}
	}

}
