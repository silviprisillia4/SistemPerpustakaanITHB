/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Admin;
import model.Borrowing;
import model.Member;
import model.PaidBook;
import model.UserTypeEnum;

/**
 *
 * @author Feliciana Gunadi
 */
public class databaseChange {

    static Controller.DatabaseHandler conn = new Controller.DatabaseHandler();

    public boolean insertNewBook(PaidBook book) {
        conn.connect();
        String query = "INSERT INTO books VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, book.getIdBook());
            stmt.setInt(2, book.getIdBranch());
            stmt.setString(3, book.getTitle());
            stmt.setString(4, book.getAuthor());
            stmt.setString(5, book.getGenre());
            stmt.setString(6, book.getPublisher());
            stmt.setInt(7, book.getPages());
            stmt.setInt(8, book.getBorrowPrice());
            stmt.setInt(9, book.getYear());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public Member getAMember(int idUser) {
        Member member = new Member();
        conn.connect();
        String query = "SELECT * FROM users WHERE idUser = '" + idUser + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                member.setAddress(rs.getString("address"));
                member.setCash(rs.getInt("cash"));
                member.setDebt(rs.getInt("debt"));
                member.setEmail(rs.getString("email"));
                member.setFirstName(rs.getString("firstName"));
                member.setLastName(rs.getString("lastName"));
                member.setPhoneNumber(rs.getString("phoneNumber"));
                member.setIdBranch(rs.getInt("idBranch"));
                member.setType(UserTypeEnum.MEMBER);
                member.setPassword(rs.getString("password"));
                member.setIdUser(rs.getInt("idUser"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    public ArrayList<Member> getAllMember(int idBranch) {
        ArrayList<Member> members = new ArrayList<>();
        conn.connect();
        String query;

        if (idBranch == 0) {
            query = "SELECT * FROM Users WHERE type = '" + UserTypeEnum.MEMBER + "'";
        } else {
            query = "SELECT * FROM Users WHERE type = '" + UserTypeEnum.MEMBER + "' && idbranch = '" + idBranch + "'";
        }

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Member member = new Member();
                member.setAddress(rs.getString("address"));
                member.setCash(rs.getInt("cash"));
                member.setDebt(rs.getInt("debt"));
                member.setEmail(rs.getString("email"));
                member.setFirstName(rs.getString("firstname"));
                member.setLastName(rs.getString("lastname"));
                member.setPhoneNumber(rs.getString("phonenumber"));
                member.setIdBranch(rs.getInt("idbranch"));
                member.setType(UserTypeEnum.MEMBER);
                member.setPassword(rs.getString("password"));
                member.setIdUser(rs.getInt("iduser"));
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public Admin getAdmin(int idBranch) {
        Admin admin = new Admin();
        conn.connect();
        String query;
        query = "SELECT * FROM Users WHERE type = '" + UserTypeEnum.ADMIN + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                admin.setIdUser(rs.getInt("iduser"));
                admin.setIdBranch(rs.getInt("idBranch"));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Borrowing> getAllBorrowList(int id, boolean condition) {
        ArrayList<Borrowing> listBorrows = new ArrayList<>();
        conn.connect();
        String query = "";
        if (condition) {
            query = "SELECT * FROM Borrows WHERE iduser = '" + id + "'";
        } else {
            query = "SELECT * FROM Borrows WHERE idbranch = '" + id + "' && status = '0'";
        }

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Borrowing borrow = new Borrowing();
                borrow.setBorrowDays(rs.getInt("borrowdays"));
                borrow.setDate(rs.getDate("date"));
                borrow.setIdBook(rs.getInt("idbook"));
                borrow.setPriceTotal(rs.getInt("pricetotal"));
                borrow.setIdBranch(rs.getInt("idbranch"));
                borrow.setIdUser(rs.getInt("idUser"));
                borrow.setIdBorrow(rs.getInt("idBorrow"));
                borrow.setMoneyFine(rs.getInt("moneyFine"));
                borrow.setStatus(rs.getInt("status"));
                listBorrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBorrows;
    }

    public boolean updateBorrowing(int idBorrow, int finePrice) {
        conn.connect();
        String query = "SELECT * FROM borrows WHERE idBorrow = '" + idBorrow + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (updateBookState(rs.getInt("idBook"))) {
                    query = "UPDATE borrows SET status='1', moneyFine = '" + finePrice + "' WHERE idBorrow = '" + idBorrow + "'";
                    try {
                        stmt.executeUpdate(query);
                        return (true);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return (false);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBookState(int idBook) {
        conn.connect();
        String query = "UPDATE books SET status='1' WHERE idBook = '" + idBook + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public PaidBook getABook(int idBuku) {
        PaidBook book = new PaidBook();
        conn.connect();
        String query = "SELECT * FROM Books WHERE idBook = '" + idBuku + "'";

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setBorrowPrice(rs.getInt("borrowPrice"));
                book.setGenre(rs.getString("genre"));
                book.setIdBook(rs.getInt("idBook"));
                book.setIdBranch(rs.getInt("idBranch"));
                book.setPages(rs.getInt("pages"));
                book.setPublisher(rs.getString("publisher"));
                book.setYear(rs.getInt("year"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public static boolean updateBook(PaidBook book) {
        conn.connect();
        String query = "UPDATE books SET borrowPrice='" + book.getBorrowPrice() + "' WHERE idBook = '" + book.getIdBook() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public void updateUser(int idUser, int fine) {
        conn.connect();
        Member member = new Member();
        int cash;
        String query = "SELECT * FROM users WHERE idUser ='" + idUser + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                member.setCash(rs.getInt("cash"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (member.getCash() >= fine) {
            cash = member.getCash() - fine;
            query = "UPDATE users SET cash = '" + cash + "' WHERE idUser = '" + idUser + "'";
            try {
                Statement stmt = conn.con.createStatement();
                stmt.executeUpdate(query);
//                    return (true);
            } catch (SQLException e) {
                e.printStackTrace();
//                    return (false);
            }
        } else {
            cash = fine - member.getCash();
            query = "UPDATE users SET cash = '0', debt = '" + cash + "' WHERE idUser = '" + idUser + "'";
            try {
                Statement stmt = conn.con.createStatement();
                stmt.executeUpdate(query);
//                    return (true);
            } catch (SQLException e) {
                e.printStackTrace();
//                    return (false);
            }
        }

    }

    public void print() {
//        ArrayList<Member> members = getAllMember();
//
//        for (int i = 0; i < members.size(); i++) {
//            System.out.println(members.get(i).getFirstName());
//        }

//        for (int i = 0; i < getAllBooks().size(); i++) {
//            System.out.println(getAllBooks().get(i).getTitle());
//        }
//        for (int i = 0; i < getAllBorrowList().size(); i++) {
//            System.out.println(getAllBorrowList().get(i).getPriceTotal());
//        }
    }
}
