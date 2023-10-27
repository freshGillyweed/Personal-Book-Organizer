package persistence;

import model.Book;
import model.Bookmark;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Code influenced by the JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonBookmarkListTest {
    protected void checkBookmark(Book book, int currentPage, Bookmark bookmark) {
        assertEquals(book, bookmark.getBook());
        assertEquals(currentPage, bookmark.getCurrentPage());
    }
}