package model;

public interface InterfaceBookState {
    final int BORROWED = 0;
    final int AVAILABLE = 1;
    final int PROCESSED = 2;
    
    abstract String selectBookState(int idBook);
}
