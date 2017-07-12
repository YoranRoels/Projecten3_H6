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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Adapters.AchievementAdapter;
import projecten3_h6.evaandroid.Domain.Achievement;
import projecten3_h6.evaandroid.Domain.AchievementRanking;
import projecten3_h6.evaandroid.Domain.AchievementType;
import projecten3_h6.evaandroid.Domain.CookingTime;
import projecten3_h6.evaandroid.Domain.Dish;
import projecten3_h6.evaandroid.Domain.DishType;
import projecten3_h6.evaandroid.Domain.Ingredient;
import projecten3_h6.evaandroid.Domain.User;
import projecten3_h6.evaandroid.R;

/**
 * Created by jensleirens on 12/07/2017.
 */

public class AchievementFragment extends Fragment {

    @BindView(R.id.achievementsMaxVeganStreak)TextView maxVeganStreak;
    @BindView(R.id.achievementsTotalVeganDays)TextView totalVeganDays;
    @BindView(R.id.achievementsTotalBronze)TextView totalBronze;
    @BindView(R.id.achievementsTotalSilver)TextView totalSilver;
    @BindView(R.id.achievementsTotalGold)TextView totalGold;
    @BindView(R.id.achievementsTotalProgressCount)TextView totalAchievements;
    @BindView(R.id.achievementsTotalProgressBar)ProgressBar totalProgressBar;
    @BindView(R.id.achievementsBronzeRecyclerView)RecyclerView bronzeRecycler;
    @BindView(R.id.achievementsSilverRecyclerView)RecyclerView silverRecycler;
    @BindView(R.id.achievementsGoldRecyclerView)RecyclerView goldRecycler;

    protected RecyclerView.LayoutManager bronzeLayoutManager;
    protected RecyclerView.LayoutManager silverLayoutManager;
    protected RecyclerView.LayoutManager goldLayoutManager;

    private AchievementAdapter bronzeAdapter;
    private AchievementAdapter silverAdapter;
    private AchievementAdapter goldAdapter;
    private User user;
    List<Achievement> totalBronzeAchievements = new ArrayList<>();
    List<Achievement> totalSilverAchievements = new ArrayList<>();
    List<Achievement> totalGoldAchievements = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_achievements, container, false);
        ButterKnife.bind(this,v);

        initdata();
        PopulateTextViews();

        bronzeLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        silverLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        goldLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

        bronzeRecycler.setLayoutManager(bronzeLayoutManager);
        silverRecycler.setLayoutManager(silverLayoutManager);
        goldRecycler.setLayoutManager(goldLayoutManager);


        bronzeAdapter = new AchievementAdapter(totalBronzeAchievements);
        silverAdapter = new AchievementAdapter(totalSilverAchievements);
        goldAdapter = new AchievementAdapter(totalGoldAchievements);
        bronzeRecycler.setAdapter(bronzeAdapter);
        silverRecycler.setAdapter(silverAdapter);
        goldRecycler.setAdapter(goldAdapter);

        return v;
    }

    public void PopulateTextViews(){

        totalVeganDays.setText(String.valueOf(user.getTotalVeganDays()));
        maxVeganStreak.setText(String.valueOf(user.getLongestStreak()));
        totalAchievements.setText(String.valueOf(user.getAchievements().size()));
        totalProgressBar.setProgress(user.getAchievements().size());

        for(Achievement a : user.getAchievements()){

            if(a.getAchievementType().getAchievementRanking() == AchievementRanking.GOLD){
                totalGoldAchievements.add(a);
            }else if(a.getAchievementType().getAchievementRanking() == AchievementRanking.SILVER){
                totalSilverAchievements.add(a);
            } else {
                totalBronzeAchievements.add(a);
            }
        }

        totalBronze.setText(String.valueOf(totalBronzeAchievements.size()));
        totalSilver.setText(String.valueOf(totalSilverAchievements.size()));
        totalGold.setText(String.valueOf(totalGoldAchievements.size()));

    }

    public void initdata(){
        List<Achievement> achievements = new ArrayList<>();

        achievements.add(new Achievement(R.drawable.carrot_trophy,R.drawable.checkbox,"We're Just Getting Started","Launch the app.",
                new AchievementType(AchievementRanking.BRONZE, 5),true));

        achievements.add(new Achievement(R.drawable.carrot_trophy,R.drawable.checkbox,"I’m On a Regime","Open the ‘Progress’ tab.",
                new AchievementType(AchievementRanking.BRONZE, 5),true));

        achievements.add(new Achievement(R.drawable.carrot_trophy,R.drawable.checkbox,"What’s For Dinner?","Open the ‘Today’ tab.",
                new AchievementType(AchievementRanking.BRONZE, 5),false));

        achievements.add(new Achievement(R.drawable.carrot_trophy,R.drawable.checkbox,"Making Progress","Complete a ‘segment’ while having all days marked as complete.",
                new AchievementType(AchievementRanking.SILVER, 10),false));

        achievements.add(new Achievement(R.drawable.carrot_trophy,R.drawable.checkbox, "Vegan Master Streak","Achieve a 25-day vegan streak.",
                new AchievementType(AchievementRanking.GOLD, 25),true));

        user = new User(achievements,null,null,0,0);
        user.setLongestStreak(25);
        user.setTotalVeganDays(35);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
