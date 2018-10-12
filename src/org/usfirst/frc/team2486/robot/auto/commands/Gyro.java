package org.usfirst.frc.team2486.robot.auto.commands;

import org.usfirst.frc.team2486.robot.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyro extends AutoCommand
{
	private double _angle;
	
	public Gyro (double Angle)
	{
		this._angle = Angle;
	}
	
	@Override
	public void run()
	{
         // Set the controller setpoint to the desired angle.
         RobotMap.AutoTurn.Controller.setSetpoint(this._angle);
         // Enables the controller.
         RobotMap.AutoTurn.Controller.enable();
         // Debug.
         SmartDashboard.putBoolean("On Target?", OnTarget());
         // Keep the robot in a loop until it is on target.
         while (OnTarget() == false) ;
         SmartDashboard.putBoolean("On Target?", OnTarget());
         sleep(1000);
         // Disables the controller since we are on target.
         RobotMap.AutoTurn.Controller.disable();
	}
	
	public static boolean OnTarget()
	{
		double error = Math.abs(RobotMap.AutoTurn.Controller.getSetpoint() - RobotMap.AutoTurn.GetData());
		return !(error > 0.25);
	}
}
