package org.usfirst.frc.team2486.robot.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Place the integer equivalent of the controller buttons here.
 */
public enum ControlButton
{
	SHIFTERS(2),
	
	HEADIN(1),
	HEADOUT(1),
	HEADCLAMP(3),
	HEADACTUATOR(7),
	
	ARMFORWARD(4),
	ARMOFF(5),
	ARMREVERSE(6),
	
	CLIMB(10),
	CLIMB_REVERSE(6),
	;
	
	private final int value;
	private static Map<Object, Object> map = new HashMap<>();
	
	private ControlButton(int value)
	{
		this.value = value;
	}
	
	static
	{
		for(ControlButton controlButton : ControlButton.values())
		{
			map.put(controlButton.value, controlButton);
		}
	}
	
	public static ControlButton valueOf(int controlButton)
	{
		return (ControlButton) map.get(controlButton);
	}
	
	public int getValue()
	{
		return value;
	}
}
