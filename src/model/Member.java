package model;
import java.util.ArrayList;
import controller.*;
import javax.swing.*;

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
    
    public JPanel printDataMember() {
        JPanel panel1 = new JPanel();
        
        JLabel labelId, labelName, labelEmail, labelPassword, labelAddress, labelPhoneNumber, labelCash, labelDebt, labelBranch;
        JLabel labelDataId, labelDataName, labelDataEmail, labelDataPassword, labelDataAddress, labelDataPhoneNumber, labelDataCash, labelDataDebt, labelDataBranch;
        
        //Label
        labelId = new JLabel("ID");
        labelId.setBounds(30, 30, 100, 20);
        labelName = new JLabel("Nama");
        labelName.setBounds(30, 60, 100, 20);
        labelEmail = new JLabel("Email");
        labelEmail.setBounds(30, 90, 100, 20);
        labelPassword = new JLabel("Password");
        labelPassword.setBounds(30, 120, 100, 20);
        labelAddress = new JLabel("Alamat");
        labelAddress.setBounds(30, 150, 100, 20);
        labelPhoneNumber = new JLabel("Telepon");
        labelPhoneNumber.setBounds(30, 180, 100, 20);
        labelCash = new JLabel("Saldo");
        labelCash.setBounds(30, 210, 100, 20);
        labelDebt = new JLabel("Hutang");
        labelDebt.setBounds(30, 240, 100, 20);
        labelBranch = new JLabel("Cabang");
        labelBranch.setBounds(30, 270, 100, 20);
        
        //Label Data
        int idUser = this.getIdUser();
        labelDataId = new JLabel(String.valueOf(idUser));
        labelDataId.setBounds(140, 30, 100, 20);
        labelDataName = new JLabel(this.getFirstName()+" "+this.getLastName());
        labelDataName.setBounds(140, 60, 150, 20);
        labelDataEmail = new JLabel(this.getEmail());
        labelDataEmail.setBounds(140, 90, 150, 20);
        labelDataPassword = new JLabel("********");
        labelDataPassword.setBounds(140, 120, 100, 20);
        labelDataAddress = new JLabel(this.getAddress());
        labelDataAddress.setBounds(140, 150, 150, 20);
        labelDataPhoneNumber = new JLabel(this.getPhoneNumber());
        labelDataPhoneNumber.setBounds(140, 180, 100, 20);
        labelDataCash = new JLabel(String.valueOf(this.getCash()));
        labelDataCash.setBounds(140, 210, 100, 20);
        labelDataDebt = new JLabel(String.valueOf(this.getDebt()));
        labelDataDebt.setBounds(140, 240, 100, 20);
        labelDataBranch = new JLabel(this.selectBranchCity(this.getIdBranch()));
        labelDataBranch.setBounds(140, 270, 100, 20);
        
        panel1.add(labelId);
        panel1.add(labelName);
        panel1.add(labelEmail);
        panel1.add(labelPassword);
        panel1.add(labelAddress);
        panel1.add(labelPhoneNumber);
        panel1.add(labelCash);
        panel1.add(labelDebt);
        panel1.add(labelBranch);
        
        panel1.add(labelDataId);
        panel1.add(labelDataName);
        panel1.add(labelDataEmail);
        panel1.add(labelDataPassword);
        panel1.add(labelDataAddress);
        panel1.add(labelDataPhoneNumber);
        panel1.add(labelDataCash);
        panel1.add(labelDataDebt);
        panel1.add(labelDataBranch);
        
        return panel1;
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