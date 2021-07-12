package model;
import java.util.ArrayList;

public class Member extends User implements InterfaceBranchCity {
    private String address;
    private String phoneNumber;
    private int cash;
    private int debt;
    private int idBranch;
    private int approved;
    private ArrayList<Borrowing> borrows = new ArrayList<>();

    public Member() {
        
    }
    
    public Member(int idUser, String firstName, String lastName, String email, String password, UserTypeEnum type, String address, String phoneNumber, int cash, int debt, int idBranch, ArrayList<Borrowing> borrows) {
        super(idUser, firstName, lastName, email, password, type);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setCash(cash);
        setDebt(debt);
        setIdBranch(idBranch);
        setBorrows(borrows);

    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }
    
    public ArrayList<Borrowing> getBorrows () {
        return borrows;
    }
    
    public void setBorrows(ArrayList<Borrowing> borrows) {
        this.borrows = borrows;
    }
    
    public void printDataMember() {
        String print = "Nama : "+getFirstName()+" "+getLastName()+"\n"
                        +"Alamat : "+getAddress()+"\n"
                        +"No Telepon : "+getPhoneNumber()+"\n"
                        +"Email : "+getEmail()+"\n"
                        +"Saldo : Rp "+getCash()+"\n"
                        +"Hutang : Rp"+getDebt()+"\n"
                        +"Cabang : "+selectBranchCity(getIdBranch());
    }

    @Override
    public String selectBranchCity(int idBranch) {
        switch(idBranch) {
            case BANDUNG :
                return "Bandung";
            case JAKARTA :
                return "Jakarta";
            case SURABAYA :
                return "Surabaya";
        }
        return "";
    }
}