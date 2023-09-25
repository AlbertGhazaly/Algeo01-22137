public class operator {
    public static void displayMatrix(float[][] mat){ //tampilin matriks
        int row = mat.length;
        int col = mat[0].length;
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                System.out.print(mat[i][j]+"  ");
            }
            System.out.println();
        }
    }

    public static float[][] delColAt(float[][] matrix,int colId){ // hasilin duplikat matriks yang didelete kolom colId 
        int row = matrix.length;
        int col = matrix[0].length;
        float[][] result = new float[row][col-1];
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

    public static float[][] multiplyMatrix(float[][] m1, float[][] m2){ // perkalian matriks
        float[][] newmatrixx = new float[m1.length][m2[0].length];
        float sum;
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
    
    public static float[][] takeCol(float[][] matrixx, int x){ // ambil baris dari kolom ke x
        int row = matrixx.length;
        float[][] newmatrixx = new float[row][1];
        for (int i=0;i<row;i++){
            newmatrixx[i][0] = matrixx[i][x];
        }
        return newmatrixx;
    }
    

    public static float[][] echelonRow(float[][] m){ // ubah matriks ke matriks eselon baris

        while (!DeterminantLibrary.isBelowDiagonalZero(m)){
            for (int i = 1; i < m.length; i++){
                if (DeterminantLibrary.leftZero(m, i) != i){
                    DeterminantLibrary.substractRowByFactor(m, DeterminantLibrary.leftZero(m, i), i, DeterminantLibrary.leftZero(m, i));
                }
            }
            for (int i = 1; i < m.length - 1; i++){
                int switchRowIdx = i;
                for (int j = i+1; j < m.length; j++){
                    if (DeterminantLibrary.leftZero(m, i) > DeterminantLibrary.leftZero(m, j)){
                        switchRowIdx = j;
                    }
                }
                if(switchRowIdx != i){
                    DeterminantLibrary.switchRow(m, i, switchRowIdx);
                }
            }
        }
        int leftZeroId;
        for (int i=0;i<m.length;i++){
            leftZeroId = DeterminantLibrary.leftZero(m, i);
            if (m[i][leftZeroId]!=1){
                float  leftzero = m[i][leftZeroId];
                for (int j =leftZeroId;j<m[0].length;j++){
                    m[i][j] = m[i][j]/leftzero;
                }
            }
        }
        return m;
    }

    public static float[][] echelonRowReduction(float[][] m){ //ubah matriks ke matriks eselon baris tereduksi
        m = echelonRow(m);
        int row = m.length;
        int leftZeroId;
        for (int i=0;i<row;i++){
            leftZeroId = DeterminantLibrary.leftZero(m, i);
            for (int j=0;j<i;j++){
                DeterminantLibrary.substractRowByFactor(m, i, j, leftZeroId);
            }
        }
        return m;
    }

    // public static void main(String[] args){
    //     float[][] m = {{1,1,1,6,2},
    //                     {1,2,3,14,4},
    //                     {2,3,2,14,5}};
    //     m = echelonRowReduction(m);
    //     displayMatrix((m));
    // }
  }


