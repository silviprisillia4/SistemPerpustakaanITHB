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
public interface InterfaceBookState {
    final int BORROWED = 0;
    final int AVAILABLE = 1;
    
    abstract String selectBookState(int idBook);
}
