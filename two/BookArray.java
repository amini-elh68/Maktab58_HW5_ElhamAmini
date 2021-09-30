package Maktab58_HW5_ElhamAmini.two;

public class BookArray {

    public static void main(String[] args) {
        Book[] books = new Book[10];
        Fiction fiction1 = new Fiction("book one");
        books[0] = fiction1;
        Fiction fiction2 = new Fiction("book two");
        books[1] = fiction2;
        NonFiction nonFiction1 = new NonFiction("book three");
        books[2] = nonFiction1;
        NonFiction nonFiction2 = new NonFiction("book four");
        books[3] = nonFiction2;
        Fiction fiction3 = new Fiction("book five");
        books[4] = fiction3;

        for (Book book : books) {
            if (book != null)
                System.out.println("title book is: " + book.getTitle() + " and price is: " + book.getPrice());
        }
    }

}
