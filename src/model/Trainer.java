// Trainer.java
public class Trainer {
    private int id;
    private String name;
    private double money;

    public Trainer(int id, String name, double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }
    public int getId()           { return id; }
    public String getName()      { return name; }
    public double getMoney()     { return money; }
}
