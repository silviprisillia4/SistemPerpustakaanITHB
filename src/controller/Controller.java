package controller;

import model.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

public class Controller {

    static DatabaseHandler conn = new DatabaseHandler();

    public Controller() {
        conn.connect();
    }

    public boolean userRegisterAvailability(String branch, String email) {
        int selectedBranch = getBranchIDByCity(branch);
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

    public boolean userLoginAvailability(String branch, String email, String password) {
        int selectedBranch = getBranchIDByCity(branch);
        if (selectedBranch == -1) {
            return false;
        } else {
            String query = "SELECT * FROM users WHERE idBranch = '" + selectedBranch + "' AND approved = '1'";
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
        String query = "SELECT * FROM branches";
        ArrayList<String> names = new ArrayList<>();
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
        boolean isValid = RegexController.firstNameValidation(firstName);
        if (isValid) {
            isValid = RegexController.lastNameValidation(lastName);
            if (isValid) {
                isValid = RegexController.addressValidation(address);
                if (isValid) {
                    isValid = RegexController.mobileNumberValidation(phone);
                    if (isValid) {
                        isValid = RegexController.emailValidation(email);
                        if (isValid) {
                            isValid = RegexController.passValidation(newPass);
                            if (isValid) {
                                if (newPass.equals(rePass)) {
                                    createNewUserAccount(selectedBranch, firstName,
                                            lastName, address, phone, email, getMD5(newPass));
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
            stmt.setString(9, UserTypeEnum.MEMBER.toString());
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

    public ArrayList<Member> getAllMembers(int idBranch) {
        ArrayList<Member> members = new ArrayList<>();
        String query;

        if (idBranch == 0) {
            query = "SELECT * FROM Users WHERE type = '" + UserTypeEnum.MEMBER + "'";
        } else {
            query = "SELECT * FROM Users WHERE type = '" + UserTypeEnum.MEMBER + "' && idbranch = '" + idBranch + "' ORDER by firstName ASC";
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
                member.setBorrows(getAllBorrowList(rs.getInt("idUser"), 0));
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
    
    public ArrayList<Member> getAllMembersOrdered(int idBranch, String order) {
        ArrayList<Member> members = new ArrayList<>();
        String query = "SELECT * FROM Users WHERE type = '" + UserTypeEnum.MEMBER + "' && idbranch = '" + idBranch + "' ORDER BY firstName "+order;
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
                member.setBorrows(getAllBorrowList(rs.getInt("idUser"), 0));
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public ArrayList<Borrowing> getAllBorrowList(int id, int condition) {
        ArrayList<Borrowing> borrows = new ArrayList<>();
        String query = "";
        if (condition == 0) {
            query = "SELECT * FROM borrows WHERE idUser = '" + id + "'";
        } else if (condition == 1) {
            query = "SELECT * FROM borrows WHERE idBranch = '" + id + "' && status = '0'";
        } else {
            query = "SELECT * FROM borrows WHERE idUser = '" + id + "' && (status = '0' || status = '3')";
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
                borrow.setBook((PaidBook) getABook(rs.getInt("idBook")));
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    public ArrayList<PaidBook> getAllBooks(int idBranch) {
        ArrayList<PaidBook> books = new ArrayList<>();
        String query = "SELECT * from books WHERE idBranch = '" + idBranch + "' AND status = 1";
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
        String query = "SELECT * from books WHERE idBranch = '" + idBranch + "' AND status = 1 ORDER BY title " + order;
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
        String query = "SELECT * from books WHERE idBranch = '" + idBranch + "' AND status = 1 AND " + filter + " LIKE '%" + search + "%'";
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

    public void getAUser(String email, int idBranch) {
        String query = "SELECT * FROM users WHERE email = '" + email + "' && idBranch = '" + idBranch + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int idUser = rs.getInt("idUser");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                UserTypeEnum type = UserTypeEnum.valueOf(rs.getString("type"));
                String password = rs.getString("password");
                if (type == UserTypeEnum.MEMBER) {
                    Member member = new Member();
                    ArrayList<Borrowing> borrows = getAllBorrowList(rs.getInt("idUser"), 0);
                    member.setIdUser(idUser);
                    member.setFirstName(firstName);
                    member.setLastName(lastName);
                    member.setType(type);
                    member.setEmail(email);
                    member.setPassword(password);
                    member.setIdBranch(idBranch);
                    member.setAddress(rs.getString("address"));
                    member.setPhoneNumber(rs.getString("phoneNumber"));
                    member.setCash(rs.getInt("cash"));
                    member.setDebt(rs.getInt("debt"));
                    member.setBorrows(borrows);
                    new UserManager().getInstance().setUser(member);
                } else if (type == UserTypeEnum.ADMIN) {
                    Admin admin = new Admin();
                    ArrayList<Member> members = getAllMembers(rs.getInt("idBranch"));
                    ArrayList<PaidBook> books = getAllBooksOrdered(rs.getInt("idBranch"), "ASC");
                    admin.setIdUser(idUser);
                    admin.setFirstName(firstName);
                    admin.setLastName(lastName);
                    admin.setType(type);
                    admin.setEmail(email);
                    admin.setPassword(password);
                    admin.setIdBranch(idBranch);
                    admin.setBooks(books);
                    admin.setMembers(members);
                    new UserManager().getInstance().setUser(admin);
                } else {
                    User owner = new User();
                    owner.setIdUser(idUser);
                    owner.setFirstName(firstName);
                    owner.setLastName(lastName);
                    owner.setType(type);
                    owner.setEmail(email);
                    owner.setPassword(password);
                    new UserManager().getInstance().setUser(owner);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Member getAMember(int idUser) {
        Member member = new Member();
        String query = "SELECT * FROM users WHERE idUser = '" + idUser + "'";
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

    public PaidBook getABook(int idBuku) {
        PaidBook book = new PaidBook();
        String query = "SELECT * FROM Books WHERE idBook = '" + idBuku + "'";

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setIdBook(rs.getInt("idBook"));
                book.setIdBranch(rs.getInt("idBranch"));
                book.setPages(rs.getInt("pages"));
                book.setPublisher(rs.getString("publisher"));
                book.setYear(rs.getInt("year"));
                book.setStatus(rs.getInt("status"));
                book.setBorrowPrice(rs.getInt("borrowPrice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public String getTitleSelectedBook(int idBook) {
        String title = "";
        String query = "SELECT * FROM books WHERE idBook = '" + idBook + "'";
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

    public boolean topUpByAdmin(int idUser, int saldo) {
        boolean isSuccess = false;
        boolean isValid = false;
        int totalCash = 0;
        int totalDebt = 0;
        String query = "SELECT * FROM users WHERE idUser = '" + idUser + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getInt("approved") == 1) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (isValid) {
            try {
                Statement stmt = conn.con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    totalCash = rs.getInt("cash");
                    totalDebt = rs.getInt("debt");
                }
                isSuccess = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //hitung total cash user
            totalCash += saldo;

            //cek cash dan debt
            if (totalDebt != 0) {
                if (totalCash >= totalDebt) {
                    totalCash -= totalDebt;
                    query = "UPDATE users SET cash = '" + totalCash + "', debt = '0' WHERE idUser = '" + idUser + "'";
                } else {
                    totalDebt -= totalCash;
                    query = "UPDATE users SET cash = '0', debt = '" + totalDebt + "' WHERE idUser = '" + idUser + "'";
                }
            } else {
                query = "UPDATE users SET cash = '" + totalCash + "' WHERE idUser = '" + idUser + "'";
            }
            try {
                Statement stmt = conn.con.createStatement();
                stmt.executeUpdate(query);
                isSuccess = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    public boolean updatePassword(Member member, String newPassword) {
        String query = "UPDATE users SET password = '" + newPassword + "'";
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
        String query = "UPDATE users SET firstName = '" + member.getFirstName()
                + "', lastName = '" + member.getLastName() + "', email = '"
                + member.getEmail() + "', address = '" + member.getAddress()
                + "', phoneNumber = '" + member.getPhoneNumber() + "' WHERE idUser = '" + member.getIdUser() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean updateBook(PaidBook book) {
        String query = "UPDATE books SET borrowPrice ='" + book.getBorrowPrice() + "' WHERE idBook = '" + book.getIdBook() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public boolean updateBookStatusAfterBorrowed(int idBook) {
        String query = "UPDATE books SET status = 0 WHERE idBook = '" + idBook + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public boolean updateBookStatusAfterReturned(int idBook) {
        String query = "UPDATE books SET status = 1 WHERE idBook = '" + idBook + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public boolean updateBorrowing(int idBorrow, int finePrice) {
        String query = "SELECT * FROM borrows WHERE idBorrow = '" + idBorrow + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (updateBookStatusAfterReturned(rs.getInt("idBook"))) {
                    query = "UPDATE borrows SET status = 3, moneyFine = '" + finePrice + "' WHERE idBorrow = '" + idBorrow + "'";
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

    public void updateUserMoney(int idUser, int fine) {
        Member member = null;
        int cash;
        String query;
        for (int i = 0; i < UserManager.getInstance().getAdmin().getMembers().size(); i++) {
            member = UserManager.getInstance().getAdmin().getMembers().get(i);
            if (member.getIdUser() == idUser) {
                break;
            }
        }
        if (member.getCash() >= fine) {
            cash = member.getCash() - fine;
            query = "UPDATE users SET cash = '" + cash + "' WHERE idUser = '" + idUser + "'";
            member.setCash(cash);
            try {
                Statement stmt = conn.con.createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            cash = fine - member.getCash();
            query = "UPDATE users SET cash = 0, debt = '" + cash + "' WHERE idUser = '" + idUser + "'";
            member.setCash(0);
            member.setDebt(cash);
            try {
                Statement stmt = conn.con.createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean insertBorrowing(Borrowing borrowing) {
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

    public boolean insertNewBook(PaidBook book) {
        String query = "INSERT INTO books VALUES(?,?,?,?,?,?,?,?,?,?)";
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
            stmt.setInt(10, book.getStatus());
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
    
    public ArrayList<Member> getMemberArrayList(int idBranch) {
        ArrayList<Member> members = new ArrayList();
        String query = "SELECT * FROM users WHERE idBranch = '" + idBranch + "' AND type = '" + UserTypeEnum.MEMBER.toString() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Member member = new Member();
                member.setIdUser(rs.getInt("idUser"));
                member.setIdBranch(rs.getInt("idBranch"));
                member.setFirstName(rs.getString("firstname"));
                member.setLastName(rs.getString("lastname"));
                member.setEmail(rs.getString("email"));
                member.setAddress(rs.getString("address"));
                member.setPhoneNumber(rs.getString("phoneNumber"));
                member.setApproved(rs.getInt("approved"));
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
    
    public ArrayList<Borrowing> getBorrowArrayList(int idBranch) {
        ArrayList<Borrowing> borrows = new ArrayList();
        String query = "SELECT * FROM borrows WHERE idBranch = '" + idBranch + "' AND (status = 0 OR status = 2)";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Borrowing borrow = new Borrowing();
                borrow.setIdBorrow(rs.getInt("idBorrow"));
                borrow.setIdBook(rs.getInt("idBook"));
                borrow.setIdUser(rs.getInt("idUser"));
                borrow.setDate(rs.getDate("date"));
                borrow.setBorrowDays(rs.getInt("borrowDays"));
                borrow.setPriceTotal(rs.getInt("priceTotal"));
                borrow.setStatus(rs.getInt("status"));
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }
    
    public void updateCashMemberAfterApprovalBorrowing(Member member, int price, boolean approval) {
        if (price != 0) {
            int cash, debt, idUser = member.getIdUser();
            String query;
            cash = member.getCash();
            debt = member.getDebt();
            if (approval) {
                if (cash >= price) {
                    cash -= price;
                    query = "UPDATE users SET cash = '" + cash + "' WHERE idUser = '" + idUser + "'";
                    member.setCash(cash);
                    try {
                        Statement stmt = conn.con.createStatement();
                        stmt.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    debt += (price - cash);
                    query = "UPDATE users SET cash = 0, debt = '" + debt + "' WHERE idUser = '" + idUser + "'";
                    member.setDebt(debt);
                    member.setCash(0);
                    try {
                        Statement stmt = conn.con.createStatement();
                        stmt.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (debt >= price) {
                    debt -= price;
                    query = "UPDATE users SET debt = '" + debt + "' WHERE idUser = '" + idUser + "'";
                    member.setDebt(debt);
                    try {
                        Statement stmt = conn.con.createStatement();
                        stmt.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    cash += (price - debt);
                    query = "UPDATE users SET debt = 0, cash = '" + cash + "' WHERE idUser = '" + idUser + "'";
                    member.setCash(cash);
                    member.setDebt(0);
                    try {
                        Statement stmt = conn.con.createStatement();
                        stmt.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    
    public void updateBorrowStatus(int idBorrow, boolean status) {
        if (status) {
            String query = "UPDATE borrows SET status = '0' WHERE idBorrow = '" + idBorrow + "'";
            try {
                Statement stmt = conn.con.createStatement();
                int rs = stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String query = "UPDATE borrows SET status = '2' WHERE idBorrow = '" + idBorrow + "'";
            try {
                Statement stmt = conn.con.createStatement();
                int rs = stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
