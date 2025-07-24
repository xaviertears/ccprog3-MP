import java.util.*;

public class MovesController {
    private List<Move> moves = new ArrayList<>();

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
