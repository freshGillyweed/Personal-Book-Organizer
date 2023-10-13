package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonalBookOrganizer {
    private BookmarkList myBookmarkList;
    private BookReviewList myBookReviewList;
    private BookWishList myBookWishList;
    private Scanner input;

    public PersonalBookOrganizer() {
        input = new Scanner(System.in);
        input.useDelimiter("\\n");
        myBookmarkList = new BookmarkList();
        myBookReviewList = new BookReviewList();
        myBookWishList = new BookWishList();
        runPBO();
    }

    private void runPBO() {
        boolean flag = true;
        int command;
        int keepGoing = 1;
        int bookID = 0;
        while (flag) {
            displayMenu();
            command = input.nextInt();
            input.nextLine();

            System.out.println("\n********************************************************");
            switchStatement(command, bookID);

            System.out.print("\nType 1 to continue. Type 0 to quit: ");
            keepGoing = input.nextInt();
            input.nextLine();
            if (keepGoing == 0) {
                flag = false;
            }
        }
    }

    private void switchStatement(int command, int bookID) {
        switch (command) {
            case 1:
                addToProgressTracker(bookID++);
                break;
            case 2:
                checkLastPage();
                break;
            case 3:
                updateLastPage();
                break;
            case 4:
                displayBookWishList();
                break;
            case 5:
                displayHighRateBooks();
                break;
            case 6:
                displayLowRateBooks();
                break;
            case 7:
                displayFavoriteBook();
        }
        System.out.println("\n********************************************************");
    }

    private void displayMenu() {
        System.out.println("\n********************************************************\n");
        System.out.println("1. Add a new book to progress tracker");
        System.out.println("2. Check where I last left off");
        System.out.println("3. Update where I last left off");
        System.out.println("4. View my book wishlist");
        System.out.println("5. View a list of books with high ratings");
        System.out.println("6. View a list of books with low ratings");
        System.out.println("7. Check my favorite book");
        System.out.print("\nChoose desired action : ");
    }

    private void addToProgressTracker(int bookID) {
        String author;
        String title;
        String genre;
        int totalPages;

        System.out.print("\nType in author: ");
        author = input.next();
        input.nextLine();
        System.out.print("Type in title: ");
        title = input.next();
        input.nextLine();
        System.out.print("Type in genre: ");
        genre = input.next();
        input.nextLine();
        System.out.print("Type in total number of pages: ");
        totalPages = input.nextInt();
        Book newBook = new Book(bookID, author,title,genre,totalPages);

        Bookmark newBookmark = new Bookmark(newBook);
        myBookmarkList.addBookmark(newBookmark);
    }

    private Bookmark findBookmark(String title) {
        List<Bookmark> returnedList = myBookmarkList.getBookmarkList();
        for (Bookmark bookmark:returnedList) {
            if ((bookmark.getBook()).getTitle().equals(title)) {
                return bookmark;
            }
        }
        return null;
    }

    private void checkLastPage() {
        String title;
        System.out.print("\nType in the title of the book: ");
        title = input.next();
        input.nextLine();

        Bookmark targetBookmark = findBookmark(title);
        assert targetBookmark != null;
        System.out.println("Start reading from pg. " + targetBookmark.getCurrentPage());
    }

    private void updateLastPage() {
        String title;
        int newLastPage;
        System.out.print("Type in the title of the book you would like to update bookmark: ");
        title = input.next();
        input.nextLine();
        System.out.print("Type in current page: ");
        newLastPage = input.nextInt();
        input.nextLine();

        Bookmark targetBookmark = findBookmark(title);
        assert targetBookmark != null;

        Book targetBook = targetBookmark.getBook();

        if (newLastPage == (targetBook.getTotalPages())) {
            addFinishedBook(targetBook);
            return;
        }
        targetBookmark.updatePage(newLastPage);
    }

    private void addFinishedBook(Book book) {
        BookReview newBookReview = new BookReview(book);
        double myRating;
        String myReview;
        System.out.print("You have finished reading \"" + book.getTitle() + "\" by " + book.getAuthor());
        System.out.print("\nAdd your rating: ");
        myRating = input.nextDouble();
        System.out.print("Add your review: ");
        myReview = input.next();
        input.nextLine();
        newBookReview.updateRating(myRating);
        newBookReview.updateReview(myReview);

        myBookReviewList.addBookReview(newBookReview);
    }

    private void displayBookWishList() {
        List<Book> bookWishList = myBookWishList.getBookWishList();
        for (Book book:bookWishList) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        }
    }

    private void displayHighRateBooks() {
        List<BookReview> bookReviewList = myBookReviewList.getHighRatedBooks();
        Book reviewedBook;
        System.out.println("Books with high ratings: \n\n");
        for (BookReview bookReview:bookReviewList) {
            reviewedBook = bookReview.getBook();
            System.out.println(reviewedBook.getTitle() + " by " + reviewedBook.getAuthor());
            System.out.println("Rating: " + bookReview.getRating());
            System.out.println("Review: " + bookReview.getReview() + "\n");
        }
    }

    private void displayLowRateBooks() {
        List<BookReview> bookReviewList = myBookReviewList.getLowRatedBooks();
        Book reviewedBook;
        System.out.println("Books with low ratings: ");
        System.out.println("***************************************\n");
        for (BookReview bookReview:bookReviewList) {
            reviewedBook = bookReview.getBook();
            System.out.println(reviewedBook.getTitle() + " by " + reviewedBook.getAuthor());
            System.out.println("Rating: " + bookReview.getRating());
            System.out.println("Review: " + bookReview.getReview() + "\n");
        }
    }

    private void displayFavoriteBook() {
        Book favoriteBook = myBookReviewList.getFavoriteBook();
        System.out.println("Your favorite book: " + favoriteBook.getTitle() + " by " + favoriteBook.getAuthor());
    }
}
