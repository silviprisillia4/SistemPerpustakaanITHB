/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.User;
import model.UserTypeEnum;
import view.AdminMenu;
import view.MemberMenu;
import view.OwnerMenu;

/**
 *
 * @author yen
 */
public class LoginHandler {
    
    public LoginHandler(String email, String branch) {
        DataController c = new DataController();
        int idBranch = c.getBranchIDByCity(branch);
        new databaseChange().getAUser(email, idBranch);
        User user = new model.UserManager().getInstance().getUser();
        if (user.getType() == UserTypeEnum.ADMIN) {
            new AdminMenu();
        } else if (user.getType() == UserTypeEnum.MEMBER) {
            new MemberMenu();
        } else {
            new OwnerMenu();
        }
    }
}
