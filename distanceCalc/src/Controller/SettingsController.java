package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Helper.ProgressDialog;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

public class SettingsController implements ActionListener, ChangeListener{
	
	private JRadioButton normalImage, maskedImage;
	private JSlider h_low, h_high, s_low, s_high, v_low, v_high;
	private JButton startButton, stopButton;
	private JLabel screen, angle, distance;
	private CameraController camController;
	
	public SettingsController(JRadioButton normalImage,JRadioButton maskedImage, JLabel screen)
	{
		this.normalImage = normalImage;
		this.maskedImage = maskedImage;
		this.normalImage.addActionListener(this);
		this.maskedImage.addActionListener(this);
		
		this.screen = screen;
		
		this.camController = new CameraController(this.screen.getWidth(), this.screen.getHeight(), this.screen);
	}
	
	public void setSliders(JSlider h_low, JSlider h_high, JSlider s_low, JSlider s_high, JSlider v_low, JSlider v_high)
	{
		this.h_low = h_low;
		this.h_low.addChangeListener(this);
		this.h_high = h_high;
		this.h_high.addChangeListener(this);
		this.s_low = s_low;
		this.s_low.addChangeListener(this);
		this.s_high = s_high;
		this.s_high.addChangeListener(this);
		this.v_low = v_low;
		this.v_low.addChangeListener(this);
		this.v_high = v_high;
		this.v_high.addChangeListener(this);
	}
	public void setButtons(JButton startButton, JButton stopButton)
	{
		this.startButton = startButton;
		this.stopButton = stopButton;
		this.startButton.addActionListener(this);
		this.stopButton.addActionListener(this);
	}
	public void setAttributes(JLabel angle, JLabel distance)
	{
		this.angle = angle;
		this.distance = distance;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.maskedImage)
			camController.setMaskedImage(true);
		if(e.getSource() == this.normalImage)
			camController.setMaskedImage(false);
		if(e.getSource() == this.startButton)
		{
			String iconPath = "/home/emir/Masaüstü/work/Java/MVCSwing/openJava/images/scan.png";
			ProgressDialog dialog = new ProgressDialog("Scanning ports on machine...", "CAM Detector", new ImageIcon(iconPath));
			dialog.showDialog();
			this.camController.startCapture();
			this.startButton.setEnabled(false);
			this.stopButton.setEnabled(true);
			
			
			Thread newThread = new Thread(() -> {
				NumberFormat formatter = new DecimalFormat("#0.00");
			    while(true)
			    {
			    	this.angle.setText("ANGLE : " + formatter.format(this.camController.getAngle()));
			    	this.distance.setText("DISTANCE : " + formatter.format(this.camController.getDistance()));
			    }
			    	
			});
			newThread.start();
			
		}
		if(e.getSource() == this.stopButton)
		{
			this.camController.stopCapture();
			this.stopButton.setEnabled(false);
			this.startButton.setEnabled(true);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == this.h_low)
			camController.setHLow(this.h_low.getValue());
		if(e.getSource() == this.h_high)
			camController.setHHigh(this.h_high.getValue());
		if(e.getSource() == this.s_low)
			camController.setSLow(this.s_low.getValue());
		if(e.getSource() == this.s_high)
			camController.setSHigh(this.s_high.getValue());
		if(e.getSource() == this.v_low)
			camController.setVLow(this.v_low.getValue());
		if(e.getSource() == this.v_high)
			camController.setVHigh(this.v_high.getValue());
	}

}
