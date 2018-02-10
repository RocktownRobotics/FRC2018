/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot;

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

	// this is an enum. its like creating a class called ControllerSetup, where the
	// only values can be SINGLE_XBOX_CONTROLLER or DUAL_XBOX_CONTROLLER
	public enum ControllerSetup {
		SINGLE_XBOX_CONTROLLER, DUAL_XBOX_CONTROLLER
	};

	/** The first joystick. Has to be an xbox controller. **/
	public Joystick xbox0 = new Joystick(0); // set to ID 1
	private JoystickButton a = new JoystickButton(xbox0, RobotMap.XBOX_A_BUTTON);
	private JoystickButton b = new JoystickButton(xbox0, RobotMap.XBOX_B_BUTTON);
	private JoystickButton x = new JoystickButton(xbox0, RobotMap.XBOX_X_BUTTON);
	private JoystickButton y = new JoystickButton(xbox0, RobotMap.XBOX_Y_BUTTON);
	private JoystickButton lBumper = new JoystickButton(xbox0, RobotMap.XBOX_L_BUMPER_BUTTON);
	private JoystickButton rBumper = new JoystickButton(xbox0, RobotMap.XBOX_R_BUMPER_BUTTON);
	private JoystickButton up = new JoystickButton(xbox0, RobotMap.XBOX_UP_BUTTON);
	private JoystickButton down = new JoystickButton(xbox0, RobotMap.XBOX_DOWN_BUTTON);
	private JoystickButton left = new JoystickButton(xbox0, RobotMap.XBOX_LEFT_BUTTON);
	private JoystickButton right = new JoystickButton(xbox0, RobotMap.XBOX_RIGHT_BUTTON);
	private JoystickButton start = new JoystickButton(xbox0, RobotMap.XBOX_START_BUTTON);
	private JoystickButton back = new JoystickButton(xbox0, RobotMap.XBOX_BACK_BUTTON);
	private AxisButton rightTrigger = new AxisButton(xbox0, RobotMap.XBOX_RIGHT_TRIGGER_AXIS, .5, true);
	private AxisButton leftTrigger = new AxisButton(xbox0, RobotMap.XBOX_LEFT_TRIGGER_AXIS, .5, true);

	private double leftY = xbox0.getRawAxis(RobotMap.XBOX_LEFT_Y_AXIS);
	private double leftX = xbox0.getRawAxis(RobotMap.XBOX_LEFT_X_AXIS);
	private double rightY = xbox0.getRawAxis(RobotMap.XBOX_RIGHT_Y_AXIS);
	private double rightX = xbox0.getRawAxis(RobotMap.XBOX_RIGHT_X_AXIS);

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

		// Keep in mind that joystick stuff is handled by the drive train already, so
		// you only have to deal with button presses here.
	}

	private void initDualXboxControllerSetup() {
		// assign commands to buttons and stuff here (described at top of this class)

		// Keep in mind that joystick stuff is handled by the drive train already, so
		// you only have to deal with button presses here.
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
