package org.usfirst.frc.team2486.robot.auto.missions.red;

import org.usfirst.frc.team2486.robot.auto.commands.Compressor;
import org.usfirst.frc.team2486.robot.auto.commands.CubeIntake;
import org.usfirst.frc.team2486.robot.auto.commands.Encoder;
import org.usfirst.frc.team2486.robot.auto.commands.Gyro;
import org.usfirst.frc.team2486.robot.auto.commands.MoveArm;
import org.usfirst.frc.team2486.robot.auto.commands.PeakOutput;
import org.usfirst.frc.team2486.robot.auto.missions.AutoMission;
import org.usfirst.frc.team2486.robot.enums.ArmStates;
import org.usfirst.frc.team2486.robot.enums.DistanceUnit;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RedLeftScale extends AutoMission
{
	public RedLeftScale()
	{
		super();
		Commands.add(new PeakOutput(0.55f));
		if(FieldData.charAt(1) == 'L')
		{
			SmartDashboard.putBoolean("Test", false);
			Commands.add(new Encoder(-400000, -440000, DistanceUnit.TICKS));
			SmartDashboard.putBoolean("Test", true);
			Commands.add(new MoveArm(ArmStates.HIGH));
			Commands.add(new CubeIntake(true));
			Commands.add(new MoveArm(ArmStates.LOW));
			Commands.add(new Compressor(true));
		}
		else if (FieldData.charAt(1) == 'R')
		{
			Commands.add(new Encoder(-335000, -335000, DistanceUnit.TICKS));
			Commands.add(new PeakOutput(0.75f));
			Commands.add(new Gyro(90));
			Commands.add(new PeakOutput(0.55f));
			Commands.add(new Encoder(-70000, -70000, DistanceUnit.TICKS));
			Commands.add(new Gyro(-45));
		}
	}
}
