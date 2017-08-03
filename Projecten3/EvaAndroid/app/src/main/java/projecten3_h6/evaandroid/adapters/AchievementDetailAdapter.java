package projecten3_h6.evaandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.domain.Achievement;
import projecten3_h6.evaandroid.R;


public class
AchievementDetailAdapter extends RecyclerView.Adapter<AchievementDetailAdapter.AchievementDetailViewHolder> {

    private int itemCount;
    private List<Achievement> achievements;

    public AchievementDetailAdapter(List<Achievement> achievements) {
        this.achievements = achievements;
        this.itemCount = achievements.size();
    }

    @Override
    public AchievementDetailAdapter.AchievementDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_achievement_detail, parent, false);
        return new AchievementDetailAdapter.AchievementDetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AchievementDetailAdapter.AchievementDetailViewHolder holder, int position) {
        ImageView achievementDetailImage = holder.achievementDetailImage;
        TextView achievementTitle = holder.achievementTitle;
        TextView achievementDescription = holder.achievementDescription;

        Context context = holder.achievementDetailImage.getContext();

        if (achievements.get(position).isCompleted()) {
            Picasso.with(context).load(achievements.get(position).getCompletedImageId()).into(achievementDetailImage);
        } else {
            Picasso.with(context).load(achievements.get(position).getImageId()).into(achievementDetailImage);
        }

        achievementTitle.setText(achievements.get(position).getTitle());
        achievementDescription.setText(achievements.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public static class AchievementDetailViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.achievementDetailImage)
        public ImageView achievementDetailImage;

        @BindView(R.id.achievementDetailTitle)
        public TextView achievementTitle;

        @BindView(R.id.achievementDetailDescription)
        public TextView achievementDescription;

        public AchievementDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

