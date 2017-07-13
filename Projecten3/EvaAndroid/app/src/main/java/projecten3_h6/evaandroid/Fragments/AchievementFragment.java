package projecten3_h6.evaandroid.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import projecten3_h6.evaandroid.Adapters.AchievementAdapter;
import projecten3_h6.evaandroid.Domain.Achievement;
import projecten3_h6.evaandroid.Domain.AchievementRanking;
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
    @BindView(R.id.achievementsLayout)LinearLayout achievementsLayout;

    protected RecyclerView.LayoutManager bronzeLayoutManager;
    protected RecyclerView.LayoutManager silverLayoutManager;
    protected RecyclerView.LayoutManager goldLayoutManager;

    private AchievementAdapter bronzeAdapter;
    private AchievementAdapter silverAdapter;
    private AchievementAdapter goldAdapter;
    private User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_achievements, container, false);
        ButterKnife.bind(this,v);

        initdata();
        populateTextViews();

        bronzeLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        silverLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        goldLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

        bronzeRecycler.setLayoutManager(bronzeLayoutManager);
        silverRecycler.setLayoutManager(silverLayoutManager);
        goldRecycler.setLayoutManager(goldLayoutManager);

        bronzeAdapter = new AchievementAdapter(user.getBronzeAchievements());
        silverAdapter = new AchievementAdapter(user.getSilverAchievements());
        goldAdapter = new AchievementAdapter(user.getGoldAchievements());
        bronzeRecycler.setAdapter(bronzeAdapter);
        silverRecycler.setAdapter(silverAdapter);
        goldRecycler.setAdapter(goldAdapter);

        return v;
    }

    public void populateTextViews(){
        totalVeganDays.setText(String.valueOf(user.getTotalVeganDays()));
        maxVeganStreak.setText(String.valueOf(user.getLongestStreak()));

        int achievementsCompleted = user.getCompletedAchievementsCount();
        int achievementsCount = user.getAchievements().size();
        String achievementsEarned = achievementsCompleted + "/" + achievementsCount;

        totalProgressBar.setProgress(achievementsCompleted);
        totalProgressBar.setMax(achievementsCount);
        totalAchievements.setText(achievementsEarned);

        totalBronze.setText(String.valueOf(user.getBronzeAchievements().size()));
        totalSilver.setText(String.valueOf(user.getSilverAchievements().size()));
        totalGold.setText(String.valueOf(user.getGoldAchievements().size()));
    }

    public void initdata(){
        List<Achievement> achievements = new ArrayList<>();

        achievements.add(new Achievement(R.drawable.bronze_app_completed,R.drawable.bronze_app,"We're Just Getting Started","Launch the app.",
                AchievementRanking.BRONZE,true));

        achievements.add(new Achievement(R.drawable.bronze_calendar_completed,R.drawable.bronze_calendar,"I’m On a Regime","Open the ‘Progress’ tab.",
                AchievementRanking.BRONZE,true));

        achievements.add(new Achievement(R.drawable.bronze_cooking_completed,R.drawable.bronze_cooking,"What’s For Dinner?","Open the ‘Today’ tab.",
                AchievementRanking.BRONZE,false));

        achievements.add(new Achievement(R.drawable.silver_checkbox_completed,R.drawable.silver_checkbox,"Making Progress","Complete a ‘segment’ while having all days marked as complete.",
                AchievementRanking.SILVER,false));

        achievements.add(new Achievement(R.drawable.gold_streak_25_completed,R.drawable.gold_streak_25, "Vegan Master Streak","Achieve a 25-day vegan streak.",
                AchievementRanking.GOLD,true));

        user = new User(achievements,null,null,0,0);
        user.setLongestStreak(15);
        user.setTotalVeganDays(34);

        user.assignAchievements();
        user.countCompletedAchievements();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.achievementsLayout)
    public void openAchievementDetails() {
        Fragment achievementDetailFragment = new AchievementDetailFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, achievementDetailFragment);
        fragmentTransaction.addToBackStack(achievementDetailFragment.toString());
        fragmentTransaction.commit();
    }
}
