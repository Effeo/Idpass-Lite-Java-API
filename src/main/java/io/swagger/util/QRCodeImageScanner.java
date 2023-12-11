package io.swagger.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * The QRCodeImageScanner externalizes the zxing dependency outside
 * of idpass-lite-java jar library.
 */

public class QRCodeImageScanner implements Function<BufferedImage, byte[]> {
    @Override
     public byte[] apply(BufferedImage img) {

        LuminanceSource source = new BufferedImageLuminanceSource(img);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        byte[] card;

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            Map m = result.getResultMetadata();

            if (m.containsKey(ResultMetadataType.BYTE_SEGMENTS)) {
                List L = (List) m.get(ResultMetadataType.BYTE_SEGMENTS);
                card = (byte[]) L.get(0);
            } else {
                card = result.getText().getBytes();
            }
        } catch (NotFoundException e) {
            return null;
        }

        return card;
    }
}