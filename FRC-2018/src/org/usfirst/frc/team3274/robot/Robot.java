/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot;

import org.usfirst.frc.team3274.robot.commands.autonomous.programs.PrimaryAutonomous;
import org.usfirst.frc.team3274.robot.commands.autonomous.programs.ScaleFromLeft;
import org.usfirst.frc.team3274.robot.subsystems.ClawArm;
import org.usfirst.frc.team3274.robot.subsystems.ClawIntake;
import org.usfirst.frc.team3274.robot.subsystems.DrivePneumatics;
import org.usfirst.frc.team3274.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3274.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3274.robot.subsystems.ForkLift;
import org.usfirst.frc.team3274.robot.subsystems.RobotCompressor;
import org.usfirst.frc.team3274.robot.visionprocessing.CameraProcessor;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final ExampleSubsystem kExampleSubsystem = new ExampleSubsystem();

	public static final DrivePneumatics kDrivePneumatics = new DrivePneumatics();
	public static final DriveTrain kDriveTrain = new DriveTrain();
	public static final RobotCompressor kCompressor = new RobotCompressor();
	public static final ClawIntake kClawIntake = new ClawIntake();
	public static final ClawArm kClawArm = new ClawArm();
	public static final ForkLift kForkLift = new ForkLift();

	public static String gameData;

	public static OI m_oi;

	// for checking when the robot is running
	public static Robot itself;

	private SendableChooser<Double> startDelayChooser = new SendableChooser<>();
	private SendableChooser<PrimaryAutonomous.StartPosition> startPositionChooser = new SendableChooser<>();
	private SendableChooser<PrimaryAutonomous.ScoringMethod> scoringMethodChooser = new SendableChooser<>();
	private SendableChooser<Boolean> twoCubeAutoChooser = new SendableChooser<>();

	private Command m_autonomousCommand;

	/**
	 * REturns the current time in seconds.
	 * 
	 * @return
	 */
	public static double getTime() {
		return System.nanoTime() / 1000000000.;
	}

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI(OI.ControllerSetup.DUAL_XBOX_CONTROLLER);

		Robot.itself = this;

		this.gameData = "";

		// *****VISION PROCESSING*****
		CameraProcessor camProcessor = new CameraProcessor();
		camProcessor.init();

	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		gameData = DriverStation.getInstance().getGameSpecificMessage();

//		 this.m_autonomousCommand = new SwitchFromRight();
		// this.m_autonomousCommand = new SwitchFromLeft();
		 //this.m_autonomousCommand = new CrossingTheLine();
		this.m_autonomousCommand = new ScaleFromLeft();
//		this.m_autonomousCommand = new ScaleFromRight();
//		this.m_autonomousCommand = new CloseFromLeft();//SWITCH IS PRIORITY, SCALE IS SECONDARY
		//this.m_autonomousCommand = new CloseFromRight();//SWITCH IS PRIORITY, SCALE IS SECONDARY
//		this.m_autonomousCommand = new SwitchFromMid();
//		this.m_autonomousCommand = new CloseFromLeft_PrioritizingScale();
//		this.m_autonomousCommand = new CloseFromRight_PrioritizingScale();
//		this.m_autonomousCommand = new SwitchFromMid();

		Robot.kForkLift.resetLiftEncoders();

		this.m_autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		System.out.println(
				"What? You think you can control Robot? Robot... will not... must not... BEGINNING REMOTE OPERATION");

		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {

		/*
		 * Note that the DriveWithJoyStick command is activated automatically in
		 * DriveTrain.initDefaultCommand()
		 */

		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		log();
	}

	private void log() {
		// For example:
		// SmartDashboard.putNumber("Shooter Speed", shooter.getRPM());
		SmartDashboard.putNumber("leftEncoder", kDriveTrain.getLeftDistance());
		SmartDashboard.putNumber("rightEncoder", kDriveTrain.getRightDistance());

		SmartDashboard.putNumber("leftEncoderRaw", kDriveTrain.getLeftRotations());
		SmartDashboard.putNumber("rightEncoderRaw", kDriveTrain.getRightRotations());

		SmartDashboard.putNumber("gyro_yaw", kDriveTrain.getYaw());

		SmartDashboard.putBoolean("Lift not at min position", kForkLift.isLiftNotAtMinHeight());
		SmartDashboard.putBoolean("Lift not at max position", kForkLift.isLiftNotAtMaxHeight());

		SmartDashboard.putBoolean("Claw is loaded", kClawIntake.isClawLoaded());

		SmartDashboard.putBoolean("Left Claw Eye", kClawIntake.get_leftClawLimitSwitch().get());
		SmartDashboard.putBoolean("Right Claw Eye", kClawIntake.get_rightClawLimitSwitch().get());

		SmartDashboard.putBoolean("Claw is Retracted", kClawArm.isClawRetracted());
		SmartDashboard.putNumber("Claw Angle", kClawArm.getAngle());
	}

}
