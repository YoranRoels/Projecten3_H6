package projecten3_h6.evaandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Adapters.ChallengeAdapter;
import projecten3_h6.evaandroid.Domain.Challenge;
import projecten3_h6.evaandroid.Domain.ChallengeType;
import projecten3_h6.evaandroid.Domain.Day;
import projecten3_h6.evaandroid.Domain.EvaApplication;
import projecten3_h6.evaandroid.Network.Calls;
import projecten3_h6.evaandroid.Network.Config;
import projecten3_h6.evaandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengeFragment extends Fragment {

    @BindView(R.id.challengeRecyclerView) RecyclerView mRecycler;

    protected RecyclerView.LayoutManager mLayoutManager;
    public EvaApplication app;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_challenges, container, false);
        ButterKnife.bind(this, v);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(mLayoutManager);

        Context context = getContext();
        app = (EvaApplication) context.getApplicationContext();


        // If today exists (only after resetting and going STRAIGHT to challenges)
        if (app.getUser().getToday() != null) {
            if (app.getUser().getToday().getDish() == null) {
                Toast.makeText(getContext(), "Choose today's dish to receive your daily challenges", Toast.LENGTH_LONG).show();
            } else {
                ChallengeAdapter adapter = new ChallengeAdapter(app.getUser().getToday().getChallenges());
                mRecycler.setAdapter(adapter);
            }
        } else {
            Toast.makeText(getContext(), "Visit the Progress tab to get started.", Toast.LENGTH_LONG).show();
        }



        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
