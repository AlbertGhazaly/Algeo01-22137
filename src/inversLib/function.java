package inversLib;
import java.text.DecimalFormat;

public class function {

    public static void displayMatrix(double[][] matrix){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[");

            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] >= 0){
                    System.out.print(" " + decimalFormat.format(matrix[i][j]) + " ");
                } else {
                    System.out.print(decimalFormat.format(matrix[i][j]) + " ");
                } 
            }

            System.out.print("]\n");
        }
    }

    public static double[][] transpose(double[][] matrix){

        int row = matrix.length, col = matrix[0].length;
        double[][] mT = new double[row][col];

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){

                mT[i][j] = matrix[j][i];
            }
        }

        return mT;
    }

    public static double[][] scalarMult(double coefficient, double[][] matrix){

        int row = matrix.length, col = matrix[0].length;

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){

                matrix[i][j] *= coefficient;
            }
        }

        return matrix;
    }
    
}
