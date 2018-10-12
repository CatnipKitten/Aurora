package org.usfirst.frc.team2486.robot.opmodes;

import java.util.HashMap;

import org.usfirst.frc.team2486.robot.RobotMap;
import org.usfirst.frc.team2486.robot.auto.missions.misc.bullet.Straight;
import org.usfirst.frc.team2486.robot.auto.missions.AutoMission;
import org.usfirst.frc.team2486.robot.auto.missions.blue.BlueLeftScale;
import org.usfirst.frc.team2486.robot.auto.missions.blue.BlueRightScale;
import org.usfirst.frc.team2486.robot.auto.missions.misc.bullet.Center;
import org.usfirst.frc.team2486.robot.auto.missions.misc.bullet.DoubleSwitch;
import org.usfirst.frc.team2486.robot.auto.missions.red.RedLeftScale;
import org.usfirst.frc.team2486.robot.auto.missions.red.RedRightScale;

import edu.wpi.first.wpilibj.DriverStation;

public class Autonomous implements IOpMode
{
	@Override
	public void init()
	{
		preInit();
		AutoMission mission = null;
		
		HashMap<Integer, AutoMission> blueMap = new HashMap<>();
		blueMap.put(0, new Center());
		blueMap.put(1, new BlueLeftScale());
		blueMap.put(2, new BlueRightScale());
		blueMap.put(3, new DoubleSwitch());
		
		HashMap<Integer, AutoMission> redMap = new HashMap<>();
		redMap.put(0, new Center());
		redMap.put(1, new RedLeftScale());
		redMap.put(2, new RedRightScale());
		redMap.put(3, new DoubleSwitch());
		
		switch(DriverStation.getInstance().getAlliance())
		{
		case Blue:
			mission = blueMap.get((int)RobotMap.CurrentAutoMode);
			break;
		case Red:
			mission = redMap.get((int)RobotMap.CurrentAutoMode);
			break;
		default:
			break;
		}
		
		if(mission != null)
			mission.execute();
		else
			DriverStation.reportError("AutoMission was null. Stopping!", false);
	}

	@Override
	public void loop()
	{
		
	}
	
	public void preInit()
	{
		RobotMap.Left.setSelectedSensorPosition(0, 0, 10);
		RobotMap.Right.setSelectedSensorPosition(0, 0, 10);
		
		RobotMap.NavX.reset();
		
		RobotMap.HeadActuator.set(false);
		RobotMap.HeadClamp.set(true);
		RobotMap.AirCompressor.setClosedLoopControl(false);
		
		RobotMap.Left.configPeakOutputForward(0.75f, 10);
		RobotMap.Left.configPeakOutputReverse(-0.75f, 10);	
		RobotMap.Left.configClosedloopRamp(0.075, 10);
		
		RobotMap.Right.configPeakOutputForward(0.75f, 10);
		RobotMap.Right.configPeakOutputReverse(-0.75f, 10);
		RobotMap.Right.configClosedloopRamp(0.075, 10);
	}
}