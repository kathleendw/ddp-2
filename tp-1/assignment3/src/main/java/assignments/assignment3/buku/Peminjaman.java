
import java.util.*;

public class Peminjaman {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll
    private static long DENDA_PER_HARI = 3000;
    private Anggota anggota;
    private Buku buku;
    private String tanggalPeminjaman;
    private String tanggalPengembalian = "-";
    private long denda = 0;
    private boolean status;

    public Peminjaman(Anggota anggota, Buku buku, String tanggalPeminjaman) {
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    @Override
    public String toString() {
        return anggota + " berhasil meminjam Buku " + buku + "!";
    }

    public void kembalikanBuku(String tanggalPengembalian) {
        // untuk melakukan semua hal yang perlu dilakukan ketika mengembalikan buku
        this.tanggalPengembalian = tanggalPengembalian;
    }
    
    public long hitungDenda() {
        // untuk menghitung denda ketika peminjaman diselesaikan (mengembalikan buku)
        Date waktuTanggalPeminjaman = new Date(Integer.parseInt(tanggalPeminjaman.substring(6)), 
        Integer.parseInt(tanggalPeminjaman.substring(3,5)), Integer.parseInt(tanggalPeminjaman.substring(0,2)));
        Date waktuTanggalPengembalian = new Date(Integer.parseInt(tanggalPengembalian.substring(6)), 
        Integer.parseInt(tanggalPengembalian.substring(3,5)), Integer.parseInt(tanggalPengembalian.substring(0,2)));
        if (selisihTanggal > 7) {
            denda = (perbedaanTanggal(tanggalPeminjaman, tanggalPengembalian) - 7) * daftarPeminjaman[cariIndexPeminjaman].getDENDA_PER_HARI();
        }
        else {
            denda = 0;
        }
        return denda;
    }

    public int perbedaanTanggal (Date tanggalPeminjaman, Date tanggalPengembalian) {
        long tanggalPeminjamanMs = tanggalPeminjaman.getTime();
        long tanggalPengembalianMs = tanggalPengembalian.getTime();
        long perbedaanWaktu = tanggalPengembalianMs - tanggalPeminjamanMs;
        int perbedaanTanggal = perbedaanWaktu / (1000 * 60 * 60 * 24);
        return perbedaanTanggal;
    }

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

    public String setTanggalPengembalian(String tanggalPengembalian){
        return this.tanggalPengembalian = tanggalPengembalian;
    }

    public long getDenda() {
        return denda;
    }

    public long setDenda(long denda){
        return this.denda = denda;
    }

    public boolean getStatus() {
        return status;
    }

    public boolean setStatus(boolean status) {
        return this.status = status;
    }

}
