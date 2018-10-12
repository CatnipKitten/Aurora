package org.usfirst.frc.team2486.robot.auto.commands;

import org.usfirst.frc.team2486.robot.RobotMap;

public class Clamp extends AutoCommand
{
private boolean _state;
	
	/**
	 * 
	 * @param state true is on, false is off
	 */
	public Clamp(boolean state)
	{
		_state = state;
	}
	
	@Override
	public void run()
	{
		RobotMap.HeadClamp.set(_state);
	}
}
