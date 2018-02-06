package org.usfirst.frc.team3274.robot.commands.autonomous.groups;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.commands.autonomous.Delay;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Primary_Autonomous extends CommandGroup {

	private String startPosition;
	private String scoringStrategy;
	private double initialDelay;

	/**
	 * Here is how you would make the robot drive forward 3 feet and then turn left
	 * 90 degrees. No, no it is not....
	 */
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
*					This is a very complicated bit of code, with many, many stacked if 
*					statements, not written for easy readability. You have been warned...	
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

			if(this.startPosition == "Left") {
				
			}
			else {
				if(this.startPosition == "Middle") {}
				else {
				}}
			
		}

		else {

			if (this.scoringStrategy == "Scale") {

				if(this.startPosition == "Left") {}
				else {
					if(this.startPosition == "Middle") {}
					else {
					}}
				
			}

			else {

				if (this.scoringStrategy == "Exchange") {

					if(this.startPosition == "Left") {}
					else {
						if(this.startPosition == "Middle") {}
						else {
						}}
					
				}

				else {
					
					if(this.startPosition == "Left") {}
					else {
						if(this.startPosition == "Middle") {}
						else {
						}}

				}
			}

		}

	}
}
