package model;
import javax.swing.*;

public class Book implements InterfaceBookState, InterfaceBranchCity {
    private int idBook;
    private int idBranch;
    private String title;
    private String author;
    private String publisher;
    private int pages;
    private int year;
    private String genre;
    private int status;

    public Book() {
        
    }
    
    public Book(int idBook, int idBranch, String title, String author, String publisher, int pages, int year, String genre, int status) {
        setIdBook(idBook);
        setIdBranch(idBranch);
        setTitle(title);
        setAuthor(author);
        setPublisher(publisher);
        setPages(pages);
        setYear(year);
        setGenre(genre);
        setStatus(status);
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
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public JPanel printBookData(JPanel panel) {
        JLabel author = new JLabel("Penulis : ");
        JLabel publisher = new JLabel("Penerbit : ");
        JLabel genre = new JLabel("Genre : ");
        JLabel pages = new JLabel("Jumlah Halaman : ");
        JLabel year = new JLabel("Tahun Terbit : ");
        JTextField inputAuthor = new JTextField();
        JTextField inputPublisher = new JTextField();
        JTextField inputGenre = new JTextField();
        JTextField inputPages = new JTextField();
        JTextField inputYear = new JTextField();

        //set components position
        author.setBounds(30, 80, 130, 30);
        publisher.setBounds(30, 140, 130, 30);
        genre.setBounds(30, 200, 130, 30);
        pages.setBounds(30, 260, 130, 30);
        year.setBounds(30, 320, 130, 30);
        inputAuthor.setBounds(200, 80, 250, 30);
        inputPublisher.setBounds(200, 140, 250, 30);
        inputGenre.setBounds(200, 200, 250, 30);
        inputPages.setBounds(200, 260, 250, 30);
        inputYear.setBounds(200, 320, 250, 30);

        //set components
        inputAuthor.setText(this.getAuthor());
        inputAuthor.setEnabled(false);
        inputPublisher.setText(this.getPublisher());
        inputPublisher.setEnabled(false);
        inputGenre.setText(this.getGenre());
        inputGenre.setEnabled(false);
        inputPages.setText(String.valueOf(this.getPages()));
        inputPages.setEnabled(false);
        inputYear.setText(String.valueOf(this.getYear()));
        inputYear.setEnabled(false);
        
        //add components to frame
        panel.add(author);
        panel.add(publisher);
        panel.add(genre);
        panel.add(pages);
        panel.add(year);
        panel.add(inputAuthor);
        panel.add(inputPublisher);
        panel.add(inputGenre);
        panel.add(inputPages);
        panel.add(inputYear);
        
        return panel;
    }
    
    @Override
    public String selectBookState(int idBook) {
        if(idBook==AVAILABLE) {
            return "Tersedia";
        } else {
            return "Dipinjam";
        }
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
