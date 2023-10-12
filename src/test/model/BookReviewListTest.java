package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BookReviewListTest {
    private BookReviewList testBookReviewList;

    @BeforeEach
    void runBefore() {
        testBookReviewList = new BookReviewList();
    }

    @Test
    void testConstructor() {
        assertEquals(0, (testBookReviewList.getBookReviewList()).size());
    }

    @Test
    void testAddBookReview() {
        Book book1 = new Book(1, "Louis Lowry", "The Giver", "Science fiction", 250);
        BookReview bookReview1 = new BookReview(book1);
        bookReview1.updateRating(8.0);
        bookReview1.updateNumberOfTimesRead();
        bookReview1.updateReview("A soft yet ruthless introduction to dystopian novels");
        testBookReviewList.addBookReview(bookReview1);
        List<BookReview> returnedList = testBookReviewList.getBookReviewList();
        assertEquals(bookReview1,returnedList.get(0));
        assertEquals(1,returnedList.size());
    }

    @Test
    void testGetHighRatedBooks() {
        Book book1 = new Book(1, "Louis Lowry", "The Giver", "Science fiction", 250);
        BookReview bookReview1 = new BookReview(book1);
        bookReview1.updateRating(9.0);

        Book book2 = new Book(2, "Vladimir Nabokov", "Lolita", "Erotic literature", 400);
        BookReview bookReview2 = new BookReview(book2);
        bookReview2.updateRating(6.0);

        Book book3 = new Book(3, "Dan Brown", "The Da Vinci Code", "Thriller", 310);
        BookReview bookReview3 = new BookReview(book3);
        bookReview3.updateRating(2.0);

        testBookReviewList.addBookReview(bookReview1);
        testBookReviewList.addBookReview(bookReview2);
        testBookReviewList.addBookReview(bookReview3);

        List<BookReview> returnedList = testBookReviewList.getHighRatedBooks();
        assertEquals(bookReview1,returnedList.get(0));
        assertEquals(bookReview2,returnedList.get(1));
        assertFalse(returnedList.contains(bookReview3));
    }

    @Test
    void testGetLowRatedBooks() {
        Book book1 = new Book(1, "Louis Lowry", "The Giver", "Science fiction", 250);
        BookReview bookReview1 = new BookReview(book1);
        bookReview1.updateRating(9.0);

        Book book2 = new Book(2, "Deborah Ellis", "The Breadwinner", "Children's literature", 120);
        BookReview bookReview2 = new BookReview(book2);
        bookReview2.updateRating(5.0);

        Book book3 = new Book(3, "Dan Brown", "The Da Vinci Code", "Thriller", 310);
        BookReview bookReview3 = new BookReview(book3);
        bookReview3.updateRating(2.0);

        testBookReviewList.addBookReview(bookReview1);
        testBookReviewList.addBookReview(bookReview2);
        testBookReviewList.addBookReview(bookReview3);

        List<BookReview> returnedList = testBookReviewList.getLowRatedBooks();
        assertEquals(bookReview3, returnedList.get(0));
        assertFalse(returnedList.contains(bookReview1));
        assertFalse(returnedList.contains(bookReview2));
    }

    @Test
    void testGetFavoriteBook() {
        Book book1 = new Book(1, "Louis Lowry", "The Giver", "Science fiction", 250);
        BookReview bookReview1 = new BookReview(book1);
        bookReview1.updateRating(9.0);

        Book book2 = new Book(2, "S.E. Hinton", "The Outsiders", "Young adult fiction", 200);
        BookReview bookReview2 = new BookReview(book2);
        bookReview2.updateRating(9.0);

        Book book3 = new Book(3, "Vladimir Nabokov", "Lolita", "Erotic literature", 400);
        BookReview bookReview3 = new BookReview(book3);
        bookReview3.updateRating(6.0);

        testBookReviewList.addBookReview(bookReview1);
        testBookReviewList.addBookReview(bookReview2);
        testBookReviewList.addBookReview(bookReview3);

        Book returnedBook = testBookReviewList.getFavoriteBook();
        assertEquals(book1,returnedBook);
    }
}
