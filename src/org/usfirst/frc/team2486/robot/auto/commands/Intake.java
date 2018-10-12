package org.usfirst.frc.team2486.robot.auto.commands;

import org.usfirst.frc.team2486.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Intake extends AutoCommand
{
	private double _left;
	private double _right;
	private double _intake;
	private long _duration;
	
	/**
	 * 
	 * @param left Left percent output.
	 * @param right Right percent output.
	 * @param intake Intake percent output.
	 * @param duration Time duration.
	 */
	public Intake(double left, double right, double intake, long duration)
	{
		_left = left;
		_right = right;
		_intake = intake;
		_duration = duration;
	}
	
	public void Run()
	{
		RobotMap.Left.set(ControlMode.PercentOutput, _left);
		RobotMap.Right.set(ControlMode.PercentOutput, _right);
		RobotMap.HeadIntake.set(ControlMode.PercentOutput, _intake);
		
		sleep(_duration);
		
		RobotMap.Left.set(ControlMode.PercentOutput, 0);
		RobotMap.Right.set(ControlMode.PercentOutput, 0);
		RobotMap.HeadIntake.set(ControlMode.PercentOutput, 0);
	}
}
