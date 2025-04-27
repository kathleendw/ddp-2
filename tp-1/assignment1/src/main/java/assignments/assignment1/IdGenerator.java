package assignments.assignment1;

/* Nama: Kathleen Daniella Wijaya
NPM: 2106637366
Kelas: DDP 2-E
Tanggal: 4 Maret 2022 */

/* Deskripsi program:
Membuat ID keanggotaan perpustakaan sesuai dengan program studi, angkatan, dan 
tanggal lahir anggota, serta memeriksa apakah ID anggota valid atau tidak
*/

import java.util.HashMap;
import java.util.Scanner;

public class IdGenerator {
    static HashMap<Character, Integer> charToValue = new HashMap<>(36);
    static char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static void main(String[] args) {
        buildMapCharToValue();
        Scanner input = new Scanner(System.in);
        System.out.println("Selamat Datang di Perpustakaan!");

        boolean shouldTerminateProgram = false;

        // Meminta user input sesuai keinginan user untuk membuat ID atau memeriksa validitas ID
        while (!shouldTerminateProgram) {
            printMenu();
            System.out.print("Menu yang anda pilih: ");
            int menu = input.nextInt();
            input.nextLine();

            /* Jika user memilih untuk membuat ID, maka program akan meminta data user 
            yang akan digunakan untuk membuat ID */
            if (menu == 1) {
                System.out.print("Program Studi: ");
                String programStudi = input.nextLine();
                System.out.print("Angkatan: ");
                String angkatan = input.nextLine();
                System.out.print("Tanggal Lahir: ");
                String tanggalLahir = input.nextLine();

                System.out.println(generateId(programStudi, angkatan, tanggalLahir));
            // Jika user memilih untuk memeriksa validitas ID, maka program akan meminta ID yang akan diperiksa 
            } else if (menu == 2) {
                System.out.print("ID Anggota: ");
                String idAnggota = input.nextLine();

                System.out.print("Validitas: ");
                System.out.println(checkValidity(idAnggota));
            // Output jika user sudah selesai menggunakan program dan ingin keluar dari program
            } else {
                shouldTerminateProgram = true;
                System.out.println("Sampai jumpa!");
            }
        }

        input.close();
    }

    // Method buildMapCharToValue adalah method untuk membuat mapping reference numbers Code 93
    public static void buildMapCharToValue() {
        for (int count = 0; count < valueToChar.length; count++) {
            charToValue.put(valueToChar[count], count);
        }
    }

    // Method getCharFromValue adalah method yang akan mengembalikan Code 93 dari value yang diberikan
    private static char getCharFromValue(int value) {
        return valueToChar[value];
    }

    // Method getValueFromChar adalah method yang akan mengembalikan value dari Code 93 yang diberikan
    private static int getValueFromChar(char character) {
        return charToValue.get(character);
    }

    // Method printMenu adalah method yang akan menampilkan pilihan menu yang dapat dipilih oleh user
    private static void printMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("Menu yang tersedia:");
        System.out.println("1. Generate ID anggota untuk anggota baru");
        System.out.println("2. Check validitas ID anggota");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
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

    /* Method generateId adalah method yang akan membuat ID keanggotaan perpustakaan
    jika data yang diinput oleh user valid */
    public static String generateId(String programStudi, String angkatan, String tanggalLahir) {
        if ((programStudi.equals("SIK") || programStudi.equals("SSI") || programStudi.equals("MIK") ||
            programStudi.equals("MTI") || programStudi.equals("DIK")) && (angkatan.length() == 4) && 
            (Integer.parseInt(angkatan) < 2022) && (Integer.parseInt(angkatan) > 1999) && (tanggalLahir.length() == 10) && 
            (Integer.parseInt(tanggalLahir.substring(0,2)) < 32) && (Integer.parseInt(tanggalLahir.substring(0,2)) > 0) &&
            (Integer.parseInt(tanggalLahir.substring(3,5)) < 13) && (Integer.parseInt(tanggalLahir.substring(3,5)) > 0) &&
            (tanggalLahir.substring(2,3).equals("/")) && (tanggalLahir.substring(5,6).equals("/"))) {
            String idAnggota11 = programStudi + angkatan.substring(angkatan.length()-2) + tanggalLahir.substring(0,2) +
                                tanggalLahir.substring(3,5) + tanggalLahir.substring(tanggalLahir.length()-2);
            return "ID Anggota: " + idAnggota11 + checksum(idAnggota11);
        }
        return "Input tidak valid!";
    }

    /* Method cekIdAnggota adalah method yang akan mengecek apakah ID yang telah diinput oleh user 
    terdiri dari uppercase alphabets dan/atau bilangan numerik */
    public static boolean cekIdAnggota(String idAnggota) {
        for (int k = 0; k < idAnggota.length(); k++) {
            if (Character.isUpperCase(idAnggota.charAt(k)) || Character.isDigit(idAnggota.charAt(k))) {
                return true;
            }
        }
        return false;
    }

    /* Method checkValidity adalah method yang akan mengecek validitas ID keanggotaan perpustakaan
    berdasarkan syarat-syarat yang dibutuhkan oleh ID yang valid */
    public static boolean checkValidity(String idAnggota) {
        if (idAnggota.length() == 13) {
            String cekChecksumIdAnggota = idAnggota.substring(0,12);
            return cekIdAnggota(idAnggota) && 
            (idAnggota.substring(idAnggota.length()-2)).equals(checksum(cekChecksumIdAnggota));
        }
        return false;
    }
}
