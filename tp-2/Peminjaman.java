import java.util.*;

public class Peminjaman {
    // Attributes
    private static long DENDA_PER_HARI = 3000;
    private Anggota anggota;
    private Buku buku;
    private String tanggalPeminjaman;
    private String tanggalPengembalian = "-";
    private long denda = 0;

    // Constructor
    public Peminjaman(Anggota anggota, Buku buku, String tanggalPeminjaman) {
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    // tampilan yang akan muncul di terminal saat objek Peminjaman dipanggil
    @Override
    public String toString() {
        return buku.toString() + "\n" +
        "Tanggal Peminjaman: " + tanggalPeminjaman + "\n" +
        "Tanggal Pengembalian: " + tanggalPengembalian + "\n" +
        "Denda: " + denda;
    }

    // method untuk mengembalikan buku pada tanggal yang diinput
    public void kembalikanBuku(String tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    // method untuk menghitung denda
    public long hitungDenda() {
        // membuat objek Date berdasarkan format yyyy-mm-dd dari tanggalPeminjaman dan tanggalPengembalian
        Date waktuTanggalPeminjaman = new Date(Integer.parseInt(tanggalPeminjaman.substring(6)), 
        Integer.parseInt(tanggalPeminjaman.substring(3,5)), Integer.parseInt(tanggalPeminjaman.substring(0,2)));
        Date waktuTanggalPengembalian = new Date(Integer.parseInt(tanggalPengembalian.substring(6)), 
        Integer.parseInt(tanggalPengembalian.substring(3,5)), Integer.parseInt(tanggalPengembalian.substring(0,2)));
        // memanggil method perbedaanTanggal 
        int selisihTanggal = perbedaanTanggal(waktuTanggalPeminjaman, waktuTanggalPengembalian);
        // jika selisih tanggal lebih dari 7
        if (selisihTanggal > 7) {
            denda = (selisihTanggal - 7) * this.getDENDA_PER_HARI();
        }
        // jika selisih tanggal kurang dari 7
        else {
            denda = 0;
        }
        return denda;
    }

    public int perbedaanTanggal (Date waktuTanggalPeminjaman, Date waktuTanggalPengembalian) {
        // mengambil waktu tanggalPeminjaman dan tanggalPengembalian dalam milisekon
        long tanggalPeminjamanMs = waktuTanggalPeminjaman.getTime();
        long tanggalPengembalianMs = waktuTanggalPengembalian.getTime();
        long perbedaanWaktu = 0;
        // mencari perbedaan waktu antara tanggalPeminjaman dan tanggalPengembalian
        if (tanggalPeminjamanMs > tanggalPengembalianMs) {
            perbedaanWaktu = tanggalPeminjamanMs - tanggalPengembalianMs;
        }
        else {
            perbedaanWaktu = tanggalPengembalianMs - tanggalPeminjamanMs;
        }
        // mengubah milisekon menjadi hari
        int perbedaanTanggal = (int) (perbedaanWaktu / (1000 * 60 * 60 * 24));
        return perbedaanTanggal;
    }

    // Getters dan setters
    public long getDENDA_PER_HARI(){
        return DENDA_PER_HARI;
    }

    public Buku getBuku() {
        return buku;
    }

    public String getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

    public String getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public long getDenda() {
        return denda;
    }

    public long setDenda(long denda){
        return this.denda = denda;
    }
}
