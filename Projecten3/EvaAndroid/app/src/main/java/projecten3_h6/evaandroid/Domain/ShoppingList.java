package projecten3_h6.evaandroid.Domain;

import java.util.List;

/**
 * Created by Mafken on 4/07/2017.
 */

public class ShoppingList {
    private List<Ingredient> ingredients;

    public ShoppingList(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
