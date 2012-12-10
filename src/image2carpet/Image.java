package image2carpet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class Image {
	protected BufferedImage img;

	public Image(){
		 img = null;
	}
	public int getWidth(){
		return img.getWidth();
	}
	public int getHeight(){
		return img.getHeight();
	}
	public int getType(){
		return img.getType();
	}
	
	public void createImg(int width, int height, int type){
		img = new BufferedImage(width,height,type);

	}
	
	public void load(String imageFileLocation){
		try {
		    img = ImageIO.read(new File(imageFileLocation));
		} catch (IOException e) {
			System.out.println("Unable to read from " + imageFileLocation);
		}
	}
	
	public void save(String imageFileLocation){
		try {
		    File outputfile = new File(imageFileLocation);
		    ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			System.out.println("Unable to save to " + imageFileLocation);
		}
	}
	
	public void fillArea(int x, int y, int width, int height, int rgbColour){
		for(int i = x; i < width; i++)
			for(int j = y; j < height; j++)
				img.setRGB(i, j, rgbColour);
	}
	
	public int getPredominantColour(int x, int y, int width, int height){
		// obtener el color predominante en el espacio determinado
        Map<Integer, Integer> m = new HashMap<Integer,Integer>();
        for(int i=x; i < width ; i++){
            for(int j=y; j < height ; j++){
                int rgb = img.getRGB(i, j);
                int[] rgbArr = getRGBArr(rgb);                
               // System.out.println("RGB "+ rgb +" red " +rgbArr[0]+ " green " +rgbArr[1] + " blue " +rgbArr[2] );
                // Filter out grays....                
               // if (!isGray(rgbArr)) {                
                    Integer counter = (Integer) m.get(rgb);   
                    if (counter == null)
                        counter = 0;
                    counter++;                                
                    m.put(rgb, counter);                
               // }                
            }
        }  
        
		return getMostCommonColour(m);
	}
	
	private int getMostCommonColour(Map map) {
		List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
              public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                  .compareTo(((Map.Entry) (o2)).getValue());
              }
        });    
        Map.Entry me = (Map.Entry) list.get(list.size()-1);
        return ((Integer)me.getKey()).intValue();        
    }   
	public int[] getRGBArr(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        return new int[]{red,green,blue};

	}
	public boolean isGray(int[] rgbArr) {
        int rgDiff = rgbArr[0] - rgbArr[1];
        int rbDiff = rgbArr[0] - rgbArr[2];
        // Filter out black, white and grays...... (tolerance within 10 pixels)
        int tolerance = 10;
        if (rgDiff > tolerance || rgDiff < -tolerance) 
            if (rbDiff > tolerance || rbDiff < -tolerance) { 
                return false;
            }                 
        return true;
    }
	
	public void drawHorizontalLine(int y, int rgbColor){
		for(int x = 0; x < img.getWidth(); x++)
			img.setRGB(x, y, rgbColor);			
	}
	
	public void drawVerticalLine(int x, int rgbColor){
		for(int y = 0; y < img.getHeight(); y++)
			img.setRGB(x, y, rgbColor);
	}
}

