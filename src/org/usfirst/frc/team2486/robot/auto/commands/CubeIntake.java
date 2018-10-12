package org.usfirst.frc.team2486.robot.auto.commands;

import org.usfirst.frc.team2486.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class CubeIntake extends AutoCommand
{
	private boolean _fire;
	public CubeIntake(boolean fire)
	{
		_fire = fire;
	}
	
	@Override
	public void run() {
		if(_fire)
		{
			RobotMap.HeadIntake.set(ControlMode.PercentOutput, 0.75);
		}
		else
		{
			RobotMap.HeadIntake.set(ControlMode.PercentOutput, -1);
		}
		
		sleep(500);
		
		RobotMap.HeadIntake.set(ControlMode.PercentOutput, 0);
	}
}
