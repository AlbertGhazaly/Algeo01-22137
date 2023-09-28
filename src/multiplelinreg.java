public class multiplelinreg{

    public static double[][] multipleLinReg(double[][] m){
        /*
        -------- SPESIFIKASI -------- 
        Menghasilkan solusi regresi linear berganda dalam bentuk matriks n x 1 di mana n adalah banyaknya peubah x + 1
        -------- KAMUS LOKAL --------
        i, j, k : integer
        El1, El2, val : double
        matrixB : matrix [0..m.colEff][0..m.colEff+1] of double
        res : matrix [0..m.colEff-1][0..1] of double
        -------- REALISASI --------
        Menggunakan function/procedure tambahan: gauss_Spl, displayMatrix
        */
        double[][] matrixB = new double[m[0].length][m[0].length+1];
        double El1 = 0;
        double El2 = 0;
        for (int i = 0; i < matrixB.length; i++){ // Looping untuk mengisi elemen matrixB
            for (int j = 0; j < matrixB[0].length; j++){
                double val = 0;
                for (int k = 0; k < m.length; k++){ // Looping untuk sum of product
                    if (i == 0){
                        El1 = 1.0;
                    } else {
                        El1 = m[k][i - 1];
                    }
                    if (j == 0){
                        El2 = 1.0;
                    } else {
                        El2 = m[k][j - 1];
                    }
                    val += El1 * El2;
                }
                matrixB[i][j] = val;
            }
        }
        System.out.println("Matrix Normal Estimation: ");
        operator.displayMatrix(matrixB);
        double[][] res = spl.gauss_Spl(matrixB);
        return res;
    }

}
