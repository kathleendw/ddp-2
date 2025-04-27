
import java.util.HashMap;

public class Mahasiswa extends Anggota{
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)
    public static int BATAS_JUMLAH_PEMINJAMAN_BUKU = 3;
    public static long BATAS_MAKSIMAL_DENDA = 5000;
    private String tanggalLahir;
    private String programStudi;
    private String angkatan;
    public HashMap<Character, Integer> charToValue = new HashMap<>(36);
    public char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    int jumlahAnggota = 0;

    public Mahasiswa(String nama, String id){
        super(nama, id, "Mahasiswa");
        this.tanggalLahir = tanggalLahir;
        this.programStudi = programStudi;
        this.angkatan = angkatan;
    }

    // Method buildMapCharToValue adalah method untuk membuat mapping reference numbers Code 93
    public void buildMapCharToValue() {
        for (int count = 0; count < valueToChar.length; count++) {
            charToValue.put(valueToChar[count], count);
        }
    }

    // Method getCharFromValue adalah method yang akan mengembalikan Code 93 dari value yang diberikan
    private char getCharFromValue(int value) {
        return valueToChar[value];
    }

    // Method getValueFromChar adalah method yang akan mengembalikan value dari Code 93 yang diberikan
    private int getValueFromChar(char character) {
        return charToValue.get(character);
    }

    /* Method checksum adalah method yang akan mengembalikan checksum C dan checksum K yang telah digabung, 
    atau dengan kata lain, dua karakter terakhir dari ID */
    public String checksum(String idAnggota11) {
        int sumC = 0;
        int rangeC = 11;
        for (int i = 0; i < idAnggota11.length(); i++) {
            char a = idAnggota11.charAt(i);
            sumC += getValueFromChar(a) * rangeC--;
        }
        int checksumC = sumC % 36;
        String charChecksumC = getCharFromValue(checksumC)+"";
        String idAnggota12 = idAnggota11 + charChecksumC;
        int sumK = 0;
        int rangeK = 12;
        for (int j = 0; j < idAnggota12.length(); j++) {
            char b = idAnggota12.charAt(j);
            sumK += getValueFromChar(b) * rangeK--;
        }
        int checksumK = sumK % 36;
        String charChecksumK = getCharFromValue(checksumK)+"";
        return charChecksumC + charChecksumK;
    }

    protected String generateId(String programStudi, String angkatan, String tanggalLahir) {
        String idAnggota11 = programStudi + angkatan.substring(angkatan.length()-2) + tanggalLahir.substring(0,2) +
                            tanggalLahir.substring(3,5) + tanggalLahir.substring(tanggalLahir.length()-2);
        return idAnggota11 + checksum(idAnggota11);
    }

    public boolean isNumerik(String tanggalLahir) {
        String[] arrOftanggalLahir = tanggalLahir.split("/");
        for (String s: arrOftanggalLahir) {
            for (char c: s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    // menambahkan mahasiswa pada daftar anggota
    public void tambahMahasiswa() {
        System.out.print("Nama: ");
        String nama = input.nextLine();
        System.out.print("Program Studi (SIK/SSI/MIK/MTI/DIK): ");
        String programStudi = input.nextLine();
        System.out.print("Angkatan: ");
        String angkatan = input.nextLine();
        System.out.print("Tanggal Lahir (dd/mm/yyyy): ");
        String tanggalLahir = input.nextLine();

        if ((programStudi.equals("SIK") || programStudi.equals("SSI") || programStudi.equals("MIK") ||
            programStudi.equals("MTI") || programStudi.equals("DIK")) && (angkatan.length() == 4) && 
            (Integer.parseInt(angkatan) < 2022) && (Integer.parseInt(angkatan) > 1999) && (tanggalLahir.length() == 10) && 
            (tanggalLahir.substring(2,3).equals("/")) && (tanggalLahir.substring(5,6).equals("/")) && (isNumerik(tanggalLahir))) {
                if ((Integer.parseInt(tanggalLahir.substring(0,2)) < 32) && (Integer.parseInt(tanggalLahir.substring(0,2)) > 0) &&
                (Integer.parseInt(tanggalLahir.substring(3,5)) < 13) && (Integer.parseInt(tanggalLahir.substring(3,5)) > 0)) {
                    jumlahAnggota++;
                    String id = generateId(programStudi, angkatan, tanggalLahir);
                    Mahasiswa mahasiswa = new Mahasiswa(nama, tanggalLahir, programStudi, angkatan, id);
                    Anggota[] membersAdd = new Anggota[jumlahAnggota];
                    membersAdd[jumlahAnggota-1] = anggota;
                    if (jumlahAnggota > 1) {
                        for (int i = 0; i < jumlahAnggota-1; i++) {
                            membersAdd[i] = members[i];
                        }
                    } 
                    members = membersAdd;
                    System.out.print(anggota);
                }
        }
        else {
            System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!");
        }
    }

     public String pinjam(Buku buku, String tanggalPeminjaman){
        int cariIndexDaftarPeminjaman = findIndexDaftarPeminjaman(buku);

        if(!buku.cekStok(1)) {
            return "Buku " + buku.getJudul() + " oleh " + buku.getPenulis() + " tidak tersedia";
        }
        else {
            int isiDaftarPeminjaman = cekDaftarPeminjaman(daftarPeminjaman);

            // jika buku yang dipinjam sudah 3
            if(isiDaftarPeminjaman >= BATAS_JUMLAH_PEMINJAMAN_BUKU){
                return "Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal";
            }else{
                // jika denda member lebih dr Rp5000
                if(this.getDenda() > BATAS_MAKSIMAL_DENDA){
                    return "Denda lebih dari Rp 10000";
                }else {
                    // jika menemukan book pada bookLoans
                    if(x == true){
                        return "Buku " + buku.getJudul() + " oleh " + buku.getPenulis() + " sedang dipinjam";
                    }else{
                        // membuat obj baru dan masukkan pada array bookLoan
                        daftarPeminjaman[cariIndexDaftarPeminjaman]= new Peminjaman(this, buku, tanggalPeminjaman);
                        
                        // untuk bookLoansHistory
                        jumlahDaftarPeminjaman++;
                        // membuat array baru
                        Peminjaman[] newSejarahPeminjaman = new Peminjaman[jumlahDaftarPeminjaman];
                        // membuat obj baru
                        Peminjaman sejarahPeminjamanBaru = new Peminjaman(this, buku, tanggalPeminjaman);
                        // meletakkan obj baru pada array baru
                        newSejarahPeminjaman[jumlahDaftarPeminjaman-1] = sejarahPeminjamanBaru;
                        // jika jumlahBookLoan lebih dari 1
                        // maka obj book yang sudah ditambahkan sebelumnya diassign kembali
                        if(jumlahDaftarPeminjaman > 1){
                            for(int i = 0; i < jumlahDaftarPeminjaman -1 ; i++){
                                newSejarahPeminjaman[i] = sejarahPeminjaman[i];
                            }
                        }
                        sejarahPeminjaman = newSejarahPeminjaman;

                        // mengeset stock buku yang baru (berkurang 1 karena sudah dipinjam)
                        buku.setStok(buku.getStok()-1);
                        return this.getNama() + " berhasil meminjam Buku " + buku.getJudul() +"!";
                        
                    }
                }
            }
        }
    }
}
