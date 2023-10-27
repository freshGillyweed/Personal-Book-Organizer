package persistence;

import model.Book;
import model.Bookmark;
import model.BookmarkList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            BookmarkList bml = new BookmarkList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBookmarkList() {
        try {
            BookmarkList bml = new BookmarkList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBookmarkList.json");
            writer.open();
            writer.write(bml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBookmarkList.json");
            bml = reader.read();
            //assertEquals("My work room", bml.getName());
            assertEquals(0, (bml.getBookmarkList()).size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBookmarkList() {
        try {
            BookmarkList bml = new BookmarkList();
            Book book1 = new Book( "Louis Lowry", "The Giver", "Science fiction", 100);
            Book book2 = new Book( "S.E. Hinton", "The Outsiders", "Young adult fiction", 200);
            bml.addBookmark(new Bookmark(book1));
            bml.addBookmark(new Bookmark(book2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBookmarkList.json");
            writer.open();
            writer.write(bml);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBookmarkList.json");
            bml = reader.read();
            //assertEquals("My work room", bml.getName());
            List<Bookmark> bookmarks = bml.getBookmarkList();
            assertEquals(2, bookmarks.size());
            Bookmark bookmark1 = bookmarks.get(0);
            Bookmark bookmark2 = bookmarks.get(1);
            checkBookmark(bookmark1.getBook(), bookmark1.getCurrentPage(), bookmark1);
            checkBookmark(bookmark2.getBook(), bookmark2.getCurrentPage(), bookmark2);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
