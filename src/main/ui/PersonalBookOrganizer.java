package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Personal Book Organizer app
public class PersonalBookOrganizer extends JPanel {

    private static final String JSON_STORE_BML = "./data/bookmarklist.json";
    private static final String JSON_STORE_BWL = "./data/bookWishlist.json";
    private static final String JSON_STORE_BRL = "./data/bookReviewlist.json";
    private BookmarkList myBookmarkList;
    private BookReviewList myBookReviewList;
    private BookWishList myBookWishList;
    private Scanner input;
    private JsonWriterBookmarkList jsonWriterBookmarkList;
    private JsonReaderBookmarkList jsonReaderBookmarkList;
    private JsonWriterBookWishList jsonWriterBookWishList;
    private JsonReaderBookWishList jsonReaderBookWishList;
    private JsonReaderBookReviewList jsonReaderBookReviewList;
    private JsonWriterBookReviewList jsonWriterBookReviewList;
    private AddWishBook userInterface;


    // MODIFIES: this
    // EFFECTS: Instantiates objects and runs the PersonalBookOrganizer application
    public PersonalBookOrganizer() {
        input = new Scanner(System.in);
        input.useDelimiter("\\n");
        myBookmarkList = new BookmarkList();
        myBookReviewList = new BookReviewList();
        myBookWishList = new BookWishList();
        jsonWriterBookmarkList = new JsonWriterBookmarkList(JSON_STORE_BML);
        jsonReaderBookmarkList = new JsonReaderBookmarkList(JSON_STORE_BML);
        jsonWriterBookWishList = new JsonWriterBookWishList(JSON_STORE_BWL);
        jsonReaderBookWishList = new JsonReaderBookWishList(JSON_STORE_BWL);
        jsonWriterBookReviewList = new JsonWriterBookReviewList(JSON_STORE_BRL);
        jsonReaderBookReviewList = new JsonReaderBookReviewList(JSON_STORE_BRL);

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
        if (command == 1) {
            addToProgressTracker();
        } else if (command == 2) {
            checkLastPage();
        } else if (command == 3) {
            updateLastPage();
        } else if (command == 4) {
            addToBookWishList();
        } else if (command == 5) {
            displayBookWishList();
        } else if (command == 6) {
            displayBooksByRate();
        } else if (command == 7) {
            displayFavoriteBook();
        } else if (command == 8) {
            saveAll();
        } else if (command == 9) {
            loadAll();;
        } else if (command == 10) {
            displayBookmarkList();
        } else {
            displayBookReviewList();
        }
    }

    // EFFECTS: displays a menu of options to user
    private void displayMenu() {
        System.out.println("\n*********************************************************************************\n");
        System.out.println("1. Add a new book to bookmark list");
        System.out.println("2. Check where I last left off");
        System.out.println("3. Update where I last left off");
        System.out.println("4. Add a new book to wishlist");
        System.out.println("5. View my book wishlist");
        System.out.println("6. View a list of books with high/low ratings");
        System.out.println("7. Check my favorite book");
        System.out.println("8. Save bookmarks, book wish list, and book reviews to file");
        System.out.println("9. Load bookmarks, book wish list, and book reviews from file");
        System.out.println("10. View my bookmark list");
        System.out.println("11. View my book reviews");
        System.out.print("\nChoose desired action : ");
    }

    // EFFECTS: saves lists for bookmark, wishbooks, and bookreviews to file
    private void saveAll() {
        saveBookmarkList();
        saveBookWishList();
        saveBookReviewList();
    }

    // EFFECTS: loads lists for bookmark, wishbooks, and bookreviews from file
    private void loadAll() {
        loadBookmarkList();
        loadBookWishList();
        loadBookReviewList();
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
        System.out.println("\n");
        loadBookWishList();
        userInterface = new AddWishBook(myBookWishList);

        JButton loadButton = new JButton("Load Book Wish List from File");
        loadButton.addActionListener(e -> loadButtonClicked(userInterface));
        //add(loadButton, gridBagConstraints);
        userInterface.addToFrame(loadButton);

        JButton saveButton = new JButton("Save Entries to File");
        saveButton.addActionListener(e -> saveButtonClicked());
        userInterface.addToFrame(saveButton);
        //super.add(saveButton, gridBagConstraints);
        //userInterface.createAndShowGUI();

        JButton subsetDisplayButton = new JButton("Display Books Over 200 Pages");
        subsetDisplayButton.addActionListener(e -> subsetDisplayButtonClicked());
        userInterface.addToFrame(subsetDisplayButton);

        JFrame frame = new JFrame("Personal Book Organizer: My Book Wish List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(userInterface);
        frame.pack();
        frame.setVisible(true);



        //System.out.println("\nAdding a new book to wishlist...\n");
        //Book newBook = getNewBook();
        //myBookWishList.addBook(newBook);


        //myBookWishList.addBook(new Book(authorListener.getAuthor(),))
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
        myBookmarkList.removeBookmark((newBookReview.getBook()).getTitle());
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

    // EFFECTS: displays bookmark list
    private void saveBookmarkList() {
        try {
            jsonWriterBookmarkList.open();
            jsonWriterBookmarkList.write(myBookmarkList);
            jsonWriterBookmarkList.close();
            System.out.println("Saved bookmark list" + " to " + JSON_STORE_BML);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_BML);
        }
    }

    // EFFECTS: displays book wish list
    private void saveBookWishList() {
        try {
            jsonWriterBookWishList.open();
            jsonWriterBookWishList.write(myBookWishList);
            jsonWriterBookWishList.close();
            System.out.println("Saved book wishlist" + " to " + JSON_STORE_BWL);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_BWL);
        }
    }

    // EFFECTS: displays book review list
    private void saveBookReviewList() {
        try {
            jsonWriterBookReviewList.open();
            jsonWriterBookReviewList.write(myBookReviewList);
            jsonWriterBookReviewList.close();
            System.out.println("Saved book review list" + " to " + JSON_STORE_BRL);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_BRL);
        }
    }

    // EFFECTS: loads bookmark list
    private void loadBookmarkList() {
        try {
            myBookmarkList = jsonReaderBookmarkList.read();
            System.out.println("Loaded bookmark list" + " from " + JSON_STORE_BML);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_BML);
        }
    }

    // EFFECTS: loads book wish list
    private void loadBookWishList() {
        try {
            myBookWishList = jsonReaderBookWishList.read();
            System.out.println("\nLoaded book wishlist" + " from " + JSON_STORE_BWL);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_BWL);
        }
    }

    // EFFECTS: loads book review list
    private void loadBookReviewList() {
        try {
            myBookReviewList = jsonReaderBookReviewList.read();
            System.out.println("Loaded book review list" + " from " + JSON_STORE_BRL);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_BRL);
        }
    }

    // EFFECTS: displays bookmark list
    private void displayBookmarkList() {
        System.out.println("\nDisplaying bookmark list...\n");
        List<Bookmark> bookmarks = myBookmarkList.getBookmarkList();
        for (Bookmark bookmark : bookmarks) {
            Book book = bookmark.getBook();
            System.out.println("\nAuthor: " + book.getAuthor());
            System.out.println("Title: " + book.getTitle());
            System.out.println(bookmark.getCurrentPage() + " page(s) read out of " + book.getTotalPages() + " pages\n");
        }
    }

    // EFFECTS: displays book review list
    private void displayBookReviewList() {
        System.out.println("\nDisplaying book review list...\n");
        List<BookReview> bookReviews = myBookReviewList.getBookReviewList();
        for (BookReview review: bookReviews) {
            Book book = review.getBook();
            System.out.println("\nAuthor: " + book.getAuthor());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Short review: " + review.getReview() + "\n");
        }
    }

    // EFFECTS: loads contents of myBookWishList from file and displays them on the textArea
    private void loadButtonClicked(AddWishBook userInterface) {
        loadBookWishList();

        for (Book book: myBookWishList.getBookWishList()) {
            userInterface.addToTextArea("***Loaded from File***\n");
            userInterface.addToTextArea("Author: " + book.getAuthor() + "\nTitle: " + book.getTitle()
                    + "\nGenre: " + book.getGenre() + "\nTotal Pages: " + book.getTotalPages() + "\n\n");
        }
        System.out.println("\n");
    }

    // EFFECTS; saves book wish list that includes books newly added using GUI
    private void saveButtonClicked() {
        myBookWishList = userInterface.getBwl();
        saveBookWishList();
    }

    private void subsetDisplayButtonClicked() {
        for (Book book: myBookWishList.getBookWishList()) {
            if (book.getTotalPages() > 200) {
                userInterface.addToTextArea("***Over 200 Pages***\n");
                userInterface.addToTextArea("Author: " + book.getAuthor() + "\nTitle: " + book.getTitle()
                        + "\nGenre: " + book.getGenre() + "\n\n");
            }
        }
    }
}