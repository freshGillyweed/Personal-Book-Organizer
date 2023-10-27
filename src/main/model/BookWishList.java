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
        }
    }

    public List<Book> getBookWishList() {
        return this.bookWishList;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        //json.put("name", name);
        json.put("BookWishes", bookWishesToJson());
        return json;
    }

    private JSONArray bookWishesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book bookWish : bookWishList) {
            jsonArray.put(bookWish.toJson());
        }

        return jsonArray;
    }
}
