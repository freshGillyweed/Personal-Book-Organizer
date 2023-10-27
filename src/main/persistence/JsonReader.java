package persistence;

import model.Book;
import model.Bookmark;
import model.BookmarkList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookmarkList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookmarkList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private BookmarkList parseBookmarkList(JSONObject jsonObject) {
        BookmarkList bml = new BookmarkList();
        addBookmarks(bml, jsonObject);
        return bml;
    }

    // MODIFIES: wr
    // EFFECTS: parses bookmarks from JSON object and adds them to bookmarklist
    private void addBookmarks(BookmarkList bml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Bookmarks");
        for (Object json : jsonArray) {
            JSONObject nextBookmark = (JSONObject) json;
            addBookmark(bml, nextBookmark);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses bookmark from JSON object and adds it to bookmarklist
    private void addBookmark(BookmarkList bml, JSONObject jsonObject) {
        String title = jsonObject.getString("Title");
        String author = jsonObject.getString("Author");
        String genre = jsonObject.getString("Genre");
        int currentPage = jsonObject.getInt("Current page");
        int totalPages = jsonObject.getInt("Total pages");

        Book book = new Book(author, title, genre, totalPages);
        Bookmark bookmark = new Bookmark(book);
        bml.addBookmark(bookmark);
    }
}
