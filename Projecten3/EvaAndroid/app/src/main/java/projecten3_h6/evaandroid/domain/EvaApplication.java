package projecten3_h6.evaandroid.domain;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import projecten3_h6.evaandroid.R;
import projecten3_h6.evaandroid.network.Calls;
import projecten3_h6.evaandroid.network.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvaApplication extends Application {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;

    }

    public int getSegmentSize() {
        return 3;
    }

    public User createNewUser() {
        User filledInUser;

        List<Achievement> achievements = new ArrayList<>();

        // Bronze (8)
        achievements.add(new Achievement(R.drawable.bronze_calendar_completed, R.drawable.bronze_calendar, "I’m On a Regime", "Open the ‘Progress’ tab.",
                AchievementType.BRONZE));
        achievements.add(new Achievement(R.drawable.bronze_cooking_completed, R.drawable.bronze_cooking, "What’s For Dinner?", "Open the ‘Today’ tab.",
                AchievementType.BRONZE));
        achievements.add(new Achievement(R.drawable.bronze_shopping_list_completed, R.drawable.bronze_shopping_list, "No More Pen and Paper", "Open the ‘Shopping List’ tab.",
                AchievementType.BRONZE));
        achievements.add(new Achievement(R.drawable.bronze_dish_icon_completed, R.drawable.bronze_dish_icon, "Planning Ahead", "Select your first three dishes.",
                AchievementType.BRONZE));
        achievements.add(new Achievement(R.drawable.bronze_shopping_basket_plus_completed, R.drawable.bronze_shopping_basket_plus, "Manual Labor", "Manually add something to your Shopping List.",
                AchievementType.BRONZE));
        achievements.add(new Achievement(R.drawable.bronze_steak_icon_completed, R.drawable.bronze_steak_icon, "Cheat Day", "Skip a vegan day.",
                AchievementType.BRONZE));
        achievements.add(new Achievement(R.drawable.bronze_calendar_1_completed, R.drawable.bronze_calendar_1, "Vegan Rookie", "Your first vegan day.",
                AchievementType.BRONZE));


        // Silver (2)
        achievements.add(new Achievement(R.drawable.silver_checkbox_completed, R.drawable.silver_checkbox, "Making Progress", "Complete a ‘segment’ while having all days marked as complete.",
                AchievementType.SILVER));
        achievements.add(new Achievement(R.drawable.silver_streak_10_completed, R.drawable.silver_streak_10, "Vegan Pro Streak", "Achieve a 10-day vegan streak.",
                AchievementType.SILVER));


        // Gold (2)
        achievements.add(new Achievement(R.drawable.gold_streak_25_completed, R.drawable.gold_streak_25, "Vegan Master Streak", "Achieve a 25-day vegan streak.",
                AchievementType.GOLD));
        achievements.add(new Achievement(R.drawable.gold_calendar_100_completed, R.drawable.gold_calendar_100, "Vegan Master", "Have a total of 100 vegan days.",
                AchievementType.GOLD));

        filledInUser = new User(achievements);

        return filledInUser;
    }

    public void earnAchievement(Context context, LayoutInflater inflater, ViewGroup container, String achievementTitle) {
        Achievement shoppingListTabAchievement;
        for(Achievement a: user.getAchievements()) {
            if(a.getTitle().equals(achievementTitle)) {
                shoppingListTabAchievement = a;
                if(!shoppingListTabAchievement.isCompleted()){
                    a.setCompleted(true);
                    View view = inflater.inflate(R.layout.achievement_earned_alert, container, false);
                    TextView achievementTitleTextView = (TextView)view.findViewById(R.id.earnedAchievementTitle);
                    achievementTitleTextView.setText(a.getTitle());
                    ImageView achievementImageView = (ImageView)view.findViewById(R.id.earnedAchievementIcon);
                    achievementImageView.setImageResource(a.getCompletedImageId());
                    Toast toast = new Toast(context);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(view);
                    toast.show();
                }
            }
        }
        user.countCompletedAchievements();
    }

    public void getRemoteAchievements(){
        Calls caller = Config.getRetrofit().create(Calls.class);
        Call<List<Achievement>> call = caller.getAchievements();

        call.enqueue(new Callback<List<Achievement>>() {
            @Override
            public void onResponse(Call<List<Achievement>> call, Response<List<Achievement>> response) {
                List<Achievement> remoteAchievements;
                List<Achievement> newAchievement = new ArrayList<Achievement>();
                remoteAchievements = response.body();
                Log.e("Backend call", "Call successful (get all achievements)");

                boolean broken = false ;
                for (Achievement remote : remoteAchievements) {
                    for(Achievement userAchievement : user.getAchievements()) {
                        if(userAchievement.getTitle().equals(remote.getTitle())){
                            broken = true ;
                            break;
                        }

                    }
                    if(!broken){
                        newAchievement.add(remote);
                        broken = false ;
                    }

                }

                user.getAchievements().addAll(newAchievement);
            }


            @Override
            public void onFailure(Call<List<Achievement>> call, Throwable t) {
                Log.e("Backend call", "Call failed (get all achievements)"+ t.getMessage());
            }
        });
    }
}