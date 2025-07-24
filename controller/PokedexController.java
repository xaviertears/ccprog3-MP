// PokedexController.java
import java.util.*;

public class PokedexController {
    private List<Pokemon> pokemons = new ArrayList<>();

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
