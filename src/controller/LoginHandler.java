package controller;
import model.*;
import view.*;

public class LoginHandler {
    
    public LoginHandler(String email, String branch) {
        Controller c = new Controller();
        
        int idBranch = c.getBranchIDByCity(branch);
        c.getAUser(email, idBranch);
        
        User user = new model.UserManager().getInstance().getUser();
        if (user instanceof Admin) {
            new view.OutputInfo().welcomeToMenuAdmin();
            new AdminMenu();
        } else if (user instanceof Member) {
            new MemberMenu();
        } else {
            new view.OutputInfo().welcomeToOwnerMenu();
            new OwnerMenu();
        }
    }
}
