package org.usfirst.frc.team3274.robot.subsystems;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.commands.SuckWeakly;
import org.usfirst.frc.team3274.robot.util.StoppableSubsystem;
import org.usfirst.frc.team3274.robot.util.TalonSRXGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawIntake extends StoppableSubsystem {

	private DigitalInput _leftClawLimitSwitch = new DigitalInput(RobotMap.LEFT_CLAW_EYE_LIMIT_SWITCH);
	private DigitalInput _rightClawLimitSwitch = new DigitalInput(RobotMap.RIGHT_CLAW_EYE_LIMIT_SWITCH);

	// private DigitalInput _upperClawLimitSwitch = new
	// DigitalInput(RobotMap.UPPER_CLAW_LIMIT_SWITCH);
	// private DigitalInput _lowerClawLimitSwitch = new
	// DigitalInput(RobotMap.LOWER_CLAW_LIMIT_SWITCH);

	private boolean clawClosed;
	
	/*
	 * private Encoder _deployEncoder = new Encoder(RobotMap.DEPLOY_ENCODER[0],
	 * RobotMap.DEPLOY_ENCODER[1], true, EncodingType.k4X);
	 */
	private PWMTalonSRX leftClaw = new PWMTalonSRX(RobotMap.CLAW_MOTOR_LEFT);
	private PWMTalonSRX rightClaw = new PWMTalonSRX(RobotMap.CLAW_MOTOR_RIGHT);
	private Solenoid clawPistons = new Solenoid(RobotMap.CLAW_PISTON);

	public ClawIntake() {
		
		this.clawClosed = true;
	}

	/**
	 * @param motorSpeed
	 *            the motor power at which the cube should be moved. Between 0 and
	 *            1, the negatives, if any, will be handled in the eject code.
	 * 
	 * @param thrustTime
	 *            the time to run the ejection motors, in seconds
	 */

	public void setCubeManipulatorMotors(double motorSpeed) {
		this.leftClaw.set(motorSpeed);
		this.rightClaw.set(-motorSpeed);

	}

	// public int getDeployRotations() {
	// return this._deployEncoder.getRaw();
	// }

	/**
	 * Return the angle of this encoder in degrees. Assumes the claw always starts
	 * in an up position, and 0 degrees straight forward.
	 * 
	 * @return the angle of the claw in degrees
	 */
	
	public boolean isClawLoaded() {

		if (_leftClawLimitSwitch.get() == false && _rightClawLimitSwitch.get() == false) {
			return false;
		} else

		{
			return true;
		}
	}


	

	public Solenoid getclawPiston() {
		return clawPistons;
	}

	public boolean isClawClosed() {
		return clawClosed;
	}
	
	public void setClawClosed(boolean closed) {
		this.clawClosed = closed;
	}

	public void OpenClaw() {
		clawPistons.set(false);
		this.clawClosed = true;
	}

	public void CloseClaw() {
		clawPistons.set(true);
		this.clawClosed = false;
	}

	@Override
	public void stop() {
		clawPistons.set(false);
		setCubeManipulatorMotors(0);
	}

	@Override
	protected void initDefaultCommand() {
setDefaultCommand(new SuckWeakly());
	}

	public DigitalInput get_leftClawLimitSwitch() {
		return _leftClawLimitSwitch;
	}

	public DigitalInput get_rightClawLimitSwitch() {
		return _rightClawLimitSwitch;
	}

	

}
