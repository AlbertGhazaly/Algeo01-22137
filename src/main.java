import java.util.*;

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

                System.out.println("Masukkan nilai SPL berupa matrix");            
                double[][] mat = operator.inputMatrix();
                double[][] solusi;
                if (operator.isNoSolution(mat)){
                    System.out.println("Matrix tidak memiliki solusi");
                }else if (operator.isSolutionParametric(mat)){
                    System.out.println("Matrix memiliki solusi parametrik atau solusi tak berhingga ");
                }else{
                    if (opt1==1){
                        System.out.println("Solusi dihitung dengan metode Elminasi Gauss");
                        solusi = spl.gauss_Spl(mat);
                    }else if (opt1 ==2){
                        System.out.println("Solusi dihitung dengan metode Elminasi Gauss-Jordan");
                        solusi = spl.gauss_Jordan_Spl(mat);
                    }else if (opt1==3){
                        System.out.println("Solusi dihitung dengan metode Matriks Balikan");
                        solusi = spl.inverse_Spl(mat);
                    }else{
                        System.out.println("Solusi dihitung dengan metode Kaidah Crammer");
                        solusi = spl.crammer_Spl(mat);
                    }
                    System.out.println("Hasil Perhitungan: ");
                    operator.displayMatrix(solusi);
                }

            } else if (opt == 2){
                System.out.println("Silakan pilih metode yang ingin digunakan: ");
                System.out.println("1. Reduksi Baris");
                System.out.println("2. Ekspansi Kofaktor");
                System.out.println("3. Kombinasi Reduksi Baris dan Ekspansi Kofaktor");
                System.out.println("Silakan pilih menu (1-3): ");
                int opt2 = in.nextInt();
                System.out.println("Progres belum sejauh itu.");           
            } else if (opt == 3){
                System.out.println("Silakan pilih metode yang ingin digunakan: ");
                System.out.println("1. Menggunakan matriks identitas");
                System.out.println("2. Metode adjoin");
                System.out.println("Silakan pilih menu (1-2): ");
                int opt3 = in.nextInt();
                System.out.println("Progres belum sejauh itu.");   
            } else if (opt == 4){
                System.out.println("Tentukan besar derajat polinom:");
                int n = in.nextInt();
                n +=1;  
                System.out.println("Silahkan masukkan input berupa:");
                System.out.println("x f(x)");
                double[][] input = new double[n][2];
                double[][] result;
                for (int i=0;i<n;i++){
                    input[i][0] = in.nextInt();
                    input[i][1] = in.nextInt();
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

            } else if (opt == 5){

            } else if (opt == 6){

            } else if (opt == 7){
                break;
            }
        }    
    }
}
