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
public class Member extends User implements InterfaceBranchCity {
    private String address;
    private String phoneNumber;
    private int cash;
    private int debt;
    private int idBranch;
    
    public Member() {
        
    }
    
    public Member(int idUser, String firstName, String lastName, String email, String password, UserType type, String address, String phoneNumber, int cash, int debt, int idBranch) {
        super(idUser, firstName, lastName, email, password, type);
        setIdBranch(idBranch);
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
    
    public void printDataMember() {
        String print = "Nama : "+getFirstName()+" "+getLastName()+"\n"
                        +"Alamat : "+getAddress()+"\n"
                        +"No Telepon : "+getPhoneNumber()+"\n"
                        +"Email : "+getEmail()+"\n"
                        +"Saldo : Rp "+getCash()+"\n"
                        +"Hutang : Rp"+getDebt()+"\n"
                        +"Cabang : <pake interface kali ya>"+getIdBranch();
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
