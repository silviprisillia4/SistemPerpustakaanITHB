package model;

public interface InterfaceBookState {
    final int BORROWED = 0;
    final int AVAILABLE = 1;
    
    abstract String selectBookState(int idBook);
}
