package projecten3_h6.evaandroid.domain;

import java.io.Serializable;

public class Achievement implements Serializable{

    private static final long serialVersionUID = 1;
    private int completedImageId;
    private int imageId;
    private String title;
    private String description;
    private AchievementType achievementType;
    private boolean completed = false;

    public Achievement(int completedImageId,int imageId, String title, String description, AchievementType achievementType) {
        this.completedImageId = completedImageId;
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.achievementType = achievementType;
    }

    public Achievement() {
    }

    public int getCompletedImageId() {
        return completedImageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public AchievementType getAchievementType() {
        return achievementType;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
