package persistence;

import model.Book;
import model.Bookmark;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBookmark(Book book, int currentPage, Bookmark bookmark) {
        assertEquals(book, bookmark.getBook());
        assertEquals(currentPage, bookmark.getCurrentPage());
    }
}