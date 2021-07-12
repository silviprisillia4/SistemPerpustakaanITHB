package controller;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import model.*;
import model.UserTypeEnum;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Controller {

    static DatabaseHandler conn = new DatabaseHandler();
    
    public ArrayList<Member> getAllMembers() {
        ArrayList<Member> members = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM users WHERE type = '"+UserTypeEnum.MEMBER+"'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Member member = new Member();
                member.setIdUser(rs.getInt("idUser"));
                member.setFirstName(rs.getString("firstName"));
                member.setLastName(rs.getString("lastName"));
                member.setEmail(rs.getString("email"));
                member.setPassword(rs.getString("password"));
                member.setAddress(rs.getString("address"));
                member.setPhoneNumber(rs.getString("phoneNumber"));
                member.setCash(rs.getInt("cash"));
                member.setDebt(rs.getInt("debt"));
                member.setIdBranch(rs.getInt("idBracnh"));
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
    
    public ArrayList<Borrowing> getAllBorrowingHistory(int idUser) {
        ArrayList<Borrowing> borrows = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM borrows WHERE idUser = '"+idUser+"' AND status = 0 OR status = 3";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Borrowing borrowing = new Borrowing();
                borrowing.setIdBorrow(rs.getInt("idBorrow"));
                borrowing.setIdBook(rs.getInt("idBook"));
                borrowing.setIdBranch(rs.getInt("idBranch"));
                borrowing.setBorrowDays(rs.getInt("borrowDays"));
                borrowing.setPriceTotal(rs.getInt("priceTotal"));
                borrowing.setDate(rs.getDate("date"));
                borrowing.setStatus(rs.getInt("status"));
                borrowing.setMoneyFine(rs.getInt("MoneyFine"));
                borrows.add(borrowing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }
    
    public ArrayList<PaidBook> getAllBooks(int idBranch) {
        ArrayList<PaidBook> books = new ArrayList<>();
        conn.connect();
        String query = "SELECT * from books WHERE idBranch = '"+idBranch+"' AND status = 1";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PaidBook book = new PaidBook();
                book.setIdBook(rs.getInt("idBook"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPages(rs.getInt("pages"));
                book.setYear(rs.getInt("year"));
                book.setGenre(rs.getString("genre"));
                book.setStatus(rs.getInt("status"));
                book.setBorrowPrice(rs.getInt("borrowPrice"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    public ArrayList<PaidBook> getAllBooksOrdered(int idBranch, String order) {
        ArrayList<PaidBook> books = new ArrayList<>();
        conn.connect();
        String query = "SELECT * from books WHERE idBranch = '"+idBranch+"' AND status = 1 ORDER BY title "+order;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PaidBook book = new PaidBook();
                book.setIdBook(rs.getInt("idBook"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPages(rs.getInt("pages"));
                book.setYear(rs.getInt("year"));
                book.setGenre(rs.getString("genre"));
                book.setStatus(rs.getInt("status"));
                book.setBorrowPrice(rs.getInt("borrowPrice"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    public ArrayList<PaidBook> getAllBooksByFilter(int idBranch, String filter, String search) {
        ArrayList<PaidBook> books = new ArrayList<>();
        conn.connect();
        String query = "SELECT * from books WHERE idBranch = '"+idBranch+"' AND status = 1 AND "+filter+" LIKE '%"+search+"%'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PaidBook book = new PaidBook();
                book.setIdBook(rs.getInt("idBook"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPages(rs.getInt("pages"));
                book.setYear(rs.getInt("year"));
                book.setGenre(rs.getString("genre"));
                book.setStatus(rs.getInt("status"));
                book.setBorrowPrice(rs.getInt("borrowPrice"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    public Member getSelectedMember(int idUser) {
        Member member = new Member();
        conn.connect();
        String query = "SELECT * FROM users WHERE idUser = '"+idUser+"'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                member.setIdUser(idUser);
                member.setFirstName(rs.getString("firstName"));
                member.setLastName(rs.getString("lastName"));
                member.setEmail(rs.getString("email"));
                member.setPassword(rs.getString("password"));
                member.setType(UserTypeEnum.MEMBER);
                member.setAddress(rs.getString("address"));
                member.setPhoneNumber(rs.getString("phoneNumber"));
                member.setCash(rs.getInt("cash"));
                member.setDebt(rs.getInt("debt"));
                member.setIdBranch(rs.getInt("idBranch"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }
    
    public PaidBook getSelectedBook(int idBook) {
        PaidBook book = new PaidBook();
        conn.connect();
        String query = "SELECT * FROM Books WHERE idBook = '"+idBook+"'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                book.setIdBranch(rs.getInt("idBranch"));
                book.setIdBook(idBook);
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPages(rs.getInt("pages"));
                book.setYear(rs.getInt("year"));
                book.setGenre(rs.getString("genre"));
                book.setBorrowPrice(rs.getInt("borrowPrice"));
                book.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
    
    public String getTitleSelectedBook(int idBook) {
        String title = "";
        conn.connect();
        String query = "SELECT * FROM books WHERE idBook = '"+idBook+"'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                title = rs.getString("title");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }
    
    public String getSelectedPassword(int idUser) {
        String password = "";
        conn.connect();
        String query = "SELECT * FROM users WHERE idUser = '"+idUser+"'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }
    
    public boolean updatePassword(Member member, String newPassword) {
        conn.connect();
        String query = "UPDATE users SET password = '"+newPassword+"'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    public boolean updateProfile(Member member) {
        conn.connect();
        String query = "UPDATE users SET firstName = '"+member.getFirstName()+
                       "', lastName = '"+member.getLastName()+"', email = '"+
                       member.getEmail()+"', address = '"+member.getAddress()+
                       "', phoneNumber = '"+member.getPhoneNumber()+"' WHERE idUser = '"+member.getIdUser()+"'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    public boolean updateBookStatus(int idBook) {
        conn.connect();
        String query = "UPDATE books SET status = 0 WHERE idBook = '"+idBook+"'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    public boolean insertBorrowing(Borrowing borrowing) {
        conn.connect();
        String query = "INSERT INTO borrows VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, borrowing.getIdBorrow());
            stmt.setInt(2, borrowing.getIdBook());
            stmt.setInt(3, borrowing.getIdUser());
            stmt.setInt(4, borrowing.getIdBranch());
            stmt.setDate(5, getCurrentDate(borrowing.getDate()));
            stmt.setInt(6, borrowing.getBorrowDays());
            stmt.setInt(7, borrowing.getPriceTotal());
            stmt.setInt(8, borrowing.getMoneyFine());
            stmt.setInt(9, borrowing.getStatus());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    private static java.sql.Date getCurrentDate(Date date) {
        java.util.Date today = date;
        return new java.sql.Date(today.getTime());
    }
        
    public String getMD5(String password) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
  
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(password.getBytes());
  
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

