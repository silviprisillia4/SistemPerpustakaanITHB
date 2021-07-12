package model;

public class Branch implements InterfaceBookState, InterfaceBranchCity {
    private int idBranch;
    private String address;
    private String city;
    
    public Branch() {
        
    }
    
    public Branch(int idBranch, String address, String city) {
        setIdBranch(idBranch);
        setAddress(address);
        setCity(city);
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
