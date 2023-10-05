import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
    <imageProcessing>

    Class ini berisi fungsi yang berkaitan dengan soal bonus
    untuk program perbesaran resolusi gambar menggunakan metode
    bicubic spline interpolation.
*/

public class imageProcessing {

    /* 
        <imageToArray>

        Fungsi untuk mengkonversi image (.png/.jpg) ke array dua dimensi

        Fungsi menerima path image dan mengembalikan array dua dimensi 
        yang tiap elemennya adalah pixel value image (0-255)
    */

    public static int[][] imageToArray(String imagePath) {
        try {
            /* Load file gambar */
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);

            /* Memperoleh dimensi gambar */
            int width = image.getWidth();
            int height = image.getHeight();

            /* Membuat array dua dimensi untuk pixel value */
            int[][] pixelArray = new int[height][width];

            /* Memindahkan value tiap pixel pada gambar ke dalam array */
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = image.getRGB(x, y);

                    /* Mengambil red channel value */
                    int red = (pixel >> 16) & 0xFF;

                    pixelArray[y][x] = red;
                }
            }
            return pixelArray;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* 
        <arrayToImage>

        Fungsi untuk mengkonversi array dua dimensi ke image (.jpg)

        Fungsi menerima array dua dimensi dan output path image, fungsi akan
        menghasilkan file .jpg/.png baru 
    */

    public static void arrayToImage(int[][] pixelArray, String outputPath) {
        try {
            int height = pixelArray.length;
            int width = pixelArray[0].length;

            /* Membuat buffer image kosong yang akan diisi dengan value dari pixelArray */
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            /* Meng setRGB dari pixelArray ke image */
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int red = pixelArray[y][x];

                    /* Image monokrom memiliki value di channel red, green, dan blue yang sama */
                    int rgb = (red << 16) | (red << 8) | red;
                    image.setRGB(x, y, rgb);
                }
            }

            /* Mengkonver buffer image ke file .jpg*/
            File outputFile = new File(outputPath);
            ImageIO.write(image, "jpg", outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 
        <get_D_MATRIX>

        Fungsi untuk menggenerasi matriks D berukuran 16x16
        yang diperlukan untuk interpolasi pixel gambar
    */

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
        return D_MATRIX;
    }

    /* 
        <get_pixel_BSI_COEF>

        Fungsi untuk menggenerasi matriks koefisien interpolasi 
        untuk tiap pixel pada gambar. 
    */

    public static double[][][][] get_pixel_BSI_COEF(int[][] imageMatrix){

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
        
        double[][][][] pixel_BSI_COEF = new double[row][col][16][1];
        double[][] tempMatrix = new double[16][1];

        double[][] D_MATRIX = get_D_MATRIX();
        double[][] X_MATRIX = bicubic.get_BSI_MATRIX();

        double[][] DX_MATRIX = operator.multiplyMatrix(X_MATRIX, D_MATRIX);

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){

                int IDX = 0;

                for (int k = 0; k < 4; k++){
                    for(int l = 0; l < 4; l++){
                        tempMatrix[IDX][0] = mirroredImageMatrix[i+l][j+k];
            
                        IDX++;
                    }
                }
                pixel_BSI_COEF[i][j] = operator.multiplyMatrix(DX_MATRIX, tempMatrix);
            }
        }
        return pixel_BSI_COEF;
    }

    /* 
        <resize>

        Fungsi untuk menggenerasi matriks baru yang berisi value pixel
        gambar yang sudah di upscale. 
    */

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

    /* 
        <run>

        Fungsi driver bagi class imageProcessing. 
    */

    public static int run(){

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nMasukkan directory file input: ");
        String inputPath = scanner.nextLine();

        Path image = Paths.get(inputPath);

        if (!Files.exists(image)) {
            System.out.println("\nFile tidak tersedia.");
            
        } else {

            String imagePath = inputPath; 

            System.out.print("Masukkan directory file output: ");

            String outputPath = scanner.nextLine(); 

            int[][] pixelArray = imageToArray(imagePath);

            if (pixelArray != null) {

                System.out.print("Masukkan perbesaran gambar: ");
                String str = scanner.nextLine();

                try {
                double scale = Double.parseDouble(str);

                int[][] newImg = resize(pixelArray, scale);
                arrayToImage(newImg, outputPath);

                System.out.println("\nBerhasil melakukan perbesaran gambar!");
                
                } catch (NumberFormatException e) {

                System.out.println("\nInput skala tidak valid.");
                System.out.println("Gagal melakukan perbesaran gambar.");
                }

            } else {
                System.out.print("Gagal melakukan perbesaran gambar.");
            }
        }
        System.out.println("\nSilakan tekan ENTER untuk kembali.");
        String BACK = scanner.nextLine();

    return 0;
    } 
}