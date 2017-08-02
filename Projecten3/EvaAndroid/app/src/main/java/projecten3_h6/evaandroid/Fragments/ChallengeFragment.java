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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Adapters.ChallengeAdapter;
import projecten3_h6.evaandroid.Domain.EvaApplication;
import projecten3_h6.evaandroid.R;

public class ChallengeFragment extends Fragment {

    @BindView(R.id.challengeRecyclerView) RecyclerView mRecycler;
    @BindView(R.id.challengeCoinsTextView) TextView challengeCoinsTextView;

    protected RecyclerView.LayoutManager mLayoutManager;

    public static EvaApplication app;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_challenges, container, false);
        ButterKnife.bind(this, v);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(mLayoutManager);

        Context context = getContext();
        app = (EvaApplication) context.getApplicationContext();

        challengeCoinsTextView.setText(String.format(Integer.toString(app.getUser().getChallengeCoins()), Locale.ENGLISH));

        // If today exists (only after resetting and going STRAIGHT to challenges)
        if (app.getUser().getToday() != null) {
            if (app.getUser().getToday().getDish() == null) {
                Toast.makeText(getContext(), "Choose today's dish to receive your daily challenges", Toast.LENGTH_LONG).show();
            } else {
                ChallengeAdapter adapter = new ChallengeAdapter(app.getUser().getToday().getChallenges(), challengeCoinsTextView);
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
