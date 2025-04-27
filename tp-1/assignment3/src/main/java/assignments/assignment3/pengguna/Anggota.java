

public abstract class Anggota extends Pengguna implements Comparable<Anggota>, CanBorrow {
    // TODO: Implementasi kelas ini sesuai dengan UML Diagram (attribute, method, inheritance, dll)

    // Attributes
    protected long denda = 0;
    protected int poin = 0;
    protected String jenis;
    protected Peminjaman[] daftarPeminjaman;
    protected Peminjaman[] sejarahPeminjaman;

    // Constructor
    Anggota (String nama, String id, String jenis){
        super(nama, id);
        this.jenis = jenis;
    }

    @Override
    // Membandingkan poin seorang anggota dengan anggota lainnya
    public int compareTo(Anggota other) {
        if (poin == other.poin) {
            return 0;  
        }
        else if (poin > other.poin) {
            return 1;
        } 
        else {
            return -1;  
        }  
    }

    // Tampilan yang akan keluar pada terminal jika anggota berhasil ditambahkan
    @Override
    public String toString() {
        return "ID Anggota: " + this.getId() + "\n" +
        "Nama Anggota: " + this.getNama() + "\n" +
        "Total Poin: " + poin + "\n" +
        "Denda: Rp" + denda;
    }

    // Getters and setters
    public Peminjaman[] getDaftarPeminjaman() {
        return daftarPeminjaman;
    }

    public Peminjaman[] getSejarahPeminjaman() {
        return sejarahPeminjaman;
    }

    public long getDenda() {
        return denda;
    }

    public void setDenda(long denda) {
        this.denda = denda;
    }

    public int getPoin() {
        return poin;
    }

    // Tampilan yang akan keluar saat user memilih detail anggota
    public void detail() {
        System.out.println(this);
        System.out.println("Riwayat Peminjaman Buku :");
        for(int i = 0; i < this.getDaftarPeminjaman().length; i++){
            if(this.getDaftarPeminjaman()[i] != null) {
                System.out.println("-------------- " + (i+1) + " ------------");
                System.out.println();
                System.out.println("JudulBuku: " + this.getDaftarPeminjaman()[i].getBuku().getJudul());
                System.out.println("Penulis Buku: " + this.getDaftarPeminjaman()[i].getBuku().getPenulis());
                System.out.println("Penerbit Buku: " + this.getDaftarPeminjaman()[i].getBuku().getPenerbit());
                System.out.println("Kategori: " + this.getDaftarPeminjaman()[i].getBuku().getKategori().getNama());
                System.out.println("Point: " + this.getDaftarPeminjaman()[i].getBuku().getKategori().getPoin());
                System.out.println("Tanggal Peminjaman: " + this.getDaftarPeminjaman()[i].getTanggalPeminjaman());
                System.out.println("Tanggal Pengembalian: " + this.getDaftarPeminjaman()[i].getTanggalPengembalian());
                System.out.println("Denda: " + this.getDaftarPeminjaman()[i].getDenda());
            }
            else {
                break;
            }
        }
    }

    // Method untuk bayar denda
    public String bayarDenda(long jumlahBayar) {
        // jika pengguna tidak memiliki denda
        if (this.getDenda() == 0) {
            return this.getNama() + " tidak memiliki denda";
        }
        else {
            // jika jumlah bayar lebih atau sama dengan denda yang dimiliki
            if (jumlahBayar >= this.getDenda()) {
                long jumlahKembalian = jumlahBayar - this.getDenda();
                this.setDenda(0);
                return this.getNama() + " berhasil membayar lunas denda" + "\n" +
                "Jumlah kembalian: Rp " + jumlahKembalian;
            }
            // jika jumlah bayar masih kurang sehingga masih ada sisa denda
            else {
                long sisaDenda = this.getDenda() - jumlahBayar;
                this.setDenda(sisaDenda);
                return this.getNama() + " berhasil membayar denda sebesar Rp " + jumlahBayar + "\n" +
                "Sisa denda saat ini: Rp " + sisaDenda;
            }
        }
    }

    // mencari index buku pada daftar peminjaman
    public int findIndexDaftarPeminjaman(Buku buku) {
        int indexDaftarPeminjaman = 0;
        for (int i = 0; i < daftarPeminjaman.length; i++){
            if(daftarPeminjaman[i] == null){
                indexDaftarPeminjaman = i;
                break;
            }
            if(daftarPeminjaman[i].getBuku().equals(buku)){
                indexDaftarPeminjaman = i;
                break;
            }
        }
        return indexDaftarPeminjaman;
    }

    // mencari index buku pada sejarah peminjaman
    public int findIndexSejarahPeminjaman(Buku buku) {
        int indexSejarahPeminjaman = 0;
        for (int i = 0; i < sejarahPeminjaman.length; i++){
            if(sejarahPeminjaman[i] == null){
                indexSejarahPeminjaman = i;
                break;
            }
            if(sejarahPeminjaman[i].getBuku().equals(buku)){
                indexSejarahPeminjaman = i;
                break;
            }
        }
        return indexSejarahPeminjaman;
    }

    // method untuk mengembalikan buku
    public String kembali(Buku buku, String tanggalPengembalian) {
        int cariIndexDaftarPeminjaman = findIndexDaftarPeminjaman(buku);
        int cariIndexSejarahPeminjaman = findIndexSejarahPeminjaman(buku);

        for (int i = 0; i < daftarBuku.length; i++) {
            // jika buku tidak terdapat pada daftar buku
            if (!daftarBuku[i].getJudul().equals(buku.getJudul())) {
                return "Buku " + buku.getJudul() + " oleh " + buku.getPenulis() + " tidak ditemukan";
            }
            else {
                // jika buku tidak sedang dipinjam
                if (daftarPeminjaman[cariIndexPeminjaman] == null) {
                    return "Buku " + buku.getJudul() + " tidak sedang dipinjam";
                }
                else {
                    // jika berhasil mengembalikan buku
                    this.setPoin(this.getPoin() + (buku.getKategori()).getPoin()); // poin bertambah
                    this.setDenda(this.getDenda() + denda); // denda bertambah
                    this.getDaftarPeminjaman()[cariIndexPeminjaman].setTanggalPengembalian(tanggalPengembalian);
                    this.getBookLoanHistory()[index1].setTanggalPengembalian(tanggalPengembalian);
                    this.getBookLoanHistory()[index1].setDenda(denda);
                    buku.setStok(buku.getStok()+1); // stok bertambah
                    daftarPeminjaman[cariIndexPeminjaman] = null;
                    return "Buku " +  buku.getJudul() + " berhasil dikembalikan oleh " + this.getNama() + " dengan denda Rp" + hitungDenda();
                }
            }
        }
    }
}
