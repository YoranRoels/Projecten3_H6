package projecten3_h6.evaandroid.Domain;

import java.io.Serializable;

public class Achievement implements Serializable{

    private static final long serialVersionUID = 1;
    private int completedImageId;
    private int imageId;
    private String title;
    private String description;
    private AchievementRanking achievementRanking;
    private boolean completed = false;

    public Achievement(int completedImageId,int imageId, String title, String description, AchievementRanking achievementRanking) {
        this.completedImageId = completedImageId;
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.achievementRanking = achievementRanking;
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

    public AchievementRanking getAchievementRanking() {
        return achievementRanking;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
