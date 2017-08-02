package projecten3_h6.evaandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.domain.CookingTime;
import projecten3_h6.evaandroid.domain.DifficultyType;
import projecten3_h6.evaandroid.domain.Dish;
import projecten3_h6.evaandroid.domain.EvaApplication;
import projecten3_h6.evaandroid.domain.Ingredient;
import projecten3_h6.evaandroid.domain.DishType;
import projecten3_h6.evaandroid.domain.User;
import projecten3_h6.evaandroid.R;

public class TodayFragment extends Fragment {

    @BindView(R.id.todayIngredients)TextView ingredientsView;
    @BindView(R.id.todayPreparation)TextView preparationView;
    @BindView(R.id.todayImage)ImageView recipeImage;
    @BindView(R.id.todayCookingTime)TextView cookingTime;
    @BindView(R.id.todayDifficulty)TextView difficulty;
    @BindView(R.id.todayType)TextView type;
    @BindView(R.id.todayDishName)TextView dishName;
    private Dish dish;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_today, container, false);
        ButterKnife.bind(this,v);

        User user;

        // Get User
        Context context = getContext();
        EvaApplication app = (EvaApplication)context.getApplicationContext();
        user = app.getUser();
        // Set correct today's dish.
        if(user.getToday() != null && user.getToday().getDish() != null) {
            dish = user.getToday().getDish();
            // Set the TextViews and ImageView for this dish.
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
            sb.append(delim);
            if(i.getAmount() == null){
                sb.append(i.getName());
            }else
            {
                sb.append(i.getName() + ": ").append(i.getAmount());
            }

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

        // Difficulty
        String sDifficulty;
        if(dish.getDifficultyType() == DifficultyType.STARTERS){
            sDifficulty = "Starters";
        } else  if(dish.getDifficultyType() == DifficultyType.ADVANCED){
            sDifficulty = "Advanced";
        } else {
            sDifficulty = "Master";
        }
        difficulty.setText(sDifficulty);

        // Rest
        preparationView.setText(dish.getPreparation());
        recipeImage.setPadding(0,0,0,0);
        recipeImage.setImageResource(dish.getImageId());
        dishName.setText(dish.getName());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
