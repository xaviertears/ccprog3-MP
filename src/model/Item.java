// Item.java (model)
public class Item {
    private String name;
    private String category;
    private String description;
    private String effect;
    private int buyPrice;
    private int sellPrice;

    public Item(String name, String category, String description, String effect, int buyPrice, int sellPrice) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.effect = effect;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }
    public String getName()        { return name; }
    public String getCategory()    { return category; }
    public String getDescription() { return description; }
    public String getEffect()      { return effect; }
    public int getBuyPrice()       { return buyPrice; }
    public int getSellPrice()      { return sellPrice; }
}
