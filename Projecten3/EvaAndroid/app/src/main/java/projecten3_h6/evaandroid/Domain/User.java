package projecten3_h6.evaandroid.Domain;

import java.util.List;

/**
 * Created by jensleirens on 11/07/2017.
 */

public class User {

    private List<Achievement> achievements;
    private List<Day> days;
    private ShoppingList shoppingList;
    private int totalVeganDays;
    private int longestStreak;

    public User(List<Achievement> achievements, List<Day> days, ShoppingList shoppingList,
                int totalVeganDays, int longestStreak) {
        this.achievements = achievements;
        this.days = days;
        this.shoppingList = shoppingList;
        this.totalVeganDays = totalVeganDays;
        this.longestStreak = longestStreak;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
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
}
