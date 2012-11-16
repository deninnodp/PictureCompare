package pac;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class GetPixelColor
{
	// a "Picture" stored as a multidimentional array, each position has 3 int
	// values, r, g, b for red green and blue
	public static int[][] colors;

	public static int[][] getArray(File filez) throws IOException
	{
		//File Creation
		File file = filez;
		BufferedImage image = ImageIO.read(file);

		//Get Height and Width and Total Pixels
		int height = image.getHeight();
		int height2 = height - 1;
		int width = image.getWidth();
		int width2 = width - 1;
		int pixels = height * width;

		System.out.println("Height: " + height);
		System.out.println("Width: " + width);
		System.out.println("PIX: " + pixels);

		colors = new int[pixels][3];

		int temp, currentx, currenty;

		currentx = -1;
		currenty = 0;

		temp = 0;

		boolean flag = true;

		//iterate through the entire picture, storing the RGB values in the array
		while (temp < pixels)
		{
			if (currentx < width2 && currenty <= height2)
			{
				//Routine for getting color data and storing it
				currentx++;

				int clr = image.getRGB(currentx, currenty);
				int red = (clr & 0x00ff0000) >> 16;
				int green = (clr & 0x0000ff00) >> 8;
				int blue = clr & 0x000000ff;

				colors[temp][0] = red;
				colors[temp][1] = green;
				colors[temp][2] = blue;

				temp++;

			}

			if (currentx == width2)
			{
				if (currenty == height2)
				{
					//Scan Completed, final pass
					// Do Nothing
				} else
				{
					//End of row of pixels, line feed
					int clr = image.getRGB(currentx, currenty);
					int red = (clr & 0x00ff0000) >> 16;
					int green = (clr & 0x0000ff00) >> 8;
					int blue = clr & 0x000000ff;
					colors[temp][0] = red;
					colors[temp][1] = green;
					colors[temp][2] = blue;

					currentx = 0;
					currenty++;
					temp++;
				}
			}
		}

		return colors;
	}

	public static void main(String[] args) throws IOException
	{
		File filee = new File("a.bmp");
		File filee2 = new File("b.bmp");

		System.out.println("First Picture:");
		int[][] a = getArray(filee);

		System.out.println("Second Picture:");
		int[][] b = getArray(filee2);

		double size1 = a.length;
		double size2 = b.length;

		int i = 0;
		double numofsame = 0;

		boolean notdone = true;

		if (size1 == size2)
		{

			while (notdone)
			{
				if (a[i][0] == b[i][0] || a[i][1] == b[i][1] || a[i][2] == b[i][2])
				{
					numofsame++;
				}
				i++;

				if (i == size1)
				{
					notdone = false;
				}
			}

			double percentage = numofsame / size1;

			System.out.println("# Same: " + numofsame);

			if (percentage * 100 < 1)
			{
				System.out.println("% Similarity: " + numofsame + "/" + size1);
			} else
			{
				System.out.println("% Similarity: " + percentage * 100 + "%");
			}

		}

	}

}
