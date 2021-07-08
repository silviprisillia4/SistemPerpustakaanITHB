package model;

public class Book implements InterfaceBookState, InterfaceBranchCity {
    private int idBook;
    private int idBranch;
    private String title;
    private String author;
    private String publisher;
    private int pages;
    private int year;
    private String genre;
    private int availability;

    public Book() {
        
    }
    
    public Book(int idBook, int idBranch, String title, String author, String publisher, int pages, int year, String genre, int availability) {
        setIdBook(idBook);
        setIdBranch(idBranch);
        setTitle(title);
        setAuthor(author);
        setPublisher(publisher);
        setPages(pages);
        setYear(year);
        setGenre(genre);
        setAvailability(availability);
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public void printDataBuku() {
        
    }
    
    public int getAvailability() {
        return availability;
    }
    
    public void setAvailability(int availability) {
        
    }
    
    @Override
    public String selectBookState(int idBook) {
        String state = "";
        if(idBook==AVAILABLE) {
            state = "Tersedia";
        } else {
            state = "Dipinjam";
        }
        return state;
    }

    @Override
    public String selectBranchCity(int idBranch) {
        switch(idBranch) {
            case BANDUNG :
                return "Bandung";
            case JAKARTA :
                return "Jakarta";
            case SURABAYA :
                return "Surabaya";
        }
        return "";
    }
}
