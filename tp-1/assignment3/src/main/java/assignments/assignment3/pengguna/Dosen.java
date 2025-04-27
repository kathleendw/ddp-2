

public class Dosen extends Anggota{
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)

    // Attributes
    private static int BATAS_JUMLAH_PEMINJAMAN_BUKU = 5;
    public static long BATAS_MAKSIMAL_DENDA = 10000;
    public static int jumlahDosen = 0;
    int jumlahDaftarPeminjaman = 0;

    // Constructor
    public Dosen(String nama, String id){
        super(nama, id, "Dosen");
    }

    // generate id untuk dosen
    protected String generateId() {
        String id = "Staf-" + (jumlahDosen + 1);
        jumlahDosen++;
        return id;
    }

    // menambahkan dosen pada daftar anggota
    public void tambahDosen() {
        System.out.print("Nama: ");
        String nama = input.nextLine();
        jumlahAnggota++;
        String id = generateId();
        Dosen dosen = new Dosen(nama, id);
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

    // mengecek jumlah buku pada daftar peminjaman
    public int cekDaftarPeminjaman(Peminjaman[] daftarPeminjaman){
        int x = 0; 
        for (int i = 0; i < daftarPeminjaman.length; i++){
            if(daftarPeminjaman[i] != null){
                x++;
            }
        }
        return x;
    }

    // method untuk meminjam buku
    public String pinjam(Buku buku, String tanggalPeminjaman){
        int cariIndexDaftarPeminjaman = findIndexDaftarPeminjaman(buku);

        // jika stok habis
        if(!buku.cekStok(1)) {
            return "Buku " + buku.getJudul() + " oleh " + buku.getPenulis() + " tidak tersedia";
        }
        else {
            int isiDaftarPeminjaman = cekDaftarPeminjaman(daftarPeminjaman);

            // jika buku yang dipinjam melebih batas jumlah peminjaman buku
            if(isiDaftarPeminjaman >= BATAS_JUMLAH_PEMINJAMAN_BUKU){
                return "Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal";
            }else{
                // jika denda pengguna melebihi batas maksimal denda
                if(this.getDenda() > BATAS_MAKSIMAL_DENDA){
                    return "Denda lebih dari Rp 10000";
                }else {
                    // jika menemukan buku pada daftar peminjaman
                    if(x == true){
                        return "Buku " + buku.getJudul() + " oleh " + buku.getPenulis() + " sedang dipinjam";
                    }else{
                        daftarPeminjaman[cariIndexDaftarPeminjaman]= new Peminjaman(this, buku, tanggalPeminjaman);
                        jumlahDaftarPeminjaman++;
                        Peminjaman[] newSejarahPeminjaman = new Peminjaman[jumlahDaftarPeminjaman];
                        Peminjaman sejarahPeminjamanBaru = new Peminjaman(this, buku, tanggalPeminjaman);
                        newSejarahPeminjaman[jumlahDaftarPeminjaman-1] = sejarahPeminjamanBaru;
                        if(jumlahDaftarPeminjaman > 1){
                            for(int i = 0; i < jumlahDaftarPeminjaman -1 ; i++){
                                newSejarahPeminjaman[i] = sejarahPeminjaman[i];
                            }
                        }
                        sejarahPeminjaman = newSejarahPeminjaman;
                        buku.setStok(buku.getStok()-1);
                        return this.getNama() + " berhasil meminjam Buku " + buku.getJudul() +"!";
                        
                    }
                }
            }
        }
    }
}
