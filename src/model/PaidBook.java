/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author SILVI PRISILLIA
 */
public class PaidBook extends Book {
    private int borrowPrice;

    public PaidBook() {
        
    }
    
    public PaidBook(int idBook, int idBranch, String title, String author, String publisher, int pages, int year, String genre, int availability, int borrowPrice) {
        super(idBook, idBranch, title, author, publisher, pages, year, genre, availability);
        setBorrowPrice(borrowPrice);
    }
    
    public int getBorrowPrice() {
        return borrowPrice;
    }

    public void setBorrowPrice(int borrowPrice) {
        this.borrowPrice = borrowPrice;
    }
}
