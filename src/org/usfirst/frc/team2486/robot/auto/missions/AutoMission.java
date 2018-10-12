package org.usfirst.frc.team2486.robot.auto.missions;

import java.lang.annotation.Target;
import java.util.ArrayList;

import org.usfirst.frc.team2486.robot.auto.commands.AutoCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoMission
{
	public ArrayList<AutoCommand> Commands;
	public Alliance AllianceColor;
	public String FieldData = null;
	
	public AutoMission()
	{
		Commands = new ArrayList<>();
		FieldData = DriverStation.getInstance().getGameSpecificMessage();
		AllianceColor = DriverStation.getInstance().getAlliance();
	}
	
	public void execute()
	{
		if(Commands.size() != 0)
			for(AutoCommand command : Commands)
				command.run();
	}
}
