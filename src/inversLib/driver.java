package inversLib;

public class driver {

    public static void main(String[] args){

        double[][] matrix = {
            {1, 1, 2, 8},
            {2, 8, 4, 5},
            {1, 9, 1, 4},
            {1, 2, 3, 4}
        };

        System.out.println("Before: ");
        function.displayMatrix(matrix);

        matrix = invers.adjoint(matrix);

        System.out.println("After: ");
        function.displayMatrix(matrix);
    }
}