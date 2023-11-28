# My Personal Project: A Personal Book Organizer

## An application that keeps track of my reading status and personal book preference
- Reading is a very personal experience: online reviews and ratings provide somewhat of a guideline for choosing new books to read, but an application that stores only my reviews and ratings on the books I've read and accordingly analyzes my book preferences will be helpful for a more satisfying reading experience. By keeping track of the books I have read/ am reading/ and would like to read along with trivial details of the books, I would obtain information that makes my reading experience enjoyable in the best way possible.

## *User Stories* list
- As a user, I would like to add a book's title, author, genre, and total page number to a progress tracker.
- As a user, I would like to check which page I have left off for a book I am reading.
- As a user, I would like to update the current page for a book I am reading.
- As a user, I would like to add a new book to my book wishlist.
- As a user, I would like to view the contents of my book wishlist.
- As a user, I would like to add a rating and short review for a book I finish reading.
- As a user, I would like to see a list of books that have been given high/low ratings.
- As a user, I would like to check my favourite book.
- As a user, I would like to have the option to save my BookmarkList to file.
- As a user, I would like to have the option to load my BookmarkList from file. 

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by 
- 1. Click the button labeled "Add New Book to Wish List" to enable text fields
- 2. Enter input for all the text fields e.g., Author: Agatha Christie, Title: And Then There Were None, Genre: mystery, Total Pages: 272
- 3. Click the button labeled "Add New Book to Wish List" again to display the inputted book in the scroll panel
- 4. Click the button labeled "Save Entries to File" to save entry
- 5. Repeat steps 1-4 as wished 


- You can generate the second required action related to the user story "adding multiple Xs to a Y" by
- 1. Click the button labeled "Display Books Over 200 Pages" to display books from the wish list that are over 200 pages in the scroll panel


- You can locate my visual component by
- 1. Looking at the button labeled "Add New Book to Wish List" at the top of the frame


- You can save the state of my application by
- 1. Click the button labeled "Save Entries to File" (note: this functionality can be checked by first adding a book, second clicking the save button, and lastly clicking the load button. The newly added book will appear in the scroll panel along with other books loaded from file)


- You can reload the state of my application by
- 1. Click the button labeled "Load Book Wish List from File". The contents will be displayed on the scroll panel


## *Ideal* Order
- 1. Load book wish list from file by clicking the button labeled "Load Book Wish List from File"
- 2. Add a few books
- 3. Save entries by clicking the button labeled "Save Entries to File"
- 4. Display books under 200 pages in the scroll panel by clicking the button labeled "Display Books Over 200 Pages"

# Phase 4: Task 2
Mon Nov 27 16:58:32 PST 2023
New book added to book wish list.

Mon Nov 27 16:59:06 PST 2023
New book added to book wish list.

Mon Nov 27 16:59:11 PST 2023
Books over 200 pages in the wish list displayed.

# Phase 4: Task 3
BookmarkList, BookReviewList, and BookWishList classes have common features, especially in that they all have methods related to adding an arbitrary number of 'Xs to Y' and returning their respective lists. In this sense, using an interface with method declarations relating to the shared behavior would improve the design.

JsonReaderBookmarkList, JsonReaderBookReviewList, and JsonReaderBookWishList classes also have common behavior in that they all read from JSON data stored in file. In addition, JsonWriterBookmarkList, JsonWriterBookReviewList, and JsonWriterBookWishList classes all represent a writer that writes JSON representation to file. For both of these cases, we can use an interface to improve readability and reusability.