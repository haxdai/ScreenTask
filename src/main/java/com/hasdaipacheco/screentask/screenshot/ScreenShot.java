/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hasdaipacheco.screentask.screenshot;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Ahmad
 */

public class ScreenShot {
	private static final String tempDir = System.getProperty("java.io.tmpdir");

	public static void takeScreenshot(int every, Boolean drawCursor) throws AWTException, IOException, Exception {
		Robot robot = new Robot();

		/**
		 * Delay the robot for 5 seconds (5000 ms) allowing you to switch to proper
		 * screen/window whose screenshot is to be taken.
		 *
		 * You can change the delay time as required.
		 */
		robot.delay(every);

		/**
		 * Create a screen capture of the active window and then create a buffered image
		 * to be saved to disk.
		 */

		Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage screenCapture = robot.createScreenCapture(screen);

		if (drawCursor)
			drawMousePointer(screenCapture);
		/**
		 * Filename where to save the file to. I am appending formatted timestamp to the
		 * filename.
		 */

		String fileNameToSaveTo = tempDir + "/ScreenTask/ScreenTask.jpg";
		String tpmFile = tempDir + "/ScreenTask/ScreenTask_tmp.jpg";

		/**
		 * Write the captured image to a file.
		 */
		File f = new File(tpmFile);
		if (!f.exists()) {
			f.mkdirs();
		}

		ImageIO.write(screenCapture, "jpg", f);
		f.renameTo(new File(fileNameToSaveTo));
	}

	private static void drawMousePointer(BufferedImage screenCapture) throws IOException, URISyntaxException {
		Point p = MouseInfo.getPointerInfo().getLocation();
		int x = p.x;
		int y = p.y;

		Image cursor = null;
		cursor = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream("site/cursor_arrow.png"));

		Graphics graphics2D = screenCapture.createGraphics();
		graphics2D.drawImage(cursor, x, y, 25, 25, null);
	}

	public static void PreviewImage(JLabel lblImage) throws URISyntaxException, MalformedURLException {
		String imagePath = tempDir + "/site/ScreenTask.jpg";
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(imagePath);

		int width = lblImage.getWidth();
		int height = lblImage.getHeight();
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		ImageIcon icon = new ImageIcon(scaledImage);

		lblImage.setIcon(icon);
	}
}
