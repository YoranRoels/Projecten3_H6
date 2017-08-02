package projecten3_h6.evaandroid.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class User implements Serializable{

    // ----- Variables -----
    // Serializable
    private static final long serialVersionUID = 4;

    // Starting date to show in Drawer
    private String startingDate = createStartingDate();

    // Achievements
    private List<Achievement> achievements = new ArrayList<>();
    private int completedAchievementsCount = 0;
    private List<Achievement> bronzeAchievements = new ArrayList<>();
    private int completedBronzeAchievementsCount = 0;
    private List<Achievement> silverAchievements = new ArrayList<>();
    private int completedSilverAchievementsCount = 0;
    private List<Achievement> goldAchievements = new ArrayList<>();
    private int completedGoldAchievementsCount = 0;

    // Days
    private List<Day> days = new ArrayList<>();
    private Day today;

    // Shopping List
    private ShoppingList shoppingList = new ShoppingList();

    // Statistics
    private int totalVeganDays = 0;
    private int longestStreak = 0;

    // Settings
    private boolean automaticShoppingEnabled = true;

    // Challenges
    private int challengeCoins = 0;

    // ----- Constructors -----
    public User(List<Achievement> achievements) {
        this.achievements = achievements;
        assignAchievements();
    }

    // ----- Getters & Setters -----
    private String createStartingDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH);
        return df.format(c.getTime());
    }

    public String getStartingDate() {
        return startingDate;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public int getCompletedBronzeAchievementsCount() {
        return completedBronzeAchievementsCount;
    }

    public int getCompletedSilverAchievementsCount() {
        return completedSilverAchievementsCount;
    }

    public int getCompletedGoldAchievementsCount() {
        return completedGoldAchievementsCount;
    }

    public int getCompletedAchievementsCount() {
        return completedAchievementsCount;
    }

    public List<Achievement> getBronzeAchievements() {
        return bronzeAchievements;
    }

    public List<Achievement> getSilverAchievements() {
        return silverAchievements;
    }

    public List<Achievement> getGoldAchievements() {
        return goldAchievements;
    }

    public List<Day> getDays() {
        return days;
    }

    public Day getToday() {
        Calendar todayCalendar = Calendar.getInstance();
        // Here we need the last four days for when you already start a new segment
        // but 'today' is still the last day of the previous segment
        List<Day> lastThreeOrFourDays = new ArrayList<>();
        if(days.size() < 4) {
            lastThreeOrFourDays = days;
        } else {
            lastThreeOrFourDays.add(days.get(days.size() - 4));
            lastThreeOrFourDays.add(days.get(days.size() - 3));
            lastThreeOrFourDays.add(days.get(days.size() - 2));
            lastThreeOrFourDays.add(days.get(days.size() - 1));
        }
        for(Day day: lastThreeOrFourDays) {
            // Int casts are needed here to avoid errors.
            if(day.getDayOfTheYear() == (int) todayCalendar.get(Calendar.DAY_OF_YEAR) &&
                    day.getYear() == (int) todayCalendar.get(Calendar.YEAR)) {
                today = day;
                break;
            }
        }

        return today;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public int getTotalVeganDays() {
        return totalVeganDays;
    }

    public int getLongestStreak() {
        return longestStreak;
    }

    public boolean isAutomaticShoppingEnabled() {
        return automaticShoppingEnabled;
    }

    public void setAutomaticShoppingEnabled(boolean automaticShoppingEnabled) {
        this.automaticShoppingEnabled = automaticShoppingEnabled;
    }

    public int getChallengeCoins() {
        return challengeCoins;
    }

    public void setChallengeCoins(int challengeCoins) {
        this.challengeCoins = challengeCoins;
    }

    // ----- Methods -----
    // Days
    public List<Day> getLastThreeDays() {
        List<Day> lastThreeDays = new ArrayList<>();
        lastThreeDays.add(days.get(days.size()-3));
        lastThreeDays.add(days.get(days.size()-2));
        lastThreeDays.add(days.get(days.size()-1));
        return lastThreeDays;
    }

    // Achievements
    private void assignAchievements() {
        bronzeAchievements.clear();
        silverAchievements.clear();
        goldAchievements.clear();

        for (Achievement a : achievements) {
            if (a.getAchievementType() == AchievementType.BRONZE) {
                bronzeAchievements.add(a);
            } else if (a.getAchievementType() == AchievementType.SILVER) {
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
                temporaryCompletedAchievementsCount += 1;
                switch (a.getAchievementType()) {
                    case BRONZE:
                        temporaryBronzeCompletedAchievementsCount += 1;
                        break;
                    case SILVER:
                        temporarySilverCompletedAchievementsCount += 1;
                        break;
                    case GOLD:
                        temporaryGoldCompletedAchievementsCount += 1;
                        break;
                }
            }
        }

        completedAchievementsCount = temporaryCompletedAchievementsCount;
        completedBronzeAchievementsCount = temporaryBronzeCompletedAchievementsCount;
        completedSilverAchievementsCount = temporarySilverCompletedAchievementsCount;
        completedGoldAchievementsCount = temporaryGoldCompletedAchievementsCount;
    }

    // Statistics
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

    public void calculateChallengeCoins() {
        int temporaryChallengeCoins = 0;
        for(Day d:days) {
            for(Challenge c: d.getChallenges()) {
                if(c.isCompleted()) {
                    temporaryChallengeCoins += 1;
                }
            }
        }
        challengeCoins = temporaryChallengeCoins;
    }
}
