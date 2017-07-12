package projecten3_h6.evaandroid.Domain;

/**
 * Created by jensleirens on 11/07/2017.
 */

public class Achievement {

    private int completedImageId;
    private int imageId;
    private String title;
    private String description;
    private AchievementType achievementType;
    private boolean completed;

    public Achievement(int completedImageId,int imageId, String title, String description, AchievementType achievementType, boolean completed ) {
        this.completedImageId = completedImageId;
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.achievementType = achievementType;
        this.completed = completed;
    }

    public int getCompletedImageId() {
        return completedImageId;
    }

    public void setCompletedImageId(int completedImageId) {
        this.completedImageId = completedImageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
