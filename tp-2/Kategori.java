public class Kategori {
    // Attributes
    private String nama;
    private int poin;

    // Constructor
    public Kategori(String nama, int poin) {
        this.nama = nama;
        this.poin = poin;
    }

    // tampilan yang akan muncul di terminal saat objek Kategori dipanggil
    @Override
    public String toString() {
        return "Kategori: " + nama + "\n" +
        "Poin: " + poin;
    }

    // Getters and setters 
    public String getNama() {
        return nama;
    }

    public int getPoin() {
        return poin;
    }
}
