package utils.operations;

import java.awt.Point;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Luis
 */
public class IO {

    public static BufferedImage readImage(File file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
            return img;
        } catch (IOException e) {
            return null;
        }
    }

    public static void writeImage(BufferedImage bufferedImage, String fileName) {
        try {
            ImageIO.write(bufferedImage, "jpg", new File("savedImages/" + fileName + ".jpg"));
        } catch (IOException e) {
        }
    }

    public static Object[] getColorByteImageArray2DFromBufferedImage(BufferedImage image) {
        int channels;
        byte[] byteData_1d;
        int Rows = image.getHeight();
        int Cols = image.getWidth();

        // Color channels           
        byte[][] rByteData = new byte[Rows][Cols];
        byte[][] gByteData = new byte[Rows][Cols];
        byte[][] bByteData = new byte[Rows][Cols];

        int m, n, i, i4, pixelLength;
        byteData_1d = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;
        if (hasAlphaChannel) {
            pixelLength = 4; //a,r,g,b;
        } else {
            pixelLength = 3; //a,r,g,b;
        }
        if (image.getType() == BufferedImage.TYPE_BYTE_GRAY || image.getType() == BufferedImage.TYPE_USHORT_GRAY) {
            pixelLength = 1;
        }
        if (image.getType() == BufferedImage.TYPE_BYTE_GRAY || image.getType() == BufferedImage.TYPE_USHORT_GRAY) {
            channels = 1;
        } else {
            channels = 3;
        }

        if (channels == 1) {
            for (i = 0; i < byteData_1d.length; i++) {
                // convert 1d to 2d
                m = i / Cols; //Row index;
                n = i % Cols;
                rByteData[m][n] = byteData_1d[i];
            }
        } else {
            for (i = 0; i < byteData_1d.length; i += pixelLength) {
                i4 = i / pixelLength;
                // convert 1d to 2d
                m = i4 / Cols; //Row index;
                n = i4 % Cols;
                if (pixelLength == 3) {
                    bByteData[m][n] = (byte) byteData_1d[i];
                    gByteData[m][n] = (byte) byteData_1d[i + 1];
                    rByteData[m][n] = (byte) byteData_1d[i + 2];
                } else if (pixelLength == 4) {
                    //Skip alpha
                    bByteData[m][n] = byteData_1d[i + 1];
                    gByteData[m][n] = byteData_1d[i + 2];
                    rByteData[m][n] = byteData_1d[i + 3];
                } else {
                    channels = 0;
                }
            }
        }
        return new Object[]{rByteData, gByteData, bByteData};
    }

    public static BufferedImage setColorByteImageArray2DToBufferedImage(byte[][] rByteData, byte[][] gByteData, byte[][] bByteData) {
        int width = rByteData[0].length;
        int height = rByteData.length;
        int i, m, n, i3, pixelLength = 3;
        byte[] byteData_1d = new byte[3 * width * height];
        for (i = 0; i < byteData_1d.length; i += pixelLength) {
            i3 = i / pixelLength;
            // convert 1d to 2d
            m = i3 / width; //Row index;
            n = i3 % width;
            byteData_1d[i] = (byte) rByteData[m][n];
            byteData_1d[i + 1] = (byte) gByteData[m][n];
            byteData_1d[i + 2] = (byte) bByteData[m][n];
        }
        int dataType = DataBuffer.TYPE_BYTE;
        DataBufferByte buffer = new DataBufferByte(byteData_1d, byteData_1d.length);

        int cs = ColorSpace.CS_LINEAR_RGB;
        ColorSpace cSpace = ColorSpace.getInstance(cs);
        ComponentColorModel ccm;
        if (dataType == DataBuffer.TYPE_INT || dataType == DataBuffer.TYPE_BYTE) {
            ccm = new ComponentColorModel(cSpace,
                    ((cs == ColorSpace.CS_GRAY)
                            ? new int[]{8} : new int[]{8, 8, 8}),
                    false, false, Transparency.OPAQUE, dataType);
        } else {
            ccm = new ComponentColorModel(
                    cSpace, false, false, Transparency.OPAQUE, dataType);
        }
        SampleModel sm = ccm.createCompatibleSampleModel(width, height);
        WritableRaster raster = Raster.createWritableRaster(sm, buffer, new Point(0, 0));
        return new BufferedImage(ccm, raster, false, null);
    }
}
