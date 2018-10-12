package org.usfirst.frc.team2486.robot.auto.missions.misc.bullet;

import org.usfirst.frc.team2486.robot.auto.commands.CubeIntake;
import org.usfirst.frc.team2486.robot.auto.commands.Drive;
import org.usfirst.frc.team2486.robot.auto.missions.AutoMission;

public class Center extends AutoMission
{
	public Center()
	{
		// Drive to the switch
		if(FieldData.charAt(0) == 'L')
		{
			Commands.add(new Drive(0.25, 0.85, 1050)); // 1000 ms @ Ventura
			//CommandList.add(new RawSpeed(0.5,  0.5,  300));
			Commands.add(new Drive(0.5, 0.25,  1000));
		}
		else
		{
			Commands.add(new Drive(0.85, 0.25, 1000));
			//CommandList.add(new RawSpeed(0.5,  0.5,  300));
			Commands.add(new Drive(0.25, 0.5,  1000));
		}
				
		// Pushes into the switch and ejects the cube
		Commands.add(new Drive(0.5, 0.5, 1000));
		//CommandList.add(new RawSpeed(0, 0, 250));
		Commands.add(new CubeIntake(true));
	}
}
