public class spl {

    public static void SPL(double[][] m, int opt){
        double[][] solusi;
        if (operator.isNoSolution(m)){
            System.out.println("Karena matriks eselon, seperti di atas");
            System.out.println("Maka matrix tidak memiliki solusi");
        }else if (operator.isSolutionParametric(m)){
            System.out.println("Oleh karena itu, matrix memiliki solusi parametrik atau solusi tak berhingga ");
        }else{
            if (opt==1){
                System.out.println("Solusi dihitung dengan metode Elminasi Gauss");
                solusi = spl.gauss_Spl(m);
                System.out.println("Hasil Perhitungan: ");
                for (int i=0;i<solusi.length;i++){
                    System.out.println("X"+(i+1)+": "+solusi[i][0]+" ");
                }
            }else if (opt ==2){
                System.out.println("Solusi dihitung dengan metode Elminasi Gauss-Jordan");
                solusi = spl.gauss_Jordan_Spl(m);
                System.out.println("Hasil Perhitungan: ");
                for (int i=0;i<solusi.length;i++){
                    System.out.println("X"+(i+1)+": "+solusi[i][0]+" ");
                }
            }else if (opt==3){
                if (determinant.detCombination(m)==0){
                    System.out.println("Determinan = 0, invers tidak ada. Maka tidak valid");
                }else{
                    System.out.println("Solusi dihitung dengan metode Matriks Balikan");
                    solusi = spl.inverse_Spl(m);
                    System.out.println("Hasil Perhitungan: ");
                    for (int i=0;i<solusi.length;i++){
                        System.out.println("X"+(i+1)+": "+solusi[i][0]+" ");
                    }
                }
            }else{
                if ((m.length==(m[0].length-1))&& (determinant.detCombination(m)==0)){
                    System.out.println("Solusi dihitung dengan metode Kaidah Crammer");
                    solusi = spl.crammer_Spl(m);
                    System.out.println("Hasil Perhitungan: ");
                    for (int i=0;i<solusi.length;i++){
                        System.out.println("X"+(i+1)+": "+solusi[i][0]+" ");
                    }
                }else{
                    System.out.println("Determinan = 0, invers tidak ada. Maka tidak Valid");
                }
                
            }
        }
    }

    public static double[][] inverse_Spl(double[][] matrix){ // menghasilkan solusi SPL menggunakan metode matrix balikan
        double[][] cMat = operator.copyMatrix(matrix);
        int col = cMat[0].length;

        double[][] inv = invers.identity(operator.delColAt(cMat,col-1));
        double[][] b_cMat = operator.takeCol(cMat, col-1);

        double[][] result_Equation_cMat = operator.multiplyMatrix(inv, b_cMat);
        return result_Equation_cMat;

    }
    
    public static double[][] gauss_Spl(double[][] matrix){ // menghasilkan solusi SPL menggunakan metode Gauss
        double[][] cmat = operator.copyMatrix(matrix);
        cmat = operator.echelonRow(cmat);
        int row = cmat.length;
        int col = cmat[0].length;
        double[][] result = new double[row][1];
        double subs;
        for (int i=row-1;i>=0;i--){
            subs = 0;
            for (int j=0;j<row;j++){
                subs += result[j][0]*cmat[i][j];
            }
            result[i][0] = (cmat[i][col-1]-subs)/cmat[i][i];
        }
        return result;
    }

    public static double[][] gauss_Jordan_Spl(double[][] matrix){ // Menghasilkan solusi SPL menggunakan metode Gauss-Jordan
        double[][] cmat = operator.copyMatrix(matrix);
        cmat = operator.echelonRowReduction(cmat);
        int row = cmat.length;
        int col = cmat[0].length;
        double[][] result = new double[row][1];
        for (int i=0;i<row;i++){
            result[i][0] = cmat[i][col-1];
        }
        return result;
    }

    public static double[][] crammer_Spl(double[][] matrix){ // Menghasilkan solusi SPL menggunakan metode Crammer
        int row = matrix.length;
        double det;
        double[][] m1 = operator.delColAt(matrix, matrix[0].length-1);
        double[][] m2 = operator.takeCol(matrix, matrix[0].length-1);
        double[][] result = new double[row][1];
        double[][] dummy = new double[m1.length][m1[0].length];
        double detMatAwal = determinant.detRowReduction(matrix);
        for (int i=0;i<row;i++){
            dummy = operator.swapCol(m1, m2, i);
            det = determinant.detRowReduction(dummy)/detMatAwal;
            result[i][0] = det;

        }
        return result;
    }   

 }

