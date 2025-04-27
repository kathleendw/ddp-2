package assignments.assignment2;

public class Member {
    private String id;
    private String name;
    private String dateOfBirth;
    private String studyProgram;
    private String angkatan;
    private long fine;
    private int point;
    private BookLoan[] bookLoans = new BookLoan[1];

    public Member(String name, String dateOfBirth, String studyProgram, String angkatan, String id) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.studyProgram = studyProgram;
        this.angkatan = angkatan;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Member " + name + " berhasil ditambahkan dengan data:" + "\n" +
        "ID Anggota: " + id + "\n" +
        "Nama Anggota: " + name + "\n" +
        "Total Point: " + point + "\n" +
        "Denda: " + fine;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getStudyProgram() {
        return studyProgram;
    }
    
    public String getAngkatan() {
        return angkatan;
    }

    public long getFine() {
        return fine;
    }

    public int getPoint() {
        return point;
    }

}
