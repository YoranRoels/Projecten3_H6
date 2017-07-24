package projecten3_h6.evaandroid.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mafken on 4/07/2017.
 */
public class ShoppingList implements Serializable{

    private static final long serialVersionUID = 6;
    private List<Ingredient> ingredients = new ArrayList<>();

    public ShoppingList(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ShoppingList() {}

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

}
