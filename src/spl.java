public class spl {

    public static float[][] inverse_Spl(float[][] matrix){
        int col = matrix[0].length;

        float[][] inv = invers_identity.invert(operator.delColAt(matrix,col-1));
        float[][] b_matrix = operator.takeCol(matrix, col-1);
        operator.displayMatrix(inv);

        float[][] result_Equation_Matrix = operator.multiplyMatrix(inv, b_matrix);
        return result_Equation_Matrix;

    }
    
    public static float[][] gauss_Spl(float[][] m){
        int row = m.length;
        int col = row+1;
        float[][] result = new float[row][1];
        m = operator.echelonRow(m);
        int subs;
        int distRow;
        for (int i=row-1;i>=0;i--){
            subs = 0;
            for (int j=i;j<row-1;j++){
                distRow = row-1-j; // jarak baris j ke baris paling bawah (row-1)
                subs += m[i][col-1-distRow]*result[row-distRow][0];
            }
            result[i][0] = (m[i][col-1]-subs)/m[i][i];
        }
        return result;
    }

    // public static void main(String[] args){
    //     float[][] m = {{1,1,1,6,1},
    //     {1,2,3,14,1},{2,3,2,14,1}};
    //     float[][] res = gauss_Spl(m);
    //     operator.displayMatrix(res);


    // }
}

