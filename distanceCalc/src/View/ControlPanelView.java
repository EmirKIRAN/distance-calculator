package View;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

import java.awt.FlowLayout;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

public class ControlPanelView extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel imageFrame, isLive, distanceValue, angleValue, directionValue;
	private JRadioButton selectImage, selectMask;
	private int WIDTH, HEIGHT;
	private Color classicBackgroundColor;
	private JSlider hLow, hHigh, sLow, sHigh, vLow, vHigh;
	private JButton startCapture, stopCapture;
	
	public ControlPanelView(String title, String iconPath) 
	{
		this.WIDTH = 900;
		this.HEIGHT = 1040;
		this.classicBackgroundColor = new Color(223, 228, 234);
		
		this.setTitle(title);
		this.setIconImage(new ImageIcon(iconPath).getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.WIDTH,this.HEIGHT);
		this.setLayout(null);
		this.setResizable(false);
		
		this.getContentPane().setBackground(this.classicBackgroundColor);
	}
	
	public void showWindow()
	{
		this.setVisible(true);
	}

}
