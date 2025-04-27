

public class Staf extends Pengguna {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    private static int jumlahStaf = 0; // menyimpan jumlah staf yang telah terdaftar menjadi pengguna SistakaNG (digunakan pada method generateId)

    public Staf(String nama, String id) {
        super(nama, id);
    }

    protected String generateId(){
        String id = "Staf-" + (jumlahStaf + 1);
        jumlahStaf++;
        return id;
    }

    @Override
    public String toString() {
        return "ID Staf: " + this.getId() + "\n" +
        "Nama Staf: " + this.getNama();
    }
}
