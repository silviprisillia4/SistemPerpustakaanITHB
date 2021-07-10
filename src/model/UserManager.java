package model;

public class UserManager {

    private static UserManager instance;
    private UserTypeEnum userType;
    private Member member;
    private Admin admin;
    private User owner;

    //lazy instantiation
//    public static UserManager getInstance() {
//        if(instance==null) {
//            instance = new UserManager();
//        }
//        return instance;
//    }
//
    public static UserManager getInstance() {
        if(instance==null) {
            instance = new UserManager();
        }
        return instance;
    }

//    public Object getUser() {
//        if(userType == UserTypeEnum.MEMBER) {
//            return member;
//        } else if(userType == UserTypeEnum.ADMIN) {
//            return admin;
//        } else {
//            return owner;
//        }
//    }

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
    //
//    public void setMember(Member member) {
//        this.member = member;
//    }
//
    public Admin getAdmin() {
        return admin;
    }
//
//    public void setAdmin(Admin admin) {
//        this.admin = admin;
//    }

    public void logOut() {
        instance = null;
        userType = null;
    }

}
