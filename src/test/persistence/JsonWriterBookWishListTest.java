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

public class JsonWriterBookWishListTest extends JsonBookWishListTest {
    @Test
    void testWriterInvalidFile() {
        try {
            BookWishList bwl = new BookWishList();
            JsonWriterBookWishList writer = new JsonWriterBookWishList("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBookWishList() {
        try {
            BookWishList bwl = new BookWishList();
            JsonWriterBookWishList writer = new JsonWriterBookWishList("./data/testWriterEmptyBookWishList.json");
            writer.open();
            writer.write(bwl);
            writer.close();

            JsonReaderBookWishList reader = new JsonReaderBookWishList("./data/testWriterEmptyBookWishList.json");
            bwl = reader.read();
            assertEquals(0, (bwl.getBookWishList()).size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBookWishList() {
        try {
            BookWishList bwl = new BookWishList();
            Book book1 = new Book( "Louis Lowry", "The Giver", "Science fiction", 100);
            Book book2 = new Book( "S.E. Hinton", "The Outsiders", "Young adult fiction", 200);
            bwl.addBook(book1);
            bwl.addBook(book2);
            JsonWriterBookWishList writer = new JsonWriterBookWishList("./data/testWriterGeneralBookWishList.json");
            writer.open();
            writer.write(bwl);
            writer.close();

            JsonReaderBookWishList reader = new JsonReaderBookWishList("./data/testWriterGeneralBookWishList.json");
            bwl = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}
