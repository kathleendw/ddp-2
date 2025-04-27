package assignments.assignment2;

import java.util.HashMap;
import java.util.Scanner;

public class Library {
    private Scanner input = new Scanner(System.in);

    private Member[] members = new Member[1];
    private Book[] books = new Book[1];
    private Category[] categories = new Category[1];
    private HashMap<Character, Integer> charToValue = new HashMap<>(36);
    private char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    int jumlahAnggota = 0;
    int jumlahKategori = 0;
    int jumlahBuku = 0;

    public static void main(String[] args) {
        Library program = new Library();
        program.menu();
    }

    public void menu() {
        int command = 0;
        boolean hasChosenExit = false;
        System.out.println("Selamat Datang di Sistem Perpustakaan SistakaNG!");
        while (!hasChosenExit) {
            System.out.println("================ Menu Utama ================\n");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tambah Kategori");
            System.out.println("3. Tambah Buku");
            System.out.println("4. Peminjaman");
            System.out.println("5. Pengembalian");
            System.out.println("6. Pembayaran Denda");
            System.out.println("7. Detail Anggota");
            System.out.println("8. 3 Peringkat Pertama");
            System.out.println("99. Keluar");
            System.out.print("Masukkan pilihan menu: ");
            command = Integer.parseInt(input.nextLine());
            System.out.println();
            if (command == 1) {
                buildMapCharToValue();
                tambahAnggota();

            } else if (command == 2) {
                tambahKategori();

            } else if (command == 3) {
                tambahBuku();

            } else if (command == 4) {
                System.out.println("---------- Peminjaman Buku ----------");
                System.out.println("ID Anggota: ");
                String id = input.nextLine();
                System.out.println("Judul Buku: ");
                String title = input.nextLine();
                System.out.println("Penulis Buku: ");
                String author = input.nextLine();
                System.out.println("Tanggal Peminjaman: ");
                String loanDate = input.nextLine();

            } else if (command == 5) {
                System.out.println("---------- Pengembalian Buku ----------");
                System.out.println("ID Anggota: ");
                String id = input.nextLine();
                System.out.println("Judul Buku: ");
                String title = input.nextLine();
                System.out.println("Penulis Buku: ");
                String author = input.nextLine();
                System.out.println("Tanggal Pengembalian: ");
                String returnDate = input.nextLine();

            } else if (command == 6) {
                System.out.println("---------- Pembayaran Denda ----------");
                System.out.println("ID Anggota: ");
                String id = input.nextLine();
                System.out.println("Jumlah: ");
                String fine = input.nextLine();

            } else if (command == 7) {
                System.out.println("---------- Detail Anggota ----------");
                System.out.println("ID Anggota: ");
                String id = input.nextLine();

            } else if (command == 8) {
                System.out.println("---------- Peringkat Anggota ----------");

            } else if (command == 99) {
                System.out.println("Terima kasih telah menggunakan SistakaNG!");
                hasChosenExit = true;

            } else {
                System.out.println("Menu tidak dikenal!");
            }
            System.out.println();
        }

        input.close();
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

    public String generateId(String studyProgram, String angkatan, String dateOfBirth) {
        String idAnggota11 = studyProgram + angkatan.substring(angkatan.length()-2) + dateOfBirth.substring(0,2) +
                            dateOfBirth.substring(3,5) + dateOfBirth.substring(dateOfBirth.length()-2);
        return idAnggota11 + checksum(idAnggota11);
    }

    public boolean isNumerik(String dateOfBirth) {
        String[] arrOfDateOfBirth = dateOfBirth.split("/");
        for (String s: arrOfDateOfBirth) {
            for (char c: s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void tambahAnggota() {
        System.out.println("---------- Tambah Anggota ----------");
        System.out.print("Nama: ");
        String name = input.nextLine();
        System.out.print("Program Studi: ");
        String studyProgram = input.nextLine();
        System.out.print("Angkatan: ");
        String angkatan = input.nextLine();
        System.out.print("Tanggal Lahir: ");
        String dateOfBirth = input.nextLine();

        if ((studyProgram.equals("SIK") || studyProgram.equals("SSI") || studyProgram.equals("MIK") ||
            studyProgram.equals("MTI") || studyProgram.equals("DIK")) && (angkatan.length() == 4) && 
            (Integer.parseInt(angkatan) < 2022) && (Integer.parseInt(angkatan) > 1999) && (dateOfBirth.length() == 10) && 
            (dateOfBirth.substring(2,3).equals("/")) && (dateOfBirth.substring(5,6).equals("/")) && (isNumerik(dateOfBirth))) {
                if ((Integer.parseInt(dateOfBirth.substring(0,2)) < 32) && (Integer.parseInt(dateOfBirth.substring(0,2)) > 0) &&
                (Integer.parseInt(dateOfBirth.substring(3,5)) < 13) && (Integer.parseInt(dateOfBirth.substring(3,5)) > 0)) {
                    jumlahAnggota++;
                    String id = generateId(studyProgram, angkatan, dateOfBirth);
                    Member anggota = new Member(name, dateOfBirth, studyProgram, angkatan, id);
                    Member[] membersAdd = new Member[jumlahAnggota];
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

    public int findIndexKategori(String name) {
        int indexKategori = 0;
        for (int i = 0; i < categories.length; i++){
            if(categories[i] == null){
                indexKategori = i;
                break;
            }
            if(categories[i].getName().toLowerCase().equals(name.toLowerCase())){
                indexKategori = i;
                break;
            }
        }
        return indexKategori;
    }

    public void tambahKategori() {
        System.out.println("---------- Tambah Kategori ----------");
        System.out.print("Nama Kategori: ");
        String name = input.nextLine();
        System.out.print("Point: ");
        int point = input.nextInt();
        input.nextLine();

        int cariIndexKategori = findIndexKategori(name);
        if (categories[cariIndexKategori] != null) {
            System.out.println("Kategori " + categories[cariIndexKategori].getName() + " sudah pernah ditambahkan");
        }
        else {
            jumlahKategori++;
            Category[] categoriesAdd = new Category[jumlahKategori];
            Category kategori = new Category(name, point);
            categoriesAdd[jumlahKategori-1] = kategori;
            if (jumlahKategori > 1) {
                for (int i = 0; i < jumlahKategori-1; i++) {
                    categoriesAdd[i] = categories[i];
                }
            } 
            categories = categoriesAdd;
            System.out.print(kategori);
        }
    }
    
    public int findIndexBuku(String title, String author) {
        int indexBuku = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                indexBuku = i;
                break;
            }
            if ((books[i].getTitle().toLowerCase().equals(title.toLowerCase())) && (books[i].getAuthor().toLowerCase().equals(author.toLowerCase()))) {
                indexBuku = i;
                break;
            }
        }
        return indexBuku;
    }

    public void tambahBuku() {
        System.out.println("---------- Tambah Buku ----------");
        System.out.print("Judul: ");
        String title = input.nextLine();
        System.out.print("Penulis: ");
        String author = input.nextLine();
        System.out.print("Penerbit: ");
        String publisher = input.nextLine();
        System.out.print("Kategori: ");
        String category = input.nextLine();
        System.out.print("Stok: ");
        int stok = input.nextInt();
        input.nextLine();

        int cariIndexBuku = findIndexBuku(title, author);
        int cariIndexKategori = findIndexKategori(category);
        if (books[cariIndexBuku] != null) {
            System.out.println("Buku " + books[cariIndexBuku].getTitle() + " oleh " + books[cariIndexBuku].getAuthor() + " sudah pernah ditambahkan");
        }
        else {
            if (stok <= 0) {
                System.out.println("Stok harus lebih dari 0");
            }
            else {
                if (categories[cariIndexKategori] == null) {
                    System.out.println("Kategori " + category + " tidak ditemukan");
                }
                else {
                    jumlahBuku++;
                    Book[] booksAdd = new Book[jumlahBuku];
                    Book buku = new Book(title, author, publisher, stok, categories[cariIndexKategori]);
                    booksAdd[jumlahBuku-1] = buku;
                    if (jumlahBuku > 1) {
                        for (int i = 0; i < jumlahBuku-1; i++) {
                            booksAdd[i] = books[i];
                        }
                    } 
                    books = booksAdd;
                    System.out.println(buku);
                }
            }
        }

        for(int j = 0; j < jumlahBuku; j++){
            if(books[j]!= null){
                System.out.println(books[j].getTitle());
            }
        }
    }
}
