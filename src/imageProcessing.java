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

    public static double[][] get_D_MATRIX(){

        double[][] D_MATRIX = new double[16][16];

        int IDX_ROW = 0;
        int IDX_COL = 0;

        while (IDX_ROW < 16){

                for (int y = 0; y < 2; y++){
                    for (int x = 0; x < 2; x++){

                        while (IDX_COL < 16){

                            for (int j = -1; j < 3; j++){
                                for (int i = -1; i < 3; i++){


                                    if (IDX_ROW < 4){
                                        if (x == i && y == j){
                                            D_MATRIX[IDX_ROW][IDX_COL] = 1;
                                        }
                                    } else if (IDX_ROW < 8){
                                        if (x+1 == i && y == j){
                                            D_MATRIX[IDX_ROW][IDX_COL] = 0.5;
                                        } else if (x-1 == i && y == j){
                                            D_MATRIX[IDX_ROW][IDX_COL] = -0.5;
                                        }

                                    } else if (IDX_ROW < 12){
                                        if (x == i && y+1 == j){
                                            D_MATRIX[IDX_ROW][IDX_COL] = 0.5;
                                        } else if (x == i && y-1 == j){
                                            D_MATRIX[IDX_ROW][IDX_COL] = -0.5;
                                        }

                                    } else {
                                        if (x == i+1 && y+1 == j){
                                            D_MATRIX[IDX_ROW][IDX_COL] = 0.25;
                                        } else if (x-1 == i && y == j){
                                            D_MATRIX[IDX_ROW][IDX_COL] = -0.25;
                                        } else if (x == i && y-1 == j){
                                            D_MATRIX[IDX_ROW][IDX_COL] = -0.25;
                                        } else if (x == i && y == j){
                                            D_MATRIX[IDX_ROW][IDX_COL] = -0.25;
                                        }
                                    }
                                    IDX_COL++;
                                }
                            }
                        }
                        IDX_ROW++;
                        IDX_COL = 0;
                    }
                }
        }
        // operator.displayMatrix(D_MATRIX);

        return D_MATRIX;
    }
    
    public static double[][][][] get_pixel_BSI_COEF(int[][] imageMatrix){

        // double[][][][] reference = {{{{1},{2},{3}},{{1},{2},{3}},{{1},{2},{3}}},
        //                             {{{1},{2},{3}},{{1},{2},{3}},{{1},{2},{3}}},
        //                             {{{1},{2},{3}},{{1},{2},{3}},{{1},{2},{3}}}};

        int row = imageMatrix.length;
        int col = imageMatrix[0].length;

        int[][] mirroredImageMatrix = new int[row+3][col+3];

        for (int i = 1; i < row+1; i++){
            for(int j = 1; j < col+1; j++){
                mirroredImageMatrix[i][j] = imageMatrix[i-1][j-1];
            }
        }

        for (int i = 1; i < row+1; i++){
            mirroredImageMatrix[i][0] = mirroredImageMatrix[i][1];
            mirroredImageMatrix[i][col+1] = mirroredImageMatrix[i][col];
            mirroredImageMatrix[i][col+2] = mirroredImageMatrix[i][col];
        }
        for (int j = 0; j < col+3; j++){
            mirroredImageMatrix[0][j] = mirroredImageMatrix[1][j];
            mirroredImageMatrix[row+1][j] = mirroredImageMatrix[row][j];
            mirroredImageMatrix[row+2][j] = mirroredImageMatrix[row][j];
        }

        // operator.displayMatrixInt(imageMatrix);
        // System.out.println();
        // operator.displayMatrixInt(mirroredImageMatrix);

        
        double[][][][] pixel_BSI_COEF = new double[row][col][16][1];
        double[][] tempMatrix = new double[16][1];

        double[][] D_MATRIX = get_D_MATRIX();
        double[][] X_MATRIX = bicubic.get_BSI_MATRIX();

        double[][] DX_MATRIX = operator.multiplyMatrix(X_MATRIX, D_MATRIX);

        // operator.displayMatrixInt(mirroredImageMatrix);

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){

                int IDX = 0;

                for (int k = 0; k < 4; k++){
                    for(int l = 0; l < 4; l++){
                        tempMatrix[IDX][0] = mirroredImageMatrix[i+l][j+k];
                        // operator.displayMatrix(tempMatrix);
                        IDX++;
                    }
                }
                // operator.displayMatrix(tempMatrix);

                pixel_BSI_COEF[i][j] = operator.multiplyMatrix(DX_MATRIX, tempMatrix);
                // operator.displayMatrix(pixel_BSI_COEF[i][j]);
            }
        }
        return pixel_BSI_COEF;
    }

    public static int[][] resize(int[][] imageMatrix, double scale){

        int width = imageMatrix[0].length;
        int height = imageMatrix.length;

        int newWidth = (int) Math.floor(width*scale);
        int newHeight = (int) Math.floor(height*scale);

        int[][] newImageMatrix = new int[newHeight][newWidth];

        double[][][][] coefMatrix = get_pixel_BSI_COEF(imageMatrix);

        for (int i = 0; i < newHeight; i++){
            for (int j = 0; j < newWidth; j++){

                double y = ((double) j/scale)- (double) Math.floor(j/scale);
                double x = ((double) i/scale)- (double) Math.floor(i/scale);

                newImageMatrix[i][j] = (int) Math.round(bicubic.get_BSI_VAL(coefMatrix[(int) Math.floor(i/scale)][(int) Math.floor(j/scale)], x, y));
                if (newImageMatrix[i][j] > 255){
                    newImageMatrix[i][j] = 255;
                } else if (newImageMatrix[i][j] < 0){
                    newImageMatrix[i][j] = 0;
                }
            }
        }

        return newImageMatrix;
    }
    public static void main(String[] args) {
        String imagePath = "image/suisei4x4.png"; // Input image path
        String outputPath = "image/suisei4x4Resized.jpg";     // Output image path

        int[][] pixelArray = imageToArray(imagePath);

        if (pixelArray != null) {
            // operator.displayMatrixInt(pixelArray);
            int[][] newImg = resize(pixelArray, 16);

            arrayToImage(newImg, outputPath);
        }
    
        get_D_MATRIX();
    }
}


