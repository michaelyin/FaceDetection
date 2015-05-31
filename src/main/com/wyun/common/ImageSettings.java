/**
 * 
 */
package com.wyun.common;

/**
 * @author michaely
 *
 */
import java.io.File;


public class ImageSettings {
	private static String imagesPath = "/opt/id-images";
	private static File imagesPathFile = null;
	
	public static String getImagesPath() {
		return imagesPath;
	}
	
	/*
	 * use this one by default
	 */
	public static void setImagesPath(String imagesPath) {
		imagesPath = new File(imagesPath).getAbsolutePath();
		setImagesPathFile(new File(imagesPath));
	}
	
	public static File getImagesPathFile() {
		if(imagesPathFile == null){
			setImagesPath(imagesPath);
		}
		return imagesPathFile;
	}
	
	public static void setImagesPathFile(File imgPathFile) {
		imagesPathFile = imgPathFile;
	}
}
