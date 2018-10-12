package org.usfirst.frc.team2486.robot;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

import org.reflections.Reflections;
import org.usfirst.frc.team2486.robot.auto.missions.Auto;
import org.usfirst.frc.team2486.robot.auto.missions.AutoMission;
import org.usfirst.frc.team2486.robot.auto.missions.red.RedLeftScale;
import org.usfirst.frc.team2486.robot.enums.PIDValues;
import org.usfirst.frc.team2486.robot.enums.RobotIDs;
import org.usfirst.frc.team2486.robot.subsystems.Arm;
import org.usfirst.frc.team2486.robot.subsystems.TurnController;
import org.usfirst.frc.team2486.robot.subsystems.TurnController.PIDF;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.google.gson.internal.reflect.ReflectionAccessor;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMap
{
	/**
	 * Left joystick of the primary driver.
	 * Handles the drive train subsystem.
	 */
	public static Joystick OperatorLeft;
	/**
	 * Right joystick of the primary driver.
	 * Handles the drive train subsystem.
	 */
	public static Joystick OperatorRight;
	/**
	 * Secondary operator's custom gamepad
	 */
	public static Joystick SystemsOperator;
	
	/**
	 *  Primary Talon SRX on the left.
	 *  Has the encoder sensor attached.
	 */
	public static TalonSRX Left;
	/**
	 *  Secondary Talon SRX on the left.
	 *  Follows the primary left's settings.
	 */
	public static TalonSRX LeftSlave;
	/**
	 *  Primary Talon SRX on the right.
	 *  Has the encoder sensor attached.
	 */
	public static TalonSRX Right;
	/**
	 *  Secondary Talon SRX on the right.
	 *  Follows the primary right's settings.
	 */
	public static TalonSRX RightSlave;
	
	/**
	 *  Primary intake motor for the power cubes.
	 */
	public static TalonSRX HeadIntake;
	/**
	 *  Slave intake motor for the power cubes.
	 *  Direction is reversed from its master Talon.
	 */
	public static TalonSRX HeadIntakeSlave;
	
	public static TalonSRX Climber;
	public static TalonSRX ClimberSlave;
	
	/**
	 *  Onboard compressor to build air pressure.
	 */
	public static Compressor AirCompressor;
	/**
	 *  Pneumatic shifters on the drivetrain.
	 */
	public static Solenoid Shifters;
	/**
	 *  Pneumatic shifters on the head.
	 */
	public static Solenoid HeadClamp;
	/**
	 *  Second manifold for piston head.
	 */
	public static Solenoid HeadActuator;
	/**
	 *  Three-state piston to raise the arm.
	 */
	public static Solenoid ArmPistonPrimary;
	/**
	 *  Second piston stage.
	 */
	public static Solenoid ArmPistonSecondary;
	
	/**
	 * The most beautiful device in the world.
	 */
	public static AHRS NavX;
	
	public static TurnController AutoTurn;
	
	public static byte CurrentAutoMode;
	
	public static final PIDValues currentPID = PIDValues.COMPBOT_DRIVETRAIN_STADIUM;
	public static final PIDValues gyroPID    = PIDValues.GYRO_STADIUM;
	
	public static void initialize()
	{
		Left  = new TalonSRX(RobotIDs.LEFT.getValue());
		Right = new TalonSRX(RobotIDs.RIGHT.getValue());
		LeftSlave  = new TalonSRX(RobotIDs.LEFTSLAVE.getValue());
		RightSlave = new TalonSRX(RobotIDs.RIGHTSLAVE.getValue());
		
		Climber           = new TalonSRX(RobotIDs.CLIMBER.getValue());
		ClimberSlave      = new TalonSRX(RobotIDs.CLIMBERSLAVE.getValue());
		ClimberSlave.setInverted(true);
		ClimberSlave.set(ControlMode.Follower, Climber.getDeviceID());
		
		HeadIntake        = new TalonSRX(RobotIDs.HEADINTAKE.getValue());
		HeadIntakeSlave   = new TalonSRX(RobotIDs.HEADINTAKESLAVE.getValue());
		HeadIntakeSlave.set(ControlMode.Follower, HeadIntake.getDeviceID());

		NavX = new AHRS(Port.kMXP);
		AutoTurn = new TurnController(gyroPID.getP(), gyroPID.getI(), gyroPID.getF(), gyroPID.getF());
		
		Left.setInverted(true);
		LeftSlave.setInverted(true);
		
		LeftSlave.set(ControlMode.Follower, Left.getDeviceID());
		RightSlave.set(ControlMode.Follower, Right.getDeviceID());
		
		Left.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 1, 10);
		Left.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		Left.setSensorPhase(false); // COMP BOT
		//Left.setSensorPhase(true);    // PRACTICE
		Left.configClosedloopRamp(0, 10);
		Left.configOpenloopRamp(0, 10);
		
		Left.configNominalOutputForward(0.0f, 10);
		Left.configNominalOutputReverse(0.0f, 10);
		Left.configPeakOutputForward(1f, 10);
		Left.configPeakOutputReverse(-1f, 10);
		
		Left.configAllowableClosedloopError(5, 0, 10);
		Left.config_kP(0, currentPID.getP(), 10);
		Left.config_kI(0, currentPID.getI(), 10);
		Left.config_kD(0, currentPID.getD(), 10);
		Left.config_kF(0, currentPID.getF(), 10);
		
		/*
		 * lets grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */
		int absolutePositionLeft = Left.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		absolutePositionLeft &= 0xFFF;
		/* set the quadrature (relative) sensor to match absolute */
		Left.setSelectedSensorPosition(0, 0, 10);
		
		Right.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 1, 10);
		Right.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		Right.setSensorPhase(false); // COMP BOT
		//Right.setSensorPhase(true); // PRACTICE
		Right.configClosedloopRamp(0, 10);
		Right.configOpenloopRamp(0, 10);
		
		Right.configNominalOutputForward(0.0f, 10);
		Right.configNominalOutputReverse(0.0f, 10);
		Right.configPeakOutputForward(1f, 10);
		Right.configPeakOutputReverse(-1f, 10);
		
		Right.configAllowableClosedloopError(5, 0, 10);
		Right.config_kP(0, currentPID.getP(), 10);
		Right.config_kI(0, currentPID.getI(), 10);
		Right.config_kD(0, currentPID.getD(), 10);
		Right.config_kF(0, currentPID.getF(), 10);
		
		int absolutePositionRight = Right.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		absolutePositionRight &= 0xFFF;
		/* set the quadrature (relative) sensor to match absolute */
		Right.setSelectedSensorPosition(0, 0, 10);
		
		OperatorLeft  = new Joystick(RobotIDs.PRIMARYLEFT.getValue());
		OperatorRight = new Joystick(RobotIDs.PRIMARYRIGHT.getValue());
		SystemsOperator = new Joystick(RobotIDs.SECONDARYOPERATOR.getValue());
		
		AirCompressor = new Compressor(RobotIDs.PCM.getValue());
		Shifters      = new Solenoid(RobotIDs.PCM.getValue(), RobotIDs.SHIFTERS.getValue());
		HeadClamp     = new Solenoid(RobotIDs.PCM.getValue(), RobotIDs.HEADCLAMP.getValue());
		
		ArmPistonPrimary    = new Solenoid(RobotIDs.PCM.getValue(), RobotIDs.ARMPRIMARY.getValue());
		HeadActuator        = new Solenoid(RobotIDs.PCM.getValue(), RobotIDs.HEADACTUATOR.getValue());
		ArmPistonSecondary  = new Solenoid(RobotIDs.PCM.getValue(), RobotIDs.ARMSECONDARY.getValue());
		
		AirCompressor.setClosedLoopControl(true);
		
		NavX.reset();
		
		CameraServer.getInstance().startAutomaticCapture();
		
//		Reflections reflections = new Reflections(Left.getClass().getPackage().getName());
//		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Auto.class);
//		
//		for(Class<?> var : classes)
//		{
//			if(var.isAssignableFrom(AutoMission.class))
//			{
//				try
//				{
//					AutoMap.put(var.get, var.asSubclass(AutoMission.class). newInstance());
//				}
//				catch(InstantiationException e)
//				{
//					e.printStackTrace();
//				}
//				catch(IllegalAccessException e)
//				{
//					e.printStackTrace();
//				}
//			}
//		}
		
		class PeriodicRunnable implements java.lang.Runnable
		{
			public void run()
			{
				SmartDashboard.putNumber("Left Speed",  Left.getSelectedSensorVelocity(0));
				SmartDashboard.putNumber("Right Speed", Right.getSelectedSensorVelocity(0));
				
				SmartDashboard.putNumber("Left Position",  Left.getSelectedSensorPosition(0));
				SmartDashboard.putNumber("Right Position", Right.getSelectedSensorPosition(0));
				
				SmartDashboard.putBoolean("Clamp", HeadClamp.get());
				
				SmartDashboard.putNumber("Heading", NavX.getAngle());
			}
		}
		
		Notifier notifier = new Notifier(new PeriodicRunnable());
		notifier.startPeriodic(0.1);
	}
}
