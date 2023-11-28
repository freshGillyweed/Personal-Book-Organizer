package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a wishlist of books
public class BookWishList implements Writable {
    private List<Book> bookWishList;

    public BookWishList() {
        this.bookWishList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: If book is not already in the list, add a book
    public void addBook(Book book) {
        if (!bookWishList.contains(book)) {
            this.bookWishList.add(book);
            //EventLog.getInstance().logEvent(new Event("New book added to book wish list."));
        }
    }

    // MODIFIES: this
    // EFFECTS: If book is not already in the list, add a book and log event
    // method overloading to prevent JsonReaderBookWishList class from logging events
    public void addBook(Book book, boolean logEvent) {
        if (!bookWishList.contains(book)) {
            this.bookWishList.add(book);
            EventLog.getInstance().logEvent(new Event("New book added to book wish list."));
        }
    }

    public List<Book> getBookWishList() {
        return this.bookWishList;
    }

    // MODIFIES: subset
    // EFFECTS: Returns a list of books over 200 pages
    public List<Book> getSubsetOfBookWishList() {
        List<Book> subset = new ArrayList<>();

        for (Book book: bookWishList) {
            if (book.getTotalPages() > 200) {
                subset.add(book);
            }
        }
        EventLog.getInstance().logEvent(new Event("Books over 200 pages in the wish list displayed."));
        return subset;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Wish books", wishBooksToJson());
        return json;
    }

    private JSONArray wishBooksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book book : bookWishList) {
            jsonArray.put(book.toJson());
        }

        return jsonArray;
    }
}
