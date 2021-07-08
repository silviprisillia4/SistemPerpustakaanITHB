
package model;

public interface InterfaceBranchCity {
    final int BANDUNG = 1;
    final int JAKARTA = 2;
    final int SURABAYA = 3;
    
    abstract String selectBranchCity(int idBranch);
}
