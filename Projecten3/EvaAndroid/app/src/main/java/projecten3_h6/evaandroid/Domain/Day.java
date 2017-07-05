package projecten3_h6.evaandroid.Domain;

/**
 * Created by Mafken on 4/07/2017.
 */

public class Day {
    private String Date;
    private Dish dish;
    private String tip;

    public Day(String date, Dish dish, String tip) {
        Date = date;
        this.dish = dish;
        this.tip = tip;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
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
