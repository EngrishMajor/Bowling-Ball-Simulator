package balltrajectory;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ContentPanel extends JPanel
{
	private final int INTERVAL = 35; //draw every 100 milliseconds
	private Timer timer;
	private double time, timeEnd;
	private final int X_BOUND = 470;
	public Ball ball;
	
	public ContentPanel()
	{
		this.setPreferredSize(new Dimension(500, 500));
		timer = new Timer(INTERVAL, new TimerAction());
		ball = new Ball();
		time = 0.1;
		timeEnd = ball.getTslide();
	}
	
	public void reset()
	{
		ball = new Ball();
		time = 0.1;
		timeEnd = ball.getTslide();
		timer.stop();
	}
	
	public void setAnimation(boolean onOrOff)
	{
		if (onOrOff)
		{
			timeEnd = ball.getTslide();
			timer.start(); //starts the animation
		}
		else
		{
			timer.stop(); //restart the timer
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		ball.draw(g);
	}
	
	class TimerAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (time >= timeEnd)
				timer.stop();
			
			timeEnd = ball.getTslide();
			repaint();
			time += .1;
			ball.setTime(time);
			if (ball.getX() >= X_BOUND)
				reset();
		}
	}
}
