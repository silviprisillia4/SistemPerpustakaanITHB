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
public class Borrowing {
    private int idBorrow;
    private int idBook;
    private int idUser;
    private int idBranch;
    private int borrowDays;
    private int priceTotal;
    private String date;

    public Borrowing(int idBorrow, int idBook, int idUser, int idBranch, int borrowDays, int priceTotal, String date) {
        this.idBorrow = idBorrow;
        this.idBook = idBook;
        this.idUser = idUser;
        this.idBranch = idBranch;
        this.borrowDays = borrowDays;
        this.priceTotal = priceTotal;
        this.date = date;
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
    
    public void printDataPeminjaman() {
        
    }
}
