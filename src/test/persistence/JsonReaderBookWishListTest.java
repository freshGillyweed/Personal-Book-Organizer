package persistence;

import model.Book;
import model.BookWishList;
import model.Bookmark;
import model.BookmarkList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderBookWishListTest extends JsonBookWishListTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReaderBookWishList reader = new JsonReaderBookWishList("./data/noSuchFile.json");
        try {
            BookWishList bwl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBookWishList() {
        JsonReaderBookWishList reader = new JsonReaderBookWishList("./data/testReaderEmptyGeneralBookWishList.json");
        try {
            BookWishList bwl = reader.read();
            assertEquals(0, (bwl.getBookWishList()).size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBookWishList() {
        JsonReaderBookWishList reader = new JsonReaderBookWishList("./data/testReaderGeneralBookWishList.json");
        try {
            BookWishList bwl = reader.read();
            List<Book> testReaderBWL = bwl.getBookWishList();
            List<Book> wishBooks = bwl.getBookWishList();
            assertEquals(2, wishBooks.size());
            assertEquals(testReaderBWL.size(), wishBooks.size());
            Book wishBook1 = wishBooks.get(0);
            Book wishBook2 = wishBooks.get(1);
            Book readerWishBook1 = testReaderBWL.get(0);
            Book readerWishBook2 = testReaderBWL.get(1);
            checkWishBook(readerWishBook1, wishBook1);
            checkWishBook(readerWishBook2, wishBook2);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
