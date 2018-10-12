package org.usfirst.frc.team2486.robot.auto.commands;

import org.usfirst.frc.team2486.robot.RobotMap;

public class Compressor extends AutoCommand
{
	private boolean _enable;
	
	public Compressor(boolean enable)
	{
		_enable = enable;
	}
	
	@Override
	public void run()
	{
		RobotMap.AirCompressor.setClosedLoopControl(_enable);
	}
}
