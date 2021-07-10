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
public class Admin extends User {
    private int idBranch;

    public Admin(int idUser, String firstName, String lastName, String email, String password, UserType type, int idBranch) {
        super(idUser, firstName, lastName, email, password, type);
        this.idBranch = idBranch;
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }

}
