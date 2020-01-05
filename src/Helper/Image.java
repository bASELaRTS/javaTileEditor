package Helper;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Image {
  public static BufferedImage loadFromFile(String filename) {
    return Helper.Image.loadFromFile(new File(filename));
  }

  public static BufferedImage loadFromFile(File file) {
    try {
      return ImageIO.read(file);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
    return null;
  }
}
