# ImageComparison

To start the application you need a server apache tomcat.
At this application are used Spring MVC.

To see the picture you need to reload the page, then the picture will appear on the right. 
If you connect the database and save the version of the picture to the database, you can avoid this situation.

javax.imageio.ImageIO;
java.awt.image.BufferedImage;
java.io.*

<code>

for (int i = 0; i < heightFirst; i++) {
	for (int j = 0; j < widthFirst; j++) {
		if (imageFirst.getRGB(j, i) != imageSecond.getRGB(j, i)) {
			int rgb1 = imageFirst.getRGB(j, i);
			int rgb2 = imageSecond.getRGB(j, i);
			int red2 = (rgb2 >> 16) & 0xff;
			int green2 = (rgb2 >> 8) & 0xff;
			int blue2 = (rgb2) & 0xff;
			int red1 = (rgb1 << 16) & 0xff;
			int green1 = (rgb1 << 8) & 0xff;
			int blue1 = (rgb1) & 0xff;
			difference = Math.abs(red1 - red2);
			difference += Math.abs(green1 - green2);
			difference += Math.abs(blue1 - blue2);
        		difference /= 3;
			result = (difference << 16) | (difference)
				| (difference << 8);
			newImage.setRGB(j, i, result);
			} else {
	  			newImage.setRGB(j, i, imageFirst.getRGB(j, i));
			}
		}
	}
}
          
</code>
