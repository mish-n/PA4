package za.ac.up.cs.cos221;
import javax.swing.*;

public class GUI {
    // mock data
    static String[][] data = {};

    static String[] staffTableHeader = { "Staff ID", "First Name", "Last Name", "Address", "Address 2", "District", "City",
            "Postal code", "Phone", "Store", "Active" };

    static String[] clientTableHeader = { "Client ID" , "First Name", "Last Name", "Email", "Address", "Active"};

    static String[] filmTableHeader = { "Film ID" , "Title", "Description", "Year", "Rental Rate", "Special Features", "Language"};

//    static String[] invetoryTableHeader = { "Film ID" , "Title", "Description", "Year", "Rental Rate", "Special Features", "Language"};

    static Database db = new Database();

    public static void UI () {
        // Frame creation
        JFrame frame = new JFrame("Sakila Database");
        frame.setLayout(null);
        frame.setResizable(false);

        data = db.getStaffData();

        // Tabs object being defined
        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(0, 0, 1000, 1000);

        // panels

        // Staff tab panel
        JPanel staffPanel = new JPanel();
        JTable staffTable = new JTable(data, staffTableHeader);
        staffTable.setBounds(0, 0, 900, 300);

        JScrollPane sp = new JScrollPane(staffTable);
//        staffTable.
        // sp.setViewportView(staffTable);
        // staffPanel.add(new JLabel("Staff data"));
        staffPanel.add(sp);

        // create Films tab
        data = db.getFilmsData();
        JPanel filmsPanel = new JPanel();
        JTable filmsTable = new JTable(data, filmTableHeader);
        filmsTable.setBounds(0, 0, 900, 300);

        JScrollPane sp3 = new JScrollPane(filmsTable);
        filmsPanel.add(new JLabel("Number of films: "));
        filmsPanel.add(sp3);

        // create Inventory tab
        data = db.getFilmsData();
        JPanel inventoryPanel = new JPanel();
        JTable inventoryTable = new JTable(data, filmTableHeader);
        filmsTable.setBounds(0, 0, 900, 300);

        JScrollPane sp4 = new JScrollPane(inventoryTable);
        inventoryPanel.add(sp4);

        // Client Tab
        data = db.getClientsData();
        JPanel clientPanel = new JPanel();
        JTable clientTable = new JTable(data, clientTableHeader);
        clientTable.setBounds(0, 0, 900, 300);

        JScrollPane sp2 = new JScrollPane(clientTable);
        clientPanel.add(sp2);

        // add Tabs
        tp.add("Staff list", staffPanel);
        tp.add("Film list", filmsPanel);
        tp.add("Clients list", clientPanel);
        tp.add("Inventory list", inventoryPanel);

        // define frame
        frame.add(tp);
        frame.setSize(800, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
