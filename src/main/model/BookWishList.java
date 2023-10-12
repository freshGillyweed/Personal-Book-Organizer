package model;

import java.util.ArrayList;
import java.util.List;

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

    List<Book> getBookWishList() {
        return this.bookWishList;
    }
}
