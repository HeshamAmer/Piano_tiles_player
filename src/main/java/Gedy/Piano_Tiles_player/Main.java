package Gedy.Piano_Tiles_player;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {
	static final int w = 400, h = 800;
	static int[] pixel_map;
	static Color[] Block_Colors;
	static Robot myRobot;
	static int timeOffset=0;

	public static int[] getPixelArray(BufferedImage img) {

		int[] pixels = new int[w];

		for (int i = 0; i < w; i++)
			pixels[i] = img.getRGB(i, 525);
		return pixels;
	}

	public static void printImgToConsole(BufferedImage im) {
		pixel_map = getPixelArray(im);
		Color C;
		for (int i = 0; i < w; i++) {
			C = new Color(pixel_map[i]);
			System.out.println(C.getRed() + "  " + C.getGreen() + " " + C.getBlue() + " ");
			// System.out.println();
		}

	}

	public static BufferedImage readImage() {

		BufferedImage im = null;
		try {
			im = ImageIO.read(new File("Capture1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return im;
	}

	public static void main(String[] args) throws InterruptedException, AWTException {

//		BufferedImage im1 = readImage();
		myRobot = new Robot();

		Monitor m1 = new Monitor();
		Block_Colors = new Color[4];
		BufferedImage img = m1.captureScreen();

		long startTimeMillis = System.currentTimeMillis();
		long recordTimeMillis = 150000;
//		long lastTimeMillis = System.currentTimeMillis();
		while ((System.currentTimeMillis() - startTimeMillis) <= recordTimeMillis) {		
			
			img = m1.captureScreen();
			Block_Colors[0] = new Color(img.getRGB(50, 490));
			Block_Colors[1] = new Color(img.getRGB(150, 490));
			Block_Colors[2] = new Color(img.getRGB(250, 490));
			Block_Colors[3] = new Color(img.getRGB(350, 490));

			for (int i = 0; i < 4; i++) {
				char c=GetChar(Block_Colors[i].getRed(),Block_Colors[i].getGreen(),Block_Colors[i].getBlue(),i);
				//System.out.println("Block " + i +": "+ Block_Colors[i].getRed()+" "+ Block_Colors[i].getGreen()+ " " +Block_Colors[i].getBlue());
				//System.out.println("character retrieved ="+c);
				//System.out.println(C.getRed() + " " + C.getGreen() + " " + C.getBlue());
			}
			// System.out.println(m1.captureScreen());
			
		}
		

	}
	static int steps=0;
	public static char GetChar(int Red, int Green, int Blue, int idx) throws InterruptedException {
		int sleepPeriod=225;
		
		if (Red == 17 && Green == 17 && Blue == 17) {
			steps++;
			
			if (steps==100)
				timeOffset+=10;
			if (steps==120)
				timeOffset+=10;
			if (steps>=140 && steps %2==0)
				timeOffset++;
	//			timeOffset=100;
			if (timeOffset>=115)
				timeOffset=115;
			System.out.println("Total sleep time = "+ (sleepPeriod-timeOffset) + " ,Steps: "+steps + "---- time offset: "+ timeOffset );
			if (timeOffset<sleepPeriod && steps%2==0)			
				timeOffset++;	

			if (idx == 0){				
				myRobot.keyPress(KeyEvent.VK_A);
				
				Thread.sleep(sleepPeriod-timeOffset);
				
				return 'A';
			}
			else if (idx == 1){
				myRobot.keyPress(KeyEvent.VK_S);
				Thread.sleep(sleepPeriod-timeOffset);
				return 'S';	
			}
			else if (idx == 2){
				myRobot.keyPress(KeyEvent.VK_D);
				Thread.sleep(sleepPeriod-timeOffset);
				return 'D';
			}
			else if (idx == 3){
				myRobot.keyPress(KeyEvent.VK_F);
				Thread.sleep(sleepPeriod-timeOffset);
				return 'F';
			}
		}
		myRobot.keyRelease(KeyEvent.VK_A);
		myRobot.keyRelease(KeyEvent.VK_S);
		myRobot.keyRelease(KeyEvent.VK_D);
		myRobot.keyRelease(KeyEvent.VK_F);
		return 'H';
	}
}
