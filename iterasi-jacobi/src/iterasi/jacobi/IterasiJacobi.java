/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iterasi.jacobi;

/**
 *
 * @author daffa
 */
public class IterasiJacobi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
            Diketahuil Persamaan SPL berikut :
                10w − 2x − y − z = 3
                −2w + 10x − y − z = 15
                −w − x + 10y − 2z = 27
                −w + x − 2y + 10z = −9
            Sehingga Didapat Matriks Berikut :
        */
        double[][] matrix = {
            {10, -2, -1, -1, 3},
            {-2, 10, -1, -1, 15},
            {-1, -1, 10, -2, 27},
            {-1, 1, -2, 10, -9},
        };
        solve(matrix);
    }
    
    private static void solve(double[][] matrix){
//        final double GALATERROR = 0.0001;
        boolean stop = false;
        /* 
            Buat Array Untuk Menampung Hasil Operasi. 
            Diisi Dengan Nilai Awal = (0,0,0,0).
        */
        double A[] = {0,0,0,0};
        
        // Membuat Elemen Array Sebagai Jumlah nilai yang Harus Dicari.
        int N = A.length; 
        
        /*
            Membuat Array Kosong dengan Tipe Data yang sama untuk menampung nilai 
            yang hasil perhitungan. Digunakan untuk menghitung Galat.
            Nilai Galat ditampung pada sebuah Array.
        */
        double[] temp = new double[N];
        double[] galat = new double[N];

        // Memulai Iterasi
        temp = A.clone();
        int count = 1;
        makeTable();
        
        //Perulangan untuk Operasi pada Iterasi
        for(count=1; count<=6;count++){
            // Operasi Untuk Mencari Nilai Setiap Elemen
            double w = (matrix[0][4] - ((matrix[0][1]*temp[1]) + (matrix[0][2]*temp[2]) + (matrix[0][3]*temp[3])))/matrix[0][0];
            temp[0] = w;
            galat[0] = Math.abs((temp[0]-A[0])/temp[0]);
            double x = (matrix[1][4] - ((matrix[1][0]*temp[0]) + (matrix[1][2]*temp[2]) + (matrix[1][3]*temp[3])))/matrix[1][1];
            temp[1] = x;
            galat[1] = Math.abs((temp[1]-A[1])/temp[1]);
            double y = (matrix[2][4] - ((matrix[2][0]*temp[0]) + (matrix[2][1]*temp[1]) + (matrix[2][3]*temp[3])))/matrix[2][2];
            temp[2] = y;
            galat[2] = Math.abs((temp[2]-A[2])/temp[2]);
            double z = (matrix[3][4] - ((matrix[3][0]*temp[0]) + (matrix[3][1]*temp[1]) + (matrix[3][2]*temp[2])))/matrix[3][3];
            temp[3] = z;
            galat[3] = Math.abs((temp[3]-A[3])/temp[3]);
            
            /* 
                Pengecekan kondisi berhenti.
                Iterasi Berhenti jika nilai setiap elemen
                pada temp sudah samadengan temp sebelum
            */
            if(temp[0] == A[0] &&
               temp[1] == A[1] &&
               temp[2] == A[2] &&
               temp[3] == A[3] ){
               stop = true;
               insertToTable(count, A, galat); // Print nilai sebelum iterasi berhenti
               closeTable();
               printSolusi(A); // Menampilkan hasil akhir
            }
            else{
                A = temp.clone();
                insertToTable(count, A, galat);
            }
        }
    }
    
    private static void insertToTable(int count, double[] hasil, double[] gal){
        int N = hasil.length;
        System.out.printf("|   %2d    ", count);
        for(int i=0; i<N; i++){
            System.out.printf("| %10.4f   ", hasil[i]);
        }
        for(int i=0; i<N; i++){
            System.out.printf("| %10.6f   ", gal[i]);
        }
        System.out.println(" |");
    }
    
    private static void printSolusi(double[] sol)
    {
        System.out.println("\nNilai Akhirnya Adalah : ");
        System.out.printf("W : %.4f\n", sol[0]);
        System.out.printf("X : %.4f\n", sol[1]);
        System.out.printf("Y : %.4f\n", sol[2]);
        System.out.printf("Z : %.4f", sol[3]);
        System.out.println("");     
    }
    
    private static void makeTable(){
        System.out.println(" ---------------------------------------------------------------------------------------------------------------------------------- ");
        System.out.println("| Iterasi |      w       |      x       |      y       |       z      |"
                + "      \u0190w      |      \u0190x      |       \u0190y     |       \u0190z      |");
        System.out.println("|---------|--------------|--------------|--------------|--------------|--------------|--------------|--------------|---------------|");
    }
    
    private static void closeTable(){
        System.out.println("|         |              |              |              |              |              |              |              |               |");
        System.out.println(" ---------------------------------------------------------------------------------------------------------------------------------- ");
    }
}

