package game.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 获取当前项目中各种资源
 * @author zzk
 *
 */
public class GameUtil {
	/**
	 * 根据图片的相对路径获取图片
	 * @param imagePath
	 * @return 图片
	 */
	public static Image getImage(String imagePath){
		URL url = GameUtil.class.getClassLoader().getResource(imagePath);
		BufferedImage img = null;
		try {
			img=ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}
