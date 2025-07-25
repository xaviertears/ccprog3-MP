// src/model/Pokemon.java
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A Pokémon with full MCO1 data:
 * – pokédex number, name, types
 * – base level, evolution info (from/to/level)
 * – base stats (HP, ATK, DEF, SPD)
 * – a list of up to 4 moves (auto‑learn “Tackle” & “Defend”)
 * – an optional held item
 */
public class Pokemon {
    private final int pokedexNumber;
    private final String name;
    private final String type1;
    private final String type2;
    private final int baseLevel;
    private final int evolvesFrom;
    private final int evolvesTo;
    private final int evolutionLevel;
    private int hp, attack, defense, speed;
    private List<Move> moves = new ArrayList<>();
    private Item heldItem;

    public Pokemon(int pokedexNumber,
                   String name,
                   String type1,
                   String type2,
                   int baseLevel,
                   int evolvesFrom,
                   int evolvesTo,
                   int evolutionLevel,
                   int hp,
                   int attack,
                   int defense,
                   int speed) {
        this.pokedexNumber  = pokedexNumber;
        this.name           = name;
        this.type1          = type1;
        this.type2          = (type2 != null && !type2.isBlank()) ? type2 : null;
        this.baseLevel      = baseLevel;
        this.evolvesFrom    = evolvesFrom;
        this.evolvesTo      = evolvesTo;
        this.evolutionLevel = evolutionLevel;
        this.hp             = hp;
        this.attack         = attack;
        this.defense        = defense;
        this.speed          = speed;

        // auto‑learn default moves
        this.moves.add(new Move("Tackle",
                "Basic physical attack",
                "TM",
                "Normal",
                null));
        this.moves.add(new Move("Defend",
                "Raise own defense by one stage",
                "TM",
                "Normal",
                null));
    }

    // getters ...
    public int getPokedexNumber() { return pokedexNumber; }
    public String getName()       { return name; }
    public String getType1()      { return type1; }
    public String getType2()      { return type2; }
    public int getBaseLevel()     { return baseLevel; }
    public int getEvolvesFrom()   { return evolvesFrom; }
    public int getEvolvesTo()     { return evolvesTo; }
    public int getEvolutionLevel(){ return evolutionLevel; }
    public int getHp()            { return hp; }
    public int getAttack()        { return attack; }
    public int getDefense()       { return defense; }
    public int getSpeed()         { return speed; }
    public List<Move> getMoves()  { return List.copyOf(moves); }
    public Item getHeldItem()     { return heldItem; }

    /** Teach a new move (up to 4, HM moves don’t count against the limit). */
    public boolean teachMove(Move m) {
        if (moves.size() >= 4 && !"HM".equalsIgnoreCase(m.getClassification()))
            return false;
        moves.add(m);
        return true;
    }

    /** Change the held item (drops the old one). */
    public void setHeldItem(Item item) {
        this.heldItem = item;
    }

    @Override
    public String toString() {
        return String.format(
            "#%-3d %-10s [%s%s] Lvl:%-2d EF:%-3d ET:%-3d EL:%-3d HP:%-3d ATK:%-3d DEF:%-3d SPD:%-3d %s",
            pokedexNumber,
            name,
            type1,
            type2 != null ? "/" + type2 : "",
            baseLevel,
            evolvesFrom,
            evolvesTo,
            evolutionLevel,
            hp, attack, defense, speed,
            heldItem != null ? "Held:" + heldItem.getName() : ""
        );
    }
}
