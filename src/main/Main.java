package main;


import controller.databaseChange;
import model.UserManager;

public class Main {

    public static void main(String[] args) {
//        new Controller.testing().print();
//        new view.AdminMenu().adminMenu(new controller.databaseChange().getAdmin(1));
//        new view.MainScreen();
       new view.OwnerMenu().ownerMenu();
        // UserManager.getInstance().setUser(new databaseChange().getAUser(2));
        // new view.AdminMenu().adminMenu();
//        new Controller.testing().print();
//        new View.AdminMenu().adminMenu(new Controller.testing().getAdmin(1));
//        new view.MainScreen();
//        new View.OwnerMenu().ownerMenu();
            new view.MemberMenu();
//            new View.AdminMenu().returnBorrow(1);
    }
    
}
