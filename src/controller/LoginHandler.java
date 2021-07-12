/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.User;
import model.UserType;

/**
 *
 * @author Yen
 */
public class LoginHandler {
    
    public LoginHandler(String email, String branch) {
        DataController c = new DataController();
        int idBranch = c.getBranchIDByCity(branch);
        c.setLoggedInUser(email, idBranch);
        User user = UserManager.getInstance().getUser();
        if (user.getType() == UserType.ADMIN) {
            //new AdminMenu();
        } else if (user.getType() == UserType.MEMBER) {
            //new MemberMenu();
        } else {
            //new OwnerMenu();
        }
    }
}
