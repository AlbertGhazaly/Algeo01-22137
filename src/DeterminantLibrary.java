public class DeterminantLibrary{
    
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
        double det = detCofactorExp(cof); // Menghitung determinan matriks cof
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
        double det = detCombination(cof); // Menghitung determinan matriks cof
        if ((rowExc + colExc) % 2 != 0){
            // Optimalisasi nilai kofaktor
            det *= -1;
        }
        return det;
    }

    public static void displayMatrix(double[][] m){
        /*
        -------- SPESIFIKASI -------- 
        I.S. : matrix m terdefinisi
        F.S. : pada layar tampil seluruh elemen matrix m
        -------- KAMUS LOKAL --------
        factor : double
        i, j : integer
        -------- REALISASI --------
        */
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m[0].length; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double detRowReduction(double[][] mat){
        /*
        -------- SPESIFIKASI -------- 
        Mencari determinan matrix m dengan metode reduksi baris
        -------- KAMUS LOKAL --------
        i, j, countSwitch : integer
        factor, det : double
        m : matrix [0..mat.rowEff-1][mat.colEff-1] of double
        -------- REALISASI --------
        Menggunakan function/procedure tambahan: leftZero, switchRow, isUpperRightTriangle, subtractRowByFactor, copyMatrix
        */
        double[][] m = copyMatrix(mat);
        double det = 1;
        int countSwitch = 0;
        while (!isBelowDiagonalZero(m)){
            for (int i = 1; i < m.length; i++){
                // OBE operasi 2 baris dengan kelipatan
                if (leftZero(m, i) != i){
                    substractRowByFactor(m, leftZero(m, i), i, leftZero(m, i));
                }
            }
            for (int i = 1; i < m.length - 1; i++){
                // OBE tukar baris
                int switchRowIdx = i;
                for (int j = i+1; j < m.length; j++){
                    // Mencari baris dengan leftZero terkecil
                    if (leftZero(m, i) > leftZero(m, j)){
                        switchRowIdx = j;
                    }
                }
                if(switchRowIdx != i){
                    // Menukar row i dengan row dengan leftZero terkecil
                    switchRow(m, i, switchRowIdx);
                    countSwitch += 1;
                }
            }
        }
        for (int i = 0; i < m.length; i++){
            // Mengalikan diagonal utama setelah baris direduksi
            det *= m[i][i];
        }
        if (countSwitch % 2 == 0){
            // Jika ditukar genap kali, determinan dikali 1
            countSwitch = 1;
        } else {
            //Jika ditukar ganjil kali, determinan dikali -1
            countSwitch = -1;
        }
        return det * countSwitch;
    }

    public static double detCofactorExp(double[][] m){
        /*
        -------- SPESIFIKASI -------- 
        Mencari determinan matrix m dengan metode ekspansi kofaktor dengan baris pada kolom 1
        -------- KAMUS LOKAL --------
        det, val, cof : double
        i : integer
        -------- REALISASI --------
        Menggunakan function/procedure tambahan: cofactorExp
        */
        if (m.length == 1){
            // Jika matrix 1x1, kembalikan elemen baris 0 kolom 0
            double det = m[0][0];
            return det;
        } else {
            // Jika tidak, gunakan kofaktor
            double det = 0;
            for (int i = 0; i < m.length; i++){
                // Looping perkalian elemen dengan determinan kofaktor
                double cof = cofactorExp(m, i, 0);
                double val = m[i][0] * cof; // Mengalikan elemen dengan kofaktor
                det += val; // Menjumlahkan seluruh hasil perkalian elemen dengan kofaktor
            }
            return det;
        }
    }

    public static double detCombination(double[][] mat){
        /*
        -------- SPESIFIKASI -------- 
        Menghitung determinan matrix m dengan metode kombinasi reduksi baris pada kolom 1 dan ekspansi kofaktor
        -------- KAMUS LOKAL --------
        i, j : integer
        factor, det, cof : double
        m : matrix [0..mat.rowEff-1][0..mat.colEff-1] of double
        -------- REALISASI --------
        Menggunakan function/procedure tambahan: subtractRowByFactor, cofactorComb, copyMatrix
        */
        double[][] m = copyMatrix(mat);
        if (m.length == 1){
            // Jika matrix 1x1, kembalikan elemen baris 0 kolom 0
            double det = m[0][0];
            return det;
        } else {
            // Jika tidak, reduksi baris lalu kalikan dengan determinan kofaktor
            for (int i = 1; i < m.length; i++){
                // Looping untuk 'mengurangi' baris pada kolom 1
                substractRowByFactor(m, 0, i, 0);
            }
            double cof = cofactorComb(m, 0, 0);
            double det = m[0][0] * cof; // Mengalikan elemen baris 0 kolom 0 dengan kofaktor
            return det;
        }        
    }

}
