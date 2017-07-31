package projecten3_h6.evaandroid.Domain;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import projecten3_h6.evaandroid.Network.Calls;
import projecten3_h6.evaandroid.Network.Config;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Day implements Serializable{

    private static final long serialVersionUID = 2;
    private int year;
    private int month;
    private int dayOfTheMonth;
    private int dayOfTheWeek;
    private String dayOfTheWeekString;
    private int dayOfTheYear;
    private Dish dish;
    private String tip;
    private boolean completed;
    private List<Challenge> challenges = new ArrayList<>();

    public Day(int year, int month, int dayOfTheMonth, int dayOfTheWeek, int dayOfTheYear) {
        this.year = year;
        this.month = month;
        this.dayOfTheMonth = dayOfTheMonth;
        this.dayOfTheWeek = dayOfTheWeek;
        this.dayOfTheYear = dayOfTheYear;
        completed = false;
        setDayOfTheWeekString();
    }

    public boolean isCompleted() {
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

    public boolean getCompleted() {
        return this.completed;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void getRemoteChallenges(){

        Calendar cal = Calendar.getInstance();
        if(cal.get(Calendar.DAY_OF_YEAR) >= dayOfTheYear || challenges == null) {

            Calls caller = Config.getRetrofit().create(Calls.class);
            Call<List<Challenge>> call = caller.getThreeRandomChallenges();
            call.enqueue(new Callback<List<Challenge>>() {
                @Override
                public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                    challenges = response.body();
                    Log.e("Backend Call", " call successful (three random challenges)");
                }

                @Override
                public void onFailure(Call<List<Challenge>> call, Throwable t) {
                    Log.e("Backend CAll", "failed to call (three random challenges) "+ t.getMessage());
                    challenges.clear();
                    challenges.add(new Challenge("No dairy for me","Don't use milk in today's dish.",ChallengeType.EASY));
                    challenges.add(new Challenge("Infect your friends","Prepare a vegan dinner with friends.",ChallengeType.MEDIUM));
                    challenges.add(new Challenge("More vegans","Convert a friend to the vegan lifestyle.",ChallengeType.HARD));
                }
            });

        }
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }
}
