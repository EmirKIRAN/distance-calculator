package Helper;

import org.opencv.core.Point;

public class Numeric {
	
	public double[] sumPoints(Point[] corners)
	{
		/*
		 * Design for axis value equal to one(1)
		 * This function sums the x and y values ​​of a point
		 * returns the same length result array(summed)
		 * */
		double[] values = new double[corners.length];
		
		for(int i=0;i<corners.length;i++)
			values[i] = corners[i].x + corners[i].y;
		
		return values;	
	}
	
	public double[] diffPoints(Point[] corners)
	{
		/*
		 * Design for axis value equal to one(1)
		 * This function subt. the x and y values ​​of a point
		 * returns the same length result array(subtracted)
		 * */
		double[] values = new double[corners.length];
		
		for(int i=0; i<corners.length; i++)
			values[i] = corners[i].x - corners[i].y;
		
		return values;
	}
	
	public int argMax(double[] values)
	{
		/*
		 * This function finds the index of maximum value in the destination array and returns.
		 * */
		int index = 0;
		double  maxValue = values[index]; // initialized with the first element of the destination array.
		
		for(int i=1;i<values.length;i++)
		{
			if(values[i]>maxValue) // find the maximum value
			{
				maxValue = values[i];
				index = i;
			}
		}
		
		return index; //returns the index of maximum value in the dst array.
	}
	
	public int argMin(double[] values)
	{
		/*
		 * This function finds the index of minimum value in the destination array and returns.
		 * */
		
		int index = 0;
		double minValue = values[index];// initialized with the first element of the destination array.
		
		for(int i=1; i<values.length;i++)
		{
			if(values[i] < minValue) // find the minimum value
			{
				minValue = values[i];
				index = i;
			}
		}
		
		return index;//returns the index of minimum value in the dst array.
	}

}
