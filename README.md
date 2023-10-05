# Tugas Besar Aljabar Linier dan Geometri : Sistem Persamaan Linier, Determinan, dan Aplikasinya

## Deskripsi Umum
Repository ini berisi library kalkulator matriks yang dibuat menggunakan bahasa Java. Program ini dibuat untuk memenuhi Tugas Besar mata kuliah Aljabar Linier dan Geometri.

### Anggota Kelompok:
| Nama  | NIM |
| ------------- | ------------- |
| Ahmad Rafi Maliki |  13522137 |
| Nicholas Reymond Sihite | 13522144  |
| Albert Ghazaly | 13522150 |

## Fitur
* **Menyelesaikan Sistem Persamaan Liniar** <br>
Penyelesaian SPL dapat menggunakan metode eliminasi Gauss, eliminasi Gauss-Jordan, matriks balikan, kaidah Crammer, dan matriks Hilbert.
* **Mencari Determinan** <br>
Determinan dapat diperoleh menggunakan metode reduksi baris, ekspansi kofaktor, dan kombinasi keduanya.
* **Mencari Matriks Balikan** <br>
Matriks balikan dapat diperoleh dengan metode OBE matriks identitas dan metode matriks adjoin.
* **Interpolasi Polinom** <br>
Akan diperoleh persamaan polinomial dari titik-titik yang diketahui.
* **Interpolasi Bicubic Spline** <br>
Metode interpolasi dua dimensi untuk memperkirakan nilai fungsi pada titik tertentu.
* **Multiple Linear Regression** <br>
Akan diperoleh persamaan linear dari titik-titik yang diketahui.
* **Perbesaran Resolusi Gambar** <br>
Memperbesar resolusi gambar dengan memanfaatkan Interpolasi Bicubic Spline.

## Struktur Program
```bash
.
├───README.md
│
├───bin
│   ├───MultiOutputStream.class  
│   ├───bicubic.class     
│   ├───determinant.class
│   ├───imageProcessing.class
│   ├───interpolasi.class
│   ├───invers.class
│   ├───main.class
│   ├───main.class
│   ├───operator.class
│   ├───operatormultiplelinreg.class
│   └───spl.class
│
├───doc
│
├───src
│   ├───MultiOutputStream.java   
│   ├───bicubic.java      
│   ├───determinant.java
│   ├───imageProcessing.java
│   ├───interpolasi.java
│   ├───invers.java
│   ├───main.java
│   ├───main.java
│   ├───operator.java
│   ├───operatormultiplelinreg.java
│   └───spl.java
│
└───test
```

## Cara menjalankan program
1. Clone Repository
   ```sh
   git clone https://github.com/albert260302/Algeo01-22137
   ```
2. Buka folder "Algeo01-22137" di terminal lalu jalankan:
   ```sh
   cd src
   javac -d ../bin Main.java
   cd../bin
   java main
   ```
