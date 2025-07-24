import java.util.*;

public class ItemsController {
    private List<Item> items = new ArrayList<>();

    public ItemsController() {
        // seed default 10 items
        addItem("HP Up",          "Vitamin",       "+10 HP EVs");
        addItem("Protein",        "Vitamin",       "+10 Attack EVs");
        addItem("Iron",           "Vitamin",       "+10 Defense EVs");
        addItem("Carbos",         "Vitamin",       "+10 Speed EVs");
        addItem("Rare Candy",     "Leveling Item", "+1 Level");
        addItem("Health Feather", "Feather",       "+1 HP EV");
        addItem("Muscle Feather", "Feather",       "+1 Attack EV");
        addItem("Resist Feather", "Feather",       "+1 Defense EV");
        addItem("Swift Feather",  "Feather",       "+1 Speed EV");
        addItem("Zinc",           "Vitamin",       "+10 Sp. Def EVs");
    }

    public void addItem(String name, String category, String effect) {
        items.add(new Item(name, category, "", effect, 0, 0));
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }

    public List<Item> searchItems(String keyword) {
        String key = keyword.toLowerCase();
        List<Item> results = new ArrayList<>();
        for (Item i : items) {
            if (i.getName().toLowerCase().contains(key)
             || i.getCategory().toLowerCase().contains(key)
             || i.getEffect().toLowerCase().contains(key)) {
                results.add(i);
            }
        }
        return results;
    }
}
