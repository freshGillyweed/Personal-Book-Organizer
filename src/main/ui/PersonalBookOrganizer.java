package ui;

import model.*;

import java.util.List;
import java.util.Scanner;

// Personal Book Organizer app
public class PersonalBookOrganizer {
    private BookmarkList myBookmarkList;
    private BookReviewList myBookReviewList;
    private BookWishList myBookWishList;
    private Scanner input;

    // MODIFIES: this
    // EFFECTS: Instantiates objects and runs the PersonalBookOrganizer application
    public PersonalBookOrganizer() {
        input = new Scanner(System.in);
        input.useDelimiter("\\n");
        myBookmarkList = new BookmarkList();
        myBookReviewList = new BookReviewList();
        myBookWishList = new BookWishList();
        runPersonalBookOrganizer();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPersonalBookOrganizer() {
        boolean flag = true;
        int command;
        int keepGoing = 1;
        while (flag) {
            displayMenu();
            command = input.nextInt();
            input.nextLine();

            System.out.println("\n*********************************************************************************");
            processCommand(command);
            System.out.println("\n*********************************************************************************");

            System.out.print("\nType 1 to continue, 0 to quit: ");
            keepGoing = input.nextInt();
            input.nextLine();
            if (keepGoing == 0) {
                flag = false;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(int command) {
        switch (command) {
            case 1:
                addToProgressTracker();
                break;
            case 2:
                checkLastPage();
                break;
            case 3:
                updateLastPage();
                break;
            case 4:
                addToBookWishList();
                break;
            case 5:
                displayBookWishList();
                break;
            case 6:
                displayBooksByRate();
                break;
            case 7:
                displayFavoriteBook();
        }
    }

    // EFFECTS: displays a menu of options to user
    private void displayMenu() {
        System.out.println("\n*********************************************************************************\n");
        System.out.println("1. Add a new book to progress tracker");
        System.out.println("2. Check where I last left off");
        System.out.println("3. Update where I last left off");
        System.out.println("4. Add a new book to wishlist");
        System.out.println("5. View my book wishlist");
        System.out.println("6. View a list of books with high/low ratings");
        System.out.println("7. Check my favorite book");
        System.out.print("\nChoose desired action : ");
    }

    // EFFECTS: gets input from user to create a new Book object and returns it
    private Book getNewBook() {
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
        return new Book(author,title,genre,totalPages);
    }

    // MODIFIES: this
    // EFFECTS: constructs a new Bookmark object and appends it to myBookmarkList
    private void addToProgressTracker() {
        System.out.println("\nAdding a new book to progress tracker...\n");
        Book newBook = getNewBook();
        Bookmark newBookmark = new Bookmark(newBook);
        myBookmarkList.addBookmark(newBookmark);
    }

    // EFFECTS: finds the Bookmark object with the given title and returns it
    private Bookmark findBookmark(String title) {
        List<Bookmark> returnedList = myBookmarkList.getBookmarkList();
        for (Bookmark bookmark:returnedList) {
            if ((bookmark.getBook()).getTitle().equals(title)) {
                return bookmark;
            }
        }
        return null;
    }

    // EFFECTS: prints out the page number the user must start reading for a given book title
    private void checkLastPage() {
        String title;
        System.out.println("\nChecking where I last left off...\n");
        System.out.print("\nType in the title of the book: ");
        title = input.next();
        input.nextLine();

        Bookmark targetBookmark = findBookmark(title);
        assert targetBookmark != null;
        System.out.println("Start reading from pg. " + targetBookmark.getCurrentPage());
    }

    // MODIFIES: this
    // EFFECTS: updates the page a user has left off for a given book title
    //          if the user inputs the last page, prompt the user to add a review
    private void updateLastPage() {
        String title;
        int newLastPage;
        System.out.println("\nUpdating where I last left off...\n");
        System.out.print("\nType in the title of the book you would like to update bookmark: ");
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

    // MODIFIES: this
    // EFFECTS: gets a new Book object and adds it to myBookWishList
    private void addToBookWishList() {
        System.out.println("\nAdding a new book to wishlist...\n");
        Book newBook = getNewBook();
        myBookWishList.addBook(newBook);
    }

    // MODIFIES: this
    // EFFECTS: gets input from user to create a new BookReview object and adds it to myBookReviewList
    private void addFinishedBook(Book book) {
        BookReview newBookReview = new BookReview(book);
        double myRating;
        String myReview;
        System.out.println("\nYou have finished reading \"" + book.getTitle() + "\" by " + book.getAuthor() + "!");
        System.out.print("\nAdd your rating: ");
        myRating = input.nextDouble();
        System.out.print("Add your review: ");
        myReview = input.next();
        input.nextLine();
        newBookReview.updateRating(myRating);
        newBookReview.updateReview(myReview);

        myBookReviewList.addBookReview(newBookReview);
    }

    // EFFECTS: Displays a list of Book objects in BookWishList
    private void displayBookWishList() {
        System.out.println("\nDisplaying book wishlist...\n");
        List<Book> bookWishList = myBookWishList.getBookWishList();
        for (Book book:bookWishList) {
            System.out.println("\"" + book.getTitle() + "\" by " + book.getAuthor());
        }
    }

    // EFFECTS: Displays a list of Book objects either by high or low ratings
    private void displayBooksByRate() {
        System.out.println("\nDisplaying books by ratings...\n");
        System.out.print("Type 1 for books with high ratings, 2 for low ratings: ");
        int choice = input.nextInt();
        List<BookReview> bookReviewList;
        if (choice == 1) {
            bookReviewList = myBookReviewList.getHighRatedBooks();
            System.out.println("Books with high ratings: \n\n");
        } else {
            bookReviewList = myBookReviewList.getLowRatedBooks();
            System.out.println("Books with low ratings: \n\n");
        }

        Book reviewedBook;

        for (BookReview bookReview:bookReviewList) {
            reviewedBook = bookReview.getBook();
            System.out.println("\"" + reviewedBook.getTitle() + "\" by " + reviewedBook.getAuthor());
            System.out.println("Rating: " + bookReview.getRating());
            System.out.println("Review: " + bookReview.getReview() + "\n");
        }
    }

    // EFFECTS: displays the Book object with the highest rate
    private void displayFavoriteBook() {
        System.out.println("\nDisplaying favorite book...\n");
        Book favoriteBook = myBookReviewList.getFavoriteBook();
        System.out.println("Your favorite book: \"" + favoriteBook.getTitle() + "\" by " + favoriteBook.getAuthor());
    }
}