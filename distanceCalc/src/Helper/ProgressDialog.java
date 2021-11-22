package Helper;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.GridLayout;

public class ProgressDialog {
	
	private JFrame frame;
	private JProgressBar bar;
	
	public ProgressDialog(String message, String title, ImageIcon icon)
	{
		this.frame = new JFrame();
		this.bar = new JProgressBar();
		
		this.bar.setValue(0);
		this.bar.setBounds(0,0,420,50);
		this.bar.setStringPainted(true);
		
		this.frame.setTitle(title);
		this.frame.setLocationRelativeTo(null);
		this.frame.add(this.bar);
		this.frame.setResizable(false);
		this.frame.setIconImage(icon.getImage());
		
		JLabel label = new JLabel(message);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		this.frame.add(label);
		
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.setSize(420,140);
		this.frame.setLayout(new GridLayout(2,1,10,20));
	}
	
	public void showDialog()
	{
		this.frame.setVisible(true);
		Thread newThread = new Thread(() -> {
			
			for(int i=0;i<100;i++)
			{
				try {
					Thread.sleep(20);
					this.bar.setValue(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.frame.dispose();
		});
		newThread.start();
	}

}
