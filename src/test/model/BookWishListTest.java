package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookWishListTest {
    private BookWishList testBookWishList;

    @BeforeEach
    void runBefore() {
        testBookWishList = new BookWishList();
    }

    @Test
    void testConstructor() {
        assertEquals(0, (testBookWishList.getBookWishList()).size());
    }

    @Test
    void testAddBook() {
        Book book1 = new Book(1,"Delia Owens","Where the Crawdads Sing","Mystery",400);
        Book book2 = new Book(2,"Milan Kundera","The Unbearable Lightness of Being","Philosophical fiction",230);
        testBookWishList.addBook(book1);
        testBookWishList.addBook(book1);
        testBookWishList.addBook(book2);

        List<Book> returnedList = testBookWishList.getBookWishList();
        assertEquals(2,returnedList.size());
        assertEquals(book1,returnedList.get(0));
        assertEquals(book2,returnedList.get(1));
    }
}
