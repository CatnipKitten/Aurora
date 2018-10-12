package org.usfirst.frc.team2486.robot.opmodes;

import org.usfirst.frc.team2486.robot.RobotMap;
import org.usfirst.frc.team2486.robot.enums.ArmStates;
import org.usfirst.frc.team2486.robot.enums.ControlButton;
import org.usfirst.frc.team2486.robot.subsystems.Arm;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class TeleOp implements IOpMode
{
	@Override
	public void init()
	{
		Arm.armLow();
		RobotMap.AutoTurn.Controller.disable();
		
		RobotMap.HeadActuator.set(false);
		RobotMap.HeadClamp.set(true);
		RobotMap.AirCompressor.setClosedLoopControl(true);
		
		RobotMap.Left.configPeakOutputForward(1f, 10);
		RobotMap.Left.configPeakOutputReverse(-1f, 10);		
		RobotMap.Right.configPeakOutputForward(1f, 10);
		RobotMap.Right.configPeakOutputReverse(-1f, 10);
	}

	private boolean shifterHeld = false;
	private boolean armHeld     = false;
	private boolean headHeld    = false;
	private boolean clamp       = false;
	
	@Override
	public void loop()
	{
		DriveTrain();
		Shifters();
		Head();
		Arm();
		Climber();
	}
	
	public void DriveTrain()
	{
		RobotMap.Left.set(ControlMode.PercentOutput, -RobotMap.OperatorLeft.getY());
		RobotMap.Right.set(ControlMode.PercentOutput, -RobotMap.OperatorRight.getY());
	}
	
	public void Shifters()
	{
		boolean leftTrigger = RobotMap.OperatorLeft.getRawButton(ControlButton.SHIFTERS.getValue());
		boolean rightTrigger = RobotMap.OperatorRight.getRawButton(ControlButton.SHIFTERS.getValue());

		if (shifterHeld == false)
			if (leftTrigger || rightTrigger)
				RobotMap.Shifters.set(!RobotMap.Shifters.get());

		if (leftTrigger || rightTrigger)
			shifterHeld = true;
		else
			shifterHeld = false;
	}
	
	public void Head()
	{
//		boolean intake1 = RobotMap.OperatorRight.getRawButton(ControlButton.HEADIN.getValue()) ||
//				RobotMap.OperatorLeft.getRawButton(ControlButton.HEADIN.getValue());
//		
//		boolean output1 = RobotMap.OperatorRight.getRawButton(ControlButton.HEADOUT.getValue()) || 
//				RobotMap.OperatorLeft.getRawButton(ControlButton.HEADOUT.getValue());
		
		boolean intake1 = RobotMap.OperatorRight.getRawButton(ControlButton.HEADIN.getValue());
		boolean output1 = RobotMap.OperatorLeft.getRawButton(ControlButton.HEADOUT.getValue());
		
		if (intake1)
		{
			// SHOOTS OUT
			if(Arm.currentState == ArmStates.HIGH)
				RobotMap.HeadIntake.set(ControlMode.PercentOutput, 0.75);
			else
				RobotMap.HeadIntake.set(ControlMode.PercentOutput, 1);
		}
		else
		{
			// TAKES IN
			if (output1)
				RobotMap.HeadIntake.set(ControlMode.PercentOutput, -1);
			else
				RobotMap.HeadIntake.set(ControlMode.PercentOutput, 0.0);
		}
		
		if(headHeld == false)
			if(RobotMap.SystemsOperator.getRawButton(ControlButton.HEADACTUATOR.getValue()))
					RobotMap.HeadActuator.set(!RobotMap.HeadActuator.get());
		
		if(RobotMap.SystemsOperator.getRawButton(ControlButton.HEADACTUATOR.getValue()))
			headHeld = true;
		else
			headHeld = false;
		
		if(clamp == false)
			if(RobotMap.SystemsOperator.getRawButton(ControlButton.HEADCLAMP.getValue()))
				RobotMap.HeadClamp.set(!RobotMap.HeadClamp.get());
		
		if(RobotMap.SystemsOperator.getRawButton(ControlButton.HEADCLAMP.getValue()))
			clamp = true;
		else
			clamp = false;
	}
	
	public void Arm()
	{
		boolean armForward = RobotMap.SystemsOperator.getRawButton(ControlButton.ARMFORWARD.getValue());
		boolean armOff     = RobotMap.SystemsOperator.getRawButton(ControlButton.ARMOFF.getValue());
		boolean armReverse = RobotMap.SystemsOperator.getRawButton(ControlButton.ARMREVERSE.getValue());
		
		if (armHeld == false)
		{
			if (armForward)
				Arm.armHigh();
			else if(armOff)
				Arm.armMid();
			else if(armReverse)
				Arm.armLow();
		}

		if (armForward || armOff || armReverse)
			armHeld = true;
		else
			armHeld = false;
	}
	
	public void Climber()
	{
		if(RobotMap.SystemsOperator.getRawButton(ControlButton.CLIMB.getValue()))
			RobotMap.Climber.set(ControlMode.PercentOutput, 1);
		else if(RobotMap.OperatorLeft.getRawButton(ControlButton.CLIMB_REVERSE.getValue()))
			RobotMap.Climber.set(ControlMode.PercentOutput, -1);
		else
			RobotMap.Climber.set(ControlMode.PercentOutput, 0);
	}
}