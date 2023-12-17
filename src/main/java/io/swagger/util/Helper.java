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
    // QR code scanner with zxing dependency in test cases only
    public static Function<BufferedImage, byte[]> qrImageScanner = new QRCodeImageScanner();

    public static byte[] scanQRCode(BufferedImage qrPic) {
        return qrImageScanner.apply(qrPic);
    }

    /*
     * Renders a QR code into a Buffered Image.
     * @param qrCode a QR code
     * @return Returns a Buffered Image of the QR code
     */
    public static BufferedImage toBufferedImage(byte[] qrCode) throws IOException{
        // Convert byte array to BufferedImage
        InputStream in = new ByteArrayInputStream(qrCode);
        BufferedImage qrcode = ImageIO.read(in);

        // Get width and height of the original image
        int qrWidth = qrcode.getWidth();
        int qrHeight = qrcode.getHeight();

        int margin = 0;
        int scale = 1;

        BufferedImage outputImage = new BufferedImage(
            (qrWidth + margin * 2) * scale,
            (qrHeight + margin * 2) * scale,
            BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < outputImage.getHeight(); y++) {
            for (int x = 0; x < outputImage.getWidth(); x++) {
                int innerX = x / scale - margin;
                int innerY = y / scale - margin;
                boolean flag = false;
    
                if (innerX >= 0 && innerX < qrWidth &&
                        innerY >= 0 && innerY < qrHeight) {
                    int pixel = qrcode.getRGB(innerX, innerY);
                    flag = pixel == Color.BLACK.getRGB();
                }
    
                outputImage.setRGB(x, y, flag ? Color.BLACK.getRGB() :
                                            Color.WHITE.getRGB());
            }
        }
    
        return outputImage;
    }
    
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