import java.util.*;
import java.io.*;


public class main {
    public static void main(String[] args){
        try{
            FileOutputStream fileStream = new FileOutputStream("out/output.txt");
            MultiOutputStream multiStream = new MultiOutputStream(System.out, fileStream);
            PrintStream output = new PrintStream(multiStream, true); // Stream baru -> output ditampilkan di CLI dan disimpan di output.txt
            PrintStream console = System.out;// stream biasa -> output hanya akan ditampilkan di CLI, tetapi tidak disimpan
            System.setOut(console);// set stream ke console
            Scanner in = new Scanner (System.in);
            while (true){
                System.out.println("\nMENU");
                System.out.println("1. Sistem Persamaan Linier");
                System.out.println("2. Determinan");
                System.out.println("3. Matriks balikan");
                System.out.println("4. Interpolasi Polinom");
                System.out.println("5. Interpolasi Bicubic Spline");
                System.out.println("6. Regresi linier berganda");
                System.out.println("7. Perbesaran gambar");
                System.out.println("8. Keluar");
                System.out.println("Silakan pilih menu (1-8): ");
                int opt = in.nextInt();
                if (opt == 1){
                    System.out.println("Silakan pilih metode yang ingin digunakan untuk menyelesaikan SPL: ");
                    System.out.println("1. Metode Eliminasi Gauss");
                    System.out.println("2. Metode Eliminasi Gauss-Jordan");
                    System.out.println("3. Matriks Balikan");
                    System.out.println("4. Kaidah Cramer");
                    System.out.println("5. Special case (Matriks Hilbert)");
                    System.out.println("Silakan pilih menu (1-4): ");
                    int opt1 = in.nextInt();
                    while (opt1<=0 && opt1>5){
                        System.out.print("Opsi tidak sesuai, silahkan input kembali: ");
                        opt1 = in.nextInt();
                    }
                    double[][] matrix;
                    System.setOut(output); // set stream ke output
                    System.out.println("Sistem Persamaan Linear");
                    if (opt1==5){
                            System.out.println("Input nilai n: ");
                            int n = in.nextInt();
                            double[][] matriks = new double[n][n+1];
                            double[][] solusi = new double[n][0];
                            matriks[0][n] = 1.0;
                            for (int i=1;i<n;i++){
                                matriks[i][n] = 0;
                            }
                            for (int i=0;i<n;i++){
                                for (int j=0;j<n;j++){
                                    matriks[i][j] = (double) 1/((j+1+i));
                                }
                            }
                            solusi = spl.gauss_Jordan_Spl(matriks);
                            for (int i=0;i<solusi.length;i++){
                                System.out.println("X"+(i+1)+": "+solusi[i][0]+" ");
                            }
                    }else{
                        matrix = operator.inputMatrix();
                        spl.SPL(matrix, opt1);
                    }
                    System.out.println();
                    System.setOut(console); // kembali set stream ke normal (console)
                    System.out.println("Ketik 1 dan [enter] untuk kembali ke Menu Utama.");
                    int back = in.nextInt();

                } else if (opt == 2){
                    System.out.println("Masukkan matriks PERSEGI (baris dan kolom sama)");
                    double[][] matrix = operator.inputMatrix();
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
                    double det;
                    System.setOut(output);
                    if (opt2 == 1){
                        System.out.println("Determinan dihitung dengan metode Reduksi Baris: ");
                        det = determinant.detRowReduction(matrix);
                        System.out.println("Determinan: " + det);                        
                    } else if (opt2 == 2){
                        System.out.println("Determinan dihitung dengan metode Ekspansi Kofaktor: ");
                        det = determinant.detCofactorExp(matrix);
                        System.out.println("Determinan: " + det);
                    } else {
                        System.out.println("Determinan dihitung dengan metode Kombinasi Reduksi Baris dan Ekspansi Kofaktor: ");
                        det = determinant.detCombination(matrix);
                        System.out.println("Determinan: " + det);
                    }
                    System.setOut(console);

                    System.out.println("Ketik 1 dan [enter] untuk kembali ke Menu Utama.");
                    int back = in.nextInt();
                        
                } else if (opt == 3){

                    System.out.println("\n[Menu Matriks Balikan] ");

                    System.out.println("\n1. Menggunakan matriks identitas (OBE)");
                    System.out.println("2. Menggunakan matriks adjoin");

                    System.out.print("\nPilih metode (1/2): ");
                    int opt3 = in.nextInt();

                    if (opt3 == 1){
                        int x = invers.runIdentity();
                    } else if (opt3 == 2){
                        int x = invers.runAdjoint();
                    } else {
                        System.out.println("Input tidak Valid."); 
                    }

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
                    System.setOut(output);
                    System.out.println("Interpolasi ");
                    System.out.println("Solusi hasil perhitungan interpolasi: ");
                    System.out.print("f(x): ");
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
                    System.out.println("Masukkan nilai x");
                    n -=1;
                    int der = in.nextIn;
                    double suminter = 0;
                    for (int i = 1;i<result.length;i++){
                        int has = 1;
                        for (int pan = 0;pan<i;pan++){
                            has = has*der;
                        }
                        suminter += has;
                    }
                    System.out.print("Hasil: "+suminter);
                    System.setOut(console);
                    System.out.println("Ketik 1 dan [enter] untuk kembali ke Menu Utama.");
                    int back = in.nextInt();

                } else if (opt == 5){
                    
                    int x = bicubic.runBicubic();


                } else if (opt == 6){
                    System.out.println("Masukkan banyaknya peubah x: ");
                    int n = in.nextInt();
                    System.out.println("Masukkan banyaknya sampel: ");
                    int m = in.nextInt();
                    System.out.println("Masukkan nilai x dan y dengan format berikut: ");
                    System.out.println("x1 x2 ... xn y");
                    System.out.println("(petunjuk: baris = banyaknya sampel, kolom = banyaknya peubah x + 1)");
                    double[][] data = operator.inputMatrix();
                    double[][] result = multiplelinreg.multipleLinReg(data);
                    System.setOut(output);
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
                    System.out.println("Nilai x1, x2, ..., xn yang diinput: ");
                    for (int i = 0; i < input.length; i++){
                        System.out.print(input[i] + " ");
                    }                    
                    System.out.println();
                    double taksiran = 0;
                    for (int i = 0; i < result.length; i++){
                        if (i == 0){
                            taksiran += result[i][0];
                        } else {
                            taksiran += result[i][0] * input[i - 1];
                        }
                    }
                    System.out.println("y = " + taksiran);
                    System.setOut(console);

                    System.out.println("Ketik 1 dan [enter] untuk kembali ke Menu Utama.");
                    int back = in.nextInt();
                
                } 
                else if (opt == 7){

                    System.out.println("\n[Menu Perbesaran Resolusi Gambar] ");
                    int x = imageProcessing.run();
                }
                
                else if (opt == 8){
                    File delfile = new File("out/output.txt");
                    System.out.println("Apakah output ingin disimpan?");
                    System.out.println("1. Iya ");
                    System.out.println("2. Tidak");
                    int opt3 = in.nextInt();
                    if (opt3 == 2){
                        fileStream.close(); 
                        delfile.delete(); //delete file
                    }
                    break;
                }
            }    
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
