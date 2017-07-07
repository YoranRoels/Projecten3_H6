package projecten3_h6.evaandroid.Domain;

import java.util.List;

/**
 * Created by Mafken on 4/07/2017.
 */

public class Dish {
    private int imageId;
    private String name;
    private CookingTime cookingTime;
    private String difficulty;
    private TypeDish type;
    private List<Ingredient> ingredients;
    private String preparation;

    public Dish(int imageId, String name, CookingTime cookingTime, String difficulty, TypeDish type,
                List<Ingredient> ingredients, String preparation) {
        this.imageId = imageId;
        this.name = name;
        this.cookingTime = cookingTime;
        this.difficulty = difficulty;
        this.type = type;
        this.ingredients = ingredients;
        this.preparation = preparation;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CookingTime getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(CookingTime cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public TypeDish getType() {
        return type;
    }

    public void setType(TypeDish type) {
        this.type = type;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }
}
