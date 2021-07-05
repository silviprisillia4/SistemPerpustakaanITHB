/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author SILVI PRISILLIA
 */
public interface InterfaceBranchCity {
    final int BANDUNG = 1;
    final int JAKARTA = 2;
    final int SURABAYA = 3;
    
    abstract String selectBranchCity(int idBranch);
}
