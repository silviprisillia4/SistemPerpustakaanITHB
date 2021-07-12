package model;
import java.util.Date;

public class Borrowing implements InterfaceBookState {
    private int idBorrow;
    private int idBook;
    private int idUser;
    private int idBranch;
    private int borrowDays;
    private int priceTotal;
    private Date date;
    private int status;
    private int moneyFine;
    private PaidBook book;
    
    public Borrowing() {
        
    }
    
    public Borrowing(int idBorrow, int idBook, int idUser, int idBranch, int borrowDays, int priceTotal, Date date, int status, int moneyFine, PaidBook book) {
        setIdBorrow(idBorrow);
        setIdBook(idBook);
        setIdUser(idUser);
        setIdBranch(idBranch);
        setBorrowDays(borrowDays);
        setPriceTotal(priceTotal);
        setDate(date);
        setStatus(status);
        setMoneyFine(moneyFine);
        setBook(book);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
       this.date = date;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
  
    public int getMoneyFine() {
        return moneyFine;
    }

    public void setMoneyFine(int moneyFine) {
        this.moneyFine = moneyFine;
    }
  
    public PaidBook getBook() {
        return book;
    }

    public void setBook(PaidBook book) {
        this.book = book;
    }
  
    public void printDataPeminjaman() {
        
    }

    @Override
    public String selectBookState(int idBook) {
        if(idBook == AVAILABLE) {
            return "Tersedia";
        } else if (idBook == BORROWED){
            return "Dipinjam";
        } else if (idBook == PROCESSED){
            return "Diproses";
        } else {
            return "Dikembalikan";
        }
    } 
}