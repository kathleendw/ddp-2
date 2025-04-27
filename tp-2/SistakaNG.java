import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;
import java.util.Collections;

public class SistakaNG {
    private static Scanner input = new Scanner(System.in);
    
    public static Staf[] daftarStaf;
    public static Anggota[] daftarAnggota;
    public static Buku[] daftarBuku;
    public static Kategori[] daftarKategori;
    public static Pengguna penggunaLoggedIn;
    static HashMap<Character, Integer> charToValue = new HashMap<>(36);
    static char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    static int indexId = 0;
    static int jumlahAnggota = 0;
    static int indexKategori = 0;
    static int jumlahKategori = 0;
    static int indexBuku = 0;
    static int jumlahBuku = 0;

    public static void main(String[] args) {
        buildMapCharToValue();
        daftarAnggota = new Anggota[1];
        daftarBuku = new Buku[1];
        daftarKategori = new Kategori[1];
        System.out.println("Start - Register Staf...");
        registerStaf();
        System.out.println("Done - Register Staf...\n");
        menu();
        input.close();
    }

    // Method ini digunakan untuk mendaftarkan staf-staf ketika program dijalankan
    private static void registerStaf() {
        String[] listNama = new String[]{"Dek Depe", "Dek DePram", "Dek Sofita", "Winter", "Boo"};
        daftarStaf = new Staf[5];
        for (int i = 0; i < listNama.length; i++) {
            // Membuat objek Staf menggunakan listNama[i]
            Staf staf = new Staf(listNama[i]);
            daftarStaf[i] = staf;
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

            // Implementasi login
            // jika yang login adalah staf
            for (int i = 0; i < daftarStaf.length; i++) {
                if (daftarStaf[i].getId().equals(id)) { // jika id yang dimasukkan benar
                    System.out.println("Halo, " + daftarStaf[i].getNama() + "! Selamat datang di SistakaNG");
                    penggunaLoggedIn = daftarStaf[i];
                    isLoginSuccess = true;
                    showMenu();
                    break;
                }
            }
            if(!isLoginSuccess) {
                // jika yang login adalah anggota
                for (int j = 0; j < daftarAnggota.length; j++) {
                    if (daftarAnggota[j] != null) {
                        if (daftarAnggota[j].getId().equals(id)) { // jika id yang dimasukkan benar
                            System.out.println("Halo, " + daftarAnggota[j].getNama() + "! Selamat datang di SistakaNG");
                            penggunaLoggedIn = daftarAnggota[j];
                            isLoginSuccess = true;
                            showMenu();
                            break;
                        }
                    }
                }
            }
            if (!isLoginSuccess) {
                // jika id yang dimasukkan salah
                System.out.println("Pengguna dengan ID " + id + " tidak ditemukan");
            }
        }
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
                System.out.println("---------- Tambah Anggota ----------");
                System.out.print("Tipe Anggota: ");
                String tipeAnggota = input.nextLine();
                if (tipeAnggota.equals("Mahasiswa")){
                    tambahMahasiswa();
                }
                else if (tipeAnggota.equals("Dosen")) {
                    tambahDosen();
                }
                else {
                    System.out.println("Tipe Anggota " + tipeAnggota + " tidak valid!");
                }
            } else if (command == 2) {
                tambahKategori();
            } else if (command == 3) {
                tambahBuku();
            } else if (command == 4) {
                hapusBuku();
            } else if (command == 5) {
                peringkatAnggota();
            } else if (command == 6) {
                detailAnggota();
            } else if (command == 7) {
                daftarPeminjamBuku();
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
                peminjamanBuku();
            } else if (command == 2) {
                pengembalianBuku();
            } else if (command == 3) {
                pembayaranDenda();
            } else if (command == 4) {
                ((Anggota) penggunaLoggedIn).detail();
            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;
            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }
    }

    // Method buildMapCharToValue adalah method untuk membuat mapping reference numbers Code 93
    public static void buildMapCharToValue() {
        for (int count = 0; count < valueToChar.length; count++) {
            charToValue.put(valueToChar[count], count);
        }
    }

    // Method getCharFromValue adalah method yang akan mengembalikan Code 93 dari value yang diberikan
    public static char getCharFromValue(int value) {
        return valueToChar[value];
    }

    // Method getValueFromChar adalah method yang akan mengembalikan value dari Code 93 yang diberikan
    public static int getValueFromChar(char character) {
        return charToValue.get(character);
    }       

    /* Method checksum adalah method yang akan mengembalikan checksum C dan checksum K yang telah digabung, 
    atau dengan kata lain, dua karakter terakhir dari ID */
    public static String checksum(String idAnggota11) {
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

    // method untuk menmbuat id mahasiswa
    public static String generateId(String programStudi, String angkatan, String tanggalLahir) {
        String idAnggota11 = programStudi + angkatan.substring(angkatan.length()-2) + tanggalLahir.substring(0,2) +
                            tanggalLahir.substring(3,5) + tanggalLahir.substring(tanggalLahir.length()-2);
        return idAnggota11 + checksum(idAnggota11);
    }

    // mengecek jika tanggal lahir yang diinput adalah digit
    public static boolean isNumerik(String tanggalLahir) {
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
    public static void tambahMahasiswa() {
        System.out.print("Nama: ");
        String nama = input.nextLine();
        System.out.print("Program Studi (SIK/SSI/MIK/MTI/DIK): ");
        String programStudi = input.nextLine();
        System.out.print("Angkatan: ");
        String angkatan = input.nextLine();
        System.out.print("Tanggal Lahir (dd/mm/yyyy): ");
        String tanggalLahir = input.nextLine();
        // syarat-syarat yang harus dipenuhi untuk mengenerate id
        if ((programStudi.equals("SIK") || programStudi.equals("SSI") || programStudi.equals("MIK") ||
            programStudi.equals("MTI") || programStudi.equals("DIK")) && (angkatan.length() == 4) && 
            (Integer.parseInt(angkatan) < 2022) && (Integer.parseInt(angkatan) > 1999) && (tanggalLahir.length() == 10) && 
            (tanggalLahir.substring(2,3).equals("/")) && (tanggalLahir.substring(5,6).equals("/")) && (isNumerik(tanggalLahir))) {
                if ((Integer.parseInt(tanggalLahir.substring(0,2)) < 32) && (Integer.parseInt(tanggalLahir.substring(0,2)) > 0) &&
                (Integer.parseInt(tanggalLahir.substring(3,5)) < 13) && (Integer.parseInt(tanggalLahir.substring(3,5)) > 0)) {
                    // karena anggota ditambah, maka jumlah anggota bertambah 1
                    jumlahAnggota++;
                    // memanggil method generateId
                    String id = generateId(programStudi, angkatan, tanggalLahir);
                    // membuat objek Mahasiswa
                    Mahasiswa mahasiswa = new Mahasiswa(nama, tanggalLahir, programStudi, angkatan, id);
                    // membuat array baru dengan size yang sudah ditambah 1
                    Anggota[] anggotaAdd = new Anggota[jumlahAnggota];
                    // memasukkan anggota pada index terakhir anggotaAdd
                    anggotaAdd[jumlahAnggota-1] = mahasiswa;
                    // jika sebelumnya sudah terdapat anggota, maka anggota-anggota tersebut juga dimasukkan pada anggotaAdd
                    if (jumlahAnggota > 1) {
                        for (int i = 0; i < jumlahAnggota-1; i++) {
                            anggotaAdd[i] = daftarAnggota[i];
                        }
                    } 
                    // memodifikasi array daftarAnggota menjadi anggotaAdd
                    daftarAnggota = anggotaAdd;
                    System.out.println("Berhasil menambahkan mahasiswa dengan data: ");
                    System.out.print(mahasiswa);
                }
        }
        // jika syarat tidak terpenuhi
        else {
            System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!");
        }
    }

    // menambahkan dosen pada daftar anggota
    public static void tambahDosen() {
        System.out.print("Nama: ");
        String nama = input.nextLine();
        // karena anggota ditambah, maka jumlah anggota bertambah 1
        jumlahAnggota++;
        // membuat objek Dosen
        Dosen dosen = new Dosen(nama);
        // membuat array baru dengan size yang sudah ditambah 1
        Anggota[] anggotaAdd = new Anggota[jumlahAnggota];
        // memasukkan anggota pada index terakhir anggotaAdd
        anggotaAdd[jumlahAnggota-1] = dosen;
        // jika sebelumnya sudah terdapat anggota, maka anggota-anggota tersebut juga dimasukkan pada anggotaAdd
        if (jumlahAnggota > 1) {
            for (int i = 0; i < jumlahAnggota-1; i++) {
                anggotaAdd[i] = daftarAnggota[i];
            }
        } 
        // memodifikasi array daftarAnggota menjadi anggotaAdd
        daftarAnggota = anggotaAdd;
        System.out.println("Berhasil menambahkan dosen dengan data: ");
        System.out.print(dosen);
    }

    // mengecek jika kategori sudah ada dalam daftarKategori (case insensitive) dan mencari index jika sudah ada
    public static boolean findKategori(String nama) {
        for (int i = 0; i < daftarKategori.length; i++){
            if((daftarKategori[i] != null) && (daftarKategori[i].getNama().toLowerCase().equals(nama.toLowerCase()))){
                indexKategori = i;
                return true;
            }
        }
        return false;
    }

    // menambahkan kategori pada daftar kategori
    public static void tambahKategori() {
        System.out.println("---------- Tambah Kategori ----------");
        System.out.print("Nama Kategori: ");
        String nama = input.nextLine();
        System.out.print("Point: ");
        int poin = input.nextInt();
        input.nextLine();
        
        boolean cariKategori = findKategori(nama);
        // jika sudah ada kategori dalam daftarKategori
        if (cariKategori) {
            System.out.println("Kategori " + daftarKategori[indexKategori].getNama() + " sudah pernah ditambahkan");
        }
        // jika tidak terdapat kategori dalam daftarKategori
        else {
            // karena kategori ditambah, maka jumlah kategori bertambah 1
            jumlahKategori++;
            // membuat array baru dengan size yang sudah ditambah 1
            Kategori[] kategoriAdd = new Kategori[jumlahKategori];
            // membuat objek Kategori
            Kategori kategori = new Kategori(nama, poin);
            // memasukkan kategori pada index terakhir kategoriAdd
            kategoriAdd[jumlahKategori-1] = kategori;
            // jika sebelumnya sudah terdapat kategori, maka kategori-kategori tersebut juga dimasukkan pada kategoriAdd
            if (jumlahKategori > 1) {
                for (int i = 0; i < jumlahKategori-1; i++) {
                    kategoriAdd[i] = daftarKategori[i];
                }
            } 
            // memodifikasi array daftarKategori menjadi kategoriAdd
            daftarKategori = kategoriAdd;
            System.out.println("Kategori " + nama + " dengan poin " + poin + " berhasil ditambahkan");
        }
    }
    
    // mengecek jika buku sudah ada dalam daftarBuku (case insensitive) dan mencari index jika sudah ada
    public static boolean findBuku(String judul, String penulis) {
        for (int i = 0; i < daftarBuku.length; i++) {
            if ((daftarBuku[i] != null) && (daftarBuku[i].getJudul().toLowerCase().equals(judul.toLowerCase())) && 
            (daftarBuku[i].getPenulis().toLowerCase().equals(penulis.toLowerCase()))) {
                indexBuku = i;
                return true;
            }
        }
        return false;
    }

    // menambahkan buku pada daftar buku
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

        boolean cariBuku = findBuku(judul, penulis);
        boolean cariKategori = findKategori(kategori);
        // jika tidak terdapat kategori dalam daftarKategori
        if (!cariKategori) {
            System.out.println("Kategori " + kategori + " tidak ditemukan");
        }
        // jika sudah ada kategori dalam daftarKategori
        else {
            // jika stok yang dimasukkan adalah 0
            if (stok <= 0) {
                System.out.println("Stok harus lebih dari 0");
            }
            // jika stok lebih dari 0
            else {
                // jika sudah ada buku dalam daftarBuku
                if (cariBuku) {
                    System.out.println("Buku " + daftarBuku[indexBuku].getJudul() + " oleh " + daftarBuku[indexBuku].getPenulis() + " sudah pernah ditambahkan");
                }
                // jika tidak terdapat buku dalam daftarBuku
                else {
                    // karena buku ditambah, maka jumlah buku bertambah 1
                    jumlahBuku++;
                    // membuat array baru dengan size yang sudah ditambah 1
                    Buku[] bukuAdd = new Buku[jumlahBuku];
                    // membuat objek Buku
                    Buku buku = new Buku(judul, penulis, penerbit, stok, daftarKategori[indexKategori]);
                    // memasukkan kategori pada index terakhir bukuAdd
                    bukuAdd[jumlahBuku-1] = buku;
                    // jika sebelumnya sudah terdapat buku, maka buku-buku tersebut juga dimasukkan pada bukuAdd
                    if (jumlahBuku > 1) {
                        for (int i = 0; i < jumlahBuku-1; i++) {
                            bukuAdd[i] = daftarBuku[i];
                        }
                    } 
                    // memodifikasi array daftarBuku menjadi bukuAdd
                    daftarBuku = bukuAdd;
                    System.out.println("Buku " + judul + " oleh " + penulis + " berhasil ditambahkan");
                }
            }
        }
    }

    // menghapus buku dari daftarBuku
    public static void hapusBuku(){
        System.out.println("---------- Hapus Buku ----------");
        System.out.print("Judul: ");
        String judul = input.nextLine();
        System.out.print("Penulis: ");
        String penulis = input.nextLine();
    
        boolean cariBuku = findBuku(judul, penulis);
        // jika sudah ada buku dalam daftarBuku
        if (cariBuku) {
            // jika buku sedang tidak dipinjam
            if (daftarBuku[indexBuku].getStok() == daftarBuku[indexBuku].getStokAwal()) {
                System.out.println("Buku " + daftarBuku[indexBuku].getJudul() + " oleh " + daftarBuku[indexBuku].getPenulis() + " berhasil dihapus");
                // karena buku dihapus, maka jumlah buku berkurang 1
                jumlahBuku--;
                // membuat arrayList baru
                ArrayList<Buku> listDaftarBuku = new ArrayList<Buku>();
                // memasukkan semua elemen array daftarBuku pada arrayList
                Collections.addAll(listDaftarBuku, daftarBuku);
                // menghapus buku yang diinginkan dari arrayList
                listDaftarBuku.remove(listDaftarBuku.get(indexBuku));
                // membuat array baru dengan size yang sudah berkurang 1
                Buku[] bukuRemove = new Buku[jumlahBuku];
                // memasukkan semua elemen arrayList pada array bukuRemove
                for(int i = 0; i < listDaftarBuku.size(); i++){
                    bukuRemove[i] = listDaftarBuku.get(i);
                }
                // memodifikasi array daftarBuku menjadi bukuRemove
                daftarBuku = bukuRemove;
            }
            // jika buku sedang dipinjam
            else {
                System.out.println("Buku " + daftarBuku[indexBuku].getJudul() + " oleh " + daftarBuku[indexBuku].getPenulis() + " tidak dapat dihapus karena sedang dipinjam");
            }
        // jika tidak terdapat buku dalam daftarBuku
        }
        else {
            System.out.println("Buku " + judul + " oleh " + penulis + " tidak ditemukan");
        }
    }

    // mengecek jika id sudah ada dalam daftarAnggota dan mencari index jika sudah ada
    public static boolean findId(String id) {
        for (int i = 0; i < daftarAnggota.length; i++) {
            if ((daftarAnggota[i] != null) && (daftarAnggota[i].getId().equals(id))){
                indexId = i;
                return true;
            }
        }
        return false;
    }

    // menampilkan detail anggota
    public static void detailAnggota(){
        System.out.println("---------- Detail Anggota ----------");
        System.out.print("ID Anggota: ");
        String idAnggota = input.nextLine();

        boolean cariId = findId(idAnggota);
        // jika id sudah ada dalam daftarAnggota
        if (cariId) {
            // memanggil method detail() dari Class Anggota
            daftarAnggota[indexId].detail();
        }
        // jika id tidak terdapat dalam daftarAnggota
        else {
            System.out.println("Anggota dengan ID " + idAnggota + " tidak ditemukan");
        }
    }

    // menampilkan anggota dengan tiga peringkat teratas berdasarkan poin
    public static void peringkatAnggota(){
        // membuat arrayList baru
        ArrayList<Anggota> listDaftarAnggota = new ArrayList<Anggota>();
        // memasukkan semua elemen array daftarAnggota pada arrayList
        Collections.addAll(listDaftarAnggota, daftarAnggota);
        // mensort arrayList berdasarkan poin
        Collections.sort(listDaftarAnggota);
        System.out.println("---------- Peringkat Anggota ----------");
        // jika belum ada anggota dalam daftarAnggota
        if(listDaftarAnggota.size() == 0) {
            System.out.println("Belum ada anggota yang terdaftar pada sistem");
        }
        // jika jumlah anggota kurang dari 3
        else if(listDaftarAnggota.size() < 3) {
            for(int i = 0; i < listDaftarAnggota.size(); i++){
                System.out.printf("-------------- %d --------------", i+1);
                System.out.println();
                System.out.println(listDaftarAnggota.get(i));
            }
        }
        // jika jumlah anggota lebih dari 3
        else {
            for(int j = 0; j < 3; j++){
                System.out.printf("-------------- %d --------------", j+1);
                System.out.println();
                System.out.println(listDaftarAnggota.get(j));
            }
        }
    }

    // menampilkan daftarPeminjam dari buku
    public static void daftarPeminjamBuku(){
        System.out.println("---------- Daftar Peminjam Buku ----------");
        System.out.print("Judul: ");
        String judul = input.nextLine();
        System.out.print("Penulis: ");
        String penulis = input.nextLine();
        
        boolean cariBuku = findBuku(judul, penulis);
        // jika sudah ada buku dalam daftarBuku
        if (cariBuku) {
            // jika buku sudah pernah dipinjam
            if(daftarBuku[indexBuku].getDaftarPeminjam()[0] != null){
                System.out.println(daftarBuku[indexBuku]);
                System.out.println("---------- Daftar Peminjam ----------");
                for(int i = 0; i < daftarBuku[indexBuku].getDaftarPeminjam().length; i++){
                    System.out.printf("----------------- %d -----------------", i+1);
                    System.out.println();
                    System.out.println(daftarBuku[indexBuku].getDaftarPeminjam()[i]);
                }
            }
            // jika buku belum pernah dipinjam
            else{
                System.out.println(daftarBuku[indexBuku]);
                System.out.println("---------- Daftar Peminjam ----------");
                System.out.println("Belum ada anggota yang meminjam buku " + judul);
                
            }
        }
        // jika tidak terdapat buku dalam daftarBuku
        else {
            System.out.println("Buku " + judul + " oleh " + penulis + " tidak ditemukan");
        }
    }

    // method untuk meminjam buku
    public static void peminjamanBuku(){
        System.out.println("---------- Peminjaman Buku ----------");
        System.out.print("Judul Buku: ");
        String judul = input.nextLine();
        System.out.print("Penulis Buku: ");
        String penulis = input.nextLine();
        System.out.print("Tanggal Peminjaman: ");
        String tanggalPeminjaman = input.nextLine();

        boolean cariBuku = findBuku(judul, penulis);
        // jika buku sudah ada dalam daftarBuku
        if (cariBuku) {
            // jika jenis penggunaLoggedIn adalah Dosen
            if (penggunaLoggedIn instanceof Dosen) {
                // memanggil method pinjam() dari class Dosen
                System.out.println(((Dosen) penggunaLoggedIn).pinjam(daftarBuku[indexBuku], tanggalPeminjaman));
            }
            // jika jenis penggunaLoggedIn adalah Mahasiswa
            else {
                // memanggil method pinjam() dari class Mahasiswa
                System.out.println(((Mahasiswa) penggunaLoggedIn).pinjam(daftarBuku[indexBuku], tanggalPeminjaman));
            }
        }
        // jika tidak terdapat buku dalam daftarBuku
        else {
            System.out.println("Buku " + judul + " oleh " + penulis + " tidak ditemukan");
        }
    }

    // method untuk mengembalikan buku
    public static void pengembalianBuku(){
        System.out.println("---------- Pengembalian Buku ----------");
        System.out.print("Judul Buku: ");
        String judul = input.nextLine();
        System.out.print("Penulis Buku: ");
        String penulis = input.nextLine();
        System.out.print("Tanggal Pengembalian: ");
        String tanggalPengembalian = input.nextLine();

        boolean cariBuku = findBuku(judul, penulis);
        // jika buku sudah ada dalam daftarBuku
        if (cariBuku) {
            // memanggil method kembali() dari class Anggota
            System.out.println(((Anggota) penggunaLoggedIn).kembali(daftarBuku[indexBuku], tanggalPengembalian));
        }
        // jika tidak terdapat buku dalam daftarBuku
        else {
            System.out.println("Buku " + judul + " oleh " + penulis + " tidak ditemukan");
        }
    }

    // method untuk membayar denda
    public static void pembayaranDenda(){
        System.out.println("---------- Pembayaran Denda ----------");
        System.out.print("Jumlah: ");
        long jumlahBayar = Long.parseLong(input.nextLine());
        //// memanggil method bayarDenda() dari class Anggota
        System.out.println(((Anggota) penggunaLoggedIn).bayarDenda(jumlahBayar));
    }
}
