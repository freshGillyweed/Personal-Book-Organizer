package ui;


import model.Book;
import model.BookWishList;

import javax.swing.*;
import java.awt.*;

// represents components of  UI that pertains to book wish list
public class AddWishBook extends JPanel {
    protected JTextField authorField;
    protected JTextField titleField;
    protected JTextField genreField;
    protected JTextField pageField;
    protected JTextArea textArea;

    private BookWishList bwl;

    private GridBagConstraints gridBagConstraints;

    private String author;
    private String title;
    private String genre;
    private String totalPages;
    private static final String newline = "\n";

    public AddWishBook(BookWishList bwl) {
        super(new GridBagLayout());
        this.bwl = bwl;

        authorField = new JTextField(20);
        authorField.setEnabled(false);

        titleField = new JTextField(20);
        titleField.setEnabled(false);

        genreField = new JTextField(20);
        genreField.setEnabled(false);

        pageField = new JTextField(20);
        pageField.setEnabled(false);

        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        setUpGridBagConstraints1();

        JButton addButton = new JButton("Add New Book to Wish List");
        addButton.addActionListener(e -> addBookClicked());
        add(addButton, gridBagConstraints);

        //add(new JLabel("Author: "), gridBagConstraints);
        //add(authorField, gridBagConstraints);
        setUpLabel(authorField, "Author: ");
        setUpLabel(titleField, "Title: ");
        setUpLabel(genreField, "Genre: ");
        setUpLabel(pageField, "Total Pages: ");

        setUpGridBagConstraints2();

        add(scrollPane, gridBagConstraints);
    }

    private void setUpLabel(JTextField textField, String label) {
        add(new JLabel(label), gridBagConstraints);
        add(textField, gridBagConstraints);
    }

    private void setUpGridBagConstraints1() {
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    }

    private void setUpGridBagConstraints2() {
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
    }

    // MODIFIES: this
    // EFFECTS: enables the text fields when addBook button is clicked
    //          and stores the input to add a new book to the book wish list
    private void addBookClicked() {
        if (!authorField.isEnabled() || !titleField.isEnabled() || !genreField.isEnabled() || !pageField.isEnabled()) {
            authorField.setEnabled(true);
            titleField.setEnabled(true);
            genreField.setEnabled(true);
            pageField.setEnabled(true);
            return;
        }

        author = authorField.getText();
        title = titleField.getText();
        genre = genreField.getText();
        totalPages = pageField.getText();

        Book newBook;

        // Check if both author and title are entered
        if (!author.isEmpty() && !title.isEmpty() && !genre.isEmpty() && !totalPages.isEmpty()) {
            addToTextArea("***New Book added***\n");
            addToTextArea("Author: " + author + "\nTitle: " + title
                    + "\nGenre: " + genre + "\nTotal Pages: " + totalPages + "\n\n");

            newBook = new Book(author, title, genre, Integer.parseInt(totalPages));
            bwl.addBook(newBook);

            clearInputField();
        }
    }

    // MODIFIES: this
    // EFFECTS: clear input fields after adding to the text area
    public void clearInputField() {
        authorField.setText("");
        titleField.setText("");
        genreField.setText("");
        pageField.setText("");
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to the frame
    public void addToFrame(JButton button) {
        add(button, gridBagConstraints);
    }

    // MODIFIES: this
    // EFFECTS: adds text to the textArea
    public void addToTextArea(String text) {
        textArea.append(text);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    // EFFECTS: returns the book wish list that includes newly added books
    public BookWishList getBwl() {
        return bwl;
    }

    /*
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Personal Book Organizer: My Book Wish List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AddWishBook addWishBookUI = new AddWishBook();
        frame.add(addWishBookUI);

        frame.pack();
        frame.setVisible(true);
    }

     */

    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddWishBook::createAndShowGUI);
    }

     */

}
