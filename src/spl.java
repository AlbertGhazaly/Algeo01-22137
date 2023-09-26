public class spl {

    public static double[][] inverse_Spl(double[][] matrix){ // menghasilkan solusi SPL menggunakan metode matrix balikan
        double[][] cMat = operator.copyMatrix(matrix);
        int col = cMat[0].length;

        double[][] inv = invers.identity(operator.delColAt(cMat,col-1));
        double[][] b_cMat = operator.takeCol(cMat, col-1);
        operator.displayMatrix(inv);

        double[][] result_Equation_cMat = operator.multiplyMatrix(inv, b_cMat);
        return result_Equation_cMat;

    }
    
    public static double[][] gauss_Spl(double[][] matrix){ // menghasilkan solusi SPL menggunakan metode Gauss
        double[][] cmat = operator.copyMatrix(matrix);
        int row = cmat.length;
        int col = row+1;
        double[][] result = new double[row][1];
        cmat = operator.echelonRow(cmat);
        int subs;
        int distRow;
        for (int i=row-1;i>=0;i--){
            subs = 0;
            for (int j=i;j<row-1;j++){
                distRow = row-1-j; // jarak baris j ke baris paling bawah (row-1)
                subs += cmat[i][col-1-distRow]*result[row-distRow][0];
            }
            result[i][0] = (cmat[i][col-1]-subs)/cmat[i][i];
        }
        return result;
    }

    public static double[][] gauss_Jordan_Spl(double[][] matrix){ // Menghasilkan solusi SPL menggunakan metode Gauss-Jordan
        double[][] cmat = operator.copyMatrix(matrix);
        int row = cmat.length;
        int col = cmat[0].length;
        cmat = operator.echelonRowReduction(cmat);
        double[][] result = new double[row][1];
        for (int i=0;i<row;i++){
            result[i][0] = cmat[i][col-1];
        }
        return result;
    }

    public static double[][] crammer_Spl(double[][] matrix){ // Menghasilkan solusi SPL menggunakan metode Crammer
        int row = matrix.length;
        double det;
        double[][] m1 = operator.delColAt(matrix, matrix[0].length-1);
        double[][] m2 = operator.takeCol(matrix, matrix[0].length-1);
        double[][] result = new double[row][1];
        double[][] dummy = new double[m1.length][m1[0].length];
        double detMatAwal = determinant.detRowReduction(matrix);
        operator.displayMatrix(m1);
        operator.displayMatrix(m2);
        for (int i=0;i<row;i++){
            dummy = operator.swapCol(m1, m2, i);
            det = determinant.detRowReduction(dummy)/detMatAwal;
            result[i][0] = det;

        }
        return result;
    }
    // public static void main(String[] args){
    //     double[][] cmat = {{1,1,1,6},
    //     {1,2,3,14},{2,3,2,14}};
    //     double[][] res = crammer_Spl(cmat);
    //     operator.displayMatrix(cmat);
    //     operator.displayMatrix(res);


    // }
 }

