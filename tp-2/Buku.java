public class Buku {
    // Attributes
    private String judul;
    private String penulis;
    private String penerbit;
    private int stokAwal;
    private int stok;
    private Kategori kategori;
    private CanBorrow[] daftarPeminjam;
    private int jumlahDaftarPeminjam;

    // Constructor
    public Buku(String judul, String penulis, String penerbit, int stok, Kategori kategori) {
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.stokAwal = stok;
        this.stok = stok;
        this.kategori = kategori;
        daftarPeminjam = new CanBorrow[jumlahDaftarPeminjam + 1];
    }

    // tampilan yang akan muncul di terminal saat objek Buku dipanggil
    @Override
    public String toString() {
        return "Judul Buku: " + judul + "\n" +
        "Penulis Buku: " + penulis + "\n" +
        "Penerbit Buku: " + penerbit + "\n" +
        kategori.toString();
    }
        
    public boolean cekStok(int stok) {
        // jika stok masih ada
        if(this.stok >= stok){
            return true;
        }
        return false;
    }

    // Getters and setters
    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }
    
    public String getPenerbit() {
        return penerbit;
    }

    public int getStokAwal() {
        return stokAwal;
    }

    public int getStok() {
        return stok;
    }

    public int setStok(int banyak) {
        return this.stok = banyak;
    }

    Kategori getKategori(){
        return kategori;
    }

    public CanBorrow[] getDaftarPeminjam(){
        return daftarPeminjam;
    }

    // menambahkan peminjam kepada array daftarPeminjam buku
    public void setDaftarPeminjam(CanBorrow peminjam) {
        // karena peminjam ditambah, maka jumlah peminjam bertambah 1
        jumlahDaftarPeminjam++;
        // membuat array baru
        CanBorrow[] peminjamAdd = new CanBorrow[jumlahDaftarPeminjam];
        // memasukkan peminjam pada index terakhir peminjamAdd
        peminjamAdd[jumlahDaftarPeminjam-1] = peminjam;
        // jika sebelumnya sudah terdapat peminjam, maka peminjam-peminjam tersebut juga dimasukkan pada peminjamAdd
        if(jumlahDaftarPeminjam > 1){
            for(int i = 0; i < jumlahDaftarPeminjam-1 ; i++){
                peminjamAdd[i] = daftarPeminjam[i];
            }
        }
        // memodifikasi array daftarPeminjam menjadi peminjamAdd
        daftarPeminjam = peminjamAdd;
    }

}
