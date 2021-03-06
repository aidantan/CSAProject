package src;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture() {
		/*
		 * not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height
	 *            the height of the desired picture
	 * @param width
	 *            the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture
	 *            the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName,
	 *         height and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
		return output;

	}

	/** Method to set the blue to 0 */
	public void zeroBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue(0);
			}
		}
	}

	public void keepOnlyBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setGreen(0);
				pixelObj.setRed(0);
			}
		}
	}

	public void keepOnlyRed() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setGreen(0);
				pixelObj.setBlue(0);
			}
		}
	}

	public void keepOnlyGreen() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(0);
				pixelObj.setBlue(0);
			}
		}
	}

	public void negate() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(255 - pixelObj.getRed());
				pixelObj.setBlue(255 - pixelObj.getBlue());
				pixelObj.setGreen(255 - pixelObj.getGreen());
			}
		}
	}

	public void grayscale() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				int average = (pixelObj.getBlue() + pixelObj.getRed() + pixelObj.getGreen()) / 3;
				pixelObj.setRed(average);
				pixelObj.setBlue(average);
				pixelObj.setGreen(average);
			}
		}
	}

	/**
	 * Method that mirrors the picture around a vertical mirror in the center of
	 * the picture from left to right
	 */
	public void mirrorVertical() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	public void mirrorVerticalRightToLeft() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				leftPixel.setColor(rightPixel.getColor());
			}
		}
	}

	public void mirrorHorizontal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length / 2; row++) {
			for (int col = 0; col < width; col++) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[pixels.length - 1 - row][col];
				bottomPixel.setColor(topPixel.getColor());
			}
		}
	}

	public void mirrorHorizontalBotToTop() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length / 2; row++) {
			for (int col = 0; col < width; col++) {
				bottomPixel = pixels[pixels.length - 1 - row][col];
				topPixel = pixels[row][col];

				topPixel.setColor(bottomPixel.getColor());
			}
		}
	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple() {
		int mirrorPoint = 276;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int count = 0;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 27; row < 97; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 13; col < mirrorPoint; col++) {

				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
				count++;
			}
		}
		System.out.println(count);
	}

	public void mirrorArms() {
		int mirrorPoint = 196;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 162; row < mirrorPoint; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 106; col < 293; col++) {

				leftPixel = pixels[row][col];
				rightPixel = pixels[mirrorPoint - row + mirrorPoint][col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	public void mirrorGull() {
		int mirrorPoint = 344;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 235; row < 319; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 235; col < mirrorPoint; col++) {

				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	public void mirrorDiagonal() // mirrors from top right to bottom left
	{
		Pixel[][] pixels = this.getPixels2D();
		Pixel topRightPixel = null;
		Pixel bottomLeftPixel = null;
		int maxLength;
		if (pixels.length < pixels[0].length) {
			maxLength = pixels.length;
		} else {
			maxLength = pixels[0].length;
		}

		for (int row = 0; row < maxLength; row++) {
			for (int col = row; col < maxLength; col++) {
				topRightPixel = pixels[row][col];
				bottomLeftPixel = pixels[col][row];
				bottomLeftPixel.setColor(topRightPixel.getColor());
			}
		}
	}

	/**
	 * copy from the passed fromPic to the specified startRow and startCol in
	 * the current picture
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length
				&& toRow < toPixels.length; fromRow++, toRow++) {
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length
					&& toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	public void copy2(Picture fromPic, int startRow, int endRow, int startCol, int endCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length && toRow < endRow; fromRow++, toRow++) {
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length
					&& toCol < endCol; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	/** Method to create a collage of several pictures */
	public void createCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 0, 0);
		this.copy(flower2, 100, 0);
		this.copy(flower1, 200, 0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}

	/**
	 * Method to show large changes in color
	 * 
	 * @param edgeDist
	 *            the distance for finding edges
	 */
	public void edgeDetection(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setColor(Color.WHITE);
			}
		}
	}

	public void fixUnderwater() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				if (pixelObj.getBlue() > pixelObj.getGreen() && pixelObj.getBlue() > pixelObj.getRed()) {

					pixelObj.setBlue(pixelObj.getBlue() * 2);
				}
			}
		}
	}

	public void edgeDetection2(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		Pixel[][] pixels = this.getPixels2D();

		for (int row = 0; row < pixels.length - 1; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				topPixel = pixels[row][col];
				bottomPixel = pixels[row + 1][col];
				if (leftPixel.colorDistance(rightPixel.getColor()) > edgeDist
						|| topPixel.colorDistance(bottomPixel.getColor()) > edgeDist)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setColor(Color.WHITE);
			}
		}
	}

	public void sharpen(int x, int y, int width, int height) {
		System.out.println("Aidan Tan Period 2 Computer Number 24");
		Pixel[][] pixels = this.getPixels2D();
		Pixel pixeld = pixels[0][0];
		for (int row = y; row < y + height; row++) {
			// loop from 13 to just before the mirror point
			for (int col = x; col < x + width; col++) {
				// array out of bounds for when the pixel is at 0,0
				if (row > 0 && col > 0) {
					pixeld = pixels[row - 1][col - 1];
				} else if (row == 0 && col == 0) {
					pixeld = pixels[row][col];
				}

				else if (row == 0 && col > 0) {
					pixeld = pixels[row][col - 1];
				} else if (row > 0 && col == 0) {
					pixeld = pixels[row - 1][col];
				}

				// getting the difference between the current pixel and the
				// diagonal pixel
				int redd = pixels[row][col].getRed() - pixeld.getRed();
				int greend = pixels[row][col].getGreen() - pixeld.getGreen();
				int blued = pixels[row][col].getBlue() - pixeld.getBlue();

				// making a value that adds half the difference and the current
				// pixel
				int setValueR = (redd / 2) + pixels[row][col].getRed();
				int setValueG = (greend / 2) + pixels[row][col].getGreen();
				int setValueB = (blued / 2) + pixels[row][col].getBlue();
				// using a limit method to check and see if the color is within
				// the bounds
				int cR = limit(setValueR);
				int cG = limit(setValueG);
				int cB = limit(setValueB);

				// setting the current pixel to the limited value
				pixels[row][col].setRed(cR);
				pixels[row][col].setBlue(cB);
				pixels[row][col].setGreen(cG);

			}

		}

	}

	public int limit(int i) {
		if (i > 255) {
			i = 255;
		} else if (i < 0) {
			i = 0;
		}
		return i;
	}

	public void encode(Picture pic) {
		Pixel[][] messagePixels = pic.getPixels2D();
		Pixel[][] currPixels = this.getPixels2D();
		Pixel currPixel = null;
		Pixel messagePixel = null;
		int count = 0;
		for (int row = 0; row < this.getHeight(); row++) {
			for (int col = 0; col < this.getWidth(); col++) {
				// if the current pixel red is odd make it even
				currPixel = currPixels[row][col];
				messagePixel = messagePixels[row][col];
				
					currPixel.setGreen(fix(currPixel.getGreen()));
					if (messagePixel.colorDistance(Color.BLACK) < 50) {
						currPixel.setGreen(currPixel.getGreen() + 1);
						count++;
					}
				}
				/*if (dominantColor(currPixel).equals("green")) {
					currPixel.setGreen(fix(currPixel.getGreen()));
					if (messagePixel.colorDistance(Color.BLACK) < 50) {
						currPixel.setGreen(currPixel.getGreen() + 1);
						count++;
					}
				} /*else if (dominantColor(currPixel).equals("red")) {
					currPixel.setGreen(fix(currPixel.getRed()));
					if (messagePixel.colorDistance(Color.BLACK) < 50) {
						currPixel.setRed(currPixel.getRed() + 1);
						count++;
					}
				} else if (dominantColor(currPixel).equals("blue")) {
					currPixel.setGreen(fix(currPixel.getBlue()));
					if (messagePixel.colorDistance(Color.BLACK) < 50) {
						currPixel.setRed(currPixel.getBlue() + 1);
						count++;
					}
				}
				*/

			}
		}
		

	public void decode()
	{
		Pixel[][] currPixels = this.getPixels2D();
		Pixel currPixel = null;
		for (int row = 0; row < this.getHeight(); row++) {
			for (int col = 0; col < this.getWidth(); col++) {
				// if the current pixel red is odd make it even
				currPixel = currPixels[row][col];
				if(currPixel.getGreen()%5==4)
				{
				currPixel.setColor(Color.BLACK);
				}
				else
				{
					currPixel.setColor(Color.WHITE);
				}
				
					
					
				}
		}
	}

	public int fix(int i) {
		System.out.print(i);
		int changeUp = 0;
		int changeDown = 0;
		int up = i;
		int down = i;
		if (i % 5 != 3) {

			while (up % 5 != 3) {

				up++;
				changeUp++;
			}
			while (down % 5 != 3) {

				down--;
				changeDown++;
			}

		}
		if(down>=255)
		{
			return up;
		}
		if(up>=255)
		{
			return down;
		}
		System.out.println(" "+i);
		if (changeUp > changeDown) {
			return down;
		}
		return up;
	}

	public String dominantColor(Pixel p) {
		if (p.getGreen() >= p.getBlue() && p.getGreen() >= p.getRed()) {
			return "green";
		} else if (p.getBlue() >= p.getGreen() && p.getBlue() >= p.getRed()) {
			return "blue";
		}
		return "red";

	}
	
	public void dct() {
		Pixel[][] currPixels = this.getPixels2D();
		 int i, j, k, l; 
		  int m = currPixels.length;
		  int  n = currPixels[0].length;
	        // dct will store the discrete cosine transform 
	        double[][] dct = new double[m][n]; 
	   
	        double ci, cj, dct1, sum; 
	   
	        for (i = 0; i < m; i++)  
	        { 
	            for (j = 0; j < n; j++)  
	            { 
	                // ci and cj depends on frequency as well as 
	                // number of row and columns of specified matrix 
	                if (i == 0) 
	                    ci = 1 / Math.sqrt(m); 
	                else
	                    ci = Math.sqrt(2) / Math.sqrt(m); 
	                      
	                if (j == 0) 
	                    cj = 1 / Math.sqrt(n); 
	                else
	                    cj = Math.sqrt(2) / Math.sqrt(n); 
	   
	                // sum will temporarily store the sum of  
	                // cosine signals 
	                sum = 0; 
	                for (k = 0; k < m; k++)  
	                { 
	                    for (l = 0; l < n; l++)  
	                    { 
	                        dct1 = currPixels[k][l].getAverage() *  
	                               Math.cos((2 * k + 1) * i * Math.PI / (2 * m)) *  
	                               Math.cos((2 * l + 1) * j * Math.PI / (2 * n)); 
	                        sum = sum + dct1; 
	                    } 
	                } 
	                dct[i][j] = ci * cj * sum; 
	            } 
	        } 
	   
	        for (i = 0; i < m; i++)  
	        { 
	            for (j = 0; j < n; j++)
	                System.out.printf("%f\t", dct[i][j]); 
	            System.out.println(); 
	        }
	}
	
	/*
	 * 
	 * 
	 * 	static strictfp void dctTransform(double matrix[][]) 
	{ 
		int i, j, k, l; 

		// dct will store the discrete cosine transform 
		double[][] dct = new double[m][n]; 

		double ck, cl, dct1, sum; 

		for (i = 0; i < m; i++) 
		{ 
			for (j = 0; j < n; j++) 
			{ 
				// ci and cj depends on frequency as well as 
				// number of row and columns of specified matrix 
			
				// sum will temporarily store the sum of 
				// cosine signals 
				sum = 0; 
				for (k = 0; k < m; k++) 
				{ 
					for (l = 0; l < n; l++) 
					{ 
					 	if (k == 0) 
					ck = 1 / Math.sqrt(m); 
				else
					ck = Math.sqrt(2) / Math.sqrt(m); 
					
				if (l == 0) 
					cl = 1 / Math.sqrt(n); 
				else
					cl = Math.sqrt(2) / Math.sqrt(n); 

					    
					    
						dct1 = matrix[k][l] * 
							Math.cos((2 * i + 1) *  k* pi / (2 * m)) * 
							Math.cos((2 * j + 1) * l * pi / (2 * n)); 
						sum = sum +ck*cl*dct1; 
					} 
				} 
				dct[i][j] =sum; 
			} 
		} 

		for (i = 0; i < m; i++) 
		{ 
			for (j = 0; j < n; j++) 
				System.out.printf("%f\t", dct[i][j]); 
			System.out.println(); 
		} 
	} 
	 */

	/**
	 * } /* Main method for testing - each class in Java can have a main method
	 */
	

} // this } is the end of class Picture, put all new methods before this
