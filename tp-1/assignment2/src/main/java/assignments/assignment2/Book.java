package assignments.assignment2;

public class Book {
    private String title;
    private String author;
    private String publisher;
    private int stok;
    private Category category;

    public Book(String title, String author, String publisher, int stok, Category category) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.stok = stok;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Buku " + title + " oleh " + author + " berhasil ditambahkan";
    }
        
    public boolean cekStok(int stok) {
        if(this.stok >= stok){
            return true;
        }
        return false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle() {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    
    public void setAuthor() {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher() {
        this.publisher = publisher;
    }

    public int getStok() {
        return stok;
    }

    public void setStok() {
        this.stok = stok;
    }

}
