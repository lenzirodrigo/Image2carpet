package image2carpet;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;



public class Image2carpet {

	private Logo logo;
	private Carpet carpet;
	
	public Image2carpet(String imageFileLocation, int rows, int columns){
		logo = new Logo(imageFileLocation);
		carpet = new Carpet(rows, columns, logo);
	}
	public void saveCarpet(String imageFileLocation) {
		carpet.save(imageFileLocation);
	}
	
	public void carpetize(){
		carpet.generateRGBCarpet();
		carpet.drawCarpet();
		carpet.drawGrid(Color.YELLOW.getRGB());
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Image2carpet i2c = new Image2carpet("data/logos/puma.png", 2, 3);
		i2c.carpetize();
		i2c.saveCarpet("data/carpets/carpetPuma.png");

	}



}
