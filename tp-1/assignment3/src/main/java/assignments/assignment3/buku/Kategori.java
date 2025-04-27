

public class Kategori {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)

    // Attributes
    private String nama;
    private int poin;

    // Constructor
    public Kategori(String nama, int poin) {
        this.nama = nama;
        this.poin = poin;
    }

    // Tampilan yang akan muncul jika kategori berhasil ditambahkan
    @Override
    public String toString() {
        return "Kategori " + nama + " dengan " + poin + " point berhasil ditambahkan";
    }

    // Getters and setters 
    public String getNama() {
        return nama;
    }

    public int getPoin() {
        return poin;
    }

}
