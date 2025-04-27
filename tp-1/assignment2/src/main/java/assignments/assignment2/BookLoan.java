package assignments.assignment2;
import java.util.Date;

public class BookLoan {
    private static long DENDA_PER_HARI = 3000;
    private Member member;
    private Book book;
    private String loanDate;
    private String returnDate;
    private long fine;
    private boolean status;

    public BookLoan(Member member, Book book, String loanDate) {
        this.member = member;
        this.book = book;
        this.loanDate = loanDate;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public long getFine() {
        return fine;
    }

    public boolean getStatus() {
        return status;
    }

}
