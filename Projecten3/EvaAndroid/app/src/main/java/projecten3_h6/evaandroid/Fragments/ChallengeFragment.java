package projecten3_h6.evaandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Adapters.AchievementDetailAdapter;
import projecten3_h6.evaandroid.Adapters.ChallengeAdapter;
import projecten3_h6.evaandroid.Domain.Achievement;
import projecten3_h6.evaandroid.Domain.Challenge;
import projecten3_h6.evaandroid.Domain.EvaApplication;
import projecten3_h6.evaandroid.Domain.User;
import projecten3_h6.evaandroid.R;

/**
 * Created by jensleirens on 25/07/2017.
 */

public class ChallengeFragment extends Fragment {

    @BindView(R.id.challengeRecyclerView)RecyclerView mRecycler;

    List<Challenge> challenges = new ArrayList<>();
    protected RecyclerView.LayoutManager mLayoutManager;
    private ChallengeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_challenges, container, false);
        ButterKnife.bind(this,v);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(mLayoutManager);

        Context context = getContext();
        EvaApplication app = (EvaApplication)context.getApplicationContext();
        User user = app.getUser();

        user.getRemoteChallenges();
        challenges = user.getChallenges();

        adapter = new ChallengeAdapter(challenges);
        mRecycler.setAdapter(adapter);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
