package projecten3_h6.evaandroid.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jensleirens on 11/07/2017.
 */

public class User {

    private List<Achievement> achievements;
    private int completedAchievementsCount = 0;
    private List<Achievement> bronzeAchievements = new ArrayList<>();
    private List<Achievement> silverAchievements = new ArrayList<>();
    private List<Achievement> goldAchievements = new ArrayList<>();
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
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }
    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public int getCompletedAchievementsCount() {
        return completedAchievementsCount;
    }
    public void setCompletedAchievementsCount(int completedAchievementsCount) {
        this.completedAchievementsCount = completedAchievementsCount;
    }

    public List<Achievement> getBronzeAchievements() {
        return bronzeAchievements;
    }
    public void setBronzeAchievements(List<Achievement> bronzeAchievements) {
        this.bronzeAchievements = bronzeAchievements;
    }

    public List<Achievement> getSilverAchievements() {
        return silverAchievements;
    }
    public void setSilverAchievements(List<Achievement> silverAchievements) {
        this.silverAchievements = silverAchievements;
    }

    public List<Achievement> getGoldAchievements() {
        return goldAchievements;
    }
    public void setGoldAchievements(List<Achievement> goldAchievements) {
        this.goldAchievements = goldAchievements;
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

    public void assignAchievements() {
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

        for (Achievement a : achievements) {
            if (a.isCompleted()) {
                temporaryCompletedAchievementsCount++;
            }
        }

        completedAchievementsCount = temporaryCompletedAchievementsCount;
    }
}
