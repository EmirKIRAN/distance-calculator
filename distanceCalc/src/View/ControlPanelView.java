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
		
		this.initFrameSection();
		this.initSettingsSection();
		this.getContentPane().setBackground(this.classicBackgroundColor);
	}
	
	public void showWindow()
	{
		this.setVisible(true);
	}
	
	private void initFrameSection()
	{
		JPanel framePanel = new JPanel(null);
		framePanel.setBounds(0,0,900,500);
		framePanel.setBackground(this.classicBackgroundColor);
		
		float centerPosition = (this.WIDTH/2) - (800/2);
		
		this.isLive = new JLabel();
		this.isLive.setText("LIVE");
		this.isLive.setForeground(Color.RED);
		this.isLive.setBounds(750,25,250,40);
		this.isLive.setIconTextGap(8);
		this.isLive.setFont(new Font("Arial", Font.BOLD, 18));
		this.isLive.setIcon(new ImageIcon("./images/icons/record.png"));
		framePanel.add(this.isLive);
		
		this.imageFrame = new JLabel();
		this.imageFrame.setBounds((int)centerPosition,20,800,400);
		this.imageFrame.setIcon(new ImageIcon("/home/emir/Masaüstü/work/Deep Learning/ship-classifier/models/test-data/cargo/2692333.jpg"));
		this.imageFrame.setBorder(BorderFactory.createMatteBorder(2, 2, 4, 2, new Color(75, 101, 132)));
		framePanel.add(this.imageFrame);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEADING,10,20));
		bottomPanel.setBounds((int)centerPosition, 425, 800,70);
		bottomPanel.setBackground(this.classicBackgroundColor);
		
		this.selectImage = new JRadioButton("NORMAL IMAGE");
		this.selectImage.setFocusable(false);
		this.selectImage.setPreferredSize(new Dimension(150,40));
		this.selectImage.setBackground(this.classicBackgroundColor);
		this.selectImage.setSelected(true);
		
		this.selectMask = new JRadioButton("MASKED IMAGE");
		this.selectMask.setFocusable(false);
		this.selectMask.setPreferredSize(new Dimension(150,40));
		this.selectMask.setBackground(this.classicBackgroundColor);
		
		this.startCapture = new JButton("START CAPTURE");
		this.startCapture.setPreferredSize(new Dimension(200,45));
		this.startCapture.setIcon(new ImageIcon("./images/icons/radar.png"));
		this.startCapture.setIconTextGap(15);
		this.startCapture.setFocusable(false);
		
		this.stopCapture = new JButton("STOP CAPTURE");
		this.stopCapture.setPreferredSize(new Dimension(200,45));
		this.stopCapture.setIcon(new ImageIcon("./images/icons/signal.png"));
		this.stopCapture.setIconTextGap(15);
		this.stopCapture.setFocusable(false);

		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(this.selectImage);
		buttonGroup.add(this.selectMask);
		
		bottomPanel.add(this.selectImage);
		bottomPanel.add(this.selectMask);
		bottomPanel.add(startCapture);
		bottomPanel.add(this.stopCapture);
		
		framePanel.add(bottomPanel);
		this.add(framePanel);
	}
	
	private void initSettingsSection()
	{
		JPanel settingsPanel = new JPanel(null);
		settingsPanel.setBounds(50,505,800,395);
		settingsPanel.setBackground(this.classicBackgroundColor);
		settingsPanel.setBorder(BorderFactory.createTitledBorder("HSV Settings"));
		
		this.hLow = new JSlider(0,255);
		this.hHigh = new JSlider(0,255);
		this.sLow = new JSlider(0,255);
		this.sHigh = new JSlider(0,255);
		this.vLow = new JSlider(0,255);
		this.vHigh = new JSlider(0,255);
		
		settingsPanel.add(addSlider(this.hLow, 20, "low__h value"));
		settingsPanel.add(addSlider(this.hHigh, 80, "high_h value"));
		settingsPanel.add(addSlider(this.sLow, 140, "low__s value"));
		settingsPanel.add(addSlider(this.sHigh, 200, "high_s value"));
		settingsPanel.add(addSlider(this.vLow, 260, "low__v value"));
		settingsPanel.add(addSlider(this.vHigh, 320, "high_v value"));
		this.add(settingsPanel);
	}
	
	private JPanel addSlider(JSlider slider, int yPos, String label)
	{
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING,40,10));
		panel.setBackground(this.classicBackgroundColor);
		panel.setBounds(20,yPos,700,70);
		
		slider.setFocusable(false);
		slider.setBackground(this.classicBackgroundColor);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(10);
		slider.setPaintTrack(true);
		slider.setFont(new Font("Arial", Font.BOLD, 8));
		slider.setMajorTickSpacing(25);
		slider.setPaintLabels(true);
		slider.setValue(0);
		slider.setPreferredSize(new Dimension(500,40));
		
		panel.add(new JLabel(label));
		panel.add(slider);
		
		return panel;
	}
	
}
