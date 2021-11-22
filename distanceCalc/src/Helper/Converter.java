package Helper;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import javax.swing.ImageIcon;

public class Converter {
	
	public ImageIcon createAwtImage(Mat mat) {
		
	    int type = 0;
	    if (mat.channels() == 1)
	        type = BufferedImage.TYPE_BYTE_GRAY;
	    else if (mat.channels() == 3)
	        type = BufferedImage.TYPE_3BYTE_BGR;
	    else
	    	return null;

	    BufferedImage image = new BufferedImage(mat.width(), mat.height(), type);
	    WritableRaster raster = image.getRaster();
	    DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
	    byte[] data = dataBuffer.getData();
	    mat.get(0, 0, data);

	    return new ImageIcon(image);
	}
	public Mat resizeImage(Mat mat, int width, int height)
	{
		Mat copy = new Mat();
		mat.copyTo(copy);
		Imgproc.resize(copy, copy, new Size(width, height));
		
		return copy;
	}

}
