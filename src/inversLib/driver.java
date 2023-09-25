package inversLib;

public class driver {

    public static void main(String[] args){

        double[][] matrix = {
            {1, 1, 2, 8},
            {2, 8, 4, 5},
            {1, 9, 1, 4},
            {1, 2, 3, 4}
        };

        System.out.println("Matrix: ");
        function.displayMatrix(matrix);


        System.out.println("Inverse Matrix metode Adjoint: ");
        function.displayMatrix(invers.adjoint(matrix));

        System.out.println("Inverse Matrix metode Identitas: ");
        function.displayMatrix(invers.identity(matrix));
    }
}