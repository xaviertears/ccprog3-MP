// src/model/Item.java
package model;

/**
 * An item that can be bought/sold and used on Pokémon.
 */
public class Item {
    private final String name;
    private final String category;
    private final String description;
    private final String effect;
    private final int buyPrice;
    private final int sellPrice;

    public Item(String name,
                String category,
                String description,
                String effect,
                int buyPrice,
                int sellPrice) {
        this.name        = name;
        this.category    = category;
        this.description = description;
        this.effect      = effect;
        this.buyPrice    = buyPrice;
        this.sellPrice   = sellPrice;
    }

    // getters...
    public String getName()        { return name; }
    public String getCategory()    { return category; }
    public String getDescription() { return description; }
    public String getEffect()      { return effect; }
    public int getBuyPrice()       { return buyPrice; }
    public int getSellPrice()      { return sellPrice; }

    @Override
    public String toString() {
        return String.format(
            "%-15s [%s] %s – %s  Buy:₽%d Sell:₽%d",
            name, category, description, effect, buyPrice, sellPrice
        );
    }
}
