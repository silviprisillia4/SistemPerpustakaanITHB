/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.User;

/**
 *
 * @author Yen
 */
public class MemberManager {
    
    private static MemberManager instance;
    private User user;
    
    private MemberManager() {
    }
    
    public static MemberManager getInstance() {
        if (instance == null) {
            instance = new MemberManager();
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
