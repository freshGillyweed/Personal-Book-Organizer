package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterBookReviewListTest extends JsonBookReviewListTest {
    @Test
    void testWriterInvalidFile() {
        try {
            BookReviewList brl = new BookReviewList();
            JsonWriterBookReviewList writer = new JsonWriterBookReviewList("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBookReviewList() {
        try {
            BookReviewList brl = new BookReviewList();
            JsonWriterBookReviewList writer = new JsonWriterBookReviewList("./data/testWriterEmptyBookReviewList.json");
            writer.open();
            writer.write(brl);
            writer.close();

            JsonReaderBookReviewList reader = new JsonReaderBookReviewList("./data/testWriterEmptyBookReviewList.json");
            brl = reader.read();
            assertEquals(0, (brl.getBookReviewList()).size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBookReviewList() {
        try {
            BookReviewList brl = new BookReviewList();
            Book book1 = new Book( "Louis Lowry", "The Giver", "Science fiction", 100);
            Book book2 = new Book( "S.E. Hinton", "The Outsiders", "Young adult fiction", 200);
            brl.addBookReview(new BookReview(book1));
            brl.addBookReview(new BookReview(book2));
            JsonWriterBookReviewList writer = new JsonWriterBookReviewList("./data/testWriterGeneralBookReviewList.json");
            writer.open();
            writer.write(brl);
            writer.close();

            JsonReaderBookReviewList reader = new JsonReaderBookReviewList("./data/testWriterGeneralBookReviewList.json");
            brl = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}
