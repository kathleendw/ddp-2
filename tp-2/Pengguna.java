public abstract class Pengguna {
    // Attributes
    private String id;
    private String nama;

    // Constructor
    public Pengguna(String nama, String id) {
        this.nama = nama;
        // jika id null, maka akan memanggil method generate id untuk membuat id
        if(id == null) {
            this.id = generateId();
        }
        else {
            this.id = id;
        }
    }

    protected abstract String generateId();
    public abstract String toString();

    // Getters dan setters
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

}
