/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot;

import org.usfirst.frc.team3274.robot.commands.Climb;
import org.usfirst.frc.team3274.robot.commands.CloseClaw;
import org.usfirst.frc.team3274.robot.commands.DeployClaw;
import static org.usfirst.frc.team3274.robot.commands.DriveWithJoystick.DriveType;

import org.usfirst.frc.team3274.robot.commands.ArmLock;
//import org.usfirst.frc.team3274.robot.commands.DropClaw;
import org.usfirst.frc.team3274.robot.commands.Eject;
import org.usfirst.frc.team3274.robot.commands.IncreaseHeight;
import org.usfirst.frc.team3274.robot.commands.Interrupt;
import org.usfirst.frc.team3274.robot.commands.LowerClaw;
import org.usfirst.frc.team3274.robot.commands.OpenClaw;
import org.usfirst.frc.team3274.robot.commands.RaiseClaw;
import org.usfirst.frc.team3274.robot.commands.ReduceHeight;
import org.usfirst.frc.team3274.robot.commands.SetHeightWithEncoder;
import org.usfirst.frc.team3274.robot.commands.ShiftDown;
import org.usfirst.frc.team3274.robot.commands.ShiftUp;
import org.usfirst.frc.team3274.robot.commands.Suck;
import org.usfirst.frc.team3274.robot.commands.TestCommand;
import org.usfirst.frc.team3274.robot.commands.autonomous.EjectAutonomous;
import org.usfirst.frc.team3274.robot.commands.autonomous.HoldForkLift;
import org.usfirst.frc.team3274.robot.commands.autonomous.RetractClaw;
import org.usfirst.frc.team3274.robot.commands.autonomous.ShiftDownForTime;
import org.usfirst.frc.team3274.robot.util.AxisButton;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Joystick.Axis;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	/** +- 0, dead zone for joystick input **/
	public static final double JOYSTICK_DEADZONE = 0.1;

	public static final DriveType DRIVE_MODE = DriveType.TANK_DRIVE;

	// this is an enum. its like creating a class called ControllerSetup, where the
	// only values can be SINGLE_XBOX_CONTROLLER or DUAL_XBOX_CONTROLLER
	public enum ControllerSetup {
		SINGLE_XBOX_CONTROLLER, DUAL_XBOX_CONTROLLER
	};

	/** The first joystick. Has to be an xbox controller. **/
	public Joystick xbox0 = new Joystick(0); // set to ID 1
	private JoystickButton a0 = new JoystickButton(xbox0, RobotMap.XBOX_A_BUTTON);
	private JoystickButton b0 = new JoystickButton(xbox0, RobotMap.XBOX_B_BUTTON);
	private JoystickButton x0 = new JoystickButton(xbox0, RobotMap.XBOX_X_BUTTON);
	private JoystickButton y0 = new JoystickButton(xbox0, RobotMap.XBOX_Y_BUTTON);
	private JoystickButton lBumper0 = new JoystickButton(xbox0, RobotMap.XBOX_L_BUMPER_BUTTON);
	private JoystickButton rBumper0 = new JoystickButton(xbox0, RobotMap.XBOX_R_BUMPER_BUTTON);
	// private JoystickButton up0 = new JoystickButton(xbox0,
	// RobotMap.XBOX_UP_BUTTON);
	// private JoystickButton down0 = new JoystickButton(xbox0,
	// RobotMap.XBOX_DOWN_BUTTON);
	// private JoystickButton left0 = new JoystickButton(xbox0,
	// RobotMap.XBOX_LEFT_BUTTON);
	// private JoystickButton right0 = new JoystickButton(xbox0,
	// RobotMap.XBOX_RIGHT_BUTTON);
	private JoystickButton start0 = new JoystickButton(xbox0, RobotMap.XBOX_START_BUTTON);
	private JoystickButton back0 = new JoystickButton(xbox0, RobotMap.XBOX_BACK_BUTTON);
	private JoystickButton l30 = new JoystickButton(xbox0, RobotMap.XBOX_L3_BUTTON);
	private JoystickButton r30 = new JoystickButton(xbox0, RobotMap.XBOX_R3_BUTTON);
//	private AxisButton rTrigger0 = new AxisButton(xbox0, RobotMap.XBOX_RIGHT_TRIGGER_AXIS, .75, true);
//	private AxisButton lTrigger0 = new AxisButton(xbox0, RobotMap.XBOX_LEFT_TRIGGER_AXIS, .75, true);
	
	private double leftY0 = xbox0.getRawAxis(RobotMap.XBOX_LEFT_Y_AXIS);
	private double leftX0 = xbox0.getRawAxis(RobotMap.XBOX_LEFT_X_AXIS);
	private double rightY0 = xbox0.getRawAxis(RobotMap.XBOX_RIGHT_Y_AXIS);
	private double rightX0 = xbox0.getRawAxis(RobotMap.XBOX_RIGHT_X_AXIS);

	/** The second joystick. Has to be an xbox controller. **/
	public Joystick xbox1 = new Joystick(1); // set to ID 1
	private JoystickButton a1 = new JoystickButton(xbox1, RobotMap.XBOX_A_BUTTON);
	private JoystickButton b1 = new JoystickButton(xbox1, RobotMap.XBOX_B_BUTTON);
	private JoystickButton x1 = new JoystickButton(xbox1, RobotMap.XBOX_X_BUTTON);
	private JoystickButton y1 = new JoystickButton(xbox1, RobotMap.XBOX_Y_BUTTON);
	private JoystickButton lBumper1 = new JoystickButton(xbox1, RobotMap.XBOX_L_BUMPER_BUTTON);
	private JoystickButton rBumper1 = new JoystickButton(xbox1, RobotMap.XBOX_R_BUMPER_BUTTON);
	// private JoystickButton up1 = new JoystickButton(xbox1,
	// RobotMap.XBOX_UP_BUTTON);
	// private JoystickButton down1 = new JoystickButton(xbox1,
	// RobotMap.XBOX_DOWN_BUTTON);
	// private JoystickButton left1 = new JoystickButton(xbox1,
	// RobotMap.XBOX_LEFT_BUTTON);
	// private JoystickButton right1 = new JoystickButton(xbox1,
	// RobotMap.XBOX_RIGHT_BUTTON);
	
//	private AxisButton stickUp1 = new AxisButton(xbox1, RobotMap.XBOX_LEFT_Y_AXIS, .75, true);
//	private AxisButton stickDown1 = new AxisButton(xbox1, RobotMap.XBOX_LEFT_Y_AXIS, .25, false);
	

	private JoystickButton start1 = new JoystickButton(xbox1, RobotMap.XBOX_START_BUTTON);
	private JoystickButton back1 = new JoystickButton(xbox1, RobotMap.XBOX_BACK_BUTTON);
	private JoystickButton l31 = new JoystickButton(xbox1, RobotMap.XBOX_L3_BUTTON);
	private JoystickButton r31 = new JoystickButton(xbox1, RobotMap.XBOX_R3_BUTTON);
//	private AxisButton rTrigger1 = new AxisButton(xbox1, RobotMap.XBOX_RIGHT_TRIGGER_AXIS, .75, true);
//	private AxisButton lTrigger1 = new AxisButton(xbox1, RobotMap.XBOX_LEFT_TRIGGER_AXIS, .75, true);

	private double leftY1 = xbox1.getRawAxis(RobotMap.XBOX_LEFT_Y_AXIS);
	private double leftX1 = xbox1.getRawAxis(RobotMap.XBOX_LEFT_X_AXIS);
	private double rightY1 = xbox1.getRawAxis(RobotMap.XBOX_RIGHT_Y_AXIS);
	private double rightX1 = xbox1.getRawAxis(RobotMap.XBOX_RIGHT_X_AXIS);

	/**
	 * Create an OI with a specified controller setup.
	 * 
	 * @param setup
	 *            the ControllerSetup to use. Can be a single controller, multiple
	 *            controllers, etc.
	 */
	public OI(ControllerSetup setup) {
		if (setup == ControllerSetup.SINGLE_XBOX_CONTROLLER) {
			initSingleXboxControllerSetup();
		} else if (setup == ControllerSetup.DUAL_XBOX_CONTROLLER) {
			initDualXboxControllerSetup();
		} else {
			// code here should never be reached
		}
	}

	private void initSingleXboxControllerSetup() {
		// assign commands to buttons and stuff here (described at top of this class)

//		rTrigger0.whileHeld(new ShiftUp());
//		lTrigger0.whileHeld(new ShiftDown());
		rBumper0.whenPressed(new OpenClaw());
		rBumper0.whenReleased(new CloseClaw());
		r30.whileHeld(new IncreaseHeight());
		r30.whenReleased(new Interrupt(Robot.kForkLift));
		l30.whileHeld(new ReduceHeight());
		l30.whenReleased(new Interrupt(Robot.kForkLift));
		lBumper0.whenPressed(new Eject());
		lBumper0.whenReleased(new Interrupt(Robot.kForkLift));
		b0.whenPressed(new LowerClaw());
		b0.whenReleased(new Interrupt(Robot.kClawArm));
		// left.whenPressed(new DropClaw());
		// right.whenPressed(new DeployClaw());
		// down.whenPressed(new RetractClaw());
		start0.whenPressed(new Climb());
		//****************************************************************************************SET UP HOLD CLAW

		// Keep in mind that joystick stuff is handled by the drive train already, so
		// you only have to deal with button presses here.

		///// ////// temporary buttons ////// /////
		x0.whenPressed(new Suck());
		x0.whenReleased(new Interrupt(Robot.kClawIntake));
		y0.whenPressed(new RaiseClaw());
		y0.whenReleased(new Interrupt(Robot.kClawArm));
		////// Why temporary? they're fine!//////
	}

	private void initDualXboxControllerSetup() {

		// first driver
		rBumper0.whileHeld(new ShiftUp());
		lBumper0.whileHeld(new ShiftDown());

		// second driver
		rBumper1.whenPressed(new IncreaseHeight());
		rBumper1.whenReleased(new HoldForkLift());
		lBumper1.whenPressed(new ReduceHeight());
		lBumper1.whenReleased(new HoldForkLift());
		b1.whenPressed(new CloseClaw());
		//x1.whenPressed(new CloseClaw());
		x1.whileHeld(new Suck());
		x1.whenReleased(new Interrupt(Robot.kClawIntake));
		r31.whenPressed(new Eject());
		//add Lift Control to Left Stick
		y1.whenPressed(new RaiseClaw());
		y1.whenReleased(new Interrupt(Robot.kClawArm));
		a1.whenPressed(new LowerClaw());
		a1.whenReleased(new Interrupt(Robot.kClawArm));
		
		start1.whenPressed(new ArmLock());
		start1.whenReleased(new Interrupt(Robot.kClawArm));
		

	
	}

	/**
	 * Causes the first xbox controller to rumble.
	 */
	public void rumbleXbox01(boolean rumble) {
		if (rumble) {
			this.xbox0.setRumble(RumbleType.kLeftRumble, 1);
		} else {
			this.xbox0.setRumble(RumbleType.kLeftRumble, 0);
		}
	}

	/**
	 * Returns the first xbox controller.
	 * 
	 * @return
	 */
	public Joystick getXbox01() {
		return xbox0;
	}
}
