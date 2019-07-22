package com.agkit.uploader.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * @program: com.agkit.uploader.utils.ImageHelper
 * @description:
 * @author: king djwb1982@163.com
 * @create: 2019-07-21 21:28
 **/
public class ImageHelper {
    public static int defalutdropWidth = 150;
    public static int defalutdropHeight = 150;

    public ImageHelper() {
    }

    private static BufferedImage makeThumbnail(Image img, int width, int height) {
        BufferedImage tag = new BufferedImage(width, height, 1);
        Graphics g = tag.getGraphics();
        g.drawImage(img.getScaledInstance(width, height, 4), 0, 0, (ImageObserver)null);
        g.dispose();
        return tag;
    }

    private static void saveSubImage(BufferedImage image, Rectangle subImageBounds, File subImageFile, int left, int top, int dropWidth, int dropHeight) throws IOException {
        if (dropWidth == 0 || dropHeight == 0) {
            dropWidth = defalutdropWidth;
            dropHeight = defalutdropHeight;
        }

        String fileName = subImageFile.getName();
        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
        BufferedImage subImage = new BufferedImage(dropWidth, dropHeight, 1);
        Graphics g = subImage.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, dropWidth, dropHeight);
        g.drawImage(image.getSubimage(subImageBounds.x, subImageBounds.y, subImageBounds.width, subImageBounds.height), left, top, (ImageObserver)null);
        g.dispose();
        File file = new File(subImageFile.getPath());
        if (!file.exists()) {
            file.mkdirs();
        }

        ImageIO.write(subImage, formatName, subImageFile);
    }

    public static void cut(File srcImageFile, File descDir, Rectangle rect, int[] intParms) throws IOException {
        Image image = ImageIO.read(srcImageFile);
        BufferedImage bImage = makeThumbnail(image, intParms[0], intParms[1]);
        saveSubImage(bImage, rect, descDir, intParms[2] < 0 ? -intParms[2] : 0, intParms[3] < 0 ? -intParms[3] : 0, intParms[4], intParms[5]);
    }
}
