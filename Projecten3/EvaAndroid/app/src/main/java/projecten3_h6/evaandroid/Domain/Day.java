package projecten3_h6.evaandroid.Domain;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mafken on 4/07/2017.
 */

public class Day {
    private int year;
    private int month;
    private int dayOfTheMonth;
    private int dayOfTheWeek;
    private String dayOfTheWeekString;
    private int dayOfTheYear;
    private Dish dish;
    private String tip;
    private Boolean completed;

    public Day(int year, int month, int dayOfTheMonth, int dayOfTheWeek, int dayOfTheYear, Dish dish, String tip) {
        this.year = year;
        this.month = month;
        this.dayOfTheMonth = dayOfTheMonth;
        this.dayOfTheWeek = dayOfTheWeek;
        this.dayOfTheYear = dayOfTheYear;
        this.dish = dish;
        this.tip = tip;
        completed = false;
        setDayOfTheWeekString();
    }

    public Day(int year, int month, int dayOfTheMonth, int dayOfTheWeek, int dayOfTheYear) {
        this.year = year;
        this.month = month;
        this.dayOfTheMonth = dayOfTheMonth;
        this.dayOfTheWeek = dayOfTheWeek;
        this.dayOfTheYear = dayOfTheYear;
        completed = false;
        setDayOfTheWeekString();
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfTheMonth() {
        return dayOfTheMonth;
    }

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public String getDayOfTheWeekString() {
        return dayOfTheWeekString;
    }

    private void setDayOfTheWeekString() {
        switch(dayOfTheWeek) {
            case Calendar.MONDAY:
                dayOfTheWeekString = "Monday";
                break;
            case Calendar.TUESDAY:
                dayOfTheWeekString = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                dayOfTheWeekString = "Wednesday";
                break;
            case Calendar.THURSDAY:
                dayOfTheWeekString = "Thursday";
                break;
            case Calendar.FRIDAY:
                dayOfTheWeekString = "Friday";
                break;
            case Calendar.SATURDAY:
                dayOfTheWeekString = "Saturday";
                break;
            case Calendar.SUNDAY:
                dayOfTheWeekString = "Sunday";
                break;
        }
    }

    public int getDayOfTheYear() {
        return dayOfTheYear;
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
