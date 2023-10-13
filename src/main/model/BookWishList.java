package model;

import java.util.ArrayList;
import java.util.List;

// represents a wishlist of books
public class BookWishList {
    private List<Book> bookWishList;

    public BookWishList() {
        this.bookWishList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: If book is not already in the list, add a book
    public void addBook(Book book) {
        if (!bookWishList.contains(book)) {
            this.bookWishList.add(book);
        }
    }

    public List<Book> getBookWishList() {
        return this.bookWishList;
    }
}
