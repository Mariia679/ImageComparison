package ua.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import ua.dto.Folder;
import ua.dto.ImagePixels;

@Controller
@SessionAttributes("image")
public class ImageController {

	/**
	 * Annotation that binds a method parameter or method return value to a
	 * named model attribute, exposed to a web view.
	 * 
	 * @return new ImagePixels in method getImage()
	 */

	@ModelAttribute("image")
	public ImagePixels getForm() {
		return new ImagePixels();
	}

	/**
	 *
	 * @return file index.jsp
	 */

	@GetMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * Simple interface SessionStatus that can be injected into handler methods,
	 * allowing them to signal that their session processing is complete.
	 * 
	 * Mark the current handler's session processing as complete, allowing for
	 * cleanup of session attributes.
	 * 
	 * @param status
	 * @return redirect to /
	 */

	@GetMapping("/cancel")
	public String cancel(SessionStatus status) {
		status.setComplete();
		return "redirect:/";
	}

	/**
	 * This method takes 2 pictures from an object of the class. ImagePixels
	 * writes them to an object of the BufferedImage class, then reads line by
	 * line the pixels compares, if there are differences, then highlights the
	 * changes, if not, then writes the same picture
	 * 
	 * @param imagePixels
	 *            is an object which is returned by reference "image"
	 * @param status
	 * @return redirect to /
	 */
	@PostMapping("/img")
	public String getImage(@ModelAttribute("image") ImagePixels imagePixels,
			SessionStatus status) {

		BufferedImage imageFirst = null;

		BufferedImage imageSecond = null;

		int widthFirst;

		int widthSecond;

		int heightFirst;

		int heightSecond;

		BufferedImage newImage = null;

		// Reading an image from an object and writing it to a MultipartFile
		// object
		MultipartFile fileFirst = imagePixels.getFileFirst();
		MultipartFile fileSecond = imagePixels.getFileSecond();

		/**
		 * @exception IllegalArgumentException
		 *                if <code>input</code> is <code>null</code>.
		 * @exception IOException
		 *                if an error occurs during reading. Try to write the
		 *                incoming files as an array of bytes
		 */
		try {
			InputStream inFirst = new ByteArrayInputStream(fileFirst.getBytes());
			imageFirst = ImageIO.read(inFirst);

			InputStream inSecond = new ByteArrayInputStream(
					fileSecond.getBytes());
			imageSecond = ImageIO.read(inSecond);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Write the length and width of the pictures into variables
		widthFirst = imageFirst.getWidth();
		widthSecond = imageSecond.getWidth();
		heightFirst = imageFirst.getHeight();
		heightSecond = imageSecond.getHeight();
		int difference;
		int result;
		if (widthFirst == widthSecond && heightFirst == heightSecond) {

			newImage = new BufferedImage(widthFirst, heightFirst,
					BufferedImage.TYPE_INT_RGB);

			for (int i = 0; i < heightFirst; i++) {
				for (int j = 0; j < widthFirst; j++) {
					if (imageFirst.getRGB(j, i) != imageSecond.getRGB(j, i)) {
						int rgb1 = imageFirst.getRGB(j, i);
						int rgb2 = imageSecond.getRGB(j, i);

						int red2 = (rgb2 >> 16) & 0xff;// right shifts the value
														// of argb 16 bits,
						// and then performs a bitwise AND operation to capture
						// only 8 bits.
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
		if (newImage != null) {
			// Write to file on server tomcat
			File pathToHome = new File(System.getProperty("catalina.home"));
			File pathToFolder = new File(pathToHome, "images" + File.separator
					+ Folder.NEW_IMAGE.name().toLowerCase());
			if (!pathToFolder.exists()) {
				pathToFolder.mkdirs();
			}
			try {
				File pathToFile = new File(pathToFolder, String.valueOf(1)
						+ ".jpg");
				ImageIO.write(newImage, "jpg", pathToFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		status.setComplete();
		return "redirect:/";
	}
}
