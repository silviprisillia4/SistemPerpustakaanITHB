/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.User;

/**
 *
 * @author yen
 */
public class UserManagerz {
    
    private static UserManagerz instance;
    private User user;
    
    private UserManagerz() {
    }
    
    public static UserManagerz getInstance() {
        if (instance == null) {
            instance = new UserManagerz();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
