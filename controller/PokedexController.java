// PokedexController.java
package controller;
import model.Pokemon;
import java.util.*;

public class PokedexController {
    private List<Pokemon> pokemons = new ArrayList<>();

    public PokedexController() {
        // seed default 10 Pokémon
        addPokemon(1, "Pikachu",   "Electric", null);
        addPokemon(2, "Snorlax",   "Normal",   null);
        addPokemon(3, "Mewtwo",    "Psychic",  null);
        addPokemon(4, "Charizard", "Fire",    "Flying");
        addPokemon(5, "Squirtle",  "Water",    null);
        addPokemon(6, "Eevee",     "Normal",   null);
        addPokemon(7, "Psyduck",   "Water",    null);
        addPokemon(8, "Gengar",    "Ghost",   "Poison");
        addPokemon(9, "Geodude",   "Rock",    "Ground");
        addPokemon(10, "Onix",      "Rock",    "Ground");
    }

    public void addPokemon(int number, String name, String type1, String type2) {
        pokemons.add(new Pokemon(number, name, type1, type2));
    }

    public List<Pokemon> getAllPokemon() {
        return new ArrayList<>(pokemons);
    }

    public List<Pokemon> searchPokemon(String keyword) {
        String key = keyword.toLowerCase();
        List<Pokemon> results = new ArrayList<>();
        for (Pokemon p : pokemons) {
            if (String.valueOf(p.getPokedexNumber()).contains(key)
             || p.getName().toLowerCase().contains(key)
             || p.getType1().toLowerCase().contains(key)
             || (p.getType2() != null && p.getType2().toLowerCase().contains(key))) {
                results.add(p);
            }
        }
        return results;
    }
}
