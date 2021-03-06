package org.usfirst.frc.team3274.robot.visionprocessing;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class CameraProcessor// extends IterativeRobot
{
	/*
	 * camera input private Mat source = new Mat(); camera output private Mat output
	 * = new Mat();
	 */

	private CvSource outputStream;
	private CvSink cvSink;

	private UsbCamera camera;

	/* size of camera picture */
	private int frameWidth = 320;
	private int frameHeight = 240;

	/* width */
	int xVar = 50;
	/* height */
	int yVar = 20;

	int startX = 320 - xVar;
	int startY = 240 - yVar;
	int endX = 320 + xVar;
	int endY = 240 + yVar;

	/* green */
	int thickness = 5;

	public void init() {
		startImageCapture();
	}

	/*
	 * public void drawRectangle() { //System.out.println("draw rect"); draw
	 * rectangle on output image Imgproc.line(output, point1, point2, boxColor,
	 * thickness); Imgproc.line(output, point2, point3, boxColor, thickness);
	 * Imgproc.line(output, point3, point4, botxColor, thickness);
	 * Imgproc.line(output, point4, point1, boxColor, thickness);
	 * 
	 * 
	 * //Imgproc.rectangle(output, point1, point3, boxColor, thickness); }
	 */
	private void startImageCapture() {
		new Thread(() -> {
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(640, 480);
            
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
            
            Mat source = new Mat();
            Mat output = new Mat();
            
            while(!Thread.interrupted()) {
                cvSink.grabFrame(source);
                Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
                outputStream.putFrame(output);
            }
        }).start();
	}
}
