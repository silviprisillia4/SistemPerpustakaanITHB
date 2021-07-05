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
public class Admin extends User implements InterfaceBranchCity {
    private int idBranch;
    
    public Admin() {}
    
    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }
    
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
