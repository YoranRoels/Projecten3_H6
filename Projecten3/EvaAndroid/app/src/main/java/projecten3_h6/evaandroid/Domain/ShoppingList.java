package projecten3_h6.evaandroid.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mafken on 4/07/2017.
 */

public class ShoppingList {
    private List<Ingredient> ingredients = new ArrayList<>();

    public ShoppingList(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public ShoppingList() {}

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
