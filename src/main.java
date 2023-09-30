import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args){
        Scanner in = new Scanner (System.in);
        while (true){
            System.out.println("MENU");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Interpolasi Bicubic Spline");
            System.out.println("6. Regresi linier berganda");
            System.out.println("7. Keluar");
            System.out.println("Silakan pilih menu (1-7): ");
            int opt = in.nextInt();
            if (opt == 1){
                System.out.println("Silakan pilih metode yang ingin digunakan: ");
                System.out.println("1. Metode Eliminasi Gauss");
                System.out.println("2. Metode Eliminasi Gauss-Jordan");
                System.out.println("3. Matriks Balikan");
                System.out.println("4. Kaidah Cramer");
                System.out.println("Silakan pilih menu (1-4): ");
                int opt1 = in.nextInt();
                while (opt1<=0 && opt1>4){
                    System.out.print("Opsi tidak sesuai, silahkan input kembali: ");
                    opt1 = in.nextInt();
                }
                double[][] solusi;
                double[][] matrix = operator.inputMatrix();
                if (operator.isNoSolution(matrix)){
                    System.out.println("matrix tidak memiliki solusi");
                }else if (operator.isSolutionParametric(matrix)){
                    System.out.println("Oleh karena itu, matrix memiliki solusi parametrik atau solusi tak berhingga ");
                }else{
                    if (opt1==1){
                        System.out.println("Solusi dihitung dengan metode Elminasi Gauss");
                        solusi = spl.gauss_Spl(matrix);
                    }else if (opt1 ==2){
                        System.out.println("Solusi dihitung dengan metode Elminasi Gauss-Jordan");
                        solusi = spl.gauss_Jordan_Spl(matrix);
                    }else if (opt1==3){
                        System.out.println("Solusi dihitung dengan metode Matriks Balikan");
                        solusi = spl.inverse_Spl(matrix);
                    }else{
                        System.out.println("Solusi dihitung dengan metode Kaidah Crammer");
                        solusi = spl.crammer_Spl(matrix);
                    }
                    System.out.println("Hasil Perhitungan: ");
                    for (int i=0;i<solusi.length;i++){
                        System.out.println("X"+(i+1)+": "+solusi[i][0]+" ");
                    }
                }

                System.out.println("Ketik 1 dan [enter] untuk kembali ke Menu Utama.");
                int back = in.nextInt();

            } else if (opt == 2){
                System.out.println("Silakan masukkan baris matrix persegi: ");
                int row = in.nextInt();
                double[][] matrix = new double[row][row];
                System.out.println("Silakan masukkan elemen matrix persegi " + row + "x" + row +": ");
                for (int i = 0; i < row; i++){
                    for (int j = 0; j < row; j++){
                        matrix[i][j] = in.nextDouble();
                    }
                }
                System.out.println("Silakan pilih metode yang ingin digunakan: ");
                System.out.println("1. Reduksi Baris");
                System.out.println("2. Ekspansi Kofaktor");
                System.out.println("3. Kombinasi Reduksi Baris dan Ekspansi Kofaktor");
                System.out.println("Silakan pilih menu (1-3): ");
                int opt2 = in.nextInt();
                while (opt2 <= 0 && opt2 > 3){
                    System.out.print("Opsi tidak sesuai, silahkan input kembali: ");
                    opt2 = in.nextInt();
                }
                if (opt2 == 1){
                    System.out.println("Determinan dihitung dengan metode Reduksi Baris: ");
                    double det = determinant.detRowReduction(matrix);
                    System.out.println("Determinan: " + det);
                } else if (opt2 == 2){
                    System.out.println("Determinan dihitung dengan metode Ekspansi Kofaktor: ");
                    double det = determinant.detCofactorExp(matrix);
                    System.out.println("Determinan: " + det);
                } else {
                    System.out.println("Determinan dihitung dengan metode Kombinasi Reduksi Baris dan Ekspansi Kofaktor: ");
                    double det = determinant.detCombination(matrix);
                    System.out.println("Determinan: " + det);
                }

                System.out.println("Ketik 1 dan [enter] untuk kembali ke Menu Utama.");
                int back = in.nextInt();
                    
            } else if (opt == 3){
                System.out.println("Silakan pilih metode yang ingin digunakan: ");
                System.out.println("1. Menggunakan matriks identitas");
                System.out.println("2. Metode adjoin");
                System.out.println("Silakan pilih menu (1-2): ");
                int opt3 = in.nextInt();
                System.out.println("Progres belum sejauh itu."); 

                System.out.println("Ketik 1 dan [enter] untuk kembali ke Menu Utama.");
                int back = in.nextInt();

            } else if (opt == 4){
                System.out.println("Tentukan besar derajat polinom:");
                int n = in.nextInt();
                n +=1;  
                System.out.println("Silahkan masukkan input berupa:");
                System.out.println("x f(x)");
                double[][] input = new double[n][2];
                double[][] result;
                for (int i=0;i<n;i++){
                    input[i][0] = in.nextDouble();
                    input[i][1] = in.nextDouble();
                }
                result = interpolasi.interpolate(input);
                
                for (int i=0; i<result.length;i++){
                    if (result[i][0]!=0){
                        if (i==0){
                            System.out.print(result[i][0]+" ");
                        }else{
                            if (result[i][0]<0){
                                System.out.print(result[i][0]+"X^"+i+" ");
                            }else{
                                System.out.print("+"+result[i][0]+"X^"+i+" ");
                            }
                        }
                    }
                }
                System.out.println();

                System.out.println("Ketik 1 dan [enter] untuk kembali ke Menu Utama.");
                int back = in.nextInt();

            } else if (opt == 5){

                System.out.println("Ketik 1 dan [enter] untuk kembali ke Menu Utama.");
                int back = in.nextInt();

            } else if (opt == 6){
                System.out.println("Masukkan banyaknya peubah x: ");
                int n = in.nextInt();
                System.out.println("Masukkan banyaknya sampel: ");
                int m = in.nextInt();
                System.out.println("Masukkan nilai x dan y dengan format: ");
                System.out.println("x1 x2 ... xn y");
                double[][] data = new double[m][n+1];
                for (int i = 0; i < m; i++){
                    for (int j = 0; j < n + 1; j++){
                        data[i][j] = in.nextDouble();
                    }
                }
                double[][] result = multiplelinreg.multipleLinReg(data);
                System.out.println("Hasil regresi: ");
                System.out.print("y = ");
                for (int i = 0; i < result.length; i++){
                    if (i == result.length - 1){
                        System.out.println(result[i][0] + "x_"+ i);
                    } else if (i == 0) {
                        System.out.print(result[i][0] + " + ");
                    } else {
                        System.out.print(result[i][0] + "x_"+ i + " + ");
                    }
                }
                System.out.println("Masukkan nilai x untuk ditafsir dengan format: ");
                System.out.println("x1 x2 ... xn");
                double[] input = new double[n];
                for (int i = 0; i < input.length; i++){
                    input[i] = in.nextDouble();
                }
                double taksiran = 0;
                for (int i = 0; i < result.length; i++){
                    if (i == 0){
                        taksiran += result[i][0];
                    } else {
                        taksiran += result[i][0] * input[i - 1];
                    }
                }
                System.out.println("y = " + taksiran);

                System.out.println("Ketik 1 dan [enter] untuk kembali ke Menu Utama.");
                int back = in.nextInt();

            } else if (opt == 7){
                break;
            }
        }    
    }
}
