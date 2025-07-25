// Move.java (model)
public class Move {
    private String name;
    private String description;
    private String classification;
    private String type1;
    private String type2;

    public Move(String name, String description, String classification, String type1, String type2) {
        this.name = name;
        this.description = description;
        this.classification = classification;
        this.type1 = type1;
        this.type2 = type2 != null && !type2.isEmpty() ? type2 : null;
    }
    public String getName()           { return name; }
    public String getDescription()    { return description; }
    public String getClassification(){ return classification; }
    public String getType1()          { return type1; }
    public String getType2()          { return type2; }
}
