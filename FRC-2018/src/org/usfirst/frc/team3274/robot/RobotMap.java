/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot;

import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	/**
	 * ProxyPort is simply a zero variable that serves as the port number for all
	 * the things that don't actually exist, like the forklift. Delete when these
	 * things are installed.
	 */
	public final static int PROXY_PORT = -1;

	////////////////////////////////////////////////
	///////////// JOYSTICK AXIS ////////////////////
	////////////////////////////////////////////////

	// xbox controller, LIFTCON controls forklift and should be on a second
	// controller, or a button
	public static final int XBOX_LEFT_Y_AXIS = 1;
	public static final int XBOX_LEFT_X_AXIS = 0;
	public static final int XBOX_RIGHT_Y_AXIS = 5;
	public static final int XBOX_RIGHT_X_AXIS = 4;
	
	public static final int XBOX_RIGHT_TRIGGER_AXIS = 3;
	public static final int XBOX_LEFT_TRIGGER_AXIS = 2;

	// flight stick
	public static final int FLIGHT_STICK_FORWARD_AXIS = 1;
	public static final int FLIGHT_STICK_THROTTLE_AXIS = 2;

	////////////////////////////////////////////////
	///////////// JOYSTICK BUTTONS /////////////////
	////////////////////////////////////////////////

	// xbox controller

	public static final int XBOX_A_BUTTON = 1;
	public static final int XBOX_B_BUTTON = 2;
	public static final int XBOX_X_BUTTON = 3;
	public static final int XBOX_Y_BUTTON = 4;
	public static final int XBOX_L_BUMPER_BUTTON = 5;
	public static final int XBOX_R_BUMPER_BUTTON = 6;
	public static final int XBOX_START_BUTTON = 8;
	public static final int XBOX_BACK_BUTTON = 7;
	public static final int XBOX_UP_BUTTON = 13;
	public static final int XBOX_DOWN_BUTTON = 14;
	public static final int XBOX_LEFT_BUTTON = 15;
	public static final int XBOX_RIGHT_BUTTON = 16;
	public static final int XBOX_R3_BUTTON = 10;
	public static final int XBOX_L3_BUTTON = 9;

	////////////////////////////////////////////////
	//////////// CAN TALONS BELOW HERE//////////////
	////////////////////////////////////////////////

	// Four wheels
	public static final int FRONT_LEFT_MOTOR = 4;
	public static final int FRONT_RIGHT_MOTOR = 3;
	public static final int REAR_LEFT_MOTOR = 1;
	public static final int REAR_RIGHT_MOTOR = 5;
	public static final int LEFT_MOTOR = 6;
	public static final int RIGHT_MOTOR = 2;

	////////////////////////////////////////////////
	/////////////// DIO's BELOW HERE////////////////
	////////////////////////////////////////////////

	// Limit Switches
	public static final int LOWER_LIMIT_SWITCH = 4;
	//public static final int UPPER_LIMIT_SWITCH = 5;
	
	public static final int LEFT_CLAW_EYE_LIMIT_SWITCH = 7; //LEFT EYE
	public static final int RIGHT_CLAW_EYE_LIMIT_SWITCH = 6; //RIGHT EYE
	
	public static final int UPPER_CLAW_LIMIT_SWITCH = 8;

	// two input ports for each encoder
	public static final int[] RIGHT_ENCODER = { 3, 2 };
	public static final int[] LEFT_ENCODER = { 1, 0 };
	public static final int[] DEPLOY_ENCODER = { PROXY_PORT, PROXY_PORT };

	////////////////////////////////////////////////
	/////////////// PWM's BELOW HERE////////////////
	////////////////////////////////////////////////

	public static final int LIFT_MOTOR_LEFT = 8;
	public static final int LIFT_MOTOR_RIGHT = 9;
	public static final int CLAW_MOTOR_LEFT = 1;
	public static final int CLAW_MOTOR_RIGHT = 2;
	public static final int DEPLOY_MOTOR = 0;

	////////////////////////////////////////////////
	/////////////// PCM's BELOW HERE////////////////
	////////////////////////////////////////////////

	public static final int SHIFTER_HIGH_GEAR = 1;
	public static final int SHIFTER_LOW_GEAR = 2;
	public static final int CLAW_PISTON = 0;

	////////////////////////////////////////////////
	////////////// Relays BELOW HERE////////////////
	////////////////////////////////////////////////

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	////////////////////////////////////////////////
	//////////// Misc. PORTS BELOW HERE/////////////
	////////////////////////////////////////////////

	public static final SPI.Port NAVX_PORT = SPI.Port.kMXP;
}
