package balltrajectory;
/**
 * @author Ken Tian, ytian13@illinois.edu
 * This class sets up the user interface for the applet.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame
{
	private JTextField radiusField, v0xField, v0yField,
					   w0xField, w0yField, muField, gField;
	private JLabel 	   radiusLabel, v0xLabel, v0yLabel,
					   w0xLabel, w0yLabel, muLabel, gLabel;
	private JButton startButton, stopButton, resetButton;
	private JToolBar toolbar;
	private ContentPanel panel;
	
	public GUI()
	{
		super("Ball Trajectory");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new ContentPanel();
		this.setPreferredSize(new Dimension(500, 500));
		this.add(panel);
		initComponents();
		this.pack();
		setVisible(true);
	}
	
	private void initComponents()
	{
		radiusField = new JTextField(5);
		radiusField.setText("0.1");
		radiusLabel = new JLabel("Radius");
		panel.add(radiusLabel);
		panel.add(radiusField);
		
		
		v0xField = new JTextField(5);
		v0xField.setText("-0.2");
		v0xLabel = new JLabel("Initial X Velocity");
		panel.add(v0xLabel);
		panel.add(v0xField);		
		
		v0yField = new JTextField(5);
		v0yField.setText("8");
		v0yLabel = new JLabel("Initial Y Velocity");
		panel.add(v0yLabel);
		panel.add(v0yField);
		
		w0xField = new JTextField(5);
		w0xField.setText("-1");
		w0xLabel = new JLabel("Initial X Angular Velocity");
		panel.add(w0xLabel);
		panel.add(w0xField);
		
		w0yField = new JTextField(5);
		w0yField.setText("2");
		w0yLabel = new JLabel("Initial Y Angular Velocity");
		panel.add(w0yLabel);
		panel.add(w0yField);
		
		muField = new JTextField(5);
		muField.setText("0.09");
		muLabel = new JLabel("Coefficient of Kinetic Friction");
		panel.add(muLabel);
		panel.add(muField);
		
		gField = new JTextField(5);
		gField.setText("9.8");
		gLabel = new JLabel("Acceleration of Gravity");
		panel.add(gLabel);
		panel.add(gField);
		
		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				startButtonActionPerformed(evt);
			}
		});
		panel.add(startButton);
		
		stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				stopButtonActionPerformed(evt);
			}
		});
		panel.add(stopButton);
		
		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				resetButtonActionPerformed(evt);
			}
		});
		panel.add(resetButton);
		
		panel.repaint();
	}
	
	private void startButtonActionPerformed(ActionEvent evt)
	{
		panel.ball.setRadius(Double.parseDouble(radiusField.getText()));
		panel.ball.setV0x(Double.parseDouble(v0xField.getText()));
		panel.ball.setV0y(Double.parseDouble(v0yField.getText()));
		panel.ball.setw0x(Double.parseDouble(w0xField.getText()));
		panel.ball.setw0y(Double.parseDouble(w0yField.getText()));
		panel.ball.setMu(Double.parseDouble(muField.getText()));
		panel.ball.setG(Double.parseDouble(gField.getText()));
		panel.setAnimation(true);
	}
	
	private void stopButtonActionPerformed(ActionEvent evt)
	{
		panel.setAnimation(false);
	}
	
	private void resetButtonActionPerformed(ActionEvent evt)
	{
		panel.reset();
		panel.repaint();
	}
}
