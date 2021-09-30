package Maktab58_HW5_ElhamAmini.two;

public abstract class Book {

    String title;
    double price;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    abstract void setPrice();
}
