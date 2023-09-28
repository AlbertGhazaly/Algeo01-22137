import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class imageProcessing {

    public static int[][] imageToArray(String imagePath) {
        try {
            // Load the JPG image using ImageIO
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);

            // Get the dimensions of the image
            int width = image.getWidth();
            int height = image.getHeight();

            // Create a 2D array to store the pixel values
            int[][] pixelArray = new int[height][width];

            // Loop through each pixel and extract its value
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = image.getRGB(x, y);

                    // Extract the red channel value (0-255)
                    int red = (pixel >> 16) & 0xFF;

                    // Store the red channel value in the array
                    pixelArray[y][x] = red;
                }
            }

            // Now you have the pixel values in the 'pixelArray' 2D array
            // You can use this array for further processing
            return pixelArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void arrayToImage(int[][] pixelArray, String outputPath) {
        try {
            int height = pixelArray.length;
            int width = pixelArray[0].length;

            // Create a BufferedImage to store the pixel values
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // Set the pixel values in the image
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int red = pixelArray[y][x];
                    int rgb = (red << 16) | (red << 8) | red;
                    image.setRGB(x, y, rgb);
                }
            }

            // Write the BufferedImage to a JPG file
            File outputFile = new File(outputPath);
            ImageIO.write(image, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public static int[][] resize(int[][] image, int newWidth, int)

    

    public static void main(String[] args) {
        String imagePath = "image/suisei4x4.png"; // Input image path
        String outputPath = "image/output.jpg";     // Output image path

        int[][] pixelArray = imageToArray(imagePath);

        if (pixelArray != null) {
            operator.displayMatrixInt(pixelArray);
            // arrayToImage(pixelArray, outputPath);
        }
    }
}


