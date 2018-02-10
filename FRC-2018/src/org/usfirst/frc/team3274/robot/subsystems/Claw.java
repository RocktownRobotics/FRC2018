package org.usfirst.frc.team3274.robot.subsystems;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.util.TalonSRXGroup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {
	private Solenoid reverseShifter = new Solenoid(RobotMap.SHIFTER_REVERSE);
	private Solenoid forwardShifter = new Solenoid(RobotMap.SHIFTER_FORWARD);

	private DigitalInput _leftClawLimitSwitch = new DigitalInput(RobotMap.LEFT_CLAW_LIMIT_SWITCH);
	private DigitalInput _rightClawLimitSwitch = new DigitalInput(RobotMap.RIGHT_CLAW_LIMIT_SWITCH);

	private boolean clawClosed;
	private boolean clawDeployed;

	public static final int ENCODER_PULSES_PER_REVOLUTION = 1343;

	private Encoder _deployEncoder = new Encoder(RobotMap.DEPLOY_ENCODER[0], RobotMap.DEPLOY_ENCODER[1], true,
			EncodingType.k4X);

	private WPI_TalonSRX leftClaw = new WPI_TalonSRX(RobotMap.CLAW_MOTOR_LEFT);
	private WPI_TalonSRX rightClaw = new WPI_TalonSRX(RobotMap.CLAW_MOTOR_RIGHT);
	private Solenoid clawPistons = new Solenoid(RobotMap.CLAW_PISTON);
	private WPI_TalonSRX DeployMotor = new WPI_TalonSRX(RobotMap.DEPLOY_MOTOR);

	/**
	 * @param motorSpeed
	 *            the motor power at which the cube should be moved. Between 0 and
	 *            1, the negatives, if any, will be handled in the eject code.
	 * 
	 * @param thrustTime
	 *            the time to run the ejection motors, in seconds
	 */

	public void setCubeManipulatorMotors(double motorSpeed) {
		if (Robot.kClaw.isClawClosed() == true) {
			this.leftClaw.set(motorSpeed);
			this.rightClaw.set(motorSpeed);

		}

		else {
			System.out.println("Ejection failed due to open claw");
		}
	}

	public int getDeployRotations() {
		return this._deployEncoder.getRaw();
	}

	public int getDeployAngle() {
		return this.getDeployRotations() * 360 / this.ENCODER_PULSES_PER_REVOLUTION;
	}

	public boolean isClawLoaded() {
		if (_leftClawLimitSwitch.get() && _rightClawLimitSwitch.get()) {
			return false;
		} else

		{
			return true;
		}
	}

	public void resetDeploy() {
		this._deployEncoder.reset();
	}

	public void deploy(double deployPower) {

		this.DeployMotor.set(deployPower);

	}

	public void retract(double retractPower) {

		this.DeployMotor.set(-retractPower);

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

	public Claw() {
		// LiveWindow.addActuator("DrivePnumatics", "GearShifter", gearShifter);
	}

	public void OpenClaw() {
		clawPistons.set(false);
	}

	public void CloseClaw() {
		clawPistons.set(true);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void stop() {
		clawPistons.set(false);

		// gearShifter.set(off);
	}

	/**
	 * 
	 * @return -1 for low and 1 for high
	 */

}
