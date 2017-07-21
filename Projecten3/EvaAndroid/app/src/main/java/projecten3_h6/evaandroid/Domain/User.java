package projecten3_h6.evaandroid.Domain;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import projecten3_h6.evaandroid.Network.Calls;
import projecten3_h6.evaandroid.Network.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jensleirens on 11/07/2017.
 */

public class User {

    private List<Achievement> achievements;
    private List<Achievement> remoteAchievement;
    private int completedAchievementsCount = 0;
    private List<Achievement> bronzeAchievements = new ArrayList<>();
    private int completedBronzeAchievementsCount = 0;
    private List<Achievement> silverAchievements = new ArrayList<>();
    private int completedSilverAchievementsCount = 0;
    private List<Achievement> goldAchievements = new ArrayList<>();
    private int completedGoldAchievementsCount = 0;
    private List<Day> days = new ArrayList<>();
    private ShoppingList shoppingList = new ShoppingList();
    private int totalVeganDays = 0;
    private int longestStreak = 0;

    public User(List<Achievement> achievements, List<Day> days, ShoppingList shoppingList,
                int totalVeganDays, int longestStreak) {
        this.achievements = achievements;
        this.days = days;
        this.shoppingList = shoppingList;
        this.totalVeganDays = totalVeganDays;
        this.longestStreak = longestStreak;

        assignAchievements();
        countCompletedAchievements();
        calculateStatistics();
    }

    public User(List<Achievement> achievements) {
        this.achievements = achievements;

        assignAchievements();
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public int getCompletedAchievementsCount() {
        return completedAchievementsCount;
    }

    public List<Achievement> getBronzeAchievements() {
        return bronzeAchievements;
    }

    public int getCompletedBronzeAchievementsCount() {
        return completedBronzeAchievementsCount;
    }

    public List<Achievement> getSilverAchievements() {
        return silverAchievements;
    }
  
    public int getCompletedSilverAchievementsCount() {
        return completedSilverAchievementsCount;
    }

    public List<Achievement> getGoldAchievements() {
        return goldAchievements;
    }
  
    public int getCompletedGoldAchievementsCount() {
        return completedGoldAchievementsCount;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public int getTotalVeganDays() {
        return totalVeganDays;
    }

    public void setTotalVeganDays(int totalVeganDays) {
        this.totalVeganDays = totalVeganDays;
    }

    public int getLongestStreak() {
        return longestStreak;
    }

    public void setLongestStreak(int longestStreak) {
        this.longestStreak = longestStreak;
    }

    private void assignAchievements() {
        bronzeAchievements.clear();
        silverAchievements.clear();
        goldAchievements.clear();

        for (Achievement a : achievements) {
            if (a.getAchievementRanking() == AchievementRanking.BRONZE) {
                bronzeAchievements.add(a);
            } else if (a.getAchievementRanking() == AchievementRanking.SILVER) {
                silverAchievements.add(a);
            } else {
                goldAchievements.add(a);
            }
        }
    }

    public void countCompletedAchievements() {
        int temporaryCompletedAchievementsCount = 0;
        int temporaryBronzeCompletedAchievementsCount = 0;
        int temporarySilverCompletedAchievementsCount = 0;
        int temporaryGoldCompletedAchievementsCount = 0;

        for (Achievement a : achievements) {
            if (a.isCompleted()) {
                temporaryCompletedAchievementsCount+=1;
                switch(a.getAchievementRanking()) {
                    case BRONZE:
                        temporaryBronzeCompletedAchievementsCount+=1;
                        break;
                    case SILVER:
                        temporarySilverCompletedAchievementsCount+=1;
                        break;
                    case GOLD:
                        temporaryGoldCompletedAchievementsCount+=1;
                        break;
                }
            }
        }

        completedAchievementsCount = temporaryCompletedAchievementsCount;
        completedBronzeAchievementsCount = temporaryBronzeCompletedAchievementsCount;
        completedSilverAchievementsCount = temporarySilverCompletedAchievementsCount;
        completedGoldAchievementsCount = temporaryGoldCompletedAchievementsCount;
    }

    public void calculateStatistics() {
        calculateTotalVeganDays();
        calculateLongestStreak();
    }

    private void calculateTotalVeganDays() {
        totalVeganDays = 0;
        for(Day day: days) {
            if(day.isCompleted()) {
                totalVeganDays+=1;
            }
        }
    }

    private void calculateLongestStreak() {
        longestStreak = 0;
        int streak = 0;
        for(Day day: days) {
            if(day.isCompleted()) {
                streak+=1;
            } else {
                if(streak > longestStreak) {
                    longestStreak = streak;
                }
                streak = 0;
            }
        }
    }

    public void getRemoteAchievements(){

        Calls caller = Config.getRetrofit().create(Calls.class);
        Call<List<Achievement>> call = caller.getAchievements();
        call.enqueue(new Callback<List<Achievement>>() {
            @Override
            public void onResponse(Call<List<Achievement>> call, Response<List<Achievement>> response) {
                remoteAchievement = response.body();
                Log.e("BackendCall", " call successful get all achievements");
            }

            @Override
            public void onFailure(Call<List<Achievement>> call, Throwable t) {
                Log.e("BackendCAll", "failed to call get all achievements "+ t.getMessage());
            }
        });

        compareAchievements();

    }

    private void compareAchievements(){
        for (Achievement a : remoteAchievement) {
            if (!achievements.contains(a)) {
                achievements.add(a);
            }
        }

    }
}
