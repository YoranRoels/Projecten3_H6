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
    private User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_today, container, false);
        ButterKnife.bind(this,v);

        // Get User
        Context context = getContext();
        EvaApplication app = (EvaApplication)context.getApplicationContext();
        user = app.getUser();
        // Set correct today's dish.
        dish = user.getToday().getDish();
        // Set the TextViews and ImageView for this dish.
        if(dish != null) {
            setTextAndVisuals();
        }

        // Achievement earned
        app.earnAchievement(getContext(), inflater, container, "What’s For Dinner?");
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

        // Rest
        preparationView.setText(dish.getPreparation());
        recipeImage.setPadding(0,0,0,0);
        recipeImage.setImageResource(dish.getImageId());
        difficulty.setText(dish.getDifficulty());
        dishName.setText(dish.getName());

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
