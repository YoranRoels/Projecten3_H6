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
            if(i.getAmount() == null || i.getAmount().equals("")){
                sb.append(i.getName());
            }else
            {
                sb.append(i.getName() + ": ").append(i.getAmount());
            }

            delim = "\n";
        }

        ingredientsView.setText(sb.toString());
        type.setText(dish.getDishType().toString());
        cookingTime.setText(dish.getCookingTime().toString());
        difficulty.setText(dish.getDifficultyType().toString());
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
