public class invers {

    public static double[][] identity(double[][] matrix){

        int row = matrix.length, col = matrix[0].length;

        /* Membuat empty matrix augmented*/
        double[][] aug = new double[row][2*col];

        /* Mengisi matrix augmented (matrix|identitas) */
        for (int i = 0; i < row; i++){
            for (int j = 0; j < 2*col; j++){

                if (j < row){
                    aug[i][j] = matrix[i][j];
                } else if (j-col == i) {
                    aug[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < row; i++){

            /* masterRow : baris pada aug yang dijadikan acuan untuk OBE
               masterEle : element pertama setelah nol pada masterRow     */
            int masterRow = i;
            double masterEle = aug[i][i];

            /* Ngeswap row kalo masterElement nya nol */
            if (masterEle == 0){

                /* Nyari row dibawah masterRow dengan masterElement yang bukan nol */
                for (int j = i+1; j < row; j++){

                    if (aug[j][i] != 0){
                        double[] temp = aug[masterRow];
                        aug[masterRow] = aug[j];
                        aug[j] = temp;

                        masterEle = aug[i][i];
                    }
                }
            }

            /* Membagi tiap element pada masterRow dengan masterEle agar masterEle jadi 1 */
            for (int j = 0 ; j < 2*col; j++){
                aug[masterRow][j] /= masterEle;
            }

            /* Mengurangi tiap element pada row != master row dengan elemen pada master row yang dikali factor */
            for (int j = 0 ; j < row; j++){
                if  (j != masterRow){

                    double factor = aug[j][masterRow];

                    for (int k = 0; k < 2*col; k++){

                        aug[j][k] -= aug[masterRow][k]*factor;
                        
                    }
                }

            }
        }

        /* Membuat empty matrix inverse*/
        double[][] invMatrix = new double[row][col];

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){

                invMatrix[i][j] = aug[i][j+col];
            }
        }

        return invMatrix;
    }    


    public static double[][] adjoint(double[][] matrix){

        int row = matrix.length, col = matrix[0].length;

        double detM = determinant.detCofactorExp(matrix);


        /* Membuat empty matrix inverse */
        double[][] invMatrix = new double[row][col];

        /* Membuat empty matrix temp untuk mencari cofactor */
        double[][] temp = new double[row-1][col-1];

        /* Looping untuk membuat matrix cofactor */
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){

                for (int k = 0; k < row-1; k++){
                    for (int l = 0; l < col-1; l++){

                        if (k >= i && l >= j){
                            temp[k][l] = matrix[k+1][l+1];
                            
                        } else if (l >= j){
                            temp[k][l] = matrix[k][l+1];
                           
                        } else if (k >= i){
                            temp[k][l] = matrix[k+1][l];
                            
                        } else {
                            temp[k][l] = matrix[k][l];
                            
                        }
                    }
                } 
                invMatrix[i][j] = Math.pow(-1, i+j)*determinant.detCombination(temp);
            }
        }
        /* matrix invers = 1/determinan x matrix cofactor */
        invMatrix = operator.scalarMult(1/detM, operator.transpose(invMatrix));

        return invMatrix;
    }    
}