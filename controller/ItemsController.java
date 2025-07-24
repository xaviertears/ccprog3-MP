import java.util.*;

public class ItemsController {
    private List<Item> items = new ArrayList<>();

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
