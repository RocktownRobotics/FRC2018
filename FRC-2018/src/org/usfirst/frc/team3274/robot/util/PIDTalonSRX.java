package org.usfirst.frc.team3274.robot.util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 * A TalonSRX that can be added to a WPILib PID loop.
 * 
 * @author AJ Snarr
 */
public class PIDTalonSRX extends TalonSRX implements PIDOutput {
	public PIDTalonSRX(int deviceNumber) {
		super(deviceNumber);
	}

	@Override
	public void pidWrite(double output) {
		this.set(ControlMode.PercentOutput, output);
	}
}
