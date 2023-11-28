package persistence;

import model.Book;
import model.BookWishList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads bookWishList from JSON data stored in file
public class JsonReaderBookWishList {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderBookWishList(String source) {
        this.source = source;
    }

    // EFFECTS: reads bookWishList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookWishList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookWishList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses bookWishList from JSON object and returns it
    private BookWishList parseBookWishList(JSONObject jsonObject) {
        BookWishList bwl = new BookWishList();
        addWishBooks(bwl, jsonObject);
        return bwl;
    }

    // MODIFIES: bml
    // EFFECTS: parses wish books from JSON object and adds them to bookWishlist
    private void addWishBooks(BookWishList bwl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Wish books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            this.addWishBook(bwl, nextBook);
        }
    }

    // MODIFIES: bml
    // EFFECTS: parses book from JSON object and adds it to bookWishlist
    private void addWishBook(BookWishList bwl, JSONObject jsonObject) {
        String title = jsonObject.getString("Title");
        String author = jsonObject.getString("Author");
        String genre = jsonObject.getString("Genre");
        int totalPages = jsonObject.getInt("Total pages");

        Book book = new Book(author, title, genre, totalPages);
        bwl.addBook(book);
    }
}
