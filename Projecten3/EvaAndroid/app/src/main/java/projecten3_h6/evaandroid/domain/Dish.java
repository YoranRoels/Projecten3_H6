package projecten3_h6.evaandroid.domain;

import java.io.Serializable;
import java.util.List;

public class Dish implements Serializable{

    private static final long serialVersionUID = 3;
    private int imageId;
    private String name;
    private CookingTime cookingTime;
    private DifficultyType difficultyType;
    private DishType type;
    private List<Ingredient> ingredients;
    private String preparation;

    public Dish(int imageId, String name, CookingTime cookingTime, DifficultyType difficultyType, DishType type,
                List<Ingredient> ingredients, String preparation) {
        this.imageId = imageId;
        this.name = name;
        this.cookingTime = cookingTime;
        this.difficultyType = difficultyType;
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

    public DifficultyType getDifficultyType() {
        return difficultyType;
    }

    public DishType getType() {
        return type;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getPreparation() {
        return preparation;
    }
}
