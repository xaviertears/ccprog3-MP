// MainApp.java
import javax.swing.*;
import java.awt.*;

public class MainApp {
    private JFrame frame;
    private JTabbedPane tabbedPane;

    // Controllers
    private PokedexController pokedexController;
    private MovesController movesController;
    private ItemsController itemsController;
    private TrainersController trainersController;

    // Panels (View)
    private PokedexPanel pokedexPanel;
    private MovesPanel movesPanel;
    private ItemsPanel itemsPanel;
    private TrainersPanel trainersPanel;

    public MainApp() {
        // Initialize controllers
        pokedexController = new PokedexController();
        movesController   = new MovesController();
        itemsController   = new ItemsController();
        trainersController= new TrainersController();

        // Initialize panels and inject controllers
        pokedexPanel   = new PokedexPanel(pokedexController);
        movesPanel     = new MovesPanel(movesController);
        itemsPanel     = new ItemsPanel(itemsController);
        trainersPanel  = new TrainersPanel(trainersController);

        // Create main window
        frame = new JFrame("Enhanced Pokédex");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        // Tabbed pane setup
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Pokédex", pokedexPanel);
        tabbedPane.addTab("Moves", movesPanel);
        tabbedPane.addTab("Items", itemsPanel);
        tabbedPane.addTab("Trainers", trainersPanel);

        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }
}

// PokedexPanel.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PokedexPanel extends JPanel {
    private PokedexController controller;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField numberField, nameField, type1Field, type2Field;
    private JButton addButton, searchButton;

    public PokedexPanel(PokedexController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(5,5));

        // Form panel
        JPanel form = new JPanel(new GridLayout(2,4,5,5));
        form.add(new JLabel("No:"));      numberField  = new JTextField(); form.add(numberField);
        form.add(new JLabel("Name:"));    nameField    = new JTextField(); form.add(nameField);
        form.add(new JLabel("Type1:"));   type1Field   = new JTextField(); form.add(type1Field);
        form.add(new JLabel("Type2:"));   type2Field   = new JTextField(); form.add(type2Field);
        add(form, BorderLayout.NORTH);

        // Table
        String[] cols = {"No","Name","Type1","Type2"};
        tableModel = new DefaultTableModel(cols, 0);
        table      = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons
        JPanel buttons = new JPanel();
        addButton    = new JButton("Add");
        searchButton = new JButton("Search");
        buttons.add(addButton);
        buttons.add(searchButton);
        add(buttons, BorderLayout.SOUTH);

        // Action listeners
        addButton.addActionListener(e -> {
            // TODO: controller.addPokemon(...) and refresh tableModel
        });
        searchButton.addActionListener(e -> {
            // TODO: controller.searchPokemon(...) and update tableModel
        });
    }
}

// MovesPanel.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MovesPanel extends JPanel {
    private MovesController controller;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField nameField, classField, typeField;
    private JButton addButton, searchButton;

    public MovesPanel(MovesController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(5,5));

        JPanel form = new JPanel(new GridLayout(2,3,5,5));
        form.add(new JLabel("Name:"));          nameField  = new JTextField(); form.add(nameField);
        form.add(new JLabel("Class (HM/TM):"));classField = new JTextField(); form.add(classField);
        form.add(new JLabel("Type:"));          typeField  = new JTextField(); form.add(typeField);
        add(form, BorderLayout.NORTH);

        String[] cols = {"Name","Class","Type"};
        tableModel = new DefaultTableModel(cols, 0);
        table      = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        addButton    = new JButton("Add");
        searchButton = new JButton("Search");
        buttons.add(addButton);
        buttons.add(searchButton);
        add(buttons, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            // TODO: controller.addMove(...) and refresh tableModel
        });
        searchButton.addActionListener(e -> {
            // TODO: controller.searchMove(...) and update tableModel
        });
    }
}

// ItemsPanel.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ItemsPanel extends JPanel {
    private ItemsController controller;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField nameField, categoryField, effectField;
    private JButton addButton, searchButton;

    public ItemsPanel(ItemsController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(5,5));

        JPanel form = new JPanel(new GridLayout(2,3,5,5));
        form.add(new JLabel("Name:"));     nameField     = new JTextField(); form.add(nameField);
        form.add(new JLabel("Category:")); categoryField = new JTextField(); form.add(categoryField);
        form.add(new JLabel("Effect:"));   effectField   = new JTextField(); form.add(effectField);
        add(form, BorderLayout.NORTH);

        String[] cols = {"Name","Category","Effect"};
        tableModel = new DefaultTableModel(cols, 0);
        table      = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        addButton    = new JButton("Add");
        searchButton = new JButton("Search");
        buttons.add(addButton);
        buttons.add(searchButton);
        add(buttons, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            // TODO: controller.addItem(...) and refresh tableModel
        });
        searchButton.addActionListener(e -> {
            // TODO: controller.searchItem(...) and update tableModel
        });
    }
}

// TrainersPanel.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TrainersPanel extends JPanel {
    private TrainersController controller;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField idField, nameField, moneyField;
    private JButton addButton, searchButton;

    public TrainersPanel(TrainersController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(5,5));

        JPanel form = new JPanel(new GridLayout(2,3,5,5));
        form.add(new JLabel("ID:"));        idField    = new JTextField(); form.add(idField);
        form.add(new JLabel("Name:"));      nameField  = new JTextField(); form.add(nameField);
        form.add(new JLabel("Money:"));     moneyField = new JTextField(); form.add(moneyField);
        add(form, BorderLayout.NORTH);

        String[] cols = {"ID","Name","Money"};
        tableModel = new DefaultTableModel(cols, 0);
        table      = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        addButton    = new JButton("Add");
        searchButton = new JButton("Search");
        buttons.add(addButton);
        buttons.add(searchButton);
        add(buttons, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            // TODO: controller.addTrainer(...) and refresh tableModel
        });
        searchButton.addActionListener(e -> {
            // TODO: controller.searchTrainer(...) and update tableModel
        });
    }
}
