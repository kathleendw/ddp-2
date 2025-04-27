package assignments.assignment2;

public class Category {
    private String name;
    private int point;

    public Category(String name, int point) {
        this.name = name;
        this.point = point;
    }

    @Override
    public String toString() {
        return "Kategori " + name + " dengan " + point + " point berhasil ditambahkan";
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint() {
        this.point = point;
    }

}
