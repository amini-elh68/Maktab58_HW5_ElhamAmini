package Maktab58_HW5_ElhamAmini.two;

public class Fiction extends Book {
    public Fiction(String title) {
        super(title);
        this.setPrice();
    }

    @Override
    void setPrice() {
        super.price = 24.99;
    }
}
