package model;

import java.util.ArrayList;

public class Admin extends User implements InterfaceBranchCity {
    private int idBranch;
    private ArrayList<Member> members= new ArrayList<>();
    private ArrayList<PaidBook> books= new ArrayList<>();

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public ArrayList<PaidBook> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<PaidBook> books) {
        this.books = books;
    }
    
    
    
    public Admin() {
        
    }
    
    public Admin(int idUser, String firstName, String lastName, String email, String password, UserTypeEnum type, int idBranch, ArrayList<Member> members, ArrayList<PaidBook> books) {
        super(idUser, firstName, lastName, email, password, type);
        setIdBranch(idBranch);
        setMembers(members);
        setBooks(books);
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
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