/**
 * 
 */
package com.wyun.facedetection;

/**
 * @author michaely
 *
 */

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

public class FaceDetector {

	public static void main(String[] args) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("\nRunning FaceDetector");

		CascadeClassifier faceDetector = new CascadeClassifier(
				FaceDetector.class.getResource(
						"lbpcascade_frontalface.xml").getPath());
		Mat image = Highgui.imread(FaceDetector.class
				.getResource("not_face.jpg").getPath());

		MatOfRect faceDetections = new MatOfRect();
		//faceDetector.detectMultiScale(image, faceDetections);
		
		MatOfInt rejectLevels = new MatOfInt();
		MatOfDouble levelWeights = new MatOfDouble();
		//faceDetector.detectMultiScale(image, faceDetections, rejectLevels, levelWeights);
		
		Size minSize = new Size(45, 45);
		Size maxSize = new Size(300, 300);
		
		faceDetector.detectMultiScale(image, faceDetections, 1.1, 3, Objdetect.CASCADE_DO_CANNY_PRUNING | Objdetect.CASCADE_DO_ROUGH_SEARCH, minSize, maxSize);;

		System.out.println(String.format("Detected %s faces",
				faceDetections.toArray().length));

		for (Rect rect : faceDetections.toArray()) {
			Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x
					+ rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
			
		}
		/*
		for(int intg:rejectLevels.toArray()){
			System.out.println("reject levels: " + intg);
		}
		*/

		String filename = "ouput.png";
		System.out.println(String.format("Writing %s", filename));
		Highgui.imwrite(filename, image);
	}
}
