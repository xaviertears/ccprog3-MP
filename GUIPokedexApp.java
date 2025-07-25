// MainApp.java
import controller.PokedexController;
import controller.MovesController;
import controller.ItemsController;
import controller.TrainersController;

import view.PokedexPanel;
import view.MovesPanel;
import view.ItemsPanel;
import view.TrainersPanel;

import model.Pokemon;
import model.Move;
import model.Item;
import model.Trainer;


public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().init());
    }

    private void init() {
        // Controllers
        PokedexController pokedexController   = new PokedexController();
        MovesController movesController       = new MovesController();
        ItemsController itemsController       = new ItemsController();
        TrainersController trainersController = new TrainersController();

        // Panels
        PokedexPanel pokedexPanel     = new PokedexPanel(pokedexController);
        MovesPanel movesPanel         = new MovesPanel(movesController);
        ItemsPanel itemsPanel         = new ItemsPanel(itemsController);
        TrainersPanel trainersPanel   = new TrainersPanel(trainersController);

        // Main frame
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
