package projecten3_h6.evaandroid.Domain;

/**
 * Created by Mafken on 4/07/2017.
 */

public class Day {
    private String dateOfTheWeek;
    private String date;
    private Dish dish;
    private String tip;
    private Boolean completed;

    public Day(String dateOfTheWeek, String date, Dish dish, String tip) {
        this.dateOfTheWeek = dateOfTheWeek;
        this.date = date;
        this.dish = dish;
        this.tip = tip;
        completed = false;
    }

    public Day(String dateOfTheWeek) {
        this.dateOfTheWeek = dateOfTheWeek;
        completed = false;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getDateOfTheWeek() {
        return dateOfTheWeek;
    }

    public void setDateOfTheWeek(String dateOfTheWeek) {
        this.dateOfTheWeek = dateOfTheWeek;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
