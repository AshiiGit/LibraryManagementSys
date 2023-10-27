import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;


public class LibraryGui {
    private JPanel rootPanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private Library library;

    public LibraryGui()
    {

        String[] columnNames = {"Name", "Author", "Publication Year", "Read Item"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 3) {
                    return Boolean.class;
                }
                return super.getColumnClass(column);
            }
        };
        library = new Library();

        loadTableDataFromFile("D:\\LibraryManagmentSystem\\src\\books.txt"); // Load data from a file

        table.getColumn("Read Item").setCellRenderer(new ButtonRenderer());
        table.getColumn("Read Item").setCellEditor(new ButtonEditor(new JCheckBox()));

        JButton addItemButton = new JButton("Add Item");
        JButton editItemButton = new JButton("Edit Item");
        JButton deleteItemButton = new JButton("Delete Item");

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JPanel inputPanel = new JPanel(new GridLayout(4, 2));

                inputPanel.add(new JLabel("Title:"));
                JTextField titleField = new JTextField();
                inputPanel.add(titleField);

                inputPanel.add(new JLabel("Author:"));
                JTextField authorField = new JTextField();
                inputPanel.add(authorField);

                inputPanel.add(new JLabel("Year:"));
                JTextField yearField = new JTextField();
                inputPanel.add(yearField);

                int result = JOptionPane.showConfirmDialog(rootPanel, inputPanel, "Add Book",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    // Get the book details from the input fields
                    String title = titleField.getText();
                    String author = authorField.getText();
                    int year = Integer.parseInt(yearField.getText());

                    // Add the book to the table
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    tableModel.addRow(new Object[]{title, author, year, false});
                    library.addBook(new Book(title, author, year,0,0));
                    writeBookToFile("D:\\LibraryManagmentSystem\\src\\books.txt", title, author, year);

                }
            }
            private void writeBookToFile(String filename, String title, String author, int year)
            {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                        writer.write("1" + "," + title + "," + author + "," + year);
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
            }



        });

        editItemButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });

        deleteItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a JPanel to hold the input field
                JPanel inputPanel = new JPanel(new GridLayout(2, 2));

                inputPanel.add(new JLabel("Item Name:"));
                JTextField itemNameField = new JTextField();
                inputPanel.add(itemNameField);

                int result = JOptionPane.showConfirmDialog(rootPanel, inputPanel, "Delete Book",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    // Get the item name to be deleted
                    String itemName = itemNameField.getText();

                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        String title = (String) tableModel.getValueAt(i, 0); // Assuming the item name is in the first column
                        if (title.equals(itemName)) {
                            tableModel.removeRow(i);
                            try {
                                deleteBookFromFile(itemName);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                            break;
                        }
                    }
                }
            }

        public void deleteBookFromFile(String itemName) throws IOException {
        String filename = "D:\\LibraryManagmentSystem\\src\\books.txt"; // Adjust to your file path
        File inputFile = new File(filename);
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("," + itemName + ",")) {
                    // Skip this line to effectively delete it from the file
                    continue;
                }
                writer.write(line + System.lineSeparator());
            }

            // Close the writer
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Replace the original file with the modified temp file
        if (inputFile.delete()) {
            if (!tempFile.renameTo(inputFile)) {
                throw new RuntimeException("Failed to rename the temp file.");
            }
        } else {
            throw new RuntimeException("Failed to delete the original file.");
        }
    }
        });




        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addItemButton);
        buttonPanel.add(editItemButton);
        buttonPanel.add(deleteItemButton);

        rootPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadTableDataFromFile(String filename) {
        bookloading loader = new bookloading();
        try {
            loader.loadbooks(filename, library); // Load data into the library
            for (item book : library.getBooks()) {
                // Add each book to the table model
                tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getYear(), false});
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LibraryGUI");
        frame.setContentPane(new LibraryGui().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 400); // Adjust the size as needed
        frame.setVisible(true);
    }

    private class ButtonRenderer extends DefaultTableCellRenderer {
        private final JButton button;

        public ButtonRenderer() {
            button = new JButton("Read");
            button.setFocusPainted(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return button;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Read");
            button.setFocusPainted(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Add your code to handle the "Read" button click here
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return button;
        }
    }
}
