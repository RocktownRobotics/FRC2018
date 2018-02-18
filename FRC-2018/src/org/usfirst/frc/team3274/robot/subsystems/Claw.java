package org.usfirst.frc.team3274.robot.subsystems;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.util.TalonSRXGroup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {

	private DigitalInput _leftClawLimitSwitch = new DigitalInput(RobotMap.LEFT_CLAW_EYE_LIMIT_SWITCH);
	private DigitalInput _rightClawLimitSwitch = new DigitalInput(RobotMap.RIGHT_CLAW_EYE_LIMIT_SWITCH);

	private DigitalInput _upperClawLimitSwitch = new DigitalInput(RobotMap.UPPER_CLAW_LIMIT_SWITCH);
	private DigitalInput _lowerClawLimitSwitch = new DigitalInput(RobotMap.LOWER_CLAW_LIMIT_SWITCH);

	private boolean clawClosed;
	private boolean clawDeployed;

	public static final int ENCODER_PULSES_PER_REVOLUTION = 1343;
	/*
	 * private Encoder _deployEncoder = new Encoder(RobotMap.DEPLOY_ENCODER[0],
	 * RobotMap.DEPLOY_ENCODER[1], true, EncodingType.k4X);
	 */
	private PWMTalonSRX leftClaw = new PWMTalonSRX(RobotMap.CLAW_MOTOR_LEFT);
	private PWMTalonSRX rightClaw = new PWMTalonSRX(RobotMap.CLAW_MOTOR_RIGHT);
	private Solenoid clawPistons = new Solenoid(RobotMap.CLAW_PISTON);
	private PWMTalonSRX deployMotor = new PWMTalonSRX(RobotMap.DEPLOY_MOTOR);

	public Claw() {
		double distancePerPulse; // in degrees
		distancePerPulse = (360. /* degrees */) / (ENCODER_PULSES_PER_REVOLUTION);
		/*
		 * _deployEncoder.setDistancePerPulse(distancePerPulse);
		 */
		this.resetDeployEncoder();
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
	public double getDeployAngle() {
		// return this.getDeployRotations() * 360 / this.ENCODER_PULSES_PER_REVOLUTION;
		// return this._deployEncoder.getDistance();
		return 0;
	}

	public void resetDeployEncoder() {
		// this._deployEncoder.reset();
	}

	public boolean isClawLoaded() {

		if (_leftClawLimitSwitch.get() || _rightClawLimitSwitch.get()) {
			return false;
		} else

		{
			return true;
		}
	}

	public void deploy(double deployPower) {
		if (!_lowerClawLimitSwitch.get()) {
			this.deployMotor.set(deployPower);
		} else {
			this.deployMotor.set(0);
		}

	}

	public void retract(double retractPower) {
		if (!_upperClawLimitSwitch.get()) {
			this.deployMotor.set(-retractPower);
		} else {
			this.deployMotor.set(0);
		}

	}
	// DoubleSolenoid gearShifter = new DoubleSolenoid(RobotMap.shifterForward,
	// RobotMap.shifterReverse);
	//
	// DoubleSolenoid.Value off = DoubleSolenoid.Value.kOff;
	// // May need to switch lowGear and HighGear values (kForward/kReverse)
	// DoubleSolenoid.Value lowGear = DoubleSolenoid.Value.kForward;
	// DoubleSolenoid.Value highGear = DoubleSolenoid.Value.kReverse;

	public Solenoid getclawPiston() {
		return clawPistons;
	}

	public boolean isClawClosed() {
		return clawClosed;
	}

	public boolean isClawDeployed() {
		return clawDeployed;
	}

	public void OpenClaw() {
		clawPistons.set(false);
		this.clawClosed = true;
	}

	public void CloseClaw() {
		clawPistons.set(true);
		this.clawClosed = false;
	}

	public void stop() {
		clawPistons.set(false);

		// gearShifter.set(off);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return -1 for low and 1 for high
	 */

}
