package projecten3_h6.evaandroid.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Domain.Achievement;
import projecten3_h6.evaandroid.Domain.CookingTime;
import projecten3_h6.evaandroid.Domain.Day;
import projecten3_h6.evaandroid.Domain.Dish;
import projecten3_h6.evaandroid.Domain.EvaApplication;
import projecten3_h6.evaandroid.Domain.Ingredient;
import projecten3_h6.evaandroid.Domain.DishType;
import projecten3_h6.evaandroid.Domain.User;
import projecten3_h6.evaandroid.Network.Calls;
import projecten3_h6.evaandroid.Network.Config;
import projecten3_h6.evaandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jensleirens on 05/07/2017.
 */

public class TodayFragment extends Fragment {

    @BindView(R.id.todayIngredients)TextView ingredientsView;
    @BindView(R.id.todayPreparation)TextView preparationView;
    @BindView(R.id.todayImage)ImageView recipeImage;
    @BindView(R.id.todayCookingTime)TextView cookingTime;
    @BindView(R.id.todayDifficulty)TextView difficulty;
    @BindView(R.id.todayType)TextView type;
    @BindView(R.id.todayDishName)TextView dishName;
    private Dish dish;
    User user;
    List<Dish> dishes = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_today, container, false);
        ButterKnife.bind(this,v);

        //todo delete test code
        //test
        Calls caller = Config.getRetrofit().create(Calls.class);
        Call<List<Dish>> call = caller.getDishes();
        call.enqueue(new Callback<List<Dish>>() {
            @Override
            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                System.out.println("api call" + response.body());
                dishes = response.body();
                Log.e("APICALL", " call successful");
                Toast.makeText(getActivity().getApplicationContext(),"call successful",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t) {
                Log.e("APICALL", "failed to call "+ t.getMessage());
                //Toast.makeText(getActivity().getApplicationContext(),"geen internet verbinding!",Toast.LENGTH_SHORT).show();
            }
        });

        System.out.println(dishes);



        //test


        // Get User
        Context context = getContext();
        EvaApplication app = (EvaApplication)context.getApplicationContext();
        user = app.getUser();
        // Set correct today's dish.
        setCorrectDish();
        // Set the TextViews and ImageView for this dish.
        if(dish != null) {
            setTextAndVisuals();
        }

        return v;
    }

    public void setTextAndVisuals(){
        // Ingredients
        StringBuilder sb = new StringBuilder();
        String delim = "";

        for(Ingredient i : dish.getIngredients()){
            sb.append(delim).append(i.getName() + ": ").append(i.getAmount());
            delim = "\n";
        }
        ingredientsView.setText(sb.toString());

        // Type
        String typeStr;
        if(dish.getType() == DishType.MAINDISH){
            typeStr = "Main Dish";
        } else if(dish.getType() == DishType.APPETIZER){
            typeStr = "Appetizer";
        } else {
            typeStr = "Dessert";
        }
        type.setText(typeStr);

        // Time
        String time;
        if(dish.getCookingTime() == CookingTime.LONG){
            time = "Long";
        } else  if(dish.getCookingTime() == CookingTime.MEDIUM){
            time = "Medium";
        } else {
            time = "Short";
        }
        cookingTime.setText(time);

        // The Rests
        preparationView.setText(dish.getPreparation());
        recipeImage.setPadding(0,0,0,0);
        recipeImage.setImageResource(dish.getImageId());
        difficulty.setText(dish.getDifficulty());
        dishName.setText(dish.getName());

    }

    public void setCorrectDish() {
        List<Day> days = user.getDays();
        int daysLength = days.size();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy", Locale.ENGLISH);
        String formattedDate = df.format(c.getTime());

        Day day1 = days.get(daysLength-3);
        String dayDate1 = day1.getDayOfTheMonth()+"/"+(day1.getMonth()+1)+"/"+day1.getYear();
        Day day2 = days.get(daysLength-2);
        String dayDate2 = day2.getDayOfTheMonth()+"/"+(day2.getMonth()+1)+"/"+day2.getYear();
        Day day3 = days.get(daysLength-1);
        String dayDate3 = day3.getDayOfTheMonth()+"/"+(day3.getMonth()+1)+"/"+day3.getYear();

        if(formattedDate.equals(dayDate1)) {
            dish = day1.getDish();
        } else if(formattedDate.equals(dayDate2)) {
            dish = day2.getDish();
        } else if(formattedDate.equals(dayDate3)) {
            dish = day3.getDish();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
