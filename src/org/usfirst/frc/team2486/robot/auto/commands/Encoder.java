package org.usfirst.frc.team2486.robot.auto.commands;

import org.usfirst.frc.team2486.robot.RobotMap;
import org.usfirst.frc.team2486.robot.enums.DistanceUnit;
import org.usfirst.frc.team2486.robot.auto.commands.AutoCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Encoder extends AutoCommand
{
	private static final double _wheelDiameter = 5.975; // Inches. Manufacturer: 5.875
	private static final double _leftTread     = 0.145; // Inches.
	private static final double _rightTread    = 0.285; // Inches.
	
	private static double _sumLeftDiameter  = _wheelDiameter + (2 * _leftTread);
	private static double _sumRightDiameter = _wheelDiameter + (2 * _rightTread);
	
	private double _distanceLeft;
	private double _distanceRight;
	private DistanceUnit _unit;
	
	public Encoder(double distanceLeft, double distanceRight, DistanceUnit unit)
	{
		_distanceLeft  = distanceLeft;
		_distanceRight = distanceRight;
		_unit = unit;
	}
	
	public void run()
	{
		// Convert to rotations.
		double desiredLeft  = _distanceLeft * _unit.getValue();
		double desiredRight = _distanceRight * _unit.getValue();
		//double desiredRotations = _distance;
		
		SmartDashboard.putNumber("Desired Left", desiredLeft);
		SmartDashboard.putNumber("Desired Left", desiredRight);
		
		RobotMap.Left.set(ControlMode.Position,  desiredLeft  + RobotMap.Left.getSelectedSensorPosition(0));
		RobotMap.Right.set(ControlMode.Position, desiredRight + RobotMap.Right.getSelectedSensorPosition(0));
		
		sleep(250);
		while(!OnTarget()) { }
		sleep(1500);
		
		//while(true) { }
	}
	
	public static boolean OnTarget()
	{
		double leftError  = Math.abs(RobotMap.Left.getClosedLoopTarget(0)  - RobotMap.Left.getSelectedSensorPosition(0));
		double rightError = Math.abs(RobotMap.Right.getClosedLoopTarget(0) - RobotMap.Right.getSelectedSensorPosition(0));
		
		return !((rightError > 1000) && (leftError > 1000));
	}
}
