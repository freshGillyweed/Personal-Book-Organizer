package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReaderBookReviewList {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderBookReviewList(String source) {
        this.source = source;
    }

    // EFFECTS: reads bookReviewList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookReviewList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookReviewList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses bookReviewList from JSON object and returns it
    private BookReviewList parseBookReviewList(JSONObject jsonObject) {
        BookReviewList brl = new BookReviewList();
        addBookReviews(brl, jsonObject);
        return brl;
    }

    // MODIFIES: bml
    // EFFECTS: parses bookReviews from JSON object and adds them to bookReviewlist
    private void addBookReviews(BookReviewList brl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Book reviews");
        for (Object json : jsonArray) {
            JSONObject nextBookReview = (JSONObject) json;
            addBookReview(brl, nextBookReview);
        }
    }

    // MODIFIES: brl
    // EFFECTS: parses bookReview from JSON object and adds it to bookReviewlist
    private void addBookReview(BookReviewList brl, JSONObject jsonObject) {
        String title = jsonObject.getString("Title");
        String author = jsonObject.getString("Author");
        String genre = jsonObject.getString("Genre");
        int totalPages = jsonObject.getInt("Total pages");

        Book book = new Book(author, title, genre, totalPages);
        BookReview bookReview = new BookReview(book);
        brl.addBookReview(bookReview);
    }
}
