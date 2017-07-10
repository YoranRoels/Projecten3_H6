package projecten3_h6.evaandroid.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Domain.CookingTime;
import projecten3_h6.evaandroid.Domain.Day;
import projecten3_h6.evaandroid.Domain.Dish;
import projecten3_h6.evaandroid.Domain.Ingredient;
import projecten3_h6.evaandroid.Domain.TypeDish;
import projecten3_h6.evaandroid.R;

/**
 * Created by Yoran on 07/07/2017.
 */

public class ProgressFragment extends Fragment {

    @BindView(R.id.progressRecyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.progressTextView) TextView progressTextView;
    @BindView(R.id.progressMotivationTextView) TextView progressMotivationTextView;

    protected RecyclerView.LayoutManager mLayoutManager;
    private ProgressAdapter adapter;
    private ArrayList<Day> days = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_progress, container, false);
        ButterKnife.bind(this,v);

        //todo implement empty progress adapter
        //todo implement progress picker dialog

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        
        initdata();
        // initemptydata();

        progressTextView.setText("You've completed 4 days. Keep it up!");
        progressMotivationTextView.setText("It's ok that you've missed a day, it's our secret.");

        adapter = new ProgressAdapter(days);
        mRecyclerView.setAdapter(adapter);
        return v;
    }

    public void initemptydata() {
        days.add(new Day("Monday"));
        days.add(new Day("Tuesday"));
        days.add(new Day("Wednesday"));
    }

    public void initdata(){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Wortelen","10"));
        ingredients.add(new Ingredient("Tomaten","10"));
        ingredients.add(new Ingredient("Ajuin","2"));
        ingredients.add(new Ingredient("Prei","5"));
        ingredients.add(new Ingredient("Zout","200g"));
        ingredients.add(new Ingredient("Wortelen","10"));

        days.add(new Day("Monday","10/07/2017",new Dish(R.drawable.winterovenschotel,"Winterovenschotel met Le Puy-linzen", CookingTime.MEDIUM, "Beginner", TypeDish.MAINDISH,
                ingredients,
                "1. Kook de linzen gaar in de groentenbouillon samen met een blaadje laurier," +
                        " een halve ui en 1/2 tl gedroogde tijm.\n" +
                        "\n" +
                        "2. Versnipper de rest van de uit en stoof ze aan in 3 el olijfolie." +
                        " Voeg de look, groenten, wat zout en de gemengde mediterraanse kruiden toe en laat alles zo goed als gaar stoven" +
                        " met het deksel op de pan. Voeg eventueel wat water toe als de groenten dreigen aan te branden.\n" +
                        "\n" +
                        "3. Warm de oven voor op 18°C en vet een ovenschaal in. Meng het broodkruim met de boter," +
                        " 2 teentjes fijngehakte knoflook, de verse kruiden, zout en peper.\n" +
                        "\n" +
                        "4. Meng de gekookte linzen (haal de halve ui en het laurierblad eruit)," +
                        " de groenten en de noten onder elkaar in de ovenschotel." +
                        " Leg bovenop een laagje van het broodkruim en bak de schotel zo'n 10 minuten in de oven," +
                        " tot het korstje licht verkleurt."),
                "A durum a day keeps the vegan away."));

        days.add(new Day("Tuesday (Today)","11/07/2017",new Dish(R.drawable.winterovenschotel,"Lasagna with vegetables", CookingTime.SHORT, "Beginner", TypeDish.MAINDISH,
                ingredients,
                "1. Kook de linzen gaar in de groentenbouillon samen met een blaadje laurier," +
                        " een halve ui en 1/2 tl gedroogde tijm.\n" +
                        "\n" +
                        "2. Versnipper de rest van de uit en stoof ze aan in 3 el olijfolie." +
                        " Voeg de look, groenten, wat zout en de gemengde mediterraanse kruiden toe en laat alles zo goed als gaar stoven" +
                        " met het deksel op de pan. Voeg eventueel wat water toe als de groenten dreigen aan te branden.\n" +
                        "\n" +
                        "3. Warm de oven voor op 18°C en vet een ovenschaal in. Meng het broodkruim met de boter," +
                        " 2 teentjes fijngehakte knoflook, de verse kruiden, zout en peper.\n" +
                        "\n" +
                        "4. Meng de gekookte linzen (haal de halve ui en het laurierblad eruit)," +
                        " de groenten en de noten onder elkaar in de ovenschotel." +
                        " Leg bovenop een laagje van het broodkruim en bak de schotel zo'n 10 minuten in de oven," +
                        " tot het korstje licht verkleurt."),
                "You can never have enough veggies."));

        days.add(new Day("Wednesday","12/07/2017",new Dish(R.drawable.winterovenschotel,"Veggie Pizza", CookingTime.LONG, "Proffesional", TypeDish.MAINDISH,
                ingredients,
                "1. Kook de linzen gaar in de groentenbouillon samen met een blaadje laurier," +
                        " een halve ui en 1/2 tl gedroogde tijm.\n" +
                        "\n" +
                        "2. Versnipper de rest van de uit en stoof ze aan in 3 el olijfolie." +
                        " Voeg de look, groenten, wat zout en de gemengde mediterraanse kruiden toe en laat alles zo goed als gaar stoven" +
                        " met het deksel op de pan. Voeg eventueel wat water toe als de groenten dreigen aan te branden.\n" +
                        "\n" +
                        "3. Warm de oven voor op 18°C en vet een ovenschaal in. Meng het broodkruim met de boter," +
                        " 2 teentjes fijngehakte knoflook, de verse kruiden, zout en peper.\n" +
                        "\n" +
                        "4. Meng de gekookte linzen (haal de halve ui en het laurierblad eruit)," +
                        " de groenten en de noten onder elkaar in de ovenschotel." +
                        " Leg bovenop een laagje van het broodkruim en bak de schotel zo'n 10 minuten in de oven," +
                        " tot het korstje licht verkleurt."),
                "There is no meat on mars."));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
    }
}
