package persistence;

import model.BookReview;
import model.BookReviewList;
import model.Bookmark;
import model.BookmarkList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderBookReviewListTest extends JsonBookReviewListTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReaderBookReviewList reader = new JsonReaderBookReviewList("./data/noSuchFile.json");
        try {
            BookReviewList brl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBookReviewList() {
        JsonReaderBookReviewList reader = new JsonReaderBookReviewList("./data/testReaderEmptyGeneralBookReviewList.json");
        try {
            BookReviewList brl = reader.read();
            assertEquals(0, (brl.getBookReviewList()).size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBookReviewList() {
        JsonReaderBookReviewList reader = new JsonReaderBookReviewList("./data/testReaderGeneralBookReviewList.json");
        try {
            BookReviewList brl = reader.read();
            List<BookReview> testReaderBRL = brl.getBookReviewList();
            List<BookReview> bookReviews = brl.getBookReviewList();
            assertEquals(2, bookReviews.size());
            assertEquals(testReaderBRL.size(), bookReviews.size());
            BookReview bookReview1 = bookReviews.get(0);
            BookReview bookReview2 = bookReviews.get(1);
            BookReview readerBookReview1 = testReaderBRL.get(0);
            BookReview readerBookReview2 = testReaderBRL.get(1);
            checkBookReview(readerBookReview1.getBook(), readerBookReview1.getRating(), readerBookReview1.getReview(), bookReview1);
            checkBookReview(readerBookReview2.getBook(), readerBookReview2.getRating(), readerBookReview2.getReview(), bookReview2);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
