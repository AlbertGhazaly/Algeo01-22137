public class determinant{

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
        double[][] m = operator.copyMatrix(mat);
        double det = 1;
        int countSwitch = 0;
        while (!operator.isBelowDiagonalZero(m)){
            for (int i = 1; i < m.length; i++){
                // OBE operasi 2 baris dengan kelipatan
                if (operator.leftZero(m, i) != i){
                    operator.substractRowByFactor(m, operator.leftZero(m, i), i, operator.leftZero(m, i));
                }
            }
            for (int i = 1; i < m.length - 1; i++){
                // OBE tukar baris
                int switchRowIdx = i;
                for (int j = i+1; j < m.length; j++){
                    // Mencari baris dengan leftZero terkecil
                    if (operator.leftZero(m, i) > operator.leftZero(m, j)){
                        switchRowIdx = j;
                    }
                }
                if(switchRowIdx != i){
                    // Menukar row i dengan row dengan leftZero terkecil
                    operator.switchRow(m, i, switchRowIdx);
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
                double cof = operator.cofactorExp(m, i, 0);
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
        double[][] m = operator.copyMatrix(mat);
        if (m.length == 1){
            // Jika matrix 1x1, kembalikan elemen baris 0 kolom 0
            double det = m[0][0];
            return det;
        } else {
            // Jika tidak, reduksi baris lalu kalikan dengan determinan kofaktor
            for (int i = 1; i < m.length; i++){
                // Looping untuk 'mengurangi' baris pada kolom 1
                if (m[0][0] != 0){
                    operator.substractRowByFactor(m, 0, i, 0);
                }
            }
            double cof = operator.cofactorComb(m, 0, 0);
            double det = m[0][0] * cof; // Mengalikan elemen baris 0 kolom 0 dengan kofaktor
            return det;
        }        
    }

}
