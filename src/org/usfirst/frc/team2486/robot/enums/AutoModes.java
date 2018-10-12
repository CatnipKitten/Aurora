package org.usfirst.frc.team2486.robot.enums;

import java.util.HashMap;
import java.util.Map;

public enum AutoModes
{
//	DISABLED(0),
//	
//	CACTUS_LEFT(1),
//	CACTUS_RIGHT(2),
//	CENTER(3),
//	LEFT_SCALE(6),
//	LEFT_SWITCH(4),
//	RIGHT_SCALE(7),
//	RIGHT_SWITCH(5)
	
	CENTER(0),
	LEFT_SCALE(1),
	RIGHT_SCALE(2),
	DOUBLESWITCH(3)
	;
	
	private final int value;
	private static Map<Integer, AutoModes> map = new HashMap<>();
	
	private AutoModes(int value)
	{
		this.value = value;
	}
	
	static
	{
		for(AutoModes autoMode : AutoModes.values())
		{
			map.put(autoMode.value, autoMode);
		}
	}
	
	public static AutoModes valueOf(int autoMode)
	{
		return (AutoModes) map.get(autoMode);
	}
	
	public int getValue()
	{
		return value;
	}
}
