package Controller;

import javax.swing.JLabel;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.videoio.VideoCapture;

import Helper.Converter;
import Helper.Preprocessing;

public class CameraController {
	
	private int width;
	private int height;
	private JLabel imageLabel;
	private VideoCapture capture;
	private Mat frame;
	private Converter converter;
	private Preprocessing preprocessing;
	private DeamonThread thread;
	private int h_low=0, h_high=0, s_low=0, s_high=0, v_low=0, v_high=0;
	private boolean isMasked = false;
	private double angle=0, distance=0;
	
	public CameraController(int width, int height, JLabel imageLabel)
	{
		this.width = width;
		this.height = height;
		this.frame = new Mat();
		this.imageLabel = imageLabel;
		this.converter = new Converter();
		this.preprocessing = new Preprocessing();
	}
	
	class DeamonThread implements Runnable
	{
		protected volatile boolean runnable = false;
		@Override
		public void run() {
			synchronized (this) {
				while(this.runnable)
				{
					if(capture.read(frame))
					{
						try
						{
							Mat image = converter.resizeImage(frame, width, height);

							Mat hsvImage = preprocessing.convertHSVFormat(image, h_low, h_high, s_low, s_high, v_low, v_high);
							Mat morphImage = preprocessing.applyMorphology(hsvImage, 3, 2, "erode");
							morphImage = preprocessing.applyMorphology(morphImage, 3, 2, "dilate");
							Mat threshImage = preprocessing.applyThreshold(morphImage, 120);
							if(isMasked)
								imageLabel.setIcon(converter.createAwtImage(threshImage));
							else
							{
								MatOfPoint maxRect = preprocessing.getMaxRectangle(preprocessing.findContours(threshImage));
								if(maxRect != null)
								{
									imageLabel.setIcon(converter.createAwtImage(preprocessing.drawRectangle(maxRect, image)));
									angle = preprocessing.calculateAngle(maxRect);
									distance = preprocessing.calculateDistance(maxRect);
								}
									
								else
									imageLabel.setIcon(converter.createAwtImage(image));
							}
								
							
							if(this.runnable == false)
								this.wait();
						}
						catch (Exception e) {
							System.err.println(e.getMessage());
						}
					}
				}
			}
		}
	}
	
	public void startCapture()
	{
		this.capture = new VideoCapture(0);
		this.thread = new DeamonThread();
		Thread t = new Thread(this.thread);
		t.setDaemon(true);
		this.thread.runnable = true;
		t.start();
	} 
	
	public void stopCapture()
	{
		this.thread.runnable = false;
		this.capture.release();
	}
	public Mat getVideoFrame()
	{
		return this.frame;
	}
	
	public void setHLow(int h_low) {this.h_low = h_low;}
	public void setHHigh(int h_high) {this.h_high = h_high;}
	public void setSLow(int s_low) {this.s_low = s_low;}
	public void setSHigh(int s_high) {this.s_high = s_high;}
	public void setVLow(int v_low) {this.v_low = v_low;}
	public void setVHigh(int v_high) {this.v_high = v_high;}
	public void setMaskedImage(boolean isMasked) {this.isMasked = isMasked;}
	
	public double getAngle() {return this.angle;}
	public double getDistance() {return this.distance;}

}
