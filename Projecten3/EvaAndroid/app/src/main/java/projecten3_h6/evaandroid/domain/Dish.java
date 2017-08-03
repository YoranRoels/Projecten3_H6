package projecten3_h6.evaandroid.domain;

import java.io.Serializable;
import java.util.List;

public class Dish implements Serializable{

    private static final long serialVersionUID = 3;
    private int imageId;
    private String name;
    private CookingTime cookingTime;
    private DifficultyType difficultyType;
    private DishType dishType;
    private List<Ingredient> ingredients;
    private String preparation;

    public Dish(int imageId, String name, CookingTime cookingTime, DifficultyType difficultyType, DishType dishType,
                List<Ingredient> ingredients, String preparation) {
        this.imageId = imageId;
        this.name = name;
        this.cookingTime = cookingTime;
        this.difficultyType = difficultyType;
        this.dishType = dishType;
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

    public DishType getDishType() {
        return dishType;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getPreparation() {
        return preparation;
    }
}
