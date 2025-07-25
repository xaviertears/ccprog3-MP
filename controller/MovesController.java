package controller;
import model.Move;
import java.util.*;

public class MovesController {
    private List<Move> moves = new ArrayList<>();

    public MovesController() {
        // seed default 14 moves
        addMove("Thunderbolt",  "TM", "Electric");
        addMove("Quick Attack", "TM", "Normal");
        addMove("Flamethrower", "TM", "Fire");
        addMove("Air Slash",    "TM", "Flying");
        addMove("Psychic",      "TM", "Psychic");
        addMove("Shadow Ball",  "TM", "Ghost");
        addMove("Body Slam",    "TM", "Normal");
        addMove("Hyper Beam",   "TM", "Normal");
        addMove("Water Gun",    "TM", "Water");
        addMove("Tackle",       "TM", "Normal");
        addMove("Rock Throw",   "TM", "Rock");
        addMove("Rollout",      "TM", "Rock");
        addMove("Magnitude",    "TM", "Ground");
        addMove("Sandstorm",    "TM", "Rock");
    }

    public void addMove(String name, String classification, String type) {
        moves.add(new Move(name, "", classification, type, null));
    }

    public List<Move> getAllMoves() {
        return new ArrayList<>(moves);
    }

    public List<Move> searchMoves(String keyword) {
        String key = keyword.toLowerCase();
        List<Move> results = new ArrayList<>();
        for (Move m : moves) {
            if (m.getName().toLowerCase().contains(key)
             || m.getClassification().toLowerCase().contains(key)
             || m.getType1().toLowerCase().contains(key)) {
                results.add(m);
            }
        }
        return results;
    }
}
