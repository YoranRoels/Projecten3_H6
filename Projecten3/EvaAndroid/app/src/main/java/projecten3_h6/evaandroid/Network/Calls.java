package projecten3_h6.evaandroid.Network;

import java.util.List;

import projecten3_h6.evaandroid.Domain.Achievement;
import projecten3_h6.evaandroid.Domain.Dish;
import projecten3_h6.evaandroid.Domain.Ingredient;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jensleirens on 18/07/2017.
 */

public interface Calls {

    @GET("achievements")
    Call<List<Achievement>> getAchievements();

    @GET("achievements/{id}")
    Call<Achievement> getAchievement(@Path("id") int id);

    @GET("dishes/")
    Call<List<Dish>> getDishes();

    @GET("dishes/{name}")
    Call<Dish> getDish(@Path("name") String name);

    @GET("ingredients")
    Call<List<Ingredient>> getIngredients();

    @GET("ingredients/{name}")
    Call<Ingredient> getIngredient(@Path("name") String name);

    @GET("dishes/three-random")
    Call<List<Dish>> getThreeRandomDishes();

}
