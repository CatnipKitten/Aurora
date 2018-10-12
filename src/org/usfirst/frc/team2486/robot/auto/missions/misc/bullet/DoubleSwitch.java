package org.usfirst.frc.team2486.robot.auto.missions.misc.bullet;

import org.usfirst.frc.team2486.robot.auto.commands.Clamp;
import org.usfirst.frc.team2486.robot.auto.commands.CubeIntake;
import org.usfirst.frc.team2486.robot.auto.commands.Drive;
import org.usfirst.frc.team2486.robot.auto.commands.Intake;
import org.usfirst.frc.team2486.robot.auto.commands.MoveHead;
import org.usfirst.frc.team2486.robot.auto.missions.AutoMission;

public class DoubleSwitch extends AutoMission
{
	public DoubleSwitch()
	{
		super();
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
		
		// Returns to the center line
		if(FieldData.charAt(0) == 'L')
		{
			Commands.add(new Drive(-0.55,  -0.25, 1000));
			//CommandList.add(new RawSpeed(0.5,  0.5,  300));
			Commands.add(new Drive(-0.25, -0.5,  1000));
		}
		else
		{
			Commands.add(new Drive(-0.25, -0.5,  1250));
			//CommandList.add(new RawSpeed(0.5,  0.5,  300));
			Commands.add(new Drive(-0.5, -0.25,  1000));
		}
		
		Commands.add(new Drive(-0.5, -0.5, 500));
		Commands.add(new MoveHead(true));
		Commands.add(new Clamp(false));
		
		// Pick up another cube from the power cube zone
		Commands.add(new Intake(0.4, 0.4, -1, 1000));
		Commands.add(new Intake(0.2, 0.2, -1, 2000));
		Commands.add(new Clamp(true));
		Commands.add(new Drive(-0.5, -0.5, 1500));
		Commands.add(new MoveHead(false));
		
		// Place another cube
		if(FieldData.charAt(0) == 'L')
		{
			Commands.add(new Drive(0.25, 0.75, 1100)); // 1000 ms @ Ventura
			//CommandList.add(new RawSpeed(0.5,  0.5,  300));
			Commands.add(new Drive(0.5, 0.25,  1000));
		}
		else
		{
			Commands.add(new Drive(0.75, 0.25, 1000));
			//CommandList.add(new RawSpeed(0.5,  0.5,  300));
			Commands.add(new Drive(0.25, 0.5,  1000));
		}
		
		// Pushes into the switch and ejects the cube
		Commands.add(new Drive(0.5, 0.5, 1000));
		//CommandList.add(new RawSpeed(0, 0, 250));
		Commands.add(new CubeIntake(true));
		
//		if(Plates.getRaw().charAt(0) == 'L')
//			CommandList.add(new RawSpeed(-0.75, -0.5, 1000));
//		else
//			CommandList.add(new RawSpeed(-0.5, -0.75, 1000));
		
		Commands.add(new Clamp(false));
		//CommandList.add(new MoveHead(true));
		//CommandList.add(new DriveIntake(0.5, 0.5, -1, 500));
	}
}