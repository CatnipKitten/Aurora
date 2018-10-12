package org.usfirst.frc.team2486.robot.auto.commands;

import org.usfirst.frc.team2486.robot.RobotMap;

public class PeakOutput extends AutoCommand
{
	private float _value;
	public PeakOutput(float value)
	{
		_value = value;
	}
	
	@Override
	public void run()
	{
		RobotMap.Left.configPeakOutputForward(_value, 10);
		RobotMap.Left.configPeakOutputReverse(-_value, 10);		
		RobotMap.Right.configPeakOutputForward(_value, 10);
		RobotMap.Right.configPeakOutputReverse(-_value, 10);
	}
}
