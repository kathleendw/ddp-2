

public abstract class Pengguna {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    private String id;
    private String nama;

    public Pengguna(String nama, String id) {
        this.nama = nama;
        this.id = id;
    }

    protected abstract String generateId();
    public abstract String toString();

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

}
