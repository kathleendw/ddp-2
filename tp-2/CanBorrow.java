public interface CanBorrow {
    public String pinjam(Buku buku, String tanggalPeminjaman);
    public String kembali(Buku buku, String tanggalPengembalian);
}
