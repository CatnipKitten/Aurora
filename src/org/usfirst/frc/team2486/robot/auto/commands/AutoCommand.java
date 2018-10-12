package org.usfirst.frc.team2486.robot.auto.commands;

public abstract class AutoCommand
{
	public void run()
	{
		
	}
	
	public void sleep(long duration)
	{
		long start = System.currentTimeMillis() + duration;
		while(System.currentTimeMillis() <= start) ;
	}
}
