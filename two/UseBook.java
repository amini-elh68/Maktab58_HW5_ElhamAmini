package Maktab58_HW5_ElhamAmini.two;

public class UseBook {

    public static void main(String[] args) {
        Fiction fiction = new Fiction("book one");
        NonFiction nonFiction = new NonFiction("book two");
        System.out.println("title of fiction book is: " + fiction.getTitle() + " and price is: " + fiction.getPrice());
        System.out.println("title of non fiction book is: " + nonFiction.getTitle() + " and price is: " + nonFiction.getPrice());
    }
}
