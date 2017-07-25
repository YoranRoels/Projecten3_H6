package projecten3_h6.evaandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Domain.Achievement;
import projecten3_h6.evaandroid.Domain.Challenge;
import projecten3_h6.evaandroid.R;

/**
 * Created by jensleirens on 25/07/2017.
 */

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder>{

    private int itemCount;
    private List<Challenge> challenges;

    public ChallengeAdapter(List<Challenge> challenges) {
        this.challenges = challenges;
        this.itemCount = challenges.size();
    }

    @Override
    public ChallengeAdapter.ChallengeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_challenge,parent,false);
        return new ChallengeAdapter.ChallengeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChallengeAdapter.ChallengeViewHolder holder, int position) {
        TextView challengeTitle = holder.challengeTitle;
        TextView challengeDescription = holder.challengeDescription;

        challengeTitle.setText(challenges.get(position).getTitle());
        challengeDescription.setText(challenges.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public static class  ChallengeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.challengeTitle)
        public TextView challengeTitle;

        @BindView(R.id.challengeDescription)
        public TextView challengeDescription;

        public ChallengeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
