package projecten3_h6.evaandroid.domain;

import java.io.Serializable;

public class Challenge implements Serializable{

    private static final long serialVersionUID = 7;
    private String title;
    private String description;
    private ChallengeType challengeType;
    private boolean completed;

    public Challenge(String title, String description, ChallengeType challengeType) {
        this.title = title;
        this.description = description;
        this.challengeType = challengeType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ChallengeType getChallengeType() {
        return challengeType;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
