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

    public void setUser(Object object) {
        if(object instanceof Member) {
            this.member = (Member) object;
        } else if(object instanceof Admin) {
            this.admin = (Admin) object;
        } else {
            this.owner = (User) object;
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