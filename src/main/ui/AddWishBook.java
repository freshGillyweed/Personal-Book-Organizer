package ui;


import model.Book;
import model.BookWishList;
import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// represents components of  UI that pertains to book wish list
public class AddWishBook extends JPanel {
    protected JTextField authorField;
    protected JTextField titleField;
    protected JTextField genreField;
    protected JTextField pageField;
    protected JTextArea textArea;

    private BookWishList bwl;

    private GridBagConstraints gridBagConstraints;

    public AddWishBook(BookWishList bwl) {
        super(new GridBagLayout());
        this.bwl = bwl;

        authorField = new JTextField(20);
        titleField = new JTextField(20);
        genreField = new JTextField(20);
        pageField = new JTextField(20);

        textArea = new JTextArea(5, 20);

        setTextFieldsEnabled();

        JScrollPane scrollPane = new JScrollPane(textArea);

        setUpGridBagConstraints1();

        JButton addButton = new JButton("Add New Book to Wish List");
        //icon cited: <a target="_blank" href="https://icons8.com/icon/l6iocFkbmCrh/book">Book</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>
        ImageIcon bookIcon = new ImageIcon("images/bookIcon.png");
        addButton.setIcon(bookIcon);
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

    // MODIFIES: this
    // EFFECTS: disables the text fields
    public void setTextFieldsEnabled() {
        authorField.setEnabled(false);
        titleField.setEnabled(false);
        genreField.setEnabled(false);
        pageField.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: performs basic setups for labels
    private void setUpLabel(JTextField textField, String label) {
        add(new JLabel(label), gridBagConstraints);
        add(textField, gridBagConstraints);
    }

    // MODIFIES: this
    // EFFECTS: performs initial setups for grid bag constraints
    private void setUpGridBagConstraints1() {
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    }

    // MODIFIES: this
    // EFFECTS: performs posterior setups for grid bag constraints
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

        String author = authorField.getText();
        String title = titleField.getText();
        String genre = genreField.getText();
        String totalPages = pageField.getText();

        Book newBook;

        // Check if both author and title are entered
        if (!author.isEmpty() && !title.isEmpty() && !genre.isEmpty() && !totalPages.isEmpty()) {
            addToTextArea("***New Book added***\n");
            addToTextArea("Author: " + author + "\nTitle: " + title
                    + "\nGenre: " + genre + "\nTotal Pages: " + totalPages + "\n\n");

            newBook = new Book(author, title, genre, Integer.parseInt(totalPages));
            bwl.addBook(newBook, true);

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

    // EFFECTS: performs setup for frame and window operations
    public void setupFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog eventLog = EventLog.getInstance();
                for (Event next : eventLog) {
                    System.out.println("\n");
                    System.out.println(next.toString() + "\n");
                }
                frame.dispose();
                System.exit(0);
            }
        });
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }
}
