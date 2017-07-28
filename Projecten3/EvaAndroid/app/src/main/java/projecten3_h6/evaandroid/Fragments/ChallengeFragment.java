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
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Adapters.AchievementDetailAdapter;
import projecten3_h6.evaandroid.Adapters.ChallengeAdapter;
import projecten3_h6.evaandroid.Domain.Achievement;
import projecten3_h6.evaandroid.Domain.Challenge;
import projecten3_h6.evaandroid.Domain.Day;
import projecten3_h6.evaandroid.Domain.EvaApplication;
import projecten3_h6.evaandroid.Domain.User;
import projecten3_h6.evaandroid.R;

public class ChallengeFragment extends Fragment {

    @BindView(R.id.challengeRecyclerView)RecyclerView mRecycler;

    protected RecyclerView.LayoutManager mLayoutManager;
    public EvaApplication app ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_challenges, container, false);
        ButterKnife.bind(this,v);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(mLayoutManager);

        //if its the first time the challengeFragment is loaded
        if(app ==  null) {
            Context context = getContext();
            app = (EvaApplication) context.getApplicationContext();
        }

        //if today is not yet set, set it
        if(app.getUser().getToday() == null) {
            app.getUser().getToday();
            app.getUser().getToday().getRemoteChallenges();
            System.out.println("today setted");
        }

        //if the daily challenges are not loaded yet
        if(app.getUser().getToday().getChallenges().size() == 0){
            //get the remote challenges of the backend
            app.getUser().getToday().getRemoteChallenges();
        }

        ChallengeAdapter adapter;
        adapter = new ChallengeAdapter(app.getUser().getToday().getChallenges());
        mRecycler.setAdapter(adapter);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
