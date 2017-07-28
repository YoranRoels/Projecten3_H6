package projecten3_h6.evaandroid.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Domain.Challenge;
import projecten3_h6.evaandroid.R;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder> {

    private int itemCount;
    private List<Challenge> challenges;

    public ChallengeAdapter(List<Challenge> challenges) {
        this.challenges = challenges;
        this.itemCount = challenges.size();
    }

    @Override
    public ChallengeAdapter.ChallengeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_challenge, parent, false);
        return new ChallengeAdapter.ChallengeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChallengeAdapter.ChallengeViewHolder holder, final int position) {
        TextView challengeTitle = holder.challengeTitle;
        TextView challengeDescription = holder.challengeDescription;
        ToggleButton toggleComplete = holder.toggleComplete;
        final int holderPosition = holder.getAdapterPosition();

        //setting the texts
        challengeTitle.setText(challenges.get(holderPosition).getTitle());
        challengeDescription.setText(challenges.get(holderPosition).getDescription());

        //if challenge is already completed check toggleButton
        toggleComplete.setChecked(challenges.get(holderPosition).isCompleted());

        toggleComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                challenges.get(holderPosition).setCompleted(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public static class ChallengeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.challengeTitle)
        public TextView challengeTitle;

        @BindView(R.id.challengeDescription)
        public TextView challengeDescription;

        @BindView(R.id.challengeToggleComplete)
        public ToggleButton toggleComplete;

        public ChallengeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
