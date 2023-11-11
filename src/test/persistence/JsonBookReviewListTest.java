package persistence;

import model.Book;
import model.BookReview;
import model.Bookmark;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonBookReviewListTest {
    protected void checkBookReview(Book book, double rating, String review, BookReview bookReview) {
        assertEquals(book, bookReview.getBook());
        assertEquals(rating, bookReview.getRating());
        assertEquals(review, bookReview.getReview());
    }
}
