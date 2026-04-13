package KumpulanCodingan; // Tentukan package sesuai nama project
import java.util.Scanner; // Import library Scanner untuk input dari user
public class Pemdas02upahpegawai {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Buat objek Scanner untuk membaca input

        // Input jumlah jam kerja dari user
        System.out.print("Masukkan jumlah jam kerja: ");
        int jamKerja = input.nextInt(); // Simpan jam kerja ke variabel jamKerja

        // Deklarasi variabel upah, lembur, denda, dan total
        int upahPerJam = 5000; // Tarif normal Rp. 5000 per jam
        int upah = 0;          // Upah dasar
        int lembur = 0;        // Upah lembur (jika ada)
        int denda = 0;         // Denda (jika ada)
        int total = 0;         // Total gaji akhir

        // Proses perhitungan dengan if-else if
        if (jamKerja > 60) {  
            // Jika jam kerja lebih dari 60 → kelebihan dihitung lembur
            upah = 60 * upahPerJam;                 // Hitung upah untuk 60 jam maksimal
            lembur = (jamKerja - 60) * 6000;        // Hitung upah lembur (Rp. 6000 per jam)
        } else if (jamKerja < 50) {  
            // Jika jam kerja kurang dari 50 → dikenakan denda
            upah = jamKerja * upahPerJam;           // Hitung upah normal
            denda = (50 - jamKerja) * 1000;         // Hitung denda Rp. 1000 per jam kekurangan
        } else {  
            // Jika jam kerja antara 50 - 60 → normal
            upah = jamKerja * upahPerJam;           // Upah normal saja, tanpa lembur/denda
        }

        // Hitung total gaji: upah + lembur - denda
        total = upah + lembur - denda;

        // Tampilkan hasil ke layar
        System.out.println("Jam kerja : " + jamKerja);
        System.out.println("Upah      = Rp. " + upah);
        System.out.println("Lembur    = Rp. " + lembur);
        System.out.println("Denda     = Rp. " + denda);
        System.out.println("---------------------------");
        System.out.println("Total     = Rp. " + total);

        input.close(); // Tutup scanner agar tidak ada kebocoran resource


    }

}
