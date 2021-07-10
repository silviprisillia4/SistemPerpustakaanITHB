/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import model.Member;
import model.User;
import model.UserType;

/**
 *
 * @author Yen
 */
public class DataController {
    
    DatabaseHandler conn = new DatabaseHandler();
    
    public DataController() {
        conn.connect();
    }
    
    public boolean userRegisterAvailability(String branch, String email) {
        int selectedBranch = getBranchIDByCity(branch);
        
        if (selectedBranch == -1) {
            return false;
        } else {
            String query = "SELECT * FROM users WHERE idBranch = '" + selectedBranch + "'";
            try {
                Statement stmt = conn.con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (rs.getString("email").equals(email)) {
                        return false;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
    
    public boolean userLoginAvailability(String branch, String email, String password) {
        int selectedBranch = getBranchIDByCity(branch);
        
        if (selectedBranch == -1) {
            return false;
        } else {
            String query = "SELECT * FROM users WHERE idBranch = '" + selectedBranch + "'";
            try {
                Statement stmt = conn.con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (rs.getString("email").equals(email)) {
                        if (rs.getString("password").equals(password)) {
                            return true;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return false;
        }
    }
    
    public int getBranchIDByCity(String name) {
        String query = "SELECT * FROM branches";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                if (rs.getString("city").equals(name)) {
                    return rs.getInt("idBranch");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public ArrayList<String> getBranchesCity() {
        ArrayList<String> names = new ArrayList();
        String query = "SELECT * FROM branches";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                names.add(rs.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }
    
    public boolean processingRegistration(int selectedBranch, String firstName, String lastName, String address, String phone, String email, String newPass, String rePass) {
        boolean isValid = Regex.firstNameValidation(firstName);
        if (isValid) {
            isValid = Regex.lastNameValidation(lastName);
            if (isValid) {
                isValid = Regex.addressValidation(address);
                if (isValid) {
                    isValid = Regex.mobileNumberValidation(phone);
                    if (isValid) {
                        isValid = Regex.emailValidation(email);
                        if (isValid) {
                            isValid = Regex.passValidation(newPass);
                            if (isValid) {
                                if (newPass.equals(rePass)) {
                                    DataController c = new DataController();
                                    c.createNewUserAccount(selectedBranch, firstName,
                                            lastName,address, phone, email, newPass);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void createNewUserAccount(int branch, String firstName, String lastName, String address, String phone, String email, String pass) {
        String query = "INSERT INTO users VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setNull(1, Types.INTEGER);
            stmt.setInt(2, branch);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, email);
            stmt.setString(6, pass);
            stmt.setString(7, address);
            stmt.setString(8, phone);
            stmt.setString(9, UserType.MEMBER.toString());
            stmt.setInt(10, 0);
            stmt.setInt(11, 0);
            stmt.setInt(12, 0);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void approveAMember(String idUser) {
        String query = "UPDATE users SET approved = '1' WHERE idUser = '" + idUser + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Member getLoggedInUser(String email) {
        Member member = new Member();
        String query = "SELECT * FROM users WHERE email = '" + email + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            member.setIdUser(rs.getInt("idUser"));
            member.setIdBranch(rs.getInt("idBranch"));
            member.setFirstName(rs.getString("firstName"));
            member.setLastName(rs.getString("lastName"));
            member.setEmail(rs.getString("email"));
            member.setPassword(rs.getString("password"));
            member.setAddress(rs.getString("address"));
            member.setPhoneNumber(rs.getString("phoneNumber"));
            member.setType(UserType.valueOf(rs.getString("type")));
            member.setCash(rs.getInt("cash"));
            member.setDebt(rs.getInt("debt"));
            member.setApproved(rs.getInt("approved"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }
}
