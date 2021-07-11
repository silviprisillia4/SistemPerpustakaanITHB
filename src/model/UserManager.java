package model;

public class UserManager {
    
    private static UserManager instance;
    private UserTypeEnum userType;
    private Member member;
    private Admin admin;
    private User owner;
    
    public static UserManager getInstance() {
        if(instance==null) {
            instance = new UserManager();
        }
        return instance;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }
    
    public void setUser(Object object) {
        if(object instanceof Member) {
            this.member = (Member) object;
            userType = UserTypeEnum.MEMBER;
        } else if(object instanceof Admin) {
            this.admin = (Admin) object;
            userType = UserTypeEnum.ADMIN;
        } else {
            this.owner = (User) object;
            userType = UserTypeEnum.OWNER;
        }
    }
    
    public Member getMember() {
        return member;
    }

    public Admin getAdmin() {
        return admin;
    }
    
    public void logOut() {
        instance = null;
        userType = null;
    }   
}