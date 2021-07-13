package model;

public class UserManager {

    private static UserManager instance;
    private Member member;
    private Admin admin;
    private User owner;

    public static UserManager getInstance() {
        if(instance==null) {
            instance = new UserManager();
        }
        return instance;
    }
    
    public User getUser() {
        if(member!=null) {
            return getMember();
        } else if(admin!=null) {
            return getAdmin();
        } else {
            return getOwner();
        }
    }

    public void setUser(User user) {
        if(user instanceof Member) {
            this.member = (Member) user;
        } else if(user instanceof Admin) {
            this.admin = (Admin) user;
        } else {
            this.owner = (User) user;
        }
    }

    public Member getMember() {
        return member;
    }

    public Admin getAdmin() {
        return admin;
    }

    public User getOwner() {
      return owner;
    }

    public void logOut() {
        instance = null;
    }
}