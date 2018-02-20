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

	
	//private DigitalInput _lowerClawLimitSwitch = new DigitalInput(RobotMap.LOWER_CLAW_LIMIT_SWITCH);
//	private DigitalInput _upperClawLimitSwitch = new DigitalInput(RobotMap.UPPER_CLAW_LIMIT_SWITCH);

	
//	 private DigitalInput _upperClawLimitSwitch = new
//	 DigitalInput(RobotMap.UPPER_CLAW_LIMIT_SWITCH);
//	 private DigitalInput _lowerClawLimitSwitch = new
//	 DigitalInput(RobotMap.LOWER_CLAW_LIMIT_SWITCH);

	private boolean clawDeployed;

	/*
	 * private Encoder _deployEncoder = new Encoder(RobotMap.DEPLOY_ENCODER[0],
	 * RobotMap.DEPLOY_ENCODER[1], true, EncodingType.k4X);
	 */
	
	
	
	private PWMTalonSRX deployMotor = new PWMTalonSRX(RobotMap.DEPLOY_MOTOR);

	public void setDeployMotorPower(double Power) {
		//if(this.isClawDeployed() == false) {
		this.deployMotor.set(Power);
	//	}
//		else {
//			System.out.println("Robot refuses to do as told and damage itself through overdeployment of the claw arm");
//	
//		}
	}
	
	public void setRetractMotorPower(double Power) {
		//if(this.isClawRetracted() == false) {
		this.deployMotor.set(-Power);
//		}
//		else {
//			System.out.println("Robot will not overretract the claw. Please refrain from ordering Robot to hit itself");
//		}
	}

//	public boolean isClawRetracted(){
//		if(this._upperClawLimitSwitch.get() == true) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//	
//	public boolean isClawDeployed(){
//		if(this._lowerClawLimitSwitch.get() == true) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
	
	

 

	

	@Override
	protected void initDefaultCommand() {

	}

	@Override
	public void stop() {
		setDeployMotorPower(0);
	}

}
