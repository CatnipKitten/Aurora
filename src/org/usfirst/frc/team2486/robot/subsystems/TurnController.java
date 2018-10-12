package org.usfirst.frc.team2486.robot.subsystems;

import org.usfirst.frc.team2486.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;


/// <summary>
/// Class for allowing the drive train to turn according to a PID loop.
/// Incorporates the gyro.
/// </summary>
public class TurnController implements PIDOutput
{
    /// <summary>
    /// Variable for holding the PID controller.
    /// Allows manual configuration of its parameters.
    /// </summary>
    public PIDController Controller;

    /// <summary>
    /// Create a new instance of TurningPID with the supplied PIDF struct.
    /// </summary>
    /// <param name="values">The PIDF values to use.</param>
    public TurnController(PIDF values) { }

    /// <summary>
    /// Creates a new instance of TurningPID with the supplied PIDF values.
    /// </summary>
    /// <param name="kp">Proportional gain.</param>
    /// <param name="ki">Integral gain.</param>
    /// <param name="kd">Derivative gain.</param>
    /// <param name="kf">Filter gain.</param>
    public TurnController(double kp, double ki, double kd, double kf)
    {
        try
        {
            // Set the NavX to run off of displacement.
            // Means it will return the distance from the start of the PID control.
            RobotMap.NavX.setPIDSourceType(PIDSourceType.kDisplacement);

            // Create a new PID controller with the given PIDF values and NavX.
            Controller = new PIDController(kp, ki, kd, kf, RobotMap.NavX, this);

            // Sets the input range of the controller to be -180 degrees to 180 degrees.
            Controller.setInputRange(-180f, 180f);

            // Sets the peak output to be 45% power on the drive train.
            Controller.setOutputRange(-.75, 75);

            // Allow for a tolerance of 10 when turning.
            Controller.setAbsoluteTolerance(5);
        }
        catch (Exception ex) { DriverStation.reportError(ex.getMessage(), true); }
    }

    /// <summary>
    /// Tells the PID controller where to get data from.
    /// Equivalent of calling <c>RobotMap.NavX.GetAngle()</c>.
    /// </summary>
    /// <returns>Current yaw reading from -180f to 180f.</returns>
    public double GetData()
    {
        // Return the current yaw reading from the NavX.
        return RobotMap.NavX.getAngle();
    }

    /// <summary>
    /// Write to the drive train motors.
    /// The right is set to -value, while the left is value.
    /// </summary>
    /// <param name="value">Speed to write as.</param>
    public void pidWrite(double value)
    {
        RobotMap.Left.set(ControlMode.PercentOutput, value);
        RobotMap.Right.set(ControlMode.PercentOutput, -value);
        // Output the value from the PID controller to the motors.
        // RobotMap.DriveTrain.SetLeftRightMotorOutputs(value, -value);
    }
    
  /// <summary>
  /// Configuration file for PIDF values.
  /// </summary>
  public class PIDF
  {
      /// <summary>
      /// Proportional gain.
      /// Determines the linear relationship of the data.
      /// </summary>
      public double kP;
      /// <summary>
      /// Integral gain.
      /// Determines the integral, or total amount of change since starting.
      /// </summary>
      public double kI;
      /// <summary>
      /// Derivative gain.
      /// Reduces the amount of oscillation by predicting the future.
      /// The derivative is the tangent of a given point in a function.
      /// </summary>
      public double kD;
      /// <summary>
      /// Filter gain.
      /// Adds a constant to the output.
      /// </summary>
      public double kF;
  }
}