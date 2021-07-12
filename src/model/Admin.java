package model;
import java.util.ArrayList;

public class Admin extends User implements InterfaceBranchCity {
    private int idBranch;
    private ArrayList<PaidBook> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    
    public Admin() {
        
    }
    
    public Admin(int idUser, String firstName, String lastName, String email, String password, UserTypeEnum type, int idBranch, ArrayList<PaidBook> books, ArrayList<Member> members) {
        super(idUser, firstName, lastName, email, password, type);
        setIdBranch(idBranch);
        setBooks(books);
        setMembers(members);
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }
    
    public ArrayList<PaidBook> getBooks() {
        return books;
    }
    
    public void setBooks(ArrayList<PaidBook> books) {
        this.books = books;
    }
    
    public ArrayList<Member> getMembers() {
        return members;
    }
    
    public void setMembers(ArrayList<Member> members) {
        this.members = members;
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
