package projecten3_h6.evaandroid.network;

import java.util.List;
import projecten3_h6.evaandroid.domain.Achievement;
import projecten3_h6.evaandroid.domain.Challenge;
import projecten3_h6.evaandroid.domain.Dish;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Calls {

    @GET("achievements")
    Call<List<Achievement>> getAchievements();

    //@GET("achievements/{id}")
    //Call<Achievement> getAchievement(@Path("id") int id);

    @GET("dishes/three-random")
    Call<List<Dish>> getThreeRandomDishes();

    @GET("challenges/three-random")
    Call<List<Challenge>> getThreeRandomChallenges();

}
