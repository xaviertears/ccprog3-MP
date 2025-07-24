// MainApp.java
import javax.swing.*;
import java.awt.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().init());
    }

    private void init() {
        // Initialize controllers
        PokedexController pokedexController = new PokedexController();
        MovesController movesController     = new MovesController();
        ItemsController itemsController     = new ItemsController();
        TrainersController trainersController = new TrainersController();

        // Initialize panels
        PokedexPanel pokedexPanel     = new PokedexPanel(pokedexController);
        MovesPanel movesPanel         = new MovesPanel(movesController);
        ItemsPanel itemsPanel         = new ItemsPanel(itemsController);
        TrainersPanel trainersPanel   = new TrainersPanel(trainersController);

        // Create main frame
        JFrame frame = new JFrame("Enhanced Pokédex");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Pokédex", pokedexPanel);
        tabbedPane.addTab("Moves", movesPanel);
        tabbedPane.addTab("Items", itemsPanel);
        tabbedPane.addTab("Trainers", trainersPanel);
        frame.add(tabbedPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

// PokedexController.java
import java.util.*;
public class PokedexController {
    private List<Pokemon> pokemons = new ArrayList<>();

    public void addPokemon(int number, String name, String type1, String type2) {
        pokemons.add(new Pokemon(number, name, type1, type2));
    }

    public List<Pokemon> getAllPokemon() {
        return new ArrayList<>(pokemons);
    }

    public List<Pokemon> searchPokemon(String keyword) {
        String key = keyword.toLowerCase();
        List<Pokemon> results = new ArrayList<>();
        for (Pokemon p : pokemons) {
            if (String.valueOf(p.getPokedexNumber()).contains(key)
             || p.getName().toLowerCase().contains(key)
             || p.getType1().toLowerCase().contains(key)
             || (p.getType2() != null && p.getType2().toLowerCase().contains(key))) {
                results.add(p);
            }
        }
        return results;
    }
}

// PokedexPanel.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class PokedexPanel extends JPanel {
    private PokedexController controller;
    private DefaultTableModel tableModel;
    public PokedexPanel(PokedexController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(5,5));
        // Form
        JPanel form = new JPanel(new GridLayout(2,4,5,5));
        JTextField noField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField t1Field = new JTextField();
        JTextField t2Field = new JTextField();
        form.add(new JLabel("No:")); form.add(noField);
        form.add(new JLabel("Name:")); form.add(nameField);
        form.add(new JLabel("Type1:")); form.add(t1Field);
        form.add(new JLabel("Type2:")); form.add(t2Field);
        add(form, BorderLayout.NORTH);
        // Table
        String[] cols = {"No","Name","Type1","Type2"};
        tableModel = new DefaultTableModel(cols, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        // Buttons
        JPanel btns = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton searchBtn = new JButton("Search");
        btns.add(addBtn); btns.add(searchBtn);
        add(btns, BorderLayout.SOUTH);
        // Actions
        addBtn.addActionListener(e -> {
            int no = Integer.parseInt(noField.getText());
            controller.addPokemon(no, nameField.getText(), t1Field.getText(), t2Field.getText());
            refreshTable(controller.getAllPokemon());
        });
        searchBtn.addActionListener(e -> {
            String key = nameField.getText();
            refreshTable(controller.searchPokemon(key));
        });
    }
    private void refreshTable(java.util.List<Pokemon> list) {
        tableModel.setRowCount(0);
        for (Pokemon p : list) {
            tableModel.addRow(new Object[]{p.getPokedexNumber(), p.getName(), p.getType1(), p.getType2()});
        }
    }
}

// MovesController.java
import java.util.*;
public class MovesController {
    private List<Move> moves = new ArrayList<>();
    public void addMove(String name, String classification, String type) {
        moves.add(new Move(name, "", classification, type, null));
    }
    public List<Move> getAllMoves() {
        return new ArrayList<>(moves);
    }
    public List<Move> searchMoves(String key) {
        String k = key.toLowerCase();
        List<Move> res = new ArrayList<>();
        for (Move m : moves) {
            if (m.getName().toLowerCase().contains(k) || m.getClassification().toLowerCase().contains(k) || m.getType1().toLowerCase().contains(k)) res.add(m);
        }
        return res;
    }
}

// MovesPanel.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class MovesPanel extends JPanel {
    private MovesController controller;
    private DefaultTableModel tableModel;
    public MovesPanel(MovesController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(5,5));
        JPanel form = new JPanel(new GridLayout(2,3,5,5));
        JTextField nameField = new JTextField();
        JTextField classField = new JTextField();
        JTextField typeField = new JTextField();
        form.add(new JLabel("Name:")); form.add(nameField);
        form.add(new JLabel("Class:")); form.add(classField);
        form.add(new JLabel("Type:")); form.add(typeField);
        add(form, BorderLayout.NORTH);
        String[] cols={"Name","Class","Type"};
        tableModel = new DefaultTableModel(cols,0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel btns=new JPanel();
        JButton addBtn=new JButton("Add");
        JButton searchBtn=new JButton("Search");
        btns.add(addBtn); btns.add(searchBtn);
        add(btns, BorderLayout.SOUTH);
        addBtn.addActionListener(e->{
            controller.addMove(nameField.getText(), classField.getText(), typeField.getText());
            refreshTable(controller.getAllMoves());
        });
        searchBtn.addActionListener(e-> refreshTable(controller.searchMoves(nameField.getText())));
    }
    private void refreshTable(java.util.List<Move> list){
        tableModel.setRowCount(0);
        for(Move m: list) tableModel.addRow(new Object[]{m.getName(), m.getClassification(), m.getType1()});
    }
}

// ItemsController.java
import java.util.*;
public class ItemsController {
    private List<Item> items = new ArrayList<>();
    public void addItem(String name, String category, String effect){
        items.add(new Item(name, category, "", effect, 0,0));
    }
    public List<Item> getAllItems(){ return new ArrayList<>(items);}    
    public List<Item> searchItems(String key){
        String k=key.toLowerCase(); List<Item> r=new ArrayList<>();
        for(Item i:items) if(i.getName().toLowerCase().contains(k)||i.getCategory().toLowerCase().contains(k)) r.add(i);
        return r;
    }
}

// ItemsPanel.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class ItemsPanel extends JPanel {
    private ItemsController controller;
    private DefaultTableModel tableModel;
    public ItemsPanel(ItemsController controller) {
        this.controller=controller; setLayout(new BorderLayout(5,5));
        JPanel form=new JPanel(new GridLayout(2,3,5,5));
        JTextField nameField=new JTextField(); JTextField catField=new JTextField(); JTextField effField=new JTextField();
        form.add(new JLabel("Name:")); form.add(nameField);
        form.add(new JLabel("Category:")); form.add(catField);
        form.add(new JLabel("Effect:")); form.add(effField);
        add(form,BorderLayout.NORTH);
        String[] cols={"Name","Category","Effect"};
        tableModel=new DefaultTableModel(cols,0);
        JTable table=new JTable(tableModel);
        add(new JScrollPane(table),BorderLayout.CENTER);
        JPanel btns=new JPanel(); JButton addBtn=new JButton("Add"); JButton searchBtn=new JButton("Search");
        btns.add(addBtn); btns.add(searchBtn); add(btns,BorderLayout.SOUTH);
        addBtn.addActionListener(e->{controller.addItem(nameField.getText(), catField.getText(), effField.getText()); refreshTable(controller.getAllItems());});
        searchBtn.addActionListener(e->refreshTable(controller.searchItems(nameField.getText())));
    }
    private void refreshTable(java.util.List<Item> list){tableModel.setRowCount(0);for(Item i:list)tableModel.addRow(new Object[]{i.getName(),i.getCategory(),i.getEffect()});}
}

// TrainersController.java
import java.util.*;
public class TrainersController{
    private List<Trainer> trainers=new ArrayList<>();
    public void addTrainer(int id,String name,double money){trainers.add(new Trainer(id,name,money));}
    public List<Trainer> getAllTrainers(){return new ArrayList<>(trainers);}    
    public List<Trainer> searchTrainers(String key){String k=key.toLowerCase();List<Trainer>r=new ArrayList<>();for(Trainer t:trainers)if(String.valueOf(t.getId()).contains(k)||t.getName().toLowerCase().contains(k))r.add(t);return r;}
}

// TrainersPanel.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class TrainersPanel extends JPanel{
    private TrainersController controller;
    private DefaultTableModel tableModel;
    public TrainersPanel(TrainersController controller){this.controller=controller;setLayout(new BorderLayout(5,5));
        JPanel form=new JPanel(new GridLayout(2,3,5,5));
        JTextField idField=new JTextField(); JTextField nameField=new JTextField(); JTextField moneyField=new JTextField();
        form.add(new JLabel("ID:")); form.add(idField);
        form.add(new JLabel("Name:")); form.add(nameField);
        form.add(new JLabel("Money:")); form.add(moneyField);
        add(form,BorderLayout.NORTH);
        String[] cols={"ID","Name","Money"}; tableModel=new DefaultTableModel(cols,0);
        JTable table=new JTable(tableModel); add(new JScrollPane(table),BorderLayout.CENTER);
        JPanel btns=new JPanel(); JButton addBtn=new JButton("Add"); JButton searchBtn=new JButton("Search"); btns.add(addBtn); btns.add(searchBtn); add(btns,BorderLayout.SOUTH);
        addBtn.addActionListener(e->{int id=Integer.parseInt(idField.getText());double m=Double.parseDouble(moneyField.getText());controller.addTrainer(id,nameField.getText(),m);refreshTable(controller.getAllTrainers());});
        searchBtn.addActionListener(e->refreshTable(controller.searchTrainers(nameField.getText())));
    }
    private void refreshTable(java.util.List<Trainer>list){tableModel.setRowCount(0);for(Trainer t:list)tableModel.addRow(new Object[]{t.getId(),t.getName(),t.getMoney()});}
}
