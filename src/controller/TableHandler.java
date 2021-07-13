package controller;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import model.UserTypeEnum;

public class TableHandler {
    
    DatabaseHandler conn = new DatabaseHandler();
    
    public TableHandler(){
        conn.connect();
    };
    
    public String[][] getUsersDataForTable(int idBranch) {
        String query = "SELECT * FROM users WHERE idBranch = '" + idBranch + "' AND type = '" + UserTypeEnum.MEMBER.toString() + "'";
        int size = 0, userCount = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metadata = rs.getMetaData();
            size = metadata.getColumnCount();
            while (rs.next()) {
                userCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[][] users = new String[userCount][size+1];
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                users[i][0] = rs.getString("idUser");
                users[i][1] = rs.getString("idBranch");
                users[i][2] = rs.getString("firstname");
                users[i][3] = rs.getString("lastname");
                users[i][4] = rs.getString("email");
                users[i][5] = rs.getString("password");
                users[i][6] = rs.getString("address");
                users[i][7] = rs.getString("phoneNumber");
                users[i][8] = rs.getString("type");
                users[i][9] = rs.getString("cash");
                users[i][10] = rs.getString("debt");
                users[i][11] = rs.getString("approved");
                int status = rs.getInt("approved");
                if (status == 0) {
                    users[i][12] = "Button";
                } else {
                    users[i][12] = "Approved";
                }
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public String[] getTableColumnsName() {
        int size = 0;
        String query = "SELECT * FROM users";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metadata = rs.getMetaData();
            size = metadata.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] columns = new String[size+1];
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metadata = rs.getMetaData();
            for (int i = 0, j = 1; j <= size; i++, j++) {
                columns[i] = metadata.getColumnName(j);
            }
            columns[size] = "Button";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }
    
    public int getMemberAmountOfABranch(int idBranch) {
        String query = "SELECT * FROM users WHERE idBranch = '" + idBranch + "' AND type = '" + UserTypeEnum.MEMBER.toString() + "'";
        int userCount = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                userCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userCount;
    }
    
    public String[] getUsersEmail(int idBranch) {
        int userCount = getMemberAmountOfABranch(idBranch);
        String[] users = new String[userCount];
        String query = "SELECT * FROM users WHERE idBranch = '" + idBranch + "' AND type = '" + UserTypeEnum.MEMBER.toString() + "'";
        int i = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                users[i] = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public String[][] getMembersData(int idBranch) {
        String query = "SELECT * FROM users WHERE idBranch = '" + idBranch + "' AND type = '" + UserTypeEnum.MEMBER.toString() + "'";
        int userCount = getMemberAmountOfABranch(idBranch);
        String[][] users = new String[userCount][8];
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                users[i][0] = rs.getString("idUser");
                users[i][1] = rs.getString("idBranch");
                users[i][2] = rs.getString("firstname");
                users[i][3] = rs.getString("lastname");
                users[i][4] = rs.getString("email");
                users[i][5] = rs.getString("address");
                users[i][6] = rs.getString("phoneNumber");
                users[i][7] = rs.getString("approved");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public String[] getMemberID(int idBranch) {
        String query = "SELECT * FROM users WHERE idBranch = '" + idBranch + "' AND type = '" + UserTypeEnum.MEMBER.toString() + "'";
        int userCount = getMemberAmountOfABranch(idBranch);
        String[] id = new String[userCount];
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                id[i] = rs.getString("idUser");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public String[][] getBorrowsData(int idBranch) {
        String query = "SELECT * FROM borrows WHERE idBranch = '" + idBranch + "' AND (status = 0 OR status = 2)";
        int borrowCount = getUnapprovedBorrowAmountOfABranch(idBranch);
        String[][] users = new String[borrowCount][7];
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                users[i][0] = rs.getString("idBorrow");
                users[i][1] = rs.getString("idBook");
                users[i][2] = rs.getString("idUser");
                users[i][3] = rs.getString("date");
                users[i][4] = rs.getString("borrowDays");
                users[i][5] = rs.getString("priceTotal");
                users[i][6] = rs.getString("status");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public int getUnapprovedBorrowAmountOfABranch(int idBranch) {
        String query = "SELECT * FROM borrows WHERE idBranch = '" + idBranch + "' AND (status = 0 OR status = 2)";
        int borrowCount = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                borrowCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowCount;
    }
}
