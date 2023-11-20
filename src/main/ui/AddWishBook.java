package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWishBook extends JPanel {
    protected JTextField authorField;
    protected JTextField titleField;
    protected JTextField genreField;
    protected JTextField pageField;
    protected JTextArea textArea;

    private String author;
    private String title;
    private String genre;
    private String totalPages;
    private static final String newline = "\n";

    public AddWishBook() {
        super(new GridBagLayout());

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

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        JButton addButton = new JButton("Add New Book to Wish List");
        addButton.addActionListener(e -> addBookClicked());
        add(addButton, c);

        add(new JLabel("Author: "), c);
        add(authorField, c);

        add(new JLabel("Title: "), c);
        add(titleField, c);

        add(new JLabel("Genre: "), c);
        add(genreField, c);

        add(new JLabel("Total Pages: "), c);
        add(pageField, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
    }

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

        // Check if both author and title are entered
        if (!author.isEmpty() && !title.isEmpty() && !genre.isEmpty() && !totalPages.isEmpty()) {
            addToTextArea("Author: " + author + "\nTitle: " + title
                    + "\nGenre: " + genre + "\nTotal Pages: " + totalPages + "\n\n");

            // Clear input fields after adding to the text area
            authorField.setText("");
            titleField.setText("");
            genreField.setText("");
            pageField.setText("");
        }
    }

    private void addToTextArea(String text) {
        textArea.append(text);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Personal Book Organizer: My Book Wish List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AddWishBook addWishBookUI = new AddWishBook();
        frame.add(addWishBookUI);

        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddWishBook::createAndShowGUI);
    }

}
