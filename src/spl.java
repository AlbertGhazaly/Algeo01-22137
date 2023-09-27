public class spl {

    public static double[][] inverse_Spl(double[][] matrix){ // menghasilkan solusi SPL menggunakan metode matrix balikan
        double[][] cMat = operator.copyMatrix(matrix);
        int col = cMat[0].length;

        double[][] inv = invers.identity(operator.delColAt(cMat,col-1));
        double[][] b_cMat = operator.takeCol(cMat, col-1);

        double[][] result_Equation_cMat = operator.multiplyMatrix(inv, b_cMat);
        return result_Equation_cMat;

    }
    
    public static double[][] gauss_Spl(double[][] matrix){ // menghasilkan solusi SPL menggunakan metode Gauss
        double[][] cmat = operator.copyMatrix(matrix);
        int row = cmat.length;
        int col = row+1;
        double[][] result = new double[row][1];
        cmat = operator.echelonRow(cmat);
        double subs;
        operator.displayMatrix(cmat);
        for (int i=row-1;i>=0;i--){
            subs = 0;
            for (int j=0;j<row;j++){
                subs += result[j][0]*cmat[i][j];
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
        for (int i=0;i<row;i++){
            dummy = operator.swapCol(m1, m2, i);
            det = determinant.detRowReduction(dummy)/detMatAwal;
            result[i][0] = det;

        }
        return result;
    }   

 }

