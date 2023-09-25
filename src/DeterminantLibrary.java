public class DeterminantLibrary{

    public static int leftZero(double[][] m, int row){
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
        double temp;
        for (int i = 0; i < m[0].length; i++){
            temp = m[r1][i];
            m[r1][i] = m[r2][i];
            m[r2][i] = temp;
        }
    }

    public static boolean isBelowDiagonalZero(double[][] m){
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
        double factor = m[r2][startCol] / m[r1][startCol];
        for (int i = startCol; i < m[0].length; i++){
            m[r2][i] -= factor * m[r1][i];
        }
    }

    public static double cofactorExp(double[][] m, int rowExc, int colExc){
        double[][] cof = new double[m.length - 1][m[0].length - 1];
        for (int i = 0; i < m.length; i++){
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
        double det = detCofactorExp(cof);
        if ((rowExc + colExc) % 2 != 0){
            det *= -1;
        }
        return det;
    }

    public static double cofactorComb(double[][] m, int rowExc, int colExc){
        double[][] cof = new double[m.length - 1][m[0].length - 1];
        for (int i = 0; i < m.length; i++){
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
        double det = detCombination(cof);
        if ((rowExc + colExc) % 2 != 0){
            det *= -1;
        }
        return det;
    }

    public static void displayMatrix(double[][] m){
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m[0].length; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double detRowReduction(double[][] m){
        double det = 1;
        int countSwitch = 0;
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
                    countSwitch += 1;
                }
            }
        }
        for (int i = 0; i < m.length; i++){
            det *= m[i][i];
        }
        if (countSwitch % 2 == 0){
            countSwitch = 1;
        } else {
            countSwitch = -1;
        }
        return det * countSwitch;
    }

    public static double detCofactorExp(double[][] m){
        if (m.length == 1){
            double det = m[0][0];
            return det;
        } else {
            double det = 0;
            for (int i = 0; i < m.length; i++){
                double cof = cofactorExp(m, i, 0);
                double val = m[i][0] * cof;
                det += val;
            }
            return det;
        }
    }

    public static double detCombination(double[][] m){
        if (m.length == 1){
            double det = m[0][0];
            return det;
        } else {
            for (int i = 1; i < m.length; i++){
                substractRowByFactor(m, 0, i, 0);
            }
            double cof = cofactorComb(m, 0, 0);
            double det = m[0][0] * cof;
            return det;
        }        
    }

}