package org.usfirst.frc.team2486.robot.auto.planner;

import java.awt.Color;

public class Generator
{	
	static FalconLinePlot plot;
	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();
		
		// Creates the graph.
		plot = new FalconLinePlot(new double[][] {{0.0, 0.0}});
		plot.xGridOn();
		plot.setXLabel("X (feet)");
		plot.yGridOn();
		plot.setYLabel("Y (feet)");
		plot.setTitle("Top-Down View of Field (30ft x 27ft)");
		
		// Shows the dimensions of the field.
		plot.setXTic(0, 27, 1); // Field height
		plot.setYTic(0, 30, 1); // Field width (driver station wall)
		
		// Field markers
		double[][] autoLine = new double[][] { {10,0}, {10, 30} };
		plot.addData(autoLine, Color.black);
		
		// Waypoints
		double[][] waypoints = new double[][]{
			{0, 29.69/12},
			{2, 15},
			{5, 15}
		};
		
		final FalconPathPlanner path = new FalconPathPlanner(waypoints);
		path.calculate(5, 0.01, 3);
		
		plot.addData(path.nodeOnlyPath, Color.blue, Color.green);
		plot.addData(path.smoothPath, Color.red, Color.blue);
		plot.addData(path.leftPath, Color.magenta);
		plot.addData(path.rightPath, Color.magenta);
	}
}