

public class Buku {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)

    // Attributes
    private String judul;
    private String penulis;
    private String penerbit;
    private int stokAwal;
    private int stok;
    private Kategori kategori;
    private CanBorrow[] daftarPeminjam;

    // Constructor
    public Buku(String judul, String penulis, String penerbit, int stok, Kategori kategori) {
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.stok = stok;
        this.kategori = kategori;
    }

    // tampilan yang akan muncul di terminal saat buku berhasil ditambahkan
    @Override
    public String toString() {
        return "Buku " + judul + " oleh " + penulis + " berhasil ditambahkan";
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

    public int getStok() {
        return stok;
    }

    public void setStok(int banyak) {
        return this.stok = banyak;
    }

    Kategori getKategori(){
        return kategori;
    }

    public CanBorrow[] getDaftarPeminjam(){
        return daftarPeminjam;
    }

}
