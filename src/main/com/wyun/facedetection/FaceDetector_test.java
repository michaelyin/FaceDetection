/**
 * 
 */
package com.wyun.facedetection;

import com.wyun.facedetection.impl.OpenCVDetector;

/**
 * @author michaely
 *
 */
public class FaceDetector_test {
	public static void main(String[] args) {

		OpenCVDetector.detectFace("passport_china.jpg");
		OpenCVDetector.detectFace("driver_license.jpg");
	}

}


