import java.util.Scanner;

/* 
    <bicubic>

    Class ini berisi fungsi yang berkaitan dengan 
    fungsi interpolasi bicubic spline.
*/

public class bicubic {

    /* 
        <power>

        Fungsi pangkat untuk membantu perhitungan matrix interpolasi,
        fungsi ini diperlukan untuk mengatasi kalkulasi 0^0 
    */

    private static double power(double base, int power){

        if (power == 0){
            return 1;   
        } else if (power < 0){
            return 1;
        }
        else {
            return (double) Math.pow(base, power);
        }
    }

    /*  
        <get_BSI_MATRIX>

        Fungsi untuk menggenerasi matrix bicubic spline interpolation,
        nilai matrix adalah konstan maka, fungsi ini hanya perlu dijalankan
        sekali setiap kali program di run 
    */
    
    public static double[][] get_BSI_MATRIX(){


        double[][] intpMatrix = new double[16][16];

        int row = 0;

        while (row < 16){

            for (int y = 0; y < 2; y++){
                for (int x = 0; x < 2; x++){

                    int i = 0, j = 0;

                    for (int col = 0; col < 16; col++){

                        /* F<x,y> */
                        if (row < 4){
                            intpMatrix[row][col] = (power( x, i)*power((double) y, j));

                        /* Fx<x,y> */
                        } else if (row < 8){
                            intpMatrix[row][col] = i*power((double) x, i-1)*power((double) y, j);

                        /* Fy<x,y> */
                        } else if (row < 12){
                            intpMatrix[row][col] = j*power((double) x, i)*power((double) y, j-1);

                        /* Fxy<x,y> */
                        } else {
                            intpMatrix[row][col] = i*j*power((double) x, i-1)*power((double) y, j-1);
                        }
                    
                        i++;

                        if (i == 4){ i = 0; j++;}
                    }
                row++;
                }
            }

        /* 
           Mereturn invers dari matrix interpolasi agar bisa langsung dipakai
           untuk mencari matrix koefisien interpolasi dengan cara mengkalikan 
           invers matrix interpolasi dengan matrix dataset 
        */
        }
        return invers.identity(intpMatrix);
    }

    /* 
        <get_BSI_COEF>

        Fungsi untuk menggenerasi matrix bicubic spline interpolation,
        nilai matrix adalah konstan maka, fungsi ini hanya perlu dijalankan
        sekali setiap kali program di run 
    */

    public static double[][] get_BSI_COEF(double[][] dataset, double[][] BSI_MATRIX){

        double[][] FVAL_MATRIX = new double[16][1];

        /*  Mengubah matrix dataset 4x4 menjadi 16x1 */
        int idx = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                FVAL_MATRIX[idx][0] = dataset[i][j];
                idx++;
            }
        }
        /* 
           Matrix koefisien interpolasi didapatkan dari perkalian matrix BSI dengan
           matrix dataset 
        */
        return operator.multiplyMatrix(BSI_MATRIX, FVAL_MATRIX);
    }

    /* 
        <get_BSI_VAL>
        
        Fungsi untuk menghasilkan nilai f<x,y> menggunakan fungsi
        hasil interpolasi bicubic spline 
    */

    public static double get_BSI_VAL(double[][] COEF, double X, double Y){

        double VAL = 0;
        int IDX = 0;

        for (int j = 0; j < 4; j++){
            for (int i = 0; i < 4; i++){
                VAL += COEF[IDX][0]*power(X, i)*power(Y, j);
                IDX++;
            }
        }

        return VAL;
    }

    /* 
        <runBicubic>
        
        Fungsi driver untuk class bicubic
    */

    public static int runBicubic(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n[Menu Interpolasi Bicubic Spline]");
        
        double[][][] get_inputMatriks = operator.inputMatrixBicubic();

        double[][] inputMatriks = get_inputMatriks[0];
        double typeInput = get_inputMatriks[1][2][0];
        double X = get_inputMatriks[1][1][0];
        double Y = get_inputMatriks[1][0][0];

        if ((inputMatriks.length != 4) || (inputMatriks[0].length != 4)){
            System.out.println("\nMatriks referensi tidak sesuai, harus 4x4!");

        } else {

            double[][] BSI_MATRIX = get_BSI_MATRIX();
            double[][] BSI_COEF = get_BSI_COEF(inputMatriks, BSI_MATRIX); 

            if (typeInput == 0){

                String Continue = "Y";

                while (Continue.equalsIgnoreCase("y")){

                    System.out.println("\nInput titik <x,y>:");

                    System.out.print("X: ");
                    X = scanner.nextDouble();

                    System.out.print("Y: ");
                    Y = scanner.nextDouble();

                    double FuncVal = get_BSI_VAL(BSI_COEF, Y, X);
                    scanner.nextLine();

                    System.out.println("f<" + X + "," + Y + "> = " + FuncVal);

                    System.out.print("\nApakah ingin melanjutkan input f<x,y> (Y/N): ");

                    Continue = scanner.nextLine();
                }

            } else {

                double FuncVal = get_BSI_VAL(BSI_COEF, Y, X);
                System.out.println("\nf<" + X + "," + Y + "> = " + FuncVal);

            }
        }

        System.out.println("\nSilakan tekan ENTER untuk kembali.");
        String BACK = scanner.nextLine();

        return 0;
    }
}
