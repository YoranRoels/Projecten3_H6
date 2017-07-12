package projecten3_h6.evaandroid.Domain;

/**
 * Created by jensleirens on 11/07/2017.
 */

public class AchievementType {

    private AchievementRanking achievementRanking;
    private int value;

    public AchievementType(AchievementRanking achievementRanking, int value) {
        this.achievementRanking = achievementRanking;
        this.value = value;
    }

    public AchievementRanking getAchievementRanking() {
        return achievementRanking;
    }

    public void setAchievementRanking(AchievementRanking achievementRanking) {
        this.achievementRanking = achievementRanking;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
