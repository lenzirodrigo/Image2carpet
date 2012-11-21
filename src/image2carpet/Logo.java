package image2carpet;

public class Logo extends Image {
	
	public Logo(String imageFileLocation){
		load(imageFileLocation);
		System.out.println("Logo width:" + this.getWidth());
		System.out.println("Logo height:" + this.getHeight());
	}
}
