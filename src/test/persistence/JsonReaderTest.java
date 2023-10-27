package persistence;

import model.Bookmark;
import model.BookmarkList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BookmarkList bml = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBookmarkList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGeneralBookmarkList.json");
        try {
            BookmarkList bml = reader.read();
            assertEquals(0, (bml.getBookmarkList()).size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBookmarkList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            BookmarkList bml = reader.read();
            //assertEquals("My work room", bml.getName());
            /*WorkRoom wr = reader.read();
            assertEquals("My work room", wr.getName());
            List<Thingy> thingies = wr.getThingies();
            assertEquals(2, thingies.size());
            checkThingy("needle", Category.STITCHING, thingies.get(0));
            checkThingy("saw", Category.WOODWORK, thingies.get(1));*/
            List<Bookmark> bookmarks = bml.getBookmarkList();
            assertEquals(2, bookmarks.size());
            Bookmark bookmark1 = bookmarks.get(0);
            Bookmark bookmark2 = bookmarks.get(1);
            checkBookmark(bookmark1.getBook(), bookmark1.getCurrentPage(), bookmark1);
            checkBookmark(bookmark2.getBook(), bookmark2.getCurrentPage(), bookmark2);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}