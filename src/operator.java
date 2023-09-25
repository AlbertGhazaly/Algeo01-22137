public class operator {
    public static void displayMatrix(float[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if (j==col-1){
                    System.out.println(mat[i][j]);

                }else{
                     System.out.print(mat[i][j]+" ");

                }
            }
        }
    }
    public static float[][] delColAt(float[][] matrix,int colId){
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

    public static float[][] multiplyMatrix(float[][] m1, float[][] m2){
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

    public static float[][] takeRow(float[][] matrixx, int x){
        int row = matrixx.length;
        float[][] newmatrixx = new float[row][1];
        for (int i=0;i<row;i++){
            newmatrixx[i][0] = matrixx[i][x];
        }
        return newmatrixx;
    }

}

