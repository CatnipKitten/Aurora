package org.usfirst.frc.team2486.robot.auto.commands;

import org.usfirst.frc.team2486.robot.RobotMap;
import org.usfirst.frc.team2486.robot.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * Drives the robot along a path using the NavX to ensure a constant heading.
 * Not tested!
 * @author Zach
 */
public class HeadingDrive extends AutoCommand
{
	public double LeftTicks  = 0;
	public double RightTicks = 0;
	public int AbsoluteError = 0;
	
	@Override
	public void run()
	{
		double initHeading = RobotMap.NavX.getAngle();
		RobotMap.Left.set(ControlMode.Position, LeftTicks + RobotMap.Left.getSelectedSensorPosition(0));
		RobotMap.Right.set(ControlMode.Position, RightTicks + RobotMap.Right.getSelectedSensorPosition(0));
		
		while(!DriveTrain.OnTarget(AbsoluteError))
		{
			if(initHeading - RobotMap.NavX.getAngle() < 0)
			{
				double leftMultiplier = Math.pow(initHeading - RobotMap.NavX.getAngle() / 180, 2);
				RobotMap.Left.configNominalOutputForward(+0.75f + leftMultiplier, 10);
				RobotMap.Left.configNominalOutputReverse(-0.75f - leftMultiplier, 10);
				
				RobotMap.Right.configNominalOutputForward(+0.75f, 10);
				RobotMap.Right.configNominalOutputReverse(-0.75f, 10);
			}
			
			else
			{
				double rightMultiplier = Math.pow(initHeading - RobotMap.NavX.getAngle() / 180, 2);
				RobotMap.Right.configNominalOutputForward(+0.75f + rightMultiplier, 10);
				RobotMap.Right.configNominalOutputReverse(-0.75f - rightMultiplier, 10);
				
				RobotMap.Left.configNominalOutputForward(+0.75f, 10);
				RobotMap.Left.configNominalOutputReverse(-0.75f, 10);
			}
		}
	}
}
