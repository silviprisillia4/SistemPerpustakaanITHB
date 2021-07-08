package model;

public class Admin extends User implements InterfaceBranchCity {
    private int idBranch;
    
    public Admin() {
        
    }
    
    public Admin(int idUser, String firstName, String lastName, String email, String password, UserTypeEnum type, int idBranch) {
        super(idUser, firstName, lastName, email, password, type);
        setIdBranch(idBranch);
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
