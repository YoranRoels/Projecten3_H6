package projecten3_h6.evaandroid.Network;

import java.util.List;
import projecten3_h6.evaandroid.Domain.Achievement;
import projecten3_h6.evaandroid.Domain.Dish;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Calls {

    @GET("achievements")
    Call<List<Achievement>> getAchievements();

    //@GET("achievements/{id}")
    //Call<Achievement> getAchievement(@Path("id") int id);

    @GET("dishes/three-random")
    Call<List<Dish>> getThreeRandomDishes();
}
