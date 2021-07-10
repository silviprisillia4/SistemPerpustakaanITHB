/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Yen
 */
public class regex {
    
    static boolean emailValidation(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return email.matches(regex);
    }

    static boolean firstNameValidation(String pass) {
        String regex = "^[a-zA-Z]*.{3,20}$";
        return pass.matches(regex);
    }
    
    static boolean lastNameValidation(String pass) {
        String regex = "^[a-zA-Z]*.{0,20}$";
        return pass.matches(regex);
    }
    
    static boolean addressValidation(String address) {
        String regex = "^[a-zA-Z0-9]*.{3,255}$";
        return address.matches(regex);
    }

    static boolean mobileNumberValidation(String mNumber) {
        String regex = "^[0-9]+.{8,15}$";
        return mNumber.matches(regex);
    }

    static boolean passValidation(String pass) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,20}$";
        return pass.matches(regex);
    }
}