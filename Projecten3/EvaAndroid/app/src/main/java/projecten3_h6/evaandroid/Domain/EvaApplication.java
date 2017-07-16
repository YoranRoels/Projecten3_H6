package projecten3_h6.evaandroid.Domain;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import projecten3_h6.evaandroid.R;

/**
 * Created by Yoran on 16/07/2017.
 */

public class EvaApplication extends Application {

    private User user = fillInUser();

    public User getUser() {
        return user;
    }

    private User fillInUser() {
        User filledInUser;
        // if(user in local storage)
        //{
            // Fill user from Local Storage
        //}
        // else
        // {

        List<Achievement> achievements = new ArrayList<>();

        achievements.add(new Achievement(R.drawable.bronze_app_completed,R.drawable.bronze_app,"We're Just Getting Started","Launch the app.",
                AchievementRanking.BRONZE,true));

        achievements.add(new Achievement(R.drawable.bronze_calendar_completed,R.drawable.bronze_calendar,"I’m On a Regime","Open the ‘Progress’ tab.",
                AchievementRanking.BRONZE,true));

        achievements.add(new Achievement(R.drawable.bronze_cooking_completed,R.drawable.bronze_cooking,"What’s For Dinner?","Open the ‘Today’ tab.",
                AchievementRanking.BRONZE,false));

        achievements.add(new Achievement(R.drawable.silver_checkbox_completed,R.drawable.silver_checkbox,"Making Progress","Complete a ‘segment’ while having all days marked as complete.",
                AchievementRanking.SILVER,false));

        achievements.add(new Achievement(R.drawable.gold_streak_25_completed,R.drawable.gold_streak_25, "Vegan Master Streak","Achieve a 25-day vegan streak.",
                AchievementRanking.GOLD,true));

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Carrots","2kg"));
        ingredients.add(new Ingredient("Tomatoes","500g"));
        ingredients.add(new Ingredient("Eggs","12"));
        ingredients.add(new Ingredient("Vanilla","200g"));
        ingredients.add(new Ingredient("Strawberries","1kg"));

        ShoppingList shoppingList = new ShoppingList(ingredients);

        filledInUser = new User(achievements,null,shoppingList,15,34);
        // }
        return filledInUser;
    }
}
