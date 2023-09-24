public class operator {
    public static float[][] delColAt(float[][] matri,int colId){
        int row = matri.length;
        int col = matri[0].length;
        float[][] result = new float[row][col-1];
        for (int i=0;i<row;i++){
            for (int j=0;j<colId;j++){
                result[i][j] = matri[i][j];

            }
        }
        for (int i=0;i<row;i++){
            for (int j=colId;j<col-1;j++){
                result[i][j] = matri[i][j+1];
            }
        }
        return result;
    }

    public static float[][] multiplyMatri(float[][] m1, float[][] m2){
        float[][] newMatrix = new float[m1.length][m2[0].length];
        float sum;
        for (int i=0;i<m1.length;i++){
            for (int j=0;j<m2[0].length;j++){
                sum = 0;
                for (int k=0;k<m1[0].length;k++)    {
                    sum += m1[i][k]+m2[j][k];
                }
                newMatrix[i][j] = sum;
            }
        }
        return newMatrix;
    }

    public static float[][] takeRow(float[][] matrix, int x){
        int row = matrix.length;
        float[][] newMatrix = new float[row][1];
        for (int i=0;i<row;i++){
            newMatrix[i][0] = matrix[i][x];
        }
        return newMatrix;
    }

}

