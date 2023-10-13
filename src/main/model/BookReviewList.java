package model;

import java.util.ArrayList;
import java.util.List;

public class BookReviewList {
    private List<BookReview> bookReviewList;

    public BookReviewList() {
        bookReviewList = new ArrayList<>();
    }

    //EFFECTS: A book review is added
    //      If a same book is reviewed more than once, remove the existing (old) review
    //      and add the most recent(new) one to the list
    //      Otherwise add a new book review for a book that wasn't reviewed before
    public void addBookReview(BookReview newBookReview) {
        for (BookReview reviewedBook:bookReviewList) {
            if ((reviewedBook.getBook()).getTitle().equals((newBookReview.getBook()).getTitle())) {
                bookReviewList.remove(reviewedBook);
                bookReviewList.add(newBookReview);
                return;
            }
        }
        bookReviewList.add(newBookReview);
    }

    // EFFECTS: A list of books with a rating of 6.0 or higher is returned
    public List<BookReview> getHighRatedBooks() {
        List<BookReview> highRatedBooks = new ArrayList<>();

        for (BookReview reviewedBook:bookReviewList) {
            if (reviewedBook.getRating() >= 6.0) {
                highRatedBooks.add(reviewedBook);
            }
        }
        return highRatedBooks;
    }

    //EFFECTS: A list of books with a rating lower than 5.0 is returned
    public List<BookReview> getLowRatedBooks() {
        List<BookReview> lowRatedBooks = new ArrayList<>();

        for (BookReview reviewedBook:bookReviewList) {
            if (reviewedBook.getRating() < 5.0) {
                lowRatedBooks.add(reviewedBook);
            }
        }
        return lowRatedBooks;
    }

    // EFFECTS: return the book with the highest rating
    // If several books share the highest rating, return the one that has been added to the list first
    public Book getFavoriteBook() {
        BookReview highestRated = bookReviewList.get(0);
        for (BookReview reviewedBook:bookReviewList) {
            if (reviewedBook.getRating() > highestRated.getRating()) {
                highestRated = reviewedBook;
            }
        }
        return highestRated.getBook();
    }

    //EFFECTS: a list of book reviews is returned
    public List<BookReview> getBookReviewList() {
        return this.bookReviewList;
    }
}
