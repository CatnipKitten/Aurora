package org.usfirst.frc.team2486.robot.auto.commands;

import org.usfirst.frc.team2486.robot.RobotMap;
import org.usfirst.frc.team2486.robot.auto.missions.AutoMission;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Drive extends AutoCommand
{
	private double _left;
	private double _right;
	private long _duration;
	/**
	 * Creates an instance of Drive that will store the driving parameters.
	 * Will not set the power to zero after completion!
	 * @param left Percent output to supply to the left motors
	 * @param right Percent output to supply to the right motors
	 * @param duration Time to drive the motors.
	 */
	public Drive(double left, double right, long duration)
	{
		_left = left;
		_right = right;
		_duration = duration;
	}
	
	@Override
	public void run()
	{
		RobotMap.Left.set(ControlMode.PercentOutput, _left);
		RobotMap.Right.set(ControlMode.PercentOutput, _right);
		
		sleep(_duration);
	}
}
