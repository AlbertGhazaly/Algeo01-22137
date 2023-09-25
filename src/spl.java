public class spl {

    public static float[][] inverse_Spl(float[][] matrix){
        int col = matrix[0].length;

        float[][] inv = invers_identity.invert(operator.delColAt(matrix,col-1));
        float[][] b_matrix = operator.takeRow(matrix, col-1);
        operator.displayMatrix(inv);

        float[][] result_Equation_Matrix = operator.multiplyMatrix(inv, b_matrix);
        return result_Equation_Matrix;

    }
    

    // public static void main(String[] args){
    //     float[][] m = {{1,1,1,6},
    //     {1,2,3,14},{2,3,2,14}};
    //     float[][] res = inverse_Spl(m);
    //     operator.displayMatrix(res);


    // }
}

