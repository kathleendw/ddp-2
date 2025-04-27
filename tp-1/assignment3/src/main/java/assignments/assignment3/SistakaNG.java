
import java.util.Scanner;
import java.util.HashMap;

public class SistakaNG {
    private static Scanner input = new Scanner(System.in);
    
    public static Staf[] daftarStaf = new Staf[5];
    public static Anggota[] daftarAnggota = new Anggota[1];
    public static Buku[] daftarBuku = new Buku[1];
    public static Kategori[] daftarKategori = new Kategori[1];
    public static Pengguna penggunaLoggedIn;
    static int jumlahKategori = 0;
    static int jumlahBuku = 0;

    public static void main(String[] args) {
        System.out.println("Start - Register Staf...");
        registerStaf();
        System.out.println("Done - Register Staf...\n");
        menu();
        input.close();
    }

    // Method ini digunakan untuk mendaftarkan staf-staf ketika program dijalankan
    private static void registerStaf() {
        String[] listNama = new String[]{"Dek Depe", "Dek DePram", "Dek Sofita", "Winter", "Boo"};

        for (int i = 0; i < listNama.length; i++) {
            // TODO: Buat objek Staf menggunakan listNama[i]
            Staf staf = new Staf(listNama[i], null);
            daftarStaf[i] = staf;
            // TODO: Setelah objek Staf behasil dibuat, uncomment 2 baris kode di bawah ini
            System.out.println("Berhasil menambahkan staf dengan data:");
            System.out.println(staf);
        }
    }

    // Method ini digunakan untuk mencetak menu utama dari SistakaNG
    public static void menu() {
        boolean hasChosenExit = false;
        System.out.println("Selamat Datang di Sistem Perpustakaan SistakaNG!");
        while (!hasChosenExit) {
            int command = 0;
            System.out.println("================ Menu Utama ================\n");
            System.out.println("1. Login");
            System.out.println("2. Keluar");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                menuLogin();
            } else if (command == 2) {
                System.out.println("Terima kasih telah menggunakan SistakaNG. Sampai jumpa di lain kesempatan!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method ini digunakan untuk mencetak menu login
    public static void menuLogin() {
        boolean isLoginSuccess = false;
        while (!isLoginSuccess) {
            System.out.println("Masukkan ID Anda untuk login ke sistem");
            System.out.print("ID: ");
            String id = input.nextLine();

            // TODO: Implementasi login -> jika login berhasil, ubah nilai isLoginSuccess menjadi true
            for (int i = 0; i < daftarStaf.length; i++) {
                if (daftarStaf[i].getId().equals(id)) {
                    System.out.println("Halo, " + daftarStaf[i] + "! Selamat datang di SistakaNG");
                    penggunaLoggedIn = daftarStaf[i];
                    isLoginSuccess = true;
                }
                else if (daftarAnggota[i].getId().equals(id)) {
                    System.out.println("Halo, " + daftarAnggota[i] + "! Selamat datang di SistakaNG");
                    penggunaLoggedIn = daftarAnggota[i];
                    isLoginSuccess = true;
                }
                else {
                    System.out.println("Pengguna dengan ID " + id + " tidak ditemukan");
                }
            }
        }
        showMenu();
    }

    // Method ini digunakan untuk mencetak menu yang dapat diakses berdasarkan jenis penggunaLoggedIn
    private static void showMenu() {
        if (penggunaLoggedIn instanceof Staf) {
            showMenuStaf();
        } else {
            showMenuAnggota();
        }
    }

    // Method ini digunakan untuk mencetak menu staf dari SistakaNG
    private static void showMenuStaf() {
        int command = 0;
        boolean hasChosenExit = false;
        while (!hasChosenExit) {
            System.out.println("================ Menu Staf ================\n");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tambah Kategori");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. 3 Peringkat Pertama");
            System.out.println("6. Detail Anggota");
            System.out.println("7. Daftar Peminjam Buku");
            System.out.println("99. Logout");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                // TODO: Implementasikan menu-nya
                buildMapCharToValue();
                System.out.println("---------- Tambah Anggota ----------");
                System.out.println("Tipe Anggota: ");
                String jenis = input.nextLine();
                if (jenis.equals("Mahasiswa")){
                    ((Mahasiswa) penggunaLoggedIn).tambahMahasiswa();
                }
                else if (jenis.equals("Dosen")) {
                    ((Dosen) penggunaLoggedIn).tambahDosen();
                }
                else {
                    System.out.println("Tipe Anggota " + jenis + " tidak valid!");
                }
            } else if (command == 2) {
                // TODO: Implementasikan menu-nya
                tambahKategori();
            } else if (command == 3) {
                // TODO: Implementasikan menu-nya
                tambahBuku();
            } else if (command == 4) {
                // TODO: Implementasikan menu-nya
                // hapus buku
            } else if (command == 5) {
                // TODO: Implementasikan menu-nya
                // tiga peringkat teratas
            } else if (command == 6) {
                // TODO: Implementasikan menu-nya
                System.out.println("---------- Detail Anggota ----------");
                System.out.println("ID Anggota: ");
                String id = input.nextLine();
                for (int i = 0; i < daftarAnggota.length; i++) {
                    if (daftarAnggota[i].getId().equals(id)) {
                        daftarAnggota[i].detail();
                    }
                    else {
                        System.out.println("Anggota dengan ID " + id + " tidak ditemukan");
                    }
                }
            } else if (command == 7) {
                // TODO: Implementasikan menu-nya
                System.out.println("---------- Daftar Peminjam Buku ----------");
                System.out.println("Judul: ");
                String judul = input.nextLine();
                System.out.println("Penulis: ");
                String penulis = input.nextLine();
                for (int i = 0; i < daftarBuku.length; i++) {
                    if (daftarBuku[i].getJudul().equals(judul) && daftarBuku[i].getPenulis().equals(penulis)) {
                        daftarBuku[i].getDaftarPeminjam();
                    }
                    else {
                        System.out.println("Buku " + daftarBuku[i].getJudul() + " oleh " + daftarBuku[i].getPenulis() + " tidak ditemukan");
                    }
                }
            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method ini digunakan untuk mencetak menu anggota dari SistakaNG
    private static void showMenuAnggota() {
        int command = 0;
        boolean hasChosenExit = false;
        while (!hasChosenExit) {
            System.out.println("================ Menu Anggota ================\n");
            System.out.println("1. Peminjaman");
            System.out.println("2. Pengembalian");
            System.out.println("3. Pembayaran Denda");
            System.out.println("4. Detail Anggota");
            System.out.println("99. Logout");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                // TODO: Implementasikan menu-nya
                System.out.println("---------- Peminjaman Buku ----------");
                System.out.println("Judul Buku: ");
                String judul = input.nextLine();
                System.out.println("Penulis Buku: ");
                String penulis = input.nextLine();
                System.out.println("Tanggal Peminjaman: ");
                String tanggalPeminjaman = input.nextLine();
                for (int i = 0; i < daftarBuku.length; i++) {
                    if (daftarBuku[i].getJudul().equals(judul) && daftarBuku[i].getPenulis().equals(penulis)) {
                        if (penggunaLoggedIn instanceof Dosen) {
                            ((Dosen) penggunaLoggedIn).pinjam(daftarBuku[i], tanggalPeminjaman);
                        }
                        else {
                            ((Mahasiswa) penggunaLoggedIn).pinjam(daftarBuku[i], tanggalPeminjaman);
                        }
                    }
                }
            } else if (command == 2) {
                // TODO: Implementasikan menu-nya
                System.out.println("---------- Pengembalian Buku ----------");
                System.out.println("Judul Buku: ");
                String judul = input.nextLine();
                System.out.println("Penulis Buku: ");
                String penulis = input.nextLine();
                System.out.println("Tanggal Pengembalian: ");
                String tanggalPengembalian = input.nextLine();
                for (int i = 0; i < daftarBuku.length; i++) {
                    if (daftarBuku[i].getJudul().equals(judul) && daftarBuku[i].getPenulis().equals(penulis)) {
                        if (penggunaLoggedIn instanceof Dosen) {
                            ((Dosen) penggunaLoggedIn).kembali(daftarBuku[i], tanggalPengembalian);
                        }
                        else {
                            ((Mahasiswa) penggunaLoggedIn).kembali(daftarBuku[i], tanggalPengembalian);
                        }
                    }
                }
            } else if (command == 3) {
                // TODO: Implementasikan menu-nya
                System.out.println("---------- Pembayaran Denda ----------");
                System.out.println("Jumlah: ");
                int jumlahBayar = 1000;
                if (penggunaLoggedIn instanceof Dosen) {
                    ((Dosen) penggunaLoggedIn).bayarDenda(jumlahBayar);
                }
                else {
                    ((Mahasiswa) penggunaLoggedIn).bayarDenda(jumlahBayar);
                }
            } else if (command == 4) {
                // TODO: Implementasikan menu-nya
                if (penggunaLoggedIn instanceof Dosen) {
                    ((Dosen) penggunaLoggedIn).detail();
                }
                else {
                    ((Mahasiswa) penggunaLoggedIn).detail();
                }
            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    public static int findIndexKategori(String nama) {
        int indexKategori = 0;
        for (int i = 0; i < daftarKategori.length; i++){
            if(daftarKategori[i] == null){
                indexKategori = i;
                break;
            }
            if(daftarKategori[i].getNama().toLowerCase().equals(nama.toLowerCase())){
                indexKategori = i;
                break;
            }
        }
        return indexKategori;
    }

    public static void tambahKategori() {
        System.out.println("---------- Tambah Kategori ----------");
        System.out.print("Nama Kategori: ");
        String nama = input.nextLine();
        System.out.print("Point: ");
        int poin = input.nextInt();
        input.nextLine();

        int cariIndexKategori = findIndexKategori(nama);
        if (daftarKategori[cariIndexKategori] != null) {
            System.out.println("Kategori " + daftarKategori[cariIndexKategori].getNama() + " sudah pernah ditambahkan");
        }
        else {
            jumlahKategori++;
            Kategori[] newDaftarKategori = new Kategori[jumlahKategori];
            Kategori kategori = new Kategori(nama, poin);
            newDaftarKategori[jumlahKategori-1] = kategori;
            if (jumlahKategori > 1) {
                for (int i = 0; i < jumlahKategori-1; i++) {
                    newDaftarKategori[i] = daftarKategori[i];
                }
            } 
            daftarKategori = newDaftarKategori;
            System.out.print(kategori);
        }
    }
    
    public static int findIndexBuku(String judul, String penulis) {
        int indexBuku = 0;
        for (int i = 0; i < daftarBuku.length; i++) {
            if (daftarBuku[i] == null) {
                indexBuku = i;
                break;
            }
            if ((daftarBuku[i].getJudul().toLowerCase().equals(judul.toLowerCase())) && (daftarBuku[i].getPenulis().toLowerCase().equals(penulis.toLowerCase()))) {
                indexBuku = i;
                break;
            }
        }
        return indexBuku;
    }

    public static void tambahBuku() {
        System.out.println("---------- Tambah Buku ----------");
        System.out.print("Judul: ");
        String judul = input.nextLine();
        System.out.print("Penulis: ");
        String penulis = input.nextLine();
        System.out.print("Penerbit: ");
        String penerbit = input.nextLine();
        System.out.print("Kategori: ");
        String kategori = input.nextLine();
        System.out.print("Stok: ");
        int stok = input.nextInt();
        input.nextLine();

        int cariIndexBuku = findIndexBuku(judul, penulis);
        int cariIndexKategori = findIndexKategori(kategori);
        if (daftarBuku[cariIndexBuku] != null) {
            System.out.println("Buku " + daftarBuku[cariIndexBuku].getJudul() + " oleh " + daftarBuku[cariIndexBuku].getPenulis() + " sudah pernah ditambahkan");
        }
        else {
            if (stok <= 0) {
                System.out.println("Stok harus lebih dari 0");
            }
            else {
                if (daftarKategori[cariIndexKategori] == null) {
                    System.out.println("Kategori " + kategori + " tidak ditemukan");
                }
                else {
                    jumlahBuku++;
                    Buku[] newDaftarBuku = new Buku[jumlahBuku];
                    Buku buku = new Buku(judul, penulis, penerbit, stok, daftarKategori[cariIndexKategori]);
                    newDaftarBuku[jumlahBuku-1] = buku;
                    if (jumlahBuku > 1) {
                        for (int i = 0; i < jumlahBuku-1; i++) {
                            newDaftarBuku[i] = daftarBuku[i];
                        }
                    } 
                    daftarBuku = newDaftarBuku;
                    System.out.println(buku);
                }
            }
        }
    }
}
