public class invers_identity {
    public static float[][] invert(float[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        // Check if the matrix is square
        if (row != col) {
            throw new IllegalArgumentException("Input matrix must be square.");
        }

        // Create an identity matrix of the same size
        float[][] identity = new float[row][row];
        for (int i = 0; i < row; i++) {
            identity[i][i] = 1;
        }

        // Gaussian elimination with partial pivoting
        for (int i = 0; i < row; i++) {
            // Find the pivot row
            int pivotRow = i;
            for (int j = i + 1; j < row; j++) {
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[pivotRow][i])) {
                    pivotRow = j;
                }
            }

            // Swap rows if necessary
            if (pivotRow != i) {
                float[] temp = matrix[i];
                matrix[i] = matrix[pivotRow];
                matrix[pivotRow] = temp;

                temp = identity[i];
                identity[i] = identity[pivotRow];
                identity[pivotRow] = temp;
            }

            // Divide the pivot row by the pivot element
            float pivot = matrix[i][i];
            if (pivot == 0) {
                throw new IllegalArgumentException("Matrix is singular. Cannot compute inverse.");
            }
            for (int j = 0; j < row; j++) {
                matrix[i][j] /= pivot;
                identity[i][j] /= pivot;
            }

            // Eliminate non-zero values above and below the pivot
            for (int j = 0; j < row; j++) {
                if (j != i) {
                    float factor = matrix[j][i];
                    for (int k = 0; k < row; k++) {
                        matrix[j][k] -= factor * matrix[i][k];
                        identity[j][k] -= factor * identity[i][k];
                    }
                }
            }
        }

        return identity;
    }
}
//     public static void main(String[] args) {
        // float[][] matrix = {
            // {1, 1, 2, 8},
            // {2, 8, 4, 5},
//             {1, 9, 1, 4},
//             {1, 2, 3, 4}
//         };

//         try {
//             float[][] inverse = invert(matrix);

//             for (int i = 0; i < inverse.length; i++) {
//                 for (int j = 0; j < inverse[i].length; j++) {
//                     System.out.print(inverse[i][j] + " ");
//                 }
//                 System.out.println();
//             }
//         } catch (IllegalArgumentException e) {
//             System.out.println(e.getMessage());
//         }
//     }
// }
