package persistence;

import model.Bookmark;
import model.BookmarkList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Code influenced by the JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonReaderBookmarkListTest extends JsonBookmarkListTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderBookmarkList reader = new JsonReaderBookmarkList("./data/noSuchFile.json");
        try {
            BookmarkList bml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBookmarkList() {
        JsonReaderBookmarkList reader = new JsonReaderBookmarkList("./data/testReaderEmptyGeneralBookmarkList.json");
        try {
            BookmarkList bml = reader.read();
            assertEquals(0, (bml.getBookmarkList()).size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBookmarkList() {
        JsonReaderBookmarkList reader = new JsonReaderBookmarkList("./data/testReaderGeneralBookmarkList.json");
        try {
            BookmarkList bml = reader.read();
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
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}