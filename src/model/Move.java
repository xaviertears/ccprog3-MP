// src/model/Move.java
package model;

/**
 * A Pokémon move with power/accuracy baked into its description.
 */
public class Move {
    private final String name;
    private final String description;
    private final String classification; // “HM” or “TM”
    private final String type1;
    private final String type2;

    public Move(String name,
                String description,
                String classification,
                String type1,
                String type2) {
        this.name           = name;
        this.description    = description;
        this.classification = classification;
        this.type1          = type1;
        this.type2          = (type2 != null && !type2.isBlank()) ? type2 : null;
    }

    // getters...
    public String getName()           { return name; }
    public String getDescription()    { return description; }
    public String getClassification() { return classification; }
    public String getType1()          { return type1; }
    public String getType2()          { return type2; }

    @Override
    public String toString() {
        return String.format(
            "%-15s %-3s [%s%s] %s",
            name,
            classification,
            type1,
            type2 != null ? "/" + type2 : "",
            description
        );
    }
}
