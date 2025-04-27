public class Staf extends Pengguna {
    // Attributes
    private static int jumlahStaf = 0;

    // Constructor
    public Staf(String nama) {
        super(nama, null); // id dibuat null
    }

    // method untuk membuat id staf
    protected String generateId(){
        String id = "STAF-" + (jumlahStaf + 1);
        jumlahStaf++;
        return id;
    }

    // tampilan yang akan muncul di terminal saat objek Staf dipanggil
    @Override
    public String toString() {
        return "ID Staf: " + super.getId() + "\n" +
        "Nama Staf: " + super.getNama();
    }
}
