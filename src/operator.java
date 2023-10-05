// import java.text.DecimalFormat;
import java.util.*;
import java.io.*;
public class operator {
    public static double[][] inputMatrix(){ //menerima nilai matrix -> matriks
        Scanner in = new Scanner (System.in);
        System.out.print("Masukkan jumlah baris: ");
        int row = in.nextInt();
        System.out.print("Masukkan jumlah Kolom: ");
        int col = in.nextInt();
        System.out.println("Pilih cara input matrix: ");
        System.out.println("1. File ");
        System.out.println("2. CLI");
        int opt2 = in.nextInt();
        while (opt2<=0 && opt2>2){
            System.out.print("Opsi tidak sesuai, silahkan input kembali: ");
            opt2 = in.nextInt();
        }
        if (opt2==2){
            System.out.println("Masukkan nilai matrix: ");
            double[][] mat = new double[row][col];
            for (int i=0;i<row;i++){
                for (int j=0;j<col;j++){
                    mat[i][j] = in.nextDouble();
                }
            }
            return mat;
        }else{
            try{
                Scanner scan = new Scanner (System.in);
                double[][] mat = new double[row][col];
                System.out.println("Masukkan path file yang ingin dibaca: ");
                System.out.print("PATH: ");
                File myFile = new File(scan.nextLine());
                Scanner Reader = new Scanner(myFile);
                int i = 0;
                while (Reader.hasNextLine()&&i<row) {
                    for (int j=0;j<col;j++){
                        mat[i][j] = Reader.nextDouble();
                    }
                    
                    i++;
                }
                scan.close();
                Reader.close();
                return mat;
            }catch (FileNotFoundException e) {
                System.out.println("ERROR, can't read FILE");
                System.out.println("Membaca matrix terpaksa melalui CLI");
                System.out.println("Masukkan nilai matrix: ");
                double[][] mat = new double[row][col];
                for (int i=0;i<row;i++){
                    for (int j=0;j<col;j++){
                        mat[i][j] = in.nextDouble();
                    }
                }
                return mat;
            }
        }
    }
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

    public static void displayMatrixInt(int[][] mat){ //tampilin matriks
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
    public static boolean isColZero(double[][] m1, int i){
        for (int j=0;j<m1.length; j++){
            if (m1[j][i] !=0){
                return false;
            }
        }
        return true;
    }
    public static double[][] delLastRow(double[][] m, int n){ // delete last row n times
        double[][] m1 = new double[m.length-n][m[0].length];
        for (int i=0;i<m1.length;i++){
            for (int j=0; j<m1[0].length;j++){
                m1[i][j] = m[i][j];
            }
        }
        return m1;
    }
    public static double[][] insertLastRow(double[][] m,int n){
        double[][] m1 = new double[m.length+n][m[0].length];
        for (int i=0;i<m.length;i++){
            for (int j=0; j<m1[0].length;j++){
                m1[i][j] = m[i][j];
            }
        }
        return m1;
    }
    public static double[][] insertColLeft(double[][] m, double[][] insM){
        double[][] m1 = new double[m.length][m[0].length+insM[0].length];
        for (int i=0;i<insM[0].length;i++){
            for (int j=0;j<m1.length;j++){
                m1[j][i] = insM[j][i];
            }
        }
        for (int i=insM[0].length;i<m1[0].length;i++){
            for (int j=0;j<m1.length;j++){
                m1[j][i] = m[j][i-insM[0].length];
            }
        }
        return m1;
    }
        public static boolean isRowZero(double[][] m, int row){
        int col = m[0].length;
        for (int i=0;i<col;i++){
            if (m[row][i]!=0){
                return false;
            }
        }
        return true;
    }
    public static double[][] echelonRow(double[][] m){ // ubah matriks ke matriks eselon baris
        double[][] zero = new double[m.length][1];
        double[][] m1 = copyMatrix(m);
        int colZer = 0;
        while (leftZero(m1, 0)!=0){
            m1 = delColAt(m1, 0);
            colZer += 1;
        }
        while (!isBelowDiagonalZero(m1)){
            for (int i = 1; i < m1.length; i++){
                if (leftZero(m1, i) < i){
                    substractRowByFactor(m1, leftZero(m1, i), i, leftZero(m1, i));
                }
            }
            for (int i = 1; i < m1.length - 1; i++){
                int switchRowIdx = i;
                for (int j = i+1; j < m1.length; j++){
                    if (leftZero(m1, i) > leftZero(m1, j)){
                        switchRowIdx = j;
                    }
                }
                if(switchRowIdx != i){
                    switchRow(m1, i, switchRowIdx);
                }
            }
        }
        int leftZeroId;
        for (int i=0;i<m1.length;i++){
            leftZeroId = leftZero(m1, i);
            if (leftZeroId!=m1[0].length){
                if (m1[i][leftZeroId]!=1){
                    double  leftzero = m1[i][leftZeroId];
                    for (int j =leftZeroId;j<m1[0].length;j++){
                        m1[i][j] = m1[i][j]/leftzero;
                    }
                }
            }
        }
        for (int i=0;i<colZer;i++){
            m1 = insertColLeft(m1, zero);
        }
        return m1;
    }

    public static double[][] echelonRowReduction(double[][] m1){ //ubah matriks ke matriks eselon baris tereduksi
        m1 = echelonRow(m1);
        int row = m1.length;
        int leftZeroId;
        for (int i=0;i<row;i++){
            if (!isRowZero(m1, i)){
                leftZeroId = leftZero(m1, i);
                for (int j=0;j<i;j++){
                    substractRowByFactor(m1, i, j, leftZeroId);
                }
            }
        }
         for (int i = 1; i < m1.length - 1; i++){
                int switchRowIdx = i;
                for (int j = i+1; j < m1.length; j++){
                    if (leftZero(m1, i) > leftZero(m1, j)){
                        switchRowIdx = j;
                    }
                }
                if(switchRowIdx != i){
                    switchRow(m1, i, switchRowIdx);
                }
            }
        return m1;
    }
    public static double[][] swapCol(double[][] mat1, double[][] rowMat, int idX){ // menukar kolom matriks
        double[][] cMat = copyMatrix(mat1);
        int row = mat1.length;
        for (int i=0;i<row;i++){
            cMat[i][idX] = rowMat[i][0];
        }
        return cMat;
    }

    public static double[][] copyMatrix(double[][] m1){ 
        /*
        -------- SPESIFIKASI -------- 
        Menghasilkan salinan matrix m1
        -------- KAMUS LOKAL --------
        i, j : integer
        copy : matrix [0..m1.rowEff-1][0..m1.colEff-1] of double
        -------- REALISASI --------
        */
        double[][] temp = new double[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++){
            for (int j = 0; j < m1[0].length; j++){
                temp[i][j] = m1[i][j];
            }
        }
        return temp;
    }

    public static int leftZero(double[][] m1, int row){
        /*
        -------- SPESIFIKASI -------- 
        Menghitung jumlah angka 0 yang berada di sebelah kiri sebelum ada elemen non-nol
        -------- KAMUS LOKAL --------
        i, count : integer
        -------- REALISASI --------
        */
        int i = 0;
        int count = 0;
        while (i < m1[0].length){
            if (m1[row][i] == 0){
                count += 1;
            } else {
                return count;
            }
            i += 1;
        }
        return count;
    }

    public static void switchRow(double[][] m1, int r1, int r2){
        /*
        -------- SPESIFIKASI -------- 
        I.S. : matrix m1 terdefinisi
        F.S. : baris R1 dan R2 matrix m1 ditukar
        -------- KAMUS LOKAL --------
        i : integer
        temp : double
        -------- REALISASI --------
        */        
        double temp;
        for (int i = 0; i < m1[0].length; i++){
            temp = m1[r1][i];
            m1[r1][i] = m1[r2][i];
            m1[r2][i] = temp;
        }
    }

    public static boolean isBelowDiagonalZero(double[][] m1){
        /*
        -------- SPESIFIKASI -------- 
        Mengecek apakah semua elemen di bawah diagonal utama 0, mengembalikan true jika ya, false jika tidak
        -------- KAMUS LOKAL --------
        i, j : integer
        -------- REALISASI --------
        */      
        for (int i = 1; i < m1.length; i++){
            for (int j = 0; j < i; j++){
                if (m1[i][j] != 0){
                    return false;
                }
            }
            for (int j = 0; j < leftZero(m1, i); j++){
                if (m1[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void substractRowByFactor(double[][] m1, int r1, int r2, int startCol){
        /*
        -------- SPESIFIKASI -------- 
        I.S. : matrix m1 terdefinisi
        F.S. : baris r2 matrix m1 berkurang dengan rumus r2 = r2 - factor * r1 mulai kolom startCol
        -------- KAMUS LOKAL --------
        factor : double
        i : integer
        -------- REALISASI --------
        */  
        double factor = m1[r2][startCol] / m1[r1][startCol];
        for (int i = startCol; i < m1[0].length; i++){
            m1[r2][i] -= factor * m1[r1][i];
        }
    }

    public static double cofactorExp(double[][] m1, int rowExc, int colExc){
        /*
        -------- SPESIFIKASI -------- 
        Menghasilkan kofaktor baris rowExc dan kolom colExc untuk fungsi detCofactorExp
        -------- KAMUS LOKAL --------
        i, j, row, col : integer
        cof : matrix [0..m1.rowEff-1][0..m1.colEff-1] of double 
        det : double
        -------- REALISASI --------
        */  
        double[][] cof = new double[m1.length - 1][m1[0].length - 1];
        for (int i = 0; i < m1.length; i++){
            // Looping untuk mengisi matrix cof dari matrix m1 tanpa elemen-elemen pada baris rowExc dan kolom colExc
            for (int j = 0; j < m1[0].length; j++){
                if ((i != rowExc) && (j != colExc)){
                    int row = i;
                    int col = j;
                    if (i > rowExc){
                        row -= 1;
                    }
                    if (j > colExc){
                        col -= 1;
                    }
                    cof[row][col] = m1[i][j];
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

    public static double cofactorComb(double[][] m1, int rowExc, int colExc){
        /*
        -------- SPESIFIKASI -------- 
        Menghasilkan kofaktor baris rowExc dan kolom colExc untuk fungsi detCombination
        -------- KAMUS LOKAL --------
        i, j, row, col : integer
        cof : matrix [0..m1.rowEff-1][0..m1.colEff-1] of double 
        det : double
        -------- REALISASI --------
        */  
        double[][] cof = new double[m1.length - 1][m1[0].length - 1];
        for (int i = 0; i < m1.length; i++){
            // Looping untuk mengisi matrix cof dari matrix m1 tanpa elemen-elemen pada baris rowExc dan kolom colExc
            for (int j = 0; j < m1[0].length; j++){
                if ((i != rowExc) && (j != colExc)){
                    int row = i;
                    int col = j;
                    if (i > rowExc){
                        row -= 1;
                    }
                    if (j > colExc){
                        col -= 1;
                    }
                    cof[row][col] = m1[i][j];
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
    //     double[][] m1 = {{1,1,1,6,2},
    //                     {1,2,3,14,4},
    //                     {2,3,2,14,5}};
    //     m1 = echelonRowReduction(m1);
    //     displayMatrix((m1));
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

    public static double[][] transpose(double[][] matrix){ // mentraspose matriks

        int row = matrix.length, col = matrix[0].length;
        double[][] mT = new double[row][col];

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){

                mT[i][j] = matrix[j][i];
            }
        }

        return mT;
    }

    public static double[][] scalarMult(double coefficient, double[][] matrix){ // mengkalikan matriks dengan skalar

        int row = matrix.length, col = matrix[0].length;

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){

                matrix[i][j] *= coefficient;
            }
        }

        return matrix;
    }
    public static boolean isNoSolution(double[][] mat){ // matriks gada solusi
        double[][] echelonRow_mat = echelonRow(mat);
        int row = echelonRow_mat.length;
        int col = echelonRow_mat[0].length;
        if (echelonRow_mat[row-1][col-1]!=0){
            for (int i=0;i<col-1;i++){
                if (echelonRow_mat[row-1][i]!=0){
                    return false;
                }
            }
            displayMatrix(echelonRow_mat);
            return true;
        }else{
            return false;
        }
    }

    public static boolean isSolutionParametric(double[][] mat){ // solusi parametrik
        double[][] echelonRow_mat = echelonRowReduction(mat);
        int row = mat.length;
        int col = echelonRow_mat[0].length;
        boolean flag = false; // sebagai penanda 
        int n = 0; // jumlah baris dimana hanya berisi 0 tiap kolom
        int j = row-1;
        while (!flag){ // menghitung jumlah n
            if (echelonRow_mat[j][col-1]==0 ){
                for (int i=0;i<col-1;i++){
                    if (echelonRow_mat[j][i]!=0){
                        flag = true;
                    }
                }
                if (!flag){
                    n +=1;
                }
            }else{
                flag = true;
            }
            j-=1;
        } 
        echelonRow_mat = delLastRow(echelonRow_mat, n);
        row = echelonRow_mat.length;
        int[] arr_is_par = new int[row];
        int[] arr_isnot_par = new int[col-row-1];
        double[][] solusi = new double[row][col-row];
        for (int i=0;i<row;i++){
            arr_is_par[i] = leftZero(echelonRow_mat, i);
        }
        int col_not_par = 0;
        int id_np = 0;
        while (col_not_par<col-1){
            if (!(isIn(col_not_par,arr_is_par))){
                arr_isnot_par[id_np] = col_not_par;
                id_np += 1;
            }
            col_not_par+=1;
        }
        for (int i=echelonRow_mat.length-1;i>=0;i--){
            int leftZeroId = leftZero(echelonRow_mat, i);
            for (int k = leftZeroId;k<col;k++){
                if (k==col-1){
                    solusi[i][0] = echelonRow_mat[i][k];
                }else{
                    if (isIn(k, arr_isnot_par)){
                        int l = 0;
                        while (arr_isnot_par[l]!=k){
                            l += 1;
                        }
                        solusi[i][l+1] -= echelonRow_mat[i][k];
                    }
                }
            }
        }
        
        if (row<col-1){ // menentukan jenis solusi
            System.out.println("Matriks: ");
            echelonRow_mat = insertLastRow(echelonRow_mat, n);
            row += n;
            displayMatrix(echelonRow_mat);
            if (n>0){
                for (int i=0;i<n;i++){
                    System.out.println("baris matrix eselon baris ke-"+(row-i)+" bernilai 0 semua");
                }
            }else{
                System.out.println("persamaan yang dimasukkan tidak cukup");
                
            }
            System.out.println("Solusi menjadi: ");
            row -= n;
            for (int i =0;i<arr_is_par.length;i++){
                System.out.print("X"+(arr_is_par[i]+1)+": "+solusi[i][0]+" ");
                for (int k = 1;k<solusi[0].length;k++){
                    if (solusi[i][k]>0){
                            System.out.print("+"+solusi[i][k]+"X"+(arr_isnot_par[k-1]+1)+" ");   
                        }else if (solusi[i][k]<0){
                            System.out.print(+solusi[i][k]+"X"+(arr_isnot_par[k-1]+1)+" ");
                        }
                    }
                System.out.println();
            }

            return true;
        }else{
            return false;
        }
    }
    public static boolean isIn(int a, int[] b){
        for (int i=0;i<b.length;i++){
            if (a==b[i]){
                return true;
            }
        }
        return false;
    }

}



/*
 1 1 1 1 10
 1 2 3 4 30
 1 2 1 2 16
 2 2 1 3 21

3 6 6 12 26
6 14 11 26 55
6 11 13 23 51
12 26 23 50 107

 3 6 4 12 26
 6 14 13 26 55
 6 7 14 23 51
 12 2 23 50 107

 3 6 6 12 1 26
6 14 11 26 2 55
6 11 13 23 3 51

1 1 1 3
1 1 2 4
1 1 3 5

1 1 1 1
1 1 1 1
1 1 1 1
 */
