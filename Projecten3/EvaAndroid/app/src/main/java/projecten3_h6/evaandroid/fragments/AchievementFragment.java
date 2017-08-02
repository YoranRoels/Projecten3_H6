package projecten3_h6.evaandroid.fragments;

import android.content.Context;
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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import projecten3_h6.evaandroid.adapters.AchievementAdapter;
import projecten3_h6.evaandroid.domain.EvaApplication;
import projecten3_h6.evaandroid.domain.User;
import projecten3_h6.evaandroid.R;

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

    private User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_achievements, container, false);
        ButterKnife.bind(this,v);

        Context context = getContext();
        EvaApplication app = (EvaApplication)context.getApplicationContext();
        user = app.getUser();
        populateTextViews();

        AchievementAdapter bronzeAdapter;
        AchievementAdapter silverAdapter;
        AchievementAdapter goldAdapter;

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

        totalBronze.setText(String.valueOf(user.getCompletedBronzeAchievementsCount()));
        totalSilver.setText(String.valueOf(user.getCompletedSilverAchievementsCount()));
        totalGold.setText(String.valueOf(user.getCompletedGoldAchievementsCount()));
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
        fragmentTransaction.commit();
    }
}
