package org.usfirst.frc.team3274.robot.subsystems;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.util.StoppableSubsystem;
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

public class ClawArm extends StoppableSubsystem {

	
//	 private DigitalInput _upperClawLimitSwitch = new
//	 DigitalInput(RobotMap.UPPER_CLAW_LIMIT_SWITCH);
//	 private DigitalInput _lowerClawLimitSwitch = new
//	 DigitalInput(RobotMap.LOWER_CLAW_LIMIT_SWITCH);

	private boolean clawDeployed;

	public static final int ENCODER_PULSES_PER_REVOLUTION = 1343;
	/*
	 * private Encoder _deployEncoder = new Encoder(RobotMap.DEPLOY_ENCODER[0],
	 * RobotMap.DEPLOY_ENCODER[1], true, EncodingType.k4X);
	 */
	
	private PWMTalonSRX deployMotor = new PWMTalonSRX(RobotMap.DEPLOY_MOTOR);

	public void setDeployMotor(double Power) {
		this.deployMotor.set(Power);
	}

//	public boolean isClawRetracted{
//		if(this._upperClawLimitSwitch.get() = 1 && this._lowerClawLimitSwitch.get() == 1) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
	
	/**
	 * @param motorSpeed
	 *            the motor power at which the cube should be moved. Between 0 and
	 *            1, the negatives, if any, will be handled in the eject code.
	 * 
	 * @param thrustTime
	 *            the time to run the ejection motors, in seconds
	 */

	// public int getDeployRotations() {
	// return this._deployEncoder.getRaw();
	// }

	public boolean isClawDeployed() {
		return clawDeployed;
	}

	@Override
	protected void initDefaultCommand() {

	}

	@Override
	public void stop() {
		setDeployMotor(0);
	}

}
