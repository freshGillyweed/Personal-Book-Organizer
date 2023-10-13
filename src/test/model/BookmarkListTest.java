package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookmarkListTest {
    private BookmarkList testBookmarkList;

    @BeforeEach
    void runBefore() {
        testBookmarkList = new BookmarkList();
    }

    @Test
    void testConstructor() {
        assertEquals(0, (testBookmarkList.getBookmarkList()).size());
    }

    @Test
    void testAddBookmark() {
        Book book1 = new Book( "Louis Lowry", "The Giver", "Science fiction", 250);
        Book book2 = new Book( "S.E. Hinton", "The Outsiders", "Young adult fiction", 140);
        Bookmark bookmark1 = new Bookmark(book1);
        Bookmark bookmark2 = new Bookmark(book2);
        testBookmarkList.addBookmark(bookmark1);
        testBookmarkList.addBookmark(bookmark2);
        assertEquals(2, (testBookmarkList.getBookmarkList()).size());
        List<Bookmark> returnedList = testBookmarkList.getBookmarkList();
        Book b1 = (returnedList.get(0)).getBook();
        assertEquals(book1.getAuthor(),b1.getAuthor());
        assertEquals(book1.getTitle(),b1.getTitle());
        assertEquals(book1.getGenre(),b1.getGenre());
        assertEquals(book1.getTotalPages(),b1.getTotalPages());
    }

    @Test
    void testUpdateBookmark() {
        Book book1 = new Book( "Louis Lowry", "The Giver", "Science fiction", 250);
        Book book2 = new Book( "S.E. Hinton", "The Outsiders", "Young adult fiction", 140);
        Bookmark bookmark1 = new Bookmark(book1);
        Bookmark bookmark2 = new Bookmark(book2);
        testBookmarkList.addBookmark(bookmark1);
        testBookmarkList.addBookmark(bookmark2);
        List<Bookmark> returnedList = testBookmarkList.getBookmarkList();
        assertEquals(1,(returnedList.get(0).getCurrentPage()));
        assertEquals(1,(returnedList.get(1).getCurrentPage()));
        testBookmarkList.updateBookmark("The Giver",35);
        returnedList = testBookmarkList.getBookmarkList();
        assertEquals(35,(returnedList.get(0).getCurrentPage()));
    }
}

