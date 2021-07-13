package controller;
import model.*;
import view.*;

public class LoginHandler {
    
    public LoginHandler(String email, String branch) {
        Controller c = new Controller();
        int idBranch = c.getBranchIDByCity(branch);
        c.getAUser(email, idBranch);
        User user = new model.UserManager().getInstance().getUser();
        if (user.getType() == UserTypeEnum.ADMIN) {
            new AdminMenu();
            new view.OutputInfo().welcomeToMenuAdmin();
        } else if (user.getType() == UserTypeEnum.MEMBER) {
            new MemberMenu();
        } else {
            new OwnerMenu();
            new view.OutputInfo().welcomeToOwnerMenu();
        }
    }
}
