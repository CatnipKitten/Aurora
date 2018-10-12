package org.usfirst.frc.team2486.robot.auto.commands;

import org.usfirst.frc.team2486.robot.RobotMap;

public class MoveHead extends AutoCommand
{
	private boolean _position;
	/**
	 * 
	 * @param position true if down, false if up
	 */
	public MoveHead(boolean position)
	{
		_position = position;
	}
	
	@Override
	public void run()
	{
		RobotMap.HeadActuator.set(_position);
	}
}
