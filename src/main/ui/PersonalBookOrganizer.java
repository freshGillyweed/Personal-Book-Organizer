package ui;

import model.Book;
import model.BookReview;
import model.Bookmark;

import java.util.ArrayList;
import java.util.List;

public class PersonalBookOrganizer {
    private List<Bookmark> myProgressTracker;
    private List<BookReview> myFinishedBooks;
    private List<Book> bookWishList;

    public PersonalBookOrganizer() {
        myProgressTracker = new ArrayList<>();
        myFinishedBooks = new ArrayList<>();
        bookWishList = new ArrayList<>();
    }

    private void runPBO() {
        while (true) {
            System.out.println("Please choose desired action : ");

        }
    }
}
