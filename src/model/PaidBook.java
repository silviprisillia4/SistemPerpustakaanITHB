package model;

public class PaidBook extends Book {
    private int borrowPrice;

    public PaidBook() {
        
    }
    
    public PaidBook(int idBook, int idBranch, String title, String author, String publisher, int pages, int year, String genre, int status, int borrowPrice) {
        super(idBook, idBranch, title, author, publisher, pages, year, genre, status);
        setBorrowPrice(borrowPrice);
    }
    
    public int getBorrowPrice() {
        return borrowPrice;
    }

    public void setBorrowPrice(int borrowPrice) {
        this.borrowPrice = borrowPrice;
    }
}
