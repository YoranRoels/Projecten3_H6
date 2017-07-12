package projecten3_h6.evaandroid.Domain;

/**
 * Created by jensleirens on 11/07/2017.
 */

public class Achievement {

    private String title;
    private String description;
    private AchievementType achievementType;

    public Achievement(String title, String description, AchievementType achievementType) {
        this.title = title;
        this.description = description;
        this.achievementType = achievementType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AchievementType getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(AchievementType achievementType) {
        this.achievementType = achievementType;
    }
}
