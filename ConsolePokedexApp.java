/**
 * Main Controller for the Enhanced Pokedex application.
 * Manages in-memory lists of Pokémon, Moves, and Items.
 * Presents a console menu, and delegates to module methods.
 **/

import java.util.*;

public class PokedexApp {

    /** Shared Scanner for reading user input from console. */
    static Scanner scanner = new Scanner(System.in);
    /** List storing all Pokémon in the Pokedex. */
    static List<Pokemon> pokemons = new ArrayList<>();
    /** List storing all available Moves. */
    static List<Move>    moves    = new ArrayList<>();
    /** List storing all available Items. */
    static List<Item>    items    = new ArrayList<>();

    /**
     * Application entry point. Initializes data and shows the main menu loop.
     */
    public static void main(String[] args) {
        initializeData();
        while (true) {
            System.out.println();
            System.out.println("****************************************");
            System.out.println("*          ENHANCED POKEDEX           *");
            System.out.println("****************************************");
            System.out.println("*  1) Pokemon Module                  *");
            System.out.println("*  2) Moves Module                    *");
            System.out.println("*  3) Items Module                    *");
            System.out.println("*  0) Exit                            *");
            System.out.println("****************************************");
            System.out.print(" Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> pokemonModule();
                case "2" -> movesModule();
                case "3" -> itemsModule();
                case "0" -> {
                    System.out.println("\nGoodbye!");
                    return;
                }
                default   -> System.out.println("\n✖ Invalid option.\n");
            }
        }
    }

    /**
     * Preloads sample Pokemon, Moves, and Items,
     * Assigns each Pokemon one held item.
     */
    static void initializeData() {
        // ── POKEMON ────────────────────────────────────────────────
        pokemons.add(new Pokemon(1,  "Pikachu",   "Electric", null,    5, 0, 0, 0, 45,  80,  50, 120));
        pokemons.add(new Pokemon(2,  "Snorlax",   "Normal",   null,    5, 0, 0, 0,160, 110,  65,  30));
        pokemons.add(new Pokemon(3,  "Mewtwo",    "Psychic",  null,    5, 0, 0, 0,106, 150,  70, 140));
        pokemons.add(new Pokemon(4,  "Charizard", "Fire",    "Flying", 5, 0, 0, 0, 78, 104,  78, 100));
        pokemons.add(new Pokemon(5,  "Squirtle",  "Water",    null,    5, 0, 0, 0, 44,  48,  65,  43));
        pokemons.add(new Pokemon(6,  "Eevee",     "Normal",   null,    5, 0, 0, 0, 65,  75,  70,  75));
        pokemons.add(new Pokemon(7,  "Psyduck",   "Water",    null,    5, 0, 0, 0, 50,  52,  48,  55));
        pokemons.add(new Pokemon(8,  "Gengar",    "Ghost",   "Poison", 5, 0, 0, 0, 60,  65,  80, 130));
        pokemons.add(new Pokemon(9,  "Geodude",   "Rock",    "Ground", 5, 0, 0, 0, 40,  80, 100,  20));
        pokemons.add(new Pokemon(10, "Onix",      "Rock",    "Ground", 5, 0, 0, 0, 35,  45, 160,  70));

        // ── MOVES ─────────────────────────────────────────────────
        moves.add(new Move("Thunderbolt",  "Power:90 Acc:100%",  "TM", "Electric", null));
        moves.add(new Move("Quick Attack", "Power:40 Acc:100%",  "TM", "Normal",   null));
        moves.add(new Move("Flamethrower", "Power:90 Acc:100%",  "TM", "Fire",     null));
        moves.add(new Move("Air Slash",    "Power:75 Acc:95%",   "TM", "Flying",   null));
        moves.add(new Move("Psychic",      "Power:90 Acc:100%",  "TM", "Psychic",  null));
        moves.add(new Move("Shadow Ball",  "Power:80 Acc:100%",  "TM", "Ghost",    null));
        moves.add(new Move("Body Slam",    "Power:85 Acc:100%",  "TM", "Normal",   null));
        moves.add(new Move("Hyper Beam",   "Power:150 Acc:90%",  "TM", "Normal",   null));
        moves.add(new Move("Water Gun",    "Power:40 Acc:100%",  "TM", "Water",    null));
        moves.add(new Move("Tackle",       "Power:40 Acc:100%",  "TM", "Normal",   null));
        moves.add(new Move("Rock Throw",   "Power:50 Acc:90%",   "TM", "Rock",     null));
        moves.add(new Move("Rollout",      "Power:30→x Acc:90%", "TM", "Rock",     null));
        moves.add(new Move("Magnitude",    "Power:10–150 Acc:100%","TM","Ground",  null));

        // ── ITEMS ─────────────────────────────────────────────────
        items.clear();
        items.add(new Item("HP Up",           "Vitamin",       "A nutritious drink for Pokémon.",           "+10 HP EVs",             10000, 5000));
        items.add(new Item("Protein",         "Vitamin",       "A nutritious drink for Pokémon.",           "+10 Attack EVs",         10000, 5000));
        items.add(new Item("Iron",            "Vitamin",       "A nutritious drink for Pokémon.",           "+10 Defense EVs",        10000, 5000));
        items.add(new Item("Carbos",          "Vitamin",       "A nutritious drink for Pokémon.",           "+10 Speed EVs",          10000, 5000));
        items.add(new Item("Rare Candy",      "Leveling Item", "A candy that increases level by 1.",        "Levels up by 1",         0, 2400));
        items.add(new Item("Health Feather",  "Feather",       "A feather that slightly increases HP.",     "+1 HP EV",               300,   150));
        items.add(new Item("Muscle Feather",  "Feather",       "A feather that slightly increases Attack.", "+1 Attack EV",           300,   150));
        items.add(new Item("Resist Feather",  "Feather",       "A feather that slightly increases Defense.","+1 Defense EV",          300,   150));
        items.add(new Item("Swift Feather",   "Feather",       "A feather that slightly increases Speed.",  "+1 Speed EV",            300,   150));
        items.add(new Item("Zinc",            "Vitamin",       "A nutritious drink for Pokémon.",           "+10 Special Defense EVs",10000, 5000));

        // Assign each Pokémon one held item
        for (int i = 0; i < pokemons.size() && i < items.size(); i++) {
            pokemons.get(i).setHeldItem(items.get(i));
        }
    }

    // ── POKEMON Module ────────────────────────────────────────────────

    /**
     * Console menu for adding, listing, and searching Pokémon.
     */
    static void pokemonModule() {
        while (true) {
            System.out.println("\n/********************************/");
            System.out.println("/*        POKEMON MODULE        */");
            System.out.println("/********************************/");
            System.out.println("/* 1) Add Pokémon               */");
            System.out.println("/* 2) List Pokémon              */");
            System.out.println("/* 3) Search Pokémon            */");
            System.out.println("/* 0) Back                      */");
            System.out.println("/********************************/");
            System.out.print(" Choice: ");
            String c = scanner.nextLine();

            switch (c) {
                case "1" -> {
                    try {
                        // 1) Pokedex Number
                        System.out.print("# : ");
                        int num = Integer.parseInt(scanner.nextLine());
                        if (pokemons.stream()
                                .anyMatch(p -> p.getPokedexNumber() == num)) {
                            System.out.println("✖ Error: Pokedex number already exists.");
                            break;  // go back to the module menu
                        }

                        // 2) Name
                        System.out.print("Name: ");
                        String nm = scanner.nextLine();
                        if (pokemons.stream()
                                .anyMatch(p -> p.getName().equalsIgnoreCase(nm))) {
                            System.out.println("✖ Error: Pokemon name already exists.");
                            break;
                        }

                        // Now that number & name are unique, continue collecting the rest of the attributes
                        System.out.print("Type1: ");
                        String t1 = scanner.nextLine();
                        System.out.print("Type2 (blank): ");
                        String t2 = scanner.nextLine();
                        System.out.print("Level: ");
                        int lvl = Integer.parseInt(scanner.nextLine());
                        System.out.print("From (#/0): ");
                        int f   = Integer.parseInt(scanner.nextLine());
                        System.out.print("To   (#/0): ");
                        int to  = Integer.parseInt(scanner.nextLine());
                        System.out.print("EvoLvl: ");
                        int el  = Integer.parseInt(scanner.nextLine());
                        System.out.print("HP: ");
                        int hp  = Integer.parseInt(scanner.nextLine());
                        System.out.print("Atk: ");
                        int at  = Integer.parseInt(scanner.nextLine());
                        System.out.print("Def: ");
                        int df  = Integer.parseInt(scanner.nextLine());
                        System.out.print("Spd: ");
                        int sp  = Integer.parseInt(scanner.nextLine());

                        // Finished adding a Pokemon
                        pokemons.add(new Pokemon(
                                num, nm,
                                t1, t2.isBlank() ? null : t2,
                                lvl, f, to, el,
                                hp, at, df, sp
                        ));
                        System.out.println("✔ Added!");

                    } catch (NumberFormatException ex) {
                        System.out.println("✖ Invalid number format.");
                    }
                }
                case "2" -> {
                    if (pokemons.isEmpty()) {
                        System.out.println("No Pokemon.");
                    } else {
                        // Header
                        System.out.printf(
                            "\n %-4s %-15s %-15s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-12s%-15s\n",
                                "No.", "Name", "Type1", "Type2", "Lvl", "EF", "ET", "EL",
                                "HP", "ATK", "DEF", "SPD", "Held"
                        );
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        // Data
                        for (Pokemon p : pokemons) {
                            System.out.println(p);
                        }
                    }

                }
                case "3" -> {
                    System.out.print("Search by #, name or type: ");
                    String kw = scanner.nextLine().trim().toLowerCase();

                    // collect matches
                    List<Pokemon> found = new ArrayList<>();
                    for (Pokemon p : pokemons) {
                        boolean match = false;
                        // 1) Pokedex #
                        if (kw.matches("\\d+") && p.getPokedexNumber() == Integer.parseInt(kw)) {
                            match = true;
                        }
                        // 2) Name
                        else if (p.getName().toLowerCase().contains(kw)) {
                            match = true;
                        }
                        // 3) Type1
                        else if (p.getType1().toLowerCase().contains(kw)) {
                            match = true;
                        }
                        // 4) Type2
                        else if (p.getType2() != null && p.getType2().toLowerCase().contains(kw)) {
                            match = true;
                        }
                        if (match) {
                            found.add(p);
                        }
                    }
                    if (found.isEmpty()) {
                        System.out.println("\n✖ No matching Pokemon found.");
                    } else {

                        System.out.printf(
                                "\n %-4s %-15s %-15s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-12s%-15s\n",
                                "No.", "Name", "Type1", "Type2",
                                "Lvl", "EF", "ET", "EL",
                                "HP", "ATK", "DEF", "SPD", "Held"
                        );
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                        for (Pokemon p : found) {
                            System.out.println(p);
                        }
                    }
                    System.out.println();
                }
                case "0" -> { return; }
                default   -> System.out.println("\n✖ Invalid option.\n");
            }
        }
    }

    // ── Moves Module ────────────────────────────────────────────────────

    /**
     * Console menu for adding, listing, and searching Moves.
     */
    static void movesModule() {
        while (true) {
            System.out.println();
            System.out.println("********************************");
            System.out.println("*         MOVES MODULE         *");
            System.out.println("********************************");
            System.out.println("*  1) Add Move                 *");
            System.out.println("*  2) List Moves               *");
            System.out.println("*  3) Search Moves             *");
            System.out.println("*  0) Back                     *");
            System.out.println("********************************");
            System.out.print(" Choice: ");
            String c = scanner.nextLine();

            switch (c) {
                case "1" -> {
                    System.out.print("Name: ");           String n  = scanner.nextLine();
                    System.out.print("Desc: ");           String d  = scanner.nextLine();
                    System.out.print("Class (HM/TM): ");  String cl = scanner.nextLine();
                    System.out.print("Type1: ");          String t1 = scanner.nextLine();
                    System.out.print("Type2 (blank): ");  String t2 = scanner.nextLine();
                    moves.add(new Move(n, d, cl, t1, t2.isBlank() ? null : t2));
                    System.out.println("✔ Move added");
                }

                case "2" -> {
                    if (moves.isEmpty()) {
                        System.out.println("No moves.");
                    } else {

                        System.out.printf(
                            "\n%-20s %-15s %-15s %-15s %s\n",
                                "Move Name", "Class", "Type1", "Type2", "Description"
                        );
                        System.out.println("----------------------------------------------------------------------------------------------");

                        for (Move m : moves) {
                            System.out.println(m);
                        }
                    }

                }
                case "3" -> {
                    System.out.print("Search by name, class (HM/TM), or type: ");
                    String kw = scanner.nextLine().trim().toLowerCase();

                    List<Move> found = new ArrayList<>();
                    for (Move m : moves) {
                        if (m.getName().toLowerCase().contains(kw)
                                || m.getClassification().toLowerCase().contains(kw)
                                || m.getType1().toLowerCase().contains(kw)
                                || (m.getType2() != null && m.getType2().toLowerCase().contains(kw))) {
                            found.add(m);
                        }
                    }

                    if (found.isEmpty()) {
                        System.out.println("\n✖ No matching moves found.");
                    } else {
                        System.out.printf(
                                "\n%-20s %-15s %-15s %-15s %s\n",
                                "Move Name", "Class", "Type1", "Type2", "Description"
                        );
                        System.out.println("----------------------------------------------------------------------------------------------");
                        // Rows
                        for (Move m : found) {
                            System.out.printf(
                                    "%-20s %-15s %-15s %-15s %s\n",
                                    m.getName(),
                                    m.getClassification(),
                                    m.getType1(),
                                    m.getType2() != null ? m.getType2() : "-",
                                    m.getDescription()
                            );
                        }
                    }
                    System.out.println();  // extra blank line before returning to menu
                }

                case "0" -> { return; }
                default   -> System.out.println("\n✖ Invalid option.\n");
            }
        }
    }

    // ── Items Module ────────────────────────────────────────────────────

    /**
     * Console menu for adding, listing, and searching Items.
     */
    static void itemsModule() {
        while (true) {
            System.out.println();
            System.out.println("********************************");
            System.out.println("*         ITEMS MODULE         *");
            System.out.println("********************************");
            System.out.println("*  1) Add Item                 *");
            System.out.println("*  2) List Items               *");
            System.out.println("*  3) Search Items             *");
            System.out.println("*  0) Back                     *");
            System.out.println("********************************");
            System.out.print(" Choice: ");
            String c = scanner.nextLine();

            switch (c) {
                case "1" -> {
                    System.out.print("Name: ");       String n  = scanner.nextLine();
                    System.out.print("Category: ");   String ca = scanner.nextLine();
                    System.out.print("Desc: ");       String d  = scanner.nextLine();
                    System.out.print("Effect: ");     String e  = scanner.nextLine();
                    System.out.print("Buy: ");        int b   = Integer.parseInt(scanner.nextLine());
                    System.out.print("Sell: ");       int s   = Integer.parseInt(scanner.nextLine());
                    items.add(new Item(n, ca, d, e, b, s));
                    System.out.println("✔ Item added");
                }

                case "2" -> {
                    if (items.isEmpty()) {
                        System.out.println("No items.");
                    } else {
                        System.out.printf(
                                "\n%-20s %-20s %-45s %-30s %8s %11s\n",
                                "Item Name", "Category", "Description", "Effect", "Buy", "Sell"
                        );
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
                        for (Item i : items) {
                            System.out.printf(
                                    "%-20s %-20s %-45s %-30s %10d%10d\n",
                                    i.getName(),
                                    i.getCategory(),
                                    i.getDescription(),
                                    i.getEffect(),
                                    i.getBuyPrice(),
                                    i.getSellPrice()
                            );
                        }
                    }


                }
                case "3" -> {
                    System.out.print("Search by name or keyword: ");
                    String kw = scanner.nextLine().trim().toLowerCase();

                    List<Item> found = new ArrayList<>();
                    for (Item i : items) {
                        if (i.getName().toLowerCase().contains(kw)
                                || i.getCategory().toLowerCase().contains(kw)
                                || i.getDescription().toLowerCase().contains(kw)
                                || i.getEffect().toLowerCase().contains(kw)) {
                            found.add(i);
                        }
                    }

                    if (found.isEmpty()) {
                        System.out.println("\n✖ No matching items found.");
                    } else {

                        System.out.printf(
                                "\n%-20s %-20s %-45s %-30s %8s %11s\n",
                                "Item Name", "Category", "Description", "Effect", "Buy", "Sell"
                        );
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
                        for (Item i : found) {
                            System.out.printf(
                                    "%-20s %-20s %-45s %-30s %10d%10d\n",
                                    i.getName(),
                                    i.getCategory(),
                                    i.getDescription(),
                                    i.getEffect(),
                                    i.getBuyPrice(),
                                    i.getSellPrice()
                            );
                        }
                    }
                    System.out.println();
                }

                case "0" -> {
                    // RETURNS from itemsModule() entirely,
                    // dropping you back into main()
                    return;
                }   default   -> System.out.println("\n✖ Invalid option.\n");
            }
            }
    }
}
