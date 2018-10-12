package org.usfirst.frc.team2486.robot.subsystems;

import org.usfirst.frc.team2486.robot.RobotMap;

public class DriveTrain
{
	public static boolean OnTarget(int absoluteError)
	{
		double leftError  = Math.abs(RobotMap.Left.getClosedLoopTarget(0)  - RobotMap.Left.getSelectedSensorPosition(0));
		double rightError = Math.abs(RobotMap.Right.getClosedLoopTarget(0) - RobotMap.Right.getSelectedSensorPosition(0));
		
		return !((rightError > absoluteError) && (leftError > absoluteError));
	}
}
