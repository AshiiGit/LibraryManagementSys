import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class LibraryGui {
    private JTextArea readingTextArea;
    private JScrollPane readingScrollPane;
    private JFrame readingFrame;
    private JPanel rootPanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private Library library;
    private int highlightedRow = -1;
    private boolean isRowHighlighted = false;


    public LibraryGui()
    {


        String[] columnNames = {"Name", "Author", "Publication Year", "Read Item"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel)
        {

            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 3) {
                    return Boolean.class;
                }
                return super.getColumnClass(column);
            }
        };
        //table.setRowSelectionAllowed(true);
        //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        library = new Library();

        loadTableDataFromFile("D:\\LibraryManagmentSystem\\src\\books.txt");

       table.getColumn("Read Item").setCellRenderer(new ButtonRenderer());
        table.getColumn("Read Item").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.setDefaultRenderer(Object.class, new HoverCellRenderer());


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    table.setSelectionBackground(Color.CYAN);
                    table.setRowSelectionInterval(row, row);
                    highlightedRow = row;
                    isRowHighlighted = true;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                table.clearSelection();
                isRowHighlighted = false;
            }
        });

        JButton addItemButton = new JButton("Add Item");
        JButton editItemButton = new JButton("Edit Item");
        JButton deleteItemButton = new JButton("Delete Item");
        JButton popularityCountButton = new JButton("Popularity Count");




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
                    library.addBook(new Book(title, author, year,0));
                    writeBookToFile("D:\\LibraryManagmentSystem\\src\\books.txt", title, author, year);

                }
            }
            private void writeBookToFile(String filename, String title, String author, int year)
            {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true)))
                    {
                        writer.write("1" + "," + title + "," + author + "," + year + "," + 0);
                        writer.newLine();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();

                    }
            }
        });

        editItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel inputPanel = new JPanel(new GridLayout(4, 2));
                inputPanel.add(new JLabel("Current Title:"));
                JTextField currentTitleField = new JTextField();
                inputPanel.add(currentTitleField);

                inputPanel.add(new JLabel("New Title:"));
                JTextField newTitleField = new JTextField();
                inputPanel.add(newTitleField);

                inputPanel.add(new JLabel("New Author:"));
                JTextField newAuthorField = new JTextField();
                inputPanel.add(newAuthorField);

                inputPanel.add(new JLabel("New Year:"));
                JTextField newYearField = new JTextField();
                inputPanel.add(newYearField);

                int result = JOptionPane.showConfirmDialog(rootPanel, inputPanel, "Edit Book",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {

                    String currentTitle = currentTitleField.getText();
                    String newTitle = newTitleField.getText();
                    String newAuthor = newAuthorField.getText();
                    int newYear = Integer.parseInt(newYearField.getText());

                    // Edit the book details in the table
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        String title = (String) tableModel.getValueAt(i, 0);
                        if (title.equals(currentTitle)) {
                            // Update the book details in the table
                            tableModel.setValueAt(newTitle, i, 0);
                            tableModel.setValueAt(newAuthor, i, 1);
                            tableModel.setValueAt(newYear, i, 2);

                            //System.out.println("going to edit in file");
                            editBookInFile(currentTitle, newTitle, newAuthor, newYear);
                            break;
                        }
                    }
                }
            }

            private void editBookInFile(String currentTitle, String newTitle, String newAuthor, int newYear)
            {
                String filename = "D:\\LibraryManagmentSystem\\src\\books.txt";
                File inputFile = new File(filename);
                File tempFile = new File("temp.txt");

                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("1" + "," + currentTitle))
                        {
                            line = "1" + "," + newTitle + "," + newAuthor + "," + newYear + "," + "0";
                        }
                        writer.write(line + System.lineSeparator());
                    }
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                if (inputFile.delete())
                {
                    if (!tempFile.renameTo(inputFile))
                    {
                        throw new RuntimeException("Failed to rename the temp file.");
                    }
                }
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

        public void deleteBookFromFile(String itemName) throws IOException
        {
        String filename = "D:\\LibraryManagmentSystem\\src\\books.txt";
        File inputFile = new File(filename);
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (line.contains("," + itemName + ","))
                {

                    continue;
                }
                writer.write(line + System.lineSeparator());
            }
            writer.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        if (inputFile.delete()) {
            if (!tempFile.renameTo(inputFile))
            {
                throw new RuntimeException("Failed to rename the temp file.");
            }
        }
        else
        {
            throw new RuntimeException("Failed to delete the original file.");
        }
    }
        });


        popularityCountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPopularityPieChart();
            }
        });

        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addItemButton);
        buttonPanel.add(editItemButton);
        buttonPanel.add(deleteItemButton);
        buttonPanel.add(popularityCountButton);

        rootPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    private JFreeChart createPopularityPieChart()
    {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (item libraryItem : library.getBooks())
        {
            dataset.setValue(libraryItem.getTitle(), libraryItem.getPopularityCount());
        }
        JFreeChart chart = ChartFactory.createPieChart("Popularity Count of Items", dataset);
        return chart;
    }
    private void displayPopularityPieChart()
    {
        JFreeChart chart = createPopularityPieChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Color.BLACK);//the version doesn't support this and i am too lazy
        // to make a new custom class rn :))))
        ApplicationFrame frame = new ApplicationFrame("Popularity Chart");
        frame.add(chartPanel);
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame); //helps to make the new window appear in center
        frame.setVisible(true);
    }

    private void loadTableDataFromFile(String filename)
    {
        bookloading loader = new bookloading();
        try {
            loader.loadbooks(filename, library);
            for (item book : library.getBooks())
            {
                tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getYear(), false});
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("LibraryGUI");
        frame.setContentPane(new LibraryGui().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 400); // Adjust the size as needed
        frame.setVisible(true);
    }
    private class ButtonRenderer extends DefaultTableCellRenderer
    {
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

    private class HoverCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected || (isRowHighlighted && row == highlightedRow)) {
                component.setBackground(table.getSelectionBackground());
            } else {
                component.setBackground(table.isRowSelected(row) ? table.getSelectionBackground() : table.getBackground());
            }

            return component;
        }
    }

    private class ButtonEditor extends DefaultCellEditor
    {
        private JButton button;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Read");
            button.setFocusPainted(false);
            button.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    readingFrame = new JFrame("Read Book");
                    readingFrame.setSize(800, 600);
                    readingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    readingFrame.setLayout(new BorderLayout());
                    readingTextArea = new JTextArea();
                    readingTextArea.setWrapStyleWord(true);
                    readingTextArea.setLineWrap(true);
                    readingTextArea.setEditable(false);
                    readingScrollPane = new JScrollPane(readingTextArea);
                    readingFrame.add(readingScrollPane, BorderLayout.CENTER);
                    int selectedRow = table.getEditingRow();
                    String bookTitle = (String) table.getValueAt(selectedRow, 0);
                    String bookContent = "Chapter 1: i don't know what i am doing  " + bookTitle;
                    readingTextArea.setText(bookContent);
                    readingFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            int choice = JOptionPane.showConfirmDialog(  readingFrame, "Do you want to exit reading the book?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                readingFrame.dispose();
                            }
                        }
                    });
                    readingFrame.setVisible(true);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return button;
        }
    }
}
