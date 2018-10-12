package org.usfirst.frc.team2486.robot.opmodes;

import org.usfirst.frc.team2486.robot.RobotMap;
import org.usfirst.frc.team2486.robot.enums.AutoModes;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Disabled implements IOpMode
{
	@Override
	public void init()
	{
		
	}

	@Override
	public void loop()
	{
		byte controller = 0;
		
		controller += RobotMap.OperatorLeft.getRawAxis(2)  > 0.5 ? 2 : 0;
		controller += RobotMap.OperatorRight.getRawAxis(2) > 0.5 ? 1 : 0;
		
		RobotMap.CurrentAutoMode = controller;
		SmartDashboard.putString("Current Auto Mode", DriverStation.getInstance().getAlliance().toString() 
				+ "_" 
				+ AutoModes.valueOf(RobotMap.CurrentAutoMode));
	}
}