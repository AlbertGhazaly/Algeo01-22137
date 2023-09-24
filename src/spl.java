public class spl {
    public static float[][] inverse_Spl(float[][] matrix){
        int col = matrix[0].length;

        float[][] inv = invers_identity.invert(operator.delColAt(matrix,col-1));
        float[][] b_matrix = operator.takeRow(matrix, col-1);

        float[][] result_Equation_Matrix = operator.multiplyMatri(inv, b_matrix);
        return result_Equation_Matrix;

    }


}

