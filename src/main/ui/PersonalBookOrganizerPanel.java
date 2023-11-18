package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonalBookOrganizerPanel implements ActionListener {
    private JFrame frame;
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JPanel panel;
    private JComboBox displayOptions;

    public PersonalBookOrganizerPanel() {
        frame = new JFrame();
        button1 = new JButton("Book Wish List");
        button1.addActionListener(this);
        label1 = new JLabel("Categories");
        panel = new JPanel();
        label2 = new JLabel("Please select category!");
        String [] displayOptionsSting = {"Display All", "Display by Genre", "Display by Author"};
        displayOptions = new JComboBox(displayOptionsSting);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        panel.setLayout(new GridLayout(0,1));
        panel.add(label1);
        panel.add(button1);
        panel.add(label2);
        panel.add(displayOptions);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Personal Book Organizer");
        frame.pack();
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        label2.setText("Book Wish List selected");
        displayOptions.setSelectedItem(0);
        //displayOptions.addActionListener(this);
    }
}
