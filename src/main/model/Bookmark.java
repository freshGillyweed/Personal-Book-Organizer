package model;

public class Bookmark {
    private Book book;
    private int currentPage;

    public Bookmark(Book book) {
        this.book = book;
        this.currentPage = 1;
    }

    // REQUIRES: page > 1
    // MODIFIES: this
    // EFFECTS: the bookmark's page is updated
    //          to show which page the reader must resume reading
    public void updatePage(int page) {
        this.currentPage = page;
    }

    // EFFECTS: the book that is bookmarked is returned
    public Book getBook() {
        return this.book;
    }

    // EFFECTS: the page that the reader must resume on is returned
    public int getCurrentPage() {
        return this.currentPage;
    }
}
