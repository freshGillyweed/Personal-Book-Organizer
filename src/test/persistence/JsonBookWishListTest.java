package persistence;

import model.Book;
import model.Bookmark;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonBookWishListTest {
    protected void checkWishBook(Book readerBook, Book bookFromWishList) {
        assertEquals(readerBook, bookFromWishList);
    }
}
