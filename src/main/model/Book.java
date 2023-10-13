package model;

public class Book {
    private int bookID;
    private String author;
    private String title;
    private String genre;
    private int totalPages;

    public Book(int bookID, String author, String title, String genre, int totalPages) {
        this.bookID = bookID;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.totalPages = totalPages;
    }

    int getBookID() {
        return this.bookID;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    String getGenre() {
        return this.genre;
    }

    public int getTotalPages() {
        return this.totalPages;
    }
}
