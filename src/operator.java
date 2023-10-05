// import java.text.DecimalFormat;
import java.util.*;
import java.io.*;
public class operator {
    public static double[][] inputMatrix(){ //menerima nilai matrix -> matriks
        Scanner in = new Scanner (System.in);

        System.out.println("\n-Dimensi Matriks-");
        System.out.print("Masukkan jumlah baris: ");
        int row = in.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        int col = in.nextInt();

        System.out.println("\n-Metode Input Matriks-");
        System.out.println("1. File .txt ");
        System.out.println("2. Command line ");
        System.out.print("\nPilih metode (1/2): ");
    
        int opt2 = in.nextInt();
        while (opt2<=0 && opt2>2){
            System.out.print("Opsi tidak sesuai, silahkan input kembali: ");
            opt2 = in.nextInt();
        }
        if (opt2==2){
            System.out.println("\nInput matriks: ");
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
                System.out.println("\nMasukkan path file yang ingin dibaca: ");
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
                System.out.println("\nERROR, can't read FILE");
                System.out.println("Membaca matrix terpaksa melalui CLI");
                System.out.println("\nInput matriks: ");
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

    public static double[][][] inputMatrixBicubic(){ //menerima nilai matrix -> matriks

        Scanner in = new Scanner (System.in);

        System.out.println("\n-Dimensi Matriks-");
        System.out.print("Masukkan jumlah baris: ");
        int row = in.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        int col = in.nextInt();

        System.out.println("\n-Metode Input Matriks-");
        System.out.println("1. File .txt ");
        System.out.println("2. Command line ");
        System.out.print("\nPilih metode (1/2): ");
    
        int opt2 = in.nextInt();
        while (opt2<=0 && opt2>2){
            System.out.print("Opsi tidak sesuai, silahkan input kembali: ");
            opt2 = in.nextInt();
        }
        if (opt2==2){
            System.out.println("\nInput matriks: ");
            double[][] mat = new double[row][col];

            for (int i=0;i<row;i++){
                for (int j=0;j<col;j++){
                    mat[i][j] = in.nextDouble();
                }
            }

            double[][][] output = {mat, {{0},{0},{0}}};
            return output;
        }else{
            try{
                Scanner scan = new Scanner (System.in);
                double[][] mat = new double[row][col];
                System.out.println("\nMasukkan path file yang ingin dibaca: ");
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

                double[][][] output = {mat,{{Reader.nextDouble()},{Reader.nextDouble()},{1}}};

                return output;
            }catch (FileNotFoundException e) {
                System.out.println("\nERROR, can't read FILE");
                System.out.println("Membaca matrix terpaksa melalui CLI");
                System.out.println("\nInput matriks: ");
                double[][] mat = new double[row][col];
                for (int i=0;i<row;i++){
                    for (int j=0;j<col;j++){
                        mat[i][j] = in.nextDouble();
                    }
                }
                double[][][] output = {mat, {{0},{0},{0}}};
                return output;
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
            if (leftZeroId!=m[0].length){
                if (m[i][leftZeroId]!=1){
                double  leftzero = m[i][leftZeroId];
                for (int j =leftZeroId;j<m[0].length;j++){
                    m[i][j] = m[i][j]/leftzero;
                }
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
    public static double[][] swapCol(double[][] mat1, double[][] rowMat, int idX){ // menukar kolom matriks
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
        int row = mat.length;
        int col = mat[0].length;
        if (echelonRow_mat[row-1][col-1]!=0){
            for (int i=0;i<col-1;i++){
                if (echelonRow_mat[row-1][i]!=0){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }
    public static boolean isSolutionParametric(double[][] mat){ // solusi parametrik
        double[][] echelonRow_mat = echelonRow(mat);
        int row =0;
        for (int i=0;i<mat.length;i++){
            if (echelonRow_mat[i][i]==1){
                row +=1;
            }
        }
        int col = mat[0].length;
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
        int row_not_one = 0;
        for (int i=0;i<echelonRow_mat.length;i++){
            if (echelonRow_mat[i][i]==0 && echelonRow_mat[i][col-1]!=0){
                row_not_one += 1;
            }
        }
        int id = 0;
        int[] arr_not_one = new int[row_not_one];
        for (int i=0;i<echelonRow_mat.length;i++){
            if (echelonRow_mat[i][i]==0 && echelonRow_mat[i][col-1]!=0){
                arr_not_one[id] = i;
                id +=1;
            }
        }
        double[][] solusi = new double[row][(col-row-1)+1+n+row_not_one];
        for (int a=row-1;a>=0;a--){
            if (!(isIn(a, arr_not_one))){
                double sum = 0;
                int x = 0;
                for (int b=n+(col-row-1);b>0;b--){ //hitung solusi parametrik
                        solusi[a][b+row_not_one] -= echelonRow_mat[a][col-2-x];
                        x += 1;
                }

                for (int b=col-2-(col-row-1+n);b>a;b--){ //col-2 -> col-2-n; c = n->0; col - (row+1)+n
                    for (int c = n+(col-row-1);c>0;c--){
                        if (a!=row-1){
                            solusi[a][c] -= (echelonRow_mat[a][b]*solusi[b][c]);
                        }
                    }
                    if (isIn(b, arr_not_one)){
                        System.out.println("ada");
                        id = 0;
                        int i=0;
                        while(arr_not_one[i]!=b){
                            id +=1;
                            i +=i;
                        }
                        id +=1;
                        solusi[a][id] -= echelonRow_mat[a][b];
                    }else{
                        sum += echelonRow_mat[a][b]*solusi[b][0];
                    }
                }
                solusi[a][0]= echelonRow_mat[a][col-1]-sum;
            }

        }
        if (row-n<col-1+row_not_one){ // menentukan jenis solusi
            if (n>0){
                displayMatrix(echelonRow_mat);
                for (int i=0;i<n;i++){
                    System.out.println("baris matrix eselon baris ke-"+(row-i)+" bernilai 0 semua");
                }
            }else{
                System.out.println("persamaan yang dimasukkan tidak cukup");
                
            }
            System.out.println("Solusi menjadi: ");
            for (int i=0;i<row-n;i++){
                if (!(isIn(i, arr_not_one))){
                    System.out.print("X"+(i+1)+": "+solusi[i][0]+" ");
                    for (int k=1;k<row_not_one+1;k++){
                        if (solusi[i][k]>0){
                            System.out.print("+"+solusi[i][k]+"X"+(arr_not_one[k-1]+1)+" ");    
                        }else if (solusi[i][k]<0){
                            System.out.print(solusi[i][k]+"X"+(arr_not_one[k-1]+1)+" ");
                        }
                    }
                    for (int k =1+row_not_one;k<(col-row-1)+n+1+row_not_one;k++){
                        if (solusi[i][k]>0){
                            System.out.print("+"+solusi[i][k]+"X"+(row+k-row_not_one-n)+" ");    
                        }else if (solusi[i][k]<0){
                            System.out.print(solusi[i][k]+"X"+(row+k-row_not_one-n)+" ");
                        }
                }
                System.out.println();
                }
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
