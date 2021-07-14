package controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Borrowing;
import model.Member;
import model.UserTypeEnum;

public class TableHandler {
    
    DatabaseHandler conn = new DatabaseHandler();
    
    public TableHandler(){
        conn.connect();
    };
    
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
}
