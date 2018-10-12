package org.usfirst.frc.team2486.robot;

import java.awt.Color;
import java.awt.GraphicsEnvironment;

import org.usfirst.frc.team2486.robot.auto.planner.FalconLinePlot;
import org.usfirst.frc.team2486.robot.opmodes.Autonomous;
import org.usfirst.frc.team2486.robot.opmodes.Disabled;
import org.usfirst.frc.team2486.robot.opmodes.IOpMode;
import org.usfirst.frc.team2486.robot.opmodes.TeleOp;
import org.usfirst.frc.team2486.robot.opmodes.Test;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot
{
	IOpMode opMode = null;
	
	@Override
	public void robotInit()
	{
		RobotMap.initialize();
	}

	@Override
	public void autonomousInit()
	{
		opMode = new Autonomous();
		opMode.init();
	}

	@Override
	public void autonomousPeriodic()
	{
		opMode.loop();
	}
	
	@Override
	public void teleopInit()
	{
		opMode = new TeleOp();
		opMode.init();
	}

	@Override
	public void teleopPeriodic()
	{
		opMode.loop();
	}
	
	@Override
	public void testInit()
	{
		opMode = new Test();
		opMode.init();
	}

	@Override
	public void testPeriodic()
	{
		opMode.loop();
	}
	
	@Override
	public void disabledInit()
	{
		opMode = new Disabled();
		opMode.init();
	}
	
	@Override
	public void disabledPeriodic()
	{
		opMode.loop();
	}
}