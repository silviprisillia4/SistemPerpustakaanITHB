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
public class Borrowing implements InterfaceBookState {
    private int idBorrow;
    private int idBook;
    private int idUser;
    private int idBranch;
    private int borrowDays;
    private int priceTotal;
    private String date;
    private int availability;

    public Borrowing() {
        
    }
    
    public Borrowing(int idBorrow, int idBook, int idUser, int idBranch, int borrowDays, int priceTotal, String date, int availability) {
        setIdBorrow(idBorrow);
        setIdBook(idBook);
        setIdUser(idUser);
        setIdBranch(idBranch);
        setBorrowDays(borrowDays);
        setPriceTotal(priceTotal);
        setDate(date);
        setAvailability(availability);
    }

    public int getIdBorrow() {
        return idBorrow;
    }

    public void setIdBorrow(int idBorrow) {
        this.idBorrow = idBorrow;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }

    public int getBorrowDays() {
        return borrowDays;
    }

    public void setBorrowDays(int borrowDays) {
        this.borrowDays = borrowDays;
    }

    public int getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(int priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
       this.date = date;
    }
    
    public int getAvailability() {
        return availability;
    }
    
    public void setAvailability(int availability) {
        this.availability = availability;
    }
    
    public void printDataPeminjaman() {
        
    }
    
    @Override
    public String selectBookState(int idBook) {
        String state = "";
        if(idBook==AVAILABLE) {
            state = "Tersedia";
        } else {
            state = "Dipinjam";
        }
        return state;
    }
}
