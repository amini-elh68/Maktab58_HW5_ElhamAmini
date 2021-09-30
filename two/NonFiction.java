package Maktab58_HW5_ElhamAmini.two;

public class NonFiction extends Book {
    public NonFiction(String title) {
        super(title);
        this.setPrice();
    }

    @Override
    void setPrice() {
        super.price = 37.99;
    }
}
