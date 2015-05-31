/**
 * 
 */
package com.wyun.facedetection.impl;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

import com.wyun.common.ImageSettings;
import com.wyun.facedetection.FaceDetector;

/**
 * @author michaely
 *
 */
public class OpenCVDetector {
	private static String PNG_AFFIX = ".png";
	private static CascadeClassifier faceDetector;
	private static String imagesPath;
	static { 
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		imagesPath = ImageSettings.getImagesPath();
		System.out.println("classifier: " + imagesPath + File.separator + "lbpcascade_frontalface.xml");
		faceDetector = new CascadeClassifier(imagesPath + File.separator + "lbpcascade_frontalface.xml");
		System.out.println("java library is " + System.getProperty("java.library.path"));
		
	}
	
	public static boolean detectFace(String imgFileName){
		
		//Mat image = Highgui.imread(FaceDetector.class.getResource("not_face.jpg").getPath());
		String absFileName = imagesPath + File.separator + imgFileName; //+ ".jpg";
		System.out.println("image file name: " + absFileName);
		Mat image = Highgui.imread(absFileName);
		
		System.out.println("input image pixels: " + image.total());

		MatOfRect faceDetections = new MatOfRect();
		//faceDetector.detectMultiScale(image, faceDetections);
		
		//MatOfInt rejectLevels = new MatOfInt();
		//MatOfDouble levelWeights = new MatOfDouble();
		//faceDetector.detectMultiScale(image, faceDetections, rejectLevels, levelWeights);
		
		Size minSize = new Size(45, 45);
		Size maxSize = new Size(300, 300);
		
		faceDetector.detectMultiScale(image, faceDetections, 1.1, 3, Objdetect.CASCADE_DO_CANNY_PRUNING | Objdetect.CASCADE_DO_ROUGH_SEARCH, minSize, maxSize);;

		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

		Rect[] rects = faceDetections.toArray();
		for (Rect rect : faceDetections.toArray()) {
			Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x
					+ rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
			System.out.println("width: " + rect.width + ", height: " + rect.height);
			
		}

		String filename = imagesPath + File.separator + imgFileName + PNG_AFFIX;
		System.out.println(String.format("Writing %s", filename));
		boolean saved = Highgui.imwrite(filename, image);
		System.out.println("image saved? " + saved);
		
		return rects.length > 0;
	}

}
