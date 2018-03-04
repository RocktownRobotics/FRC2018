package org.usfirst.frc.team3274.robot.subsystems;

import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.util.StoppableSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class ClawArm extends StoppableSubsystem {

	public static final double ENCODER_PULSES_PER_REVOLUTION = 1343;

	private DigitalInput _upperClawLimitSwitch = new DigitalInput(RobotMap.UPPER_CLAW_LIMIT_SWITCH);

	// private Encoder _deployEncoder = new Encoder(RobotMap.DEPLOY_ENCODER[0],
	// RobotMap.DEPLOY_ENCODER[1], true, EncodingType.k4X);

	private PWMTalonSRX _deployMotor = new PWMTalonSRX(RobotMap.DEPLOY_MOTOR);

	public ClawArm() {
		double distancePerPulse; // in feet
		distancePerPulse = (360 /* degrees */) / (ENCODER_PULSES_PER_REVOLUTION);

		// _deployEncoder.setDistancePerPulse(distancePerPulse);

		this.resetEncoder();
	}

	/**
	 * Takes in positive power to raise claw, and negative to lower.
	 * 
	 * @param power
	 *            in range -1 <= p <= 1
	 */
	public void setPower(double power) {
		// if (this.isClawRetracted() == false && power > 0) {
		this._deployMotor.set(power);
		// }
	}

	public boolean isClawRetracted() {
		if (this._upperClawLimitSwitch.get() == true) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns angle in degrees, where 0 is the upper position.
	 * 
	 * @return
	 */
	public double getAngle() {
		// return _deployEncoder.getDistance();
		return 0;
	}

	public void resetEncoder() {
		// _deployEncoder.reset();
	}

	@Override
	protected void initDefaultCommand() {

	}

	@Override
	public void stop() {
		setPower(0);
	}

}
