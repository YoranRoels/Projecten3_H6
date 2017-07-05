package projecten3_h6.evaandroid.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Domain.CookingTime;
import projecten3_h6.evaandroid.Domain.Dish;
import projecten3_h6.evaandroid.Domain.Ingredient;
import projecten3_h6.evaandroid.Domain.TypeDish;
import projecten3_h6.evaandroid.R;

/**
 * Created by jensleirens on 05/07/2017.
 */

public class TodayFragment extends Fragment {

    @BindView(R.id.todayIngredients)TextView ingredientsView;
    @BindView(R.id.todayPreperation)TextView preperationView;
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

        PopulateTextViews();

        return v;
    }

    public void PopulateTextViews(){

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Wortelen","10"));
        ingredients.add(new Ingredient("Tomaten","10"));
        ingredients.add(new Ingredient("Ajuin","2"));
        ingredients.add(new Ingredient("Prei","5"));
        ingredients.add(new Ingredient("Zout","200g"));
        ingredients.add(new Ingredient("Wortelen","10"));


        dish = new Dish(R.drawable.winterovenschotel,"Winterovenschotel met Le Puy-linzen", CookingTime.MEDIUM, "For starters", TypeDish.MAINDISH,
                ingredients,
                        "1.Kook de linzen gaar in de groentenbouillon samen met een blaadje laurier," +
                        " een halve ui en 1/2 tl gedroogde tijm.\n" +
                        "\n" +
                        "2.Versnipper de rest van de uit en stoof ze aan in 3 el olijfolie." +
                        " Voeg de look, groenten, wat zout en de gemengde mediterraanse kruiden toe en laat alles zo goed als gaar stoven" +
                        " met het deksel op de pan. Voeg eventueel wat water toe als de groenten dreigen aan te branden.\n" +
                        "\n" +
                        "3.Warm de oven voor op 18°C en vet een ovenschaal in. Meng het broodkruim met de boter," +
                        " 2 teentjes fijngehakte knoflook, de verse kruiden, zout en peper.\n" +
                        "\n" +
                        "4.Meng de gekookte linzen (haal de halve ui en het laurierblad eruit)," +
                        " de groenten en de noten onder elkaar in de ovenschotel." +
                        " Leg bovenop een laagje van het broodkruim en bak de schotel zo'n 10 minuten in de oven," +
                        " tot het korstje licht verkleurt.");


        StringBuilder sb = new StringBuilder();
        for(Ingredient i : dish.getIngredients()){
            sb.append(i.getName() + ": ").append(i.getAmount()).append("\n");
        }

        String typeStr;
        String time;

        if(dish.getType() == TypeDish.MAINDISH){
            typeStr = "Main Dish";
        } else if(dish.getType() == TypeDish.APPETIZER){
            typeStr = "Appetizer";
        } else {
            typeStr = "Dessert";
        }


        if(dish.getCookingTime() == CookingTime.LONG){
            time = "Long";
        } else  if(dish.getCookingTime() == CookingTime.MEDIUM){
            time = "Medium";
        } else {
            time = "Short";
        }

        type.setText(typeStr);
        cookingTime.setText(time);
        ingredientsView.setText(sb.toString());
        preperationView.setText(dish.getPreparation());
        recipeImage.setImageResource(dish.getImageId());
        difficulty.setText(dish.getDifficulty());
        dishName.setText(dish.getName());

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Today");
    }
}
