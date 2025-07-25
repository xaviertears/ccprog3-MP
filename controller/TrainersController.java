// TrainersController.java
package controller;
import model.Trainer;
import java.util.*;

public class TrainersController {
    private List<Trainer> trainers = new ArrayList<>();

    public TrainersController() {
        // seed default trainer
        addTrainer(1, "Ash Ketchum", 1000000.0);
    }

    public void addTrainer(int id, String name, double money) {
        trainers.add(new Trainer(id, name, money));
    }

    public List<Trainer> getAllTrainers() {
        return new ArrayList<>(trainers);
    }

    public List<Trainer> searchTrainers(String keyword) {
        String key = keyword.toLowerCase();
        List<Trainer> results = new ArrayList<>();
        for (Trainer t : trainers) {
            if (String.valueOf(t.getId()).contains(key)
             || t.getName().toLowerCase().contains(key)) {
                results.add(t);
            }
        }
        return results;
    }
}
