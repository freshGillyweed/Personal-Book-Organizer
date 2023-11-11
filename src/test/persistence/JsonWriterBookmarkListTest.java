package persistence;

import model.Book;
import model.Bookmark;
import model.BookmarkList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Code influenced by the JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterBookmarkListTest extends JsonBookmarkListTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            BookmarkList bml = new BookmarkList();
            JsonWriterBookmarkList writer = new JsonWriterBookmarkList("./data/my\0illegal:fileName.json");
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
            JsonWriterBookmarkList writer = new JsonWriterBookmarkList("./data/testWriterEmptyBookmarkList.json");
            writer.open();
            writer.write(bml);
            writer.close();

            JsonReaderBookmarkList reader = new JsonReaderBookmarkList("./data/testWriterEmptyBookmarkList.json");
            bml = reader.read();
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
            JsonWriterBookmarkList writer = new JsonWriterBookmarkList("./data/testWriterGeneralBookmarkList.json");
            writer.open();
            writer.write(bml);
            writer.close();

            JsonReaderBookmarkList reader = new JsonReaderBookmarkList("./data/testWriterGeneralBookmarkList.json");
            bml = reader.read();
            List<Bookmark> testReaderBML = bml.getBookmarkList();
            List<Bookmark> bookmarks = bml.getBookmarkList();
            assertEquals(2, bookmarks.size());
            assertEquals(testReaderBML.size(), bookmarks.size());
            Bookmark bookmark1 = bookmarks.get(0);
            Bookmark bookmark2 = bookmarks.get(1);
            Bookmark readerBookmark1 = testReaderBML.get(0);
            Bookmark readerBookmark2 = testReaderBML.get(1);
            checkBookmark(readerBookmark1.getBook(), readerBookmark1.getCurrentPage(), bookmark1);
            checkBookmark(readerBookmark2.getBook(), readerBookmark2.getCurrentPage(), bookmark2);
            /*List<Bookmark> bookmarks = bml.getBookmarkList();
            assertEquals(2, bookmarks.size());
            Bookmark bookmark1 = bookmarks.get(0);
            Bookmark bookmark2 = bookmarks.get(1);
            checkBookmark(bookmark1.getBook(), bookmark1.getCurrentPage(), bookmark1);
            checkBookmark(bookmark2.getBook(), bookmark2.getCurrentPage(), bookmark2);*/
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
