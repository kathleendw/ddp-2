public abstract class Anggota extends Pengguna implements Comparable<Anggota>, CanBorrow {
    // Attributes
    protected long denda = 0;
    protected int poin = 0;
    protected Peminjaman[] daftarPeminjaman;
    protected Peminjaman[] sejarahPeminjaman;
    public int jumlahDaftarPeminjaman = 0;
    public int jumlahSejarahPeminjaman = 0;

    // Constructor
    Anggota (String nama, String id){
        super(nama, id);
        daftarPeminjaman = new Peminjaman[jumlahDaftarPeminjaman+1];
        sejarahPeminjaman = new Peminjaman[jumlahSejarahPeminjaman+1];
    }

    // membandingkan poin seorang anggota dengan anggota lainnya
    @Override
    public int compareTo(Anggota other) {
        // jika poinnya sama
        if (this.poin == other.poin) {
            // urutkan secara leksikografi
            if (this.getNama().compareTo(other.getNama()) > 0) {
                return 0;
            }
            else {
                return -1;
            }
        }
        else if (this.poin > other.poin) {
            return 2;
        } 
        else {
            return 1;  
        }  
    }

    // tampilan yang akan muncul di terminal saat objek Anggota dipanggil
    @Override
    public String toString() {
        return "ID Anggota: " + this.getId() + "\n" +
        "Nama Anggota: " + this.getNama() + "\n" +
        "Total Poin: " + poin + "\n" +
        "Denda: Rp" + denda;
    }

    // tampilan yang akan muncul di terminal saat ingin melihat detail anggota
    public void detail() {
        System.out.println(this);
        System.out.println("Riwayat Peminjaman Buku :");
        for(int i = 0; i < this.getSejarahPeminjaman().length; i++){
            if(this.getSejarahPeminjaman()[i] != null) {
                System.out.println("-------------- " + (i+1) + " ------------");
                System.out.println();
                System.out.println("Judul Buku: " + this.getSejarahPeminjaman()[i].getBuku().getJudul());
                System.out.println("Penulis Buku: " + this.getSejarahPeminjaman()[i].getBuku().getPenulis());
                System.out.println("Penerbit Buku: " + this.getSejarahPeminjaman()[i].getBuku().getPenerbit());
                System.out.println("Kategori: " + this.getSejarahPeminjaman()[i].getBuku().getKategori().getNama());
                System.out.println("Point: " + this.getSejarahPeminjaman()[i].getBuku().getKategori().getPoin());
                System.out.println("Tanggal Peminjaman: " + this.getSejarahPeminjaman()[i].getTanggalPeminjaman());
                System.out.println("Tanggal Pengembalian: " + this.getSejarahPeminjaman()[i].getTanggalPengembalian());
                System.out.println("Denda: " + this.getSejarahPeminjaman()[i].getDenda());
            }
            else {
                System.out.println("Belum pernah meminjam buku");
            }
        }
    }

    // method untuk membayar denda
    public String bayarDenda(long jumlahBayar) {
        // jika anggota tidak memiliki denda
        if (this.getDenda() == 0) {
            return this.getNama() + " tidak memiliki denda";
        }
        else {
            // jika jumlah yang dibayar anggota lebih atau sama dengan denda yang dimiliki
            if (jumlahBayar >= this.getDenda()) {
                long jumlahKembalian = jumlahBayar - this.getDenda();
                // menset denda dengan 0 karena sudah lunas
                this.setDenda(0);
                return this.getNama() + " berhasil membayar lunas denda" + "\n" +
                "Jumlah kembalian: Rp " + jumlahKembalian;
            }
            // jika jumlah yang dibayar anggota masih kurang sehingga masih ada sisa denda
            else {
                long sisaDenda = this.getDenda() - jumlahBayar;
                // menset denda dengan sisa denda yang dimiliki
                this.setDenda(sisaDenda);
                return this.getNama() + " berhasil membayar denda sebesar Rp " + jumlahBayar + "\n" +
                "Sisa denda saat ini: Rp " + sisaDenda;
            }
        }
    }

    // method untuk mengembalikan buku
    public String kembali(Buku buku, String tanggalPengembalian) {
        long hitungDenda = 0;
        // mengakses daftarPeminjaman anggota
        for(int i = 0; i < this.daftarPeminjaman.length; i++){
            // jika anggota sedang meminjam buku dan buku terdapat pada daftarPeminjaman
            if(this.daftarPeminjaman[i] != null && (this.daftarPeminjaman[i].getBuku().getJudul().toLowerCase()).equals(buku.getJudul().toLowerCase()) &&
            (this.daftarPeminjaman[i].getBuku().getPenulis().toLowerCase()).equals(buku.getPenulis().toLowerCase())){
                // memasukkan tanggalPengembalian pada buku dalam daftarPeminjaman
                this.getDaftarPeminjaman()[i].kembalikanBuku(tanggalPengembalian);
                // menset poin dengan menambahkan poin yang sudah dimiliki dan poin dari kategori buku yang baru saja dikembalikan
                this.setPoin(this.getPoin() + (buku.getKategori()).getPoin());
                // menghitung denda 
                hitungDenda = this.daftarPeminjaman[i].hitungDenda();
                // menset denda dengan menambahkan denda yang sudah dimiliki dan denda yang baru saja didapatkan
                this.setDenda(this.getDenda() + hitungDenda);
                // memasukkan tanggalPengembalian pada buku dalam sejarahPeminjaman
                this.getSejarahPeminjaman()[i].kembalikanBuku(tanggalPengembalian);
                // memasukkan denda pada buku dalam sejarahPeminjaman
                this.getSejarahPeminjaman()[i].setDenda(hitungDenda);
                // stok buku bertambah 1 karena sudah kembali
                buku.setStok(buku.getStok() + 1);
                // buku dalam daftarPeminjaman yang sudah dikembalikan oleh anggota dijadikan null
                daftarPeminjaman[i] = null;
                return "Buku " + buku.getJudul() + " berhasil dikembalikan oleh " + this.getNama() + " dengan denda " + hitungDenda + "!";
            }
        }
        return "Buku " + buku.getJudul() + " tidak sedang dipinjam oleh " + this.getNama();
    }

    // Getters and setters
    public Peminjaman[] getDaftarPeminjaman() {
        return daftarPeminjaman;
    }

    // menambahkan objek peminjaman kepada array daftarPeminjaman anggota
    public void setDaftarPeminjaman(Peminjaman peminjaman) {
        // karena peminjaman ditambah, maka jumlah peminjaman bertambah 1
        jumlahDaftarPeminjaman++;
        // membuat array baru
        Peminjaman[] peminjamanAdd = new Peminjaman[jumlahDaftarPeminjaman];
        // memasukkan peminjaman pada index terakhir peminjamanAdd
        peminjamanAdd[jumlahDaftarPeminjaman-1] = peminjaman;
        // jika sebelumnya sudah terdapat peminjaman, maka peminjaman-peminjaman tersebut juga dimasukkan pada peminjamanAdd
        if(jumlahDaftarPeminjaman > 1){
            for(int i = 0; i < jumlahDaftarPeminjaman-1 ; i++){
                peminjamanAdd[i] = daftarPeminjaman[i];
            }
        }
        // memodifikasi array daftarPeminjaman menjadi peminjamanAdd
        daftarPeminjaman = peminjamanAdd;
    }

    public Peminjaman[] getSejarahPeminjaman() {
        return sejarahPeminjaman;
    }

    // menambahkan objek peminjaman kepada array sejarahPeminjaman anggota
    public void setSejarahPeminjaman(Peminjaman peminjaman) {
        // karena peminjaman ditambah, maka jumlah peminjaman bertambah 1
        jumlahSejarahPeminjaman++;
        // membuat array baru
        Peminjaman[] peminjamanAdd = new Peminjaman[jumlahSejarahPeminjaman];
        // memasukkan peminjaman pada index terakhir peminjamanAdd
        peminjamanAdd[jumlahSejarahPeminjaman-1] = peminjaman;
        // jika sebelumnya sudah terdapat peminjaman, maka peminjaman-peminjaman tersebut juga dimasukkan pada peminjamanAdd
        if(jumlahSejarahPeminjaman > 1){
            for(int i = 0; i < jumlahSejarahPeminjaman-1 ; i++){
                peminjamanAdd[i] = sejarahPeminjaman[i];
            }
        }
        // memodifikasi array sejarahPeminjaman menjadi peminjamanAdd
        sejarahPeminjaman = peminjamanAdd;
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

    public void setPoin(int poin) {
        this.poin = poin;
    }

}
