package projecten3_h6.evaandroid.Domain;

import java.io.Serializable;

/**
 * Created by jensleirens on 25/07/2017.
 */

public class Challenge implements Serializable{

    private static final long serialVersionUID = 7;
    private String title;
    private String description;
    private ChallengeType challengeType;

    public Challenge(String title, String description, ChallengeType challengeType) {
        this.title = title;
        this.description = description;
        this.challengeType = challengeType;
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

    public ChallengeType getChallengeType() {
        return challengeType;
    }

    public void setChallengeType(ChallengeType challengeType) {
        this.challengeType = challengeType;
    }
}
