package Helper;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;

public class Preprocessing {
	
	public Preprocessing() {}
	
	public Mat applyThreshold(Mat image, int threshValue)
	{
		Mat threshImage = new Mat();
		Imgproc.threshold(image, threshImage, threshValue, 255, Imgproc.THRESH_BINARY);
		
		return threshImage;
	}
	
	public Mat applyMorphology(Mat image, int kernelSize, int iter, String type)
	{
		Mat morphImage = new Mat();
		Mat kernel = Mat.ones(kernelSize, kernelSize, CvType.CV_32F);
		if(type.equals("erode"))
			Imgproc.erode(image, morphImage, kernel, new Point(-1,-1) ,iter);
		else
			Imgproc.dilate(image, morphImage, kernel, new Point(-1,-1) ,iter);
		
		return morphImage;
	}
	
	public Mat convertHSVFormat(Mat frame,int h_low, int h_high, int s_low, int s_high, int v_low, int v_high)
	{
		Mat image = new Mat();
		Mat result = new Mat();
		Imgproc.cvtColor(frame, image, Imgproc.COLOR_BGR2HSV);
		
		Scalar lower = new Scalar(h_low, s_low, v_low);
		Scalar upper = new Scalar(h_high, s_high, v_high);
		
		Core.inRange(image, lower, upper, result);
		
		return result;
	}
	
	public List<MatOfPoint> findContours(Mat threshImage)
	{
		Mat canny = new Mat();
		Imgproc.Canny(threshImage, canny, 100, 200);
		
		List<MatOfPoint> contours = new ArrayList<>();
		Mat hierarchy = new Mat();
		Imgproc.findContours(canny, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
		
		return contours;
	}
	
	public MatOfPoint getMaxRectangle(List<MatOfPoint> contours)
	{
		double maxArea=0, currentArea=0;
		MatOfPoint maxContour = null;
		
		for(MatOfPoint c : contours)
		{
			currentArea = Imgproc.minAreaRect(new MatOfPoint2f(c.toArray())).size.area();
			if(currentArea > maxArea)
			{
				maxArea = currentArea;
				maxContour = c;
			}
		}
		
		return maxContour;
	}
	
	public Mat drawRectangle(MatOfPoint rect, Mat image)
	{
		RotatedRect rectangle = Imgproc.minAreaRect(new MatOfPoint2f(rect.toArray()));
		Point[] points = new Point[4];
		rectangle.points(points);
		for(int i=0; i<4; ++i)
			Imgproc.line(image, points[i], points[(i+1)%4], new Scalar(255,255,255));
			
		
		return image;
	}
	

	private Point[] sortPointArray(Point[] points)
	{
		Point[] sorted = new Point[points.length];
		Numeric num = new Numeric();
		
		double[] summed = num.sumPoints(points);
		sorted[0] = points[num.argMin(summed)];
		sorted[2] = points[num.argMax(summed)];
		
		double[] diff = num.diffPoints(points);
		sorted[1] = points[num.argMax(diff)];
		sorted[3] = points[num.argMin(diff)];
		
		return sorted;
	}
	
	public double calculateAngle(MatOfPoint contour)
	{
		RotatedRect rectangle = Imgproc.minAreaRect(new MatOfPoint2f(contour.toArray()));
		
		return rectangle.angle;
	}
	public double calculateDistance(MatOfPoint contour)
	{
		RotatedRect rectangle = Imgproc.minAreaRect(new MatOfPoint2f(contour.toArray()));
		Point[] points = new Point[4];
		rectangle.points(points);
		
		Point[] sortedPoints = sortPointArray(points);
		double width = sortedPoints[1].x - sortedPoints[0].x;
		double distance = (4537 / width) * 2.54;
		
		return distance;
	}

}
