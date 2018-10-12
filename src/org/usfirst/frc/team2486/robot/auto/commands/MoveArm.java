package org.usfirst.frc.team2486.robot.auto.commands;

import org.usfirst.frc.team2486.robot.enums.ArmStates;
import org.usfirst.frc.team2486.robot.subsystems.Arm;

public class MoveArm extends AutoCommand
{
	private ArmStates _state;
	
	public MoveArm(ArmStates state)
	{
		_state = state;
	}
	
	@Override
	public void run()
	{
		Arm.armPos(_state);
		//Arm.armHigh();
		sleep(3000);
	}
}
