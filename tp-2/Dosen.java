public class Dosen extends Anggota{
    // Attributes
    private static int BATAS_JUMLAH_PEMINJAMAN_BUKU = 5;
    public static long BATAS_MAKSIMAL_DENDA = 10000;
    public static int jumlahDosen = 0;

    // Constructor
    public Dosen(String nama){
        super(nama, null); // id dibuat null
    }

    // method untuk membuat id dosen
    protected String generateId() {
        String id = "DOSEN-" + (jumlahDosen + 1);
        jumlahDosen++;
        return id;
    }

    // mengecek jumlah buku pada daftarPeminjaman
    public int jumlahDaftarPeminjaman(Peminjaman[] daftarPeminjaman){
        int jumlah = 0; 
        for (int i = 0; i < daftarPeminjaman.length; i++){
            if(daftarPeminjaman[i] != null){
                jumlah++;
            }
        }
        return jumlah;
    }

    // mengecek jika buku sedang dipinjam atau tidak
    public boolean sedangDiPinjam(Buku buku){
        // mengakses array berisi buku yang sedang dipinjam
        for(int i = 0; i < this.getDaftarPeminjaman().length; i++){
            // jika buku terdapat pada daftarPeminjaman
            if(this.getDaftarPeminjaman()[i] != null && 
            (this.getDaftarPeminjaman()[i].getBuku().getJudul().toLowerCase()).equals(buku.getJudul().toLowerCase()) &&
            (this.getDaftarPeminjaman()[i].getBuku().getPenulis().toLowerCase()).equals(buku.getPenulis().toLowerCase())){
                return true;
            }
        }
        return false;
    }

    // method untuk meminjam buku
    public String pinjam(Buku buku, String tanggalPeminjaman){
        int isiDaftarPeminjaman = jumlahDaftarPeminjaman(this.getDaftarPeminjaman());

        // jika stok habis
        if(!buku.cekStok(1)) {
            return "Buku " + buku.getJudul() + " oleh " + buku.getPenulis() + " tidak tersedia";
        }
        else {
            // jika buku yang dipinjam melebih batas jumlah peminjaman buku
            if(isiDaftarPeminjaman >= BATAS_JUMLAH_PEMINJAMAN_BUKU) {
                return "Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal";
            }
            else {
                // jika denda pengguna melebihi batas maksimal denda
                if(this.getDenda() > BATAS_MAKSIMAL_DENDA) {
                    return "Denda lebih dari Rp 10000";
                }
                else {
                    // jika buku sedang dipinjam
                    if(sedangDiPinjam(buku)){
                        return "Buku " + buku.getJudul() + " oleh " + buku.getPenulis() + " sedang dipinjam";
                    }
                    else {
                        // membuat objek Peminjaman baru
                        Peminjaman peminjaman = new Peminjaman(this, buku, tanggalPeminjaman);
                        // menambahkan objek pada daftarPeminjaman anggota
                        this.setDaftarPeminjaman(peminjaman);
                        // menambahkan objek pada sejarahPeminjaman anggota
                        this.setSejarahPeminjaman(peminjaman);
                        // menambahkan anggota pada daftarPeminjam buku
                        buku.setDaftarPeminjam((Anggota) this);
                        // stok buku berkurang karena dipinjam
                        buku.setStok(buku.getStok()-1);
                        return this.getNama() + " berhasil meminjam Buku " + buku.getJudul() + "!";
                    }
                }
            }
        }
    }
}
