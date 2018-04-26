/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3274.robot.Robot;

/**
 * An example command. You can replace me with your own command.
 */
public class SetHeightFromBottom extends Command {

	private double desiredHeight;
	private double startTime;
	private double timeToRun;
	public static final double timePerInch = 0.02;
	public static final double liftPower = 0.999999;

	/**
	 * 
	 * @param targetHeight in inches
	 */
	public SetHeightFromBottom(double targetHeight) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kForkLift);
		this.desiredHeight = targetHeight;

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		this.timeToRun = this.desiredHeight * this.timePerInch;
		this.startTime = Robot.getTime();
		
		
		System.out.println("starting setheightfrombottom");

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		Robot.kForkLift.setLiftPower(this.liftPower);

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {

		if (Robot.kForkLift.isLiftNotAtMaxHeight()) {
			if (Robot.getTime() < this.timeToRun + this.startTime) {
				return false;
			} else {
				System.out.println("Robot has reached the desired height");
				return true;
			}
		} else {
			System.out.println("Robot is about to blow itself up. Luckily, Robot is smart and won't do it.");
			return true;
		}

	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.kForkLift.setLiftPower(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
