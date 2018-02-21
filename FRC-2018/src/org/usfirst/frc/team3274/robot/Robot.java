/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import sun.security.krb5.internal.tools.Klist;

import org.usfirst.frc.team3274.robot.commands.ExampleCommand;
import org.usfirst.frc.team3274.robot.commands.SetHeightWithEncoder;
import org.usfirst.frc.team3274.robot.commands.autonomous.ResetHeight;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.Primary_Autonomous;
import org.usfirst.frc.team3274.robot.commands.autonomous.groups.TestAuto;
import org.usfirst.frc.team3274.robot.subsystems.ClawArm;
import org.usfirst.frc.team3274.robot.subsystems.ClawIntake;
import org.usfirst.frc.team3274.robot.subsystems.DrivePneumatics;
import org.usfirst.frc.team3274.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3274.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3274.robot.subsystems.ForkLift;
import org.usfirst.frc.team3274.robot.subsystems.RobotCompressor;

import com.sun.javafx.scene.control.behavior.TwoLevelFocusPopupBehavior;

import VisionProcessing.CameraProcessor;

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
	private SendableChooser<String> startPositionChooser = new SendableChooser<>();
	private SendableChooser<String> scoringMethodChooser = new SendableChooser<>();
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

		// add Initial Delay options
		startDelayChooser.addDefault("No Delay", 0.0);
		startDelayChooser.addObject("Delay (2 sec)", 2.0);
		SmartDashboard.putData("Delaying", startDelayChooser);

		// add Starting Position options
		startPositionChooser.addObject("Left", "Left");
		startPositionChooser.addObject("Middle", "Middle");
		startPositionChooser.addObject("Right", "Right");
		SmartDashboard.putData("Starting in", startPositionChooser);

		// add Scoring Method options
		scoringMethodChooser.addDefault("Switch", "Switch");
		scoringMethodChooser.addObject("Scale", "Scale");
		scoringMethodChooser.addObject("None", "None");
		scoringMethodChooser.addObject("Exchange(Must be in Middle)", "Exchange");
		SmartDashboard.putData("Attempting to Score", scoringMethodChooser);

		// add two-cube auto option
		twoCubeAutoChooser.addDefault("Disable Two Cube Auto", false);
		twoCubeAutoChooser.addObject("Enable Two Cube Auto", true);
		SmartDashboard.putData("Attempting to Do Two Cubes", twoCubeAutoChooser);

		SmartDashboard.putData("Reset Height", new ResetHeight());
		SmartDashboard.putData("Set Height 2", new SetHeightWithEncoder(1, .01));
		// SmartDashboard.putData("",);

		Robot.itself = this;

		this.gameData = "";

		// *****VISION PROCESSING*****
		new CameraProcessor().init();

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
		System.out.println("Robot got placed in the" + startPositionChooser.getSelected() + "position");
		System.out
				.println("Robot is trying to do something. Specifically, use the" + scoringMethodChooser.getSelected());
		System.out.println("Robot just feels like sitting around for" + startDelayChooser.getSelected() + "seconds...");
		System.out.println("Robot " + (twoCubeAutoChooser.getSelected() ? "feels like" : "doesn't feel like")
				+ " trying to go for a two-cube autonomous");

		this.m_autonomousCommand = new Primary_Autonomous(startDelayChooser.getSelected(),
				scoringMethodChooser.getSelected(), startPositionChooser.getSelected(),
				twoCubeAutoChooser.getSelected());

		m_autonomousCommand.start();

		// how to get game type from driver station
		// game data is either: "LLL", "RRR", "LRL", "RLR"
		this.gameData = DriverStation.getInstance().getGameSpecificMessage();
		// if (gameData.equals("LLL")) {
		// // one auto code here
		// } else {
		// // another auto code here
		// }

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

		SmartDashboard.putNumber("gyro_yaw", kDriveTrain.getYaw());

		SmartDashboard.putBoolean("Lift not at min position", kForkLift.isLiftNotAtMinHeight());
		SmartDashboard.putBoolean("Lift not at max position", kForkLift.isLiftNotAtMaxHeight());

		SmartDashboard.putBoolean("Claw is loaded", kClawIntake.isClawLoaded());

		SmartDashboard.putBoolean("Left Claw Eye", kClawIntake.get_leftClawLimitSwitch().get());
		SmartDashboard.putBoolean("Right Claw Eye", kClawIntake.get_rightClawLimitSwitch().get());
	}

}
