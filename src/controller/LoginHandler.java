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
        UserManager.getInstance().setUser(c.getLoggedInUser(email, idBranch));
        User user = UserManager.getInstance().getUser();
        if (user.getType() == UserType.ADMIN) {
            //new AdminMenu();
            System.out.println("admin");
        } else if (user.getType() == UserType.MEMBER) {
            //new MemberMenu();
            System.out.println("member");
        } else {
            //new OwnerMenu();
            System.out.println("owner");
        }
    }
}
