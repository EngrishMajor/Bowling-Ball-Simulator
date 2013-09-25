package balltrajectory;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


/**
 * @author Ken Tian, ytian13@illinois.edu
 * This is a Ball class used for the calculations of a ball trajectory.
 */
public class Ball 
{
	private double radius; //radius of the ball
	private double v0x, v0y; //initial coordinates of the velocity
	private double w0x, w0y; //initial coordinates of the angular velocity
	private double mu; //coefficient of kinetic friction
	private double g; //acceleration of gravity, not sure if it needs to be constant
	
	private double v0xcp, v0ycp;
	private double c;
	private double v0xrot, v0yrot;
	private double xrot, yrot;
	private double theta;
	
	private double t;
	private double x, y;
	private double v0cp;
	private double tslide;
	
	private double v0xInit, v0yInit;
	
	private final int DIAMETER = 20;
	
	private ArrayList<Integer> listXPoints;
	private ArrayList<Integer> listYPoints;
	
	/**
	 * Default constructor. Initializes a ball of size 1 that isn't moving.
	 */
	public Ball()
	{
		radius = 1;
		v0x = 0;
		v0y = 0;
		w0x = 0;
		w0y = 0;
		mu = 0;
		g = 9.8;
		v0xcp = 0;
		v0ycp = 0;
		c = 0;
		v0xrot = 0;
		v0yrot = 0;
		xrot = 0;
		yrot = 0;
		theta = 0;
		v0cp = 0;
		tslide = 0;
		listXPoints = new ArrayList<Integer>();
		listYPoints = new ArrayList<Integer>();
		v0xInit = 0;
		v0yInit = 0;
	}
	
	public void setRadius(double radius)
	{
		this.radius = radius;
	}
	
	public void setV0x(double v0x)
	{
		this.v0x = v0x;
	}
	
	public void setV0y(double v0y)
	{
		this.v0y = v0y;
	}
	
	public void setw0x(double w0x)
	{
		this.w0x = w0x;
	}
	
	public void setw0y(double w0y)
	{
		this.w0y = w0y;
	}
	
	public void setMu(double mu)
	{
		this.mu = mu;
	}
	
	public void setG(double g)
	{
		this.g = g;
	}
	
	public void setTime(double t)
	{
		this.t = t;
	}
	
	/**
	 * Calculates v0xcp.
	 */
	private void calcV0xcp()
	{
		v0xcp = v0x - (radius * w0y);
	}
	
	/**
	 * Calculates v0ycp.
	 */
	private void calcV0ycp()
	{
		v0ycp = v0y + (radius * w0x);
	}
	
	/**
	 * Calculates c.
	 */
	private void calcC()
	{
		c = v0xcp / v0ycp;
	}
	
	/**
	 * Calculates v0xrot.
	 */
	private void calcV0xrot()
	{
		v0xrot = (v0x - c * v0y) / Math.sqrt(1 + Math.pow(c, 2));
	}
	
	private void calcV0yrot()
	{
		v0yrot = ((c * v0x + v0y) / Math.sqrt(1+ Math.pow(c, 2)));
	}
	
	private void calcXrot()
	{
		xrot = v0xrot * t;
	}
	
	private void calcYrot()
	{
		yrot = (v0yrot * t) - (mu * g * Math.pow(t, 2) / 2);
	}
	
	private void calcTheta()
	{
		theta = Math.atan(-c);
	}
	
	private void calcXY()
	{
		x = (((xrot * Math.cos(theta)) - (yrot * Math.sin(theta))));
		y = (((xrot * Math.sin(theta)) + (yrot * Math.cos(theta))));
	}
	
	private void calcV0cp()
	{
		v0cp = Math.sqrt(Math.pow(v0xcp, 2) + Math.pow(v0ycp, 2));
	}
	
	public void calcTslide()
	{
		tslide = 2 * v0cp / (7 * mu * g);
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getTslide()
	{
		return tslide;
	}
	
	public void draw(Graphics g)
	{
		calcV0xcp();
		calcV0ycp();
		calcC();
		calcV0xrot();
		calcV0yrot();
		calcXrot();
		calcYrot();
		calcTheta();
		calcXY();
		calcV0cp();
		calcTslide();
		int current_x = 250 + (int)(x * 100);
		int current_y = 460 - (int)(y * 14) - DIAMETER;
		if ((current_x >= 0) && (current_x <= 500) && 
			(current_y >= 0) && (current_y <= 470))
		{
			listXPoints.add(current_x + DIAMETER / 2);
			listYPoints.add(current_y + DIAMETER / 2);
			g.setColor(Color.BLUE);
			g.fillOval(current_x, current_y, DIAMETER, DIAMETER); //just make the same ball every time
		}
		
		g.setColor(Color.BLACK);

		v0xInit = v0x;
		v0yInit = v0y;
		
		g.drawLine(250 + DIAMETER / 2, 450 , 250 + (int)(v0xInit * 100), 200 + (int)(v0yInit * 14));
		g.drawLine(250 + DIAMETER / 2, 450, 250 + DIAMETER / 2, 200);
		g.setColor(Color.RED);
		for (int i = 0; i < listXPoints.size() - 1; i++)
			g.drawLine(listXPoints.get(i), listYPoints.get(i), 
					   listXPoints.get(i + 1), listYPoints.get(i + 1));
	}
}