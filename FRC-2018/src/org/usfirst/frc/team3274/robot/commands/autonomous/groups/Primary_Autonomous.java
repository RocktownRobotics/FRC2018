package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.commands.Eject;
import org.usfirst.frc.team3274.robot.commands.SetHeight;
import org.usfirst.frc.team3274.robot.commands.autonomous.Delay;
import org.usfirst.frc.team3274.robot.commands.autonomous.DeployClaw;
import org.usfirst.frc.team3274.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.commands.autonomous.TurnRobot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Primary_Autonomous extends CommandGroup {

	private String startPosition;
	private String scoringStrategy;
	private double initialDelay;
	
	private boolean switchIsRight() {
		if (Robot.gameData == "RLR") {
			return true;
		}
		else {
			if(Robot.gameData == "RRR") {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	
	private boolean scaleIsRight() {
		if (Robot.gameData == "LRL") {
			return true;
		}
		else {
			if(Robot.gameData == "RRR") {
				return true;
			}
			else {
				return false;
			}
		}
	}

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees. No, no it is not....
	 */

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * This is a very complicated bit of code, with many, many stacked if
	 * statements, not written for easy readability. You have been warned...
	 * 
	 * Additionally, if you are using my EditBox settings, your eyes will be bleeding by the end of it all...
	 */
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Primary_Autonomous(double startDelay, String scoreSelection, String startPos) {

		addSequential(new ShiftDownForTime());

		this.initialDelay = startDelay;
		this.scoringStrategy = scoreSelection;
		this.startPosition = startPos;
		
		

		addSequential(new Delay(this.initialDelay));

		if (this.scoringStrategy == "Switch") {

			if (this.startPosition == "Left") {
				if(this.switchIsRight()) {
					addSequential(new DriveForward(4));
					addSequential(new TurnRobot(-90));
					addSequential(new DriveForward(21));
					addSequential(new TurnRobot(90));
					addSequential(new DriveForward(4));
					
					addSequential(new DriveForward(3));
					addParallel(new DeployClaw());
					addParallel(new SetHeight(20, 5));
					addSequential(new TurnRobot(-90));
					addSequential(new Eject());
				}
				else {
					
					addSequential(new DriveForward(8));
					
					addSequential(new DriveForward(3));
					addParallel(new DeployClaw());
					addParallel(new SetHeight(20, 5));
					addSequential(new TurnRobot(90));
					addSequential(new Eject());
					
				}
			} else {
				if (this.startPosition == "Middle") {
				} else {
				}
			}

		}

		else {

			if (this.scoringStrategy == "Scale") {

				if (this.startPosition == "Left") {
				} else {
					if (this.startPosition == "Middle") {
					} else {
					}
				}

			}

			else {

				if (this.scoringStrategy == "Exchange") {

					if (this.startPosition == "Left") {
					} else {
						if (this.startPosition == "Middle") {
						} else {
						}
					}

				}

				else {

					if (this.startPosition == "Left") {
					} else {
						if (this.startPosition == "Middle") {
						} else {
						}
					}

				}
			}

		}

	}
}
