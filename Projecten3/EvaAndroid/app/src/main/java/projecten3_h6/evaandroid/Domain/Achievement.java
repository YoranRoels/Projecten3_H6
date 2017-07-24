package projecten3_h6.evaandroid.Domain;


import java.io.Serializable;

/**
 * Created by jensleirens on 11/07/2017.
 */

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

    public AchievementRanking getAchievementRanking() {
        return achievementRanking;
    }

    public void setAchievementRanking(AchievementRanking achievementRanking) {
        this.achievementRanking = achievementRanking;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean getCompleted() {
        return this.completed;
    }

}
