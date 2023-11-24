package io.swagger.util;

import org.idpass.lite.Card;
import org.idpass.lite.exceptions.InvalidCardException;

import com.google.zxing.WriterException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.function.Function;

public class Helper {
    /**
     * Renders an ID PASS Lite card into a QR code image.
     *
     * @param card An ID PASS Lite card
     * @return Returns a QR code image of the card
     * @throws InvalidCardException Corrupted card
    */
    public static BufferedImage toBufferedImage(Card card)
            throws InvalidCardException
    {
        BitSet qrpixels = card.asQRCode();
        int qrsidelen = (int) Math.sqrt(qrpixels.length() - 1);
        int margin = card.getMargin();
        int scale = card.getScale();

        BufferedImage qrcode = new BufferedImage(
            (qrsidelen + margin * 2) * scale,
            (qrsidelen + margin * 2) * scale,
            BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < qrcode.getHeight(); y++) {
            for (int x = 0; x < qrcode.getWidth(); x++) {
                int innerX = x / scale - margin;
                int innerY = y / scale - margin;
                boolean flag = false;

                if (innerX >= 0 && innerX < qrsidelen &&
                        innerY >= 0 && innerY < qrsidelen) {
                    flag = qrpixels.get(innerX + innerY * qrsidelen);
                }

                qrcode.setRGB(x, y, flag ? Color.BLACK.getRGB() :
                                           Color.WHITE.getRGB());
            }
        }

        return qrcode;
    }
}