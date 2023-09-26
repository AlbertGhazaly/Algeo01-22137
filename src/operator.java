// import java.text.DecimalFormat;

public class operator {
    public static void displayMatrix(double[][] mat){ //tampilin matriks
        int row = mat.length;
        int col = mat[0].length;
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                System.out.print(mat[i][j]+"  ");
            }
            System.out.println();
        }
    }

    public static double[][] delColAt(double[][] matrix,int colId){ // hasilin duplikat matriks yang didelete kolom colId 
        int row = matrix.length;
        int col = matrix[0].length;
        double[][] result = new double[row][col-1];
        for (int i=0;i<row;i++){
            for (int j=0;j<colId;j++){
                result[i][j] = matrix[i][j];

            }
        }
        for (int i=0;i<row;i++){
            for (int j=colId;j<col-1;j++){
                result[i][j] = matrix[i][j+1];
            }
        }
        return result;
    }

    public static double[][] multiplyMatrix(double[][] m1, double[][] m2){ // perkalian matriks
        double[][] newmatrixx = new double[m1.length][m2[0].length];
        double sum;
        for (int i=0;i<m1.length;i++){
            for (int j=0;j<m2[0].length;j++){
                sum = 0;
                for (int k=0;k<m1[0].length;k++)    {
                    sum += m1[i][k]*m2[k][j];
                }
                newmatrixx[i][j] = sum;
            }
        }
        return newmatrixx;
    }
    
    public static double[][] takeCol(double[][] matrixx, int x){ // ambil baris dari kolom ke x
        int row = matrixx.length;
        double[][] newmatrixx = new double[row][1];
        for (int i=0;i<row;i++){
            newmatrixx[i][0] = matrixx[i][x];
        }
        return newmatrixx;
    }
    

    public static double[][] echelonRow(double[][] m){ // ubah matriks ke matriks eselon baris

        while (!isBelowDiagonalZero(m)){
            for (int i = 1; i < m.length; i++){
                if (leftZero(m, i) != i){
                    substractRowByFactor(m, leftZero(m, i), i, leftZero(m, i));
                }
            }
            for (int i = 1; i < m.length - 1; i++){
                int switchRowIdx = i;
                for (int j = i+1; j < m.length; j++){
                    if (leftZero(m, i) > leftZero(m, j)){
                        switchRowIdx = j;
                    }
                }
                if(switchRowIdx != i){
                    switchRow(m, i, switchRowIdx);
                }
            }
        }
        int leftZeroId;
        for (int i=0;i<m.length;i++){
            leftZeroId = leftZero(m, i);
            if (m[i][leftZeroId]!=1){
                double  leftzero = m[i][leftZeroId];
                for (int j =leftZeroId;j<m[0].length;j++){
                    m[i][j] = m[i][j]/leftzero;
                }
            }
        }
        return m;
    }

    public static double[][] echelonRowReduction(double[][] m){ //ubah matriks ke matriks eselon baris tereduksi
        m = echelonRow(m);
        int row = m.length;
        int leftZeroId;
        for (int i=0;i<row;i++){
            leftZeroId = leftZero(m, i);
            for (int j=0;j<i;j++){
                substractRowByFactor(m, i, j, leftZeroId);
            }
        }
        return m;
    }
    public static double[][] swapCol(double[][] mat1, double[][] rowMat, int idX){
        double[][] cMat = copyMatrix(mat1);
        int row = mat1.length;
        for (int i=0;i<row;i++){
            cMat[i][idX] = rowMat[i][0];
        }
        return cMat;
    }

    public static double[][] copyMatrix(double[][] m){
        /*
        -------- SPESIFIKASI -------- 
        Menghasilkan salinan matrix m
        -------- KAMUS LOKAL --------
        i, j : integer
        copy : matrix [0..m.rowEff-1][0..m.colEff-1] of double
        -------- REALISASI --------
        */
        double[][] temp = new double[m.length][m[0].length];
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m[0].length; j++){
                temp[i][j] = m[i][j];
            }
        }
        return temp;
    }

    public static int leftZero(double[][] m, int row){
        /*
        -------- SPESIFIKASI -------- 
        Menghitung jumlah angka 0 yang berada di sebelah kiri sebelum ada elemen non-nol
        -------- KAMUS LOKAL --------
        i, count : integer
        -------- REALISASI --------
        */
        int i = 0;
        int count = 0;
        while (i < m[0].length){
            if (m[row][i] == 0){
                count += 1;
            } else {
                return count;
            }
            i += 1;
        }
        return count;
    }

    public static void switchRow(double[][] m, int r1, int r2){
        /*
        -------- SPESIFIKASI -------- 
        I.S. : matrix m terdefinisi
        F.S. : baris R1 dan R2 matrix m ditukar
        -------- KAMUS LOKAL --------
        i : integer
        temp : double
        -------- REALISASI --------
        */        
        double temp;
        for (int i = 0; i < m[0].length; i++){
            temp = m[r1][i];
            m[r1][i] = m[r2][i];
            m[r2][i] = temp;
        }
    }

    public static boolean isBelowDiagonalZero(double[][] m){
        /*
        -------- SPESIFIKASI -------- 
        Mengecek apakah semua elemen di bawah diagonal utama 0, mengembalikan true jika ya, false jika tidak
        -------- KAMUS LOKAL --------
        i, j : integer
        -------- REALISASI --------
        */      
        for (int i = 1; i < m.length; i++){
            for (int j = 0; j < i; j++){
                if (m[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void substractRowByFactor(double[][] m, int r1, int r2, int startCol){
        /*
        -------- SPESIFIKASI -------- 
        I.S. : matrix m terdefinisi
        F.S. : baris r2 matrix m berkurang dengan rumus r2 = r2 - factor * r1 mulai kolom startCol
        -------- KAMUS LOKAL --------
        factor : double
        i : integer
        -------- REALISASI --------
        */  
        double factor = m[r2][startCol] / m[r1][startCol];
        for (int i = startCol; i < m[0].length; i++){
            m[r2][i] -= factor * m[r1][i];
        }
    }

    public static double cofactorExp(double[][] m, int rowExc, int colExc){
        /*
        -------- SPESIFIKASI -------- 
        Menghasilkan kofaktor baris rowExc dan kolom colExc untuk fungsi detCofactorExp
        -------- KAMUS LOKAL --------
        i, j, row, col : integer
        cof : matrix [0..m.rowEff-1][0..m.colEff-1] of double 
        det : double
        -------- REALISASI --------
        */  
        double[][] cof = new double[m.length - 1][m[0].length - 1];
        for (int i = 0; i < m.length; i++){
            // Looping untuk mengisi matrix cof dari matrix m tanpa elemen-elemen pada baris rowExc dan kolom colExc
            for (int j = 0; j < m[0].length; j++){
                if ((i != rowExc) && (j != colExc)){
                    int row = i;
                    int col = j;
                    if (i > rowExc){
                        row -= 1;
                    }
                    if (j > colExc){
                        col -= 1;
                    }
                    cof[row][col] = m[i][j];
                }
            }
        }
        double det = determinant.detCofactorExp(cof); // Menghitung determinan matriks cof
        if ((rowExc + colExc) % 2 != 0){
            // Optimalisasi nilai kofaktor
            det *= -1;
        }
        return det;
    }

    public static double cofactorComb(double[][] m, int rowExc, int colExc){
        /*
        -------- SPESIFIKASI -------- 
        Menghasilkan kofaktor baris rowExc dan kolom colExc untuk fungsi detCombination
        -------- KAMUS LOKAL --------
        i, j, row, col : integer
        cof : matrix [0..m.rowEff-1][0..m.colEff-1] of double 
        det : double
        -------- REALISASI --------
        */  
        double[][] cof = new double[m.length - 1][m[0].length - 1];
        for (int i = 0; i < m.length; i++){
            // Looping untuk mengisi matrix cof dari matrix m tanpa elemen-elemen pada baris rowExc dan kolom colExc
            for (int j = 0; j < m[0].length; j++){
                if ((i != rowExc) && (j != colExc)){
                    int row = i;
                    int col = j;
                    if (i > rowExc){
                        row -= 1;
                    }
                    if (j > colExc){
                        col -= 1;
                    }
                    cof[row][col] = m[i][j];
                }
            }
        }
        double det = determinant.detCombination(cof); // Menghitung determinan matriks cof
        if ((rowExc + colExc) % 2 != 0){
            // Optimalisasi nilai kofaktor
            det *= -1;
        }
        return det;
    }


    // public static void main(String[] args){
    //     double[][] m = {{1,1,1,6,2},
    //                     {1,2,3,14,4},
    //                     {2,3,2,14,5}};
    //     m = echelonRowReduction(m);
    //     displayMatrix((m));
    // }

    // public static void displayMatrix(double[][] matrix){
    //     DecimalFormat decimalFormat = new DecimalFormat("0.00");

    //     for (int i = 0; i < matrix.length; i++) {
    //         System.out.print("[");

    //         for (int j = 0; j < matrix[0].length; j++) {
    //             if (matrix[i][j] >= 0){
    //                 System.out.print(" " + decimalFormat.format(matrix[i][j]) + " ");
    //             } else {
    //                 System.out.print(decimalFormat.format(matrix[i][j]) + " ");
    //             } 
    //         }

    //         System.out.print("]\n");
    //     }
    // }

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


