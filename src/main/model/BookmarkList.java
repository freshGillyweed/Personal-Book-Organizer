package model;

import java.util.ArrayList;
import java.util.List;

// represents a list of bookmarks
public class BookmarkList {
    private List<Bookmark> bookmarkList;

    public BookmarkList() {
        bookmarkList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: A Bookmark object is added to bookmarkList
    public void addBookmark(Bookmark bookmark) {
        bookmarkList.add(bookmark);
    }

    // REQUIRES: a valid bookTitle
    // MODIFIES: this
    // EFFECTS: updates the pages read for the book of given ID
    void updateBookmark(String bookTitle, int page) {
        for (Bookmark bookmark:bookmarkList) {
            if (bookTitle.equals((bookmark.getBook()).getTitle())) {
                bookmark.updatePage(page);
                return;
            }
        }
    }

    // EFFECTS: a list of bookmarks is returned
    public List<Bookmark> getBookmarkList() {
        return this.bookmarkList;
    }
}
