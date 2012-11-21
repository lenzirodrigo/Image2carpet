package image2carpet;


public class Carpet extends Image {
	private int rowsCarpet;
	private int columnsCarpet;
	private int columnWidth;
	private int rowHeight;
	private int[][] rgbCarpet;
	private Image logo;

	public Carpet(int rowsCarpet, int columnsCarpet, Image logo) {
		this.rowsCarpet = rowsCarpet;
		this.columnsCarpet = columnsCarpet;
		rgbCarpet = new int[rowsCarpet][columnsCarpet];
		this.logo = logo;
		columnWidth = (int) Math.ceil(logo.getWidth()/columnsCarpet);
		rowHeight = (int) Math.ceil(logo.getHeight() / rowsCarpet);
		createCarpet(columnsCarpet*columnWidth, rowsCarpet*rowHeight, logo.getType());
		System.out.println("Carpet columnWidth:"+ columnWidth);
		System.out.println("Carpet rowHeight" + rowHeight);
	}

	public void createCarpet(int width, int height, int type) {
		createImg(width, height, type);

	}

	public void drawGrid(int rgbColour) {
		drawVerticalLines(rgbColour);
		drawHorizontalLines(rgbColour);
	}

	public void drawVerticalLines(int rgbColour) {
		for (int i = 0; i < columnsCarpet; i++) {
			int x = i * columnWidth;
			System.out.println("drawVerticalLine: " + x);
			drawVerticalLine(x, rgbColour);
		}
	}

	public void drawHorizontalLines(int rgbColour) {
		for (int j = 0; j < rowsCarpet; j++) {
			int y = j * rowHeight;
			System.out.println("drawHorizontalLine: " + y);
			drawHorizontalLine(y, rgbColour);
		}
	}

	public void generateRGBCarpet() {
		System.out.println("Generating Carpet Map:");
		for (int i = 0; i < rowsCarpet; i++) {
			for (int j = 0; j < columnsCarpet; j++) {
				int fromWidth = i * columnWidth;
				int fromHeight = j * rowHeight;
				int toWidth = columnWidth + (i * columnWidth);
				int toHeight = rowHeight + (j * rowHeight);
				rgbCarpet[i][j] = logo.getPredominantColour(fromWidth,
						fromHeight, toWidth, toHeight);
				System.out.println("RGBCarpet["+i+ "]["+j+"] "+"(" + fromWidth + "," + fromHeight + ")(" + toWidth
						+ "," + toHeight + ") " + rgbCarpet[i][j]);
			}
		}
	}

	public void drawCarpet() {
		System.out.println("Drawing Carpet:");
		for (int i = 0; i < rowsCarpet; i++)
			for (int j = 0; j < columnsCarpet; j++) {
				int fromWidth = i * columnWidth;
				int fromHeight = j * rowHeight;
				int toWidth = columnWidth + (i * columnWidth);
				int toHeight = rowHeight + (j * rowHeight);
				fillArea(fromWidth, fromHeight, toWidth, toHeight,
						rgbCarpet[i][j]);
				System.out.println("Carpet "+"(" + fromWidth + ", " + fromHeight + ") (" + toWidth
						+ ", " + toHeight + ") " + rgbCarpet[i][j]);
			}
	}

}
