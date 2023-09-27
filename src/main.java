import java.util.*;

public class main {
    public static void Main(String[] args){
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
                System.out.println("Masukkan nilai SPL berupa matrix");            
                double[][] mat = operator.inputMatrix();

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

            } else if (opt == 5){

            } else if (opt == 6){

            } else if (opt == 7){
                break;
            }
        }    
    }
}
