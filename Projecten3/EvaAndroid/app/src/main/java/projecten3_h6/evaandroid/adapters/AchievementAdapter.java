package projecten3_h6.evaandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.domain.Achievement;
import projecten3_h6.evaandroid.R;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder> {

    private int itemCount;
    private List<Achievement> achievements;

    public AchievementAdapter(List<Achievement> achievements) {
        this.achievements = achievements;
        this.itemCount = achievements.size();
    }

    @Override
    public AchievementAdapter.AchievementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_achievement, parent, false);
        return new AchievementAdapter.AchievementViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AchievementAdapter.AchievementViewHolder holder, int position) {
        ImageView achievementImage = holder.achievementImage;

        Context context = holder.achievementImage.getContext();

        if (achievements.get(position).isCompleted()) {
            Picasso.with(context).load(achievements.get(position).getCompletedImageId()).into(achievementImage);
        } else {
            Picasso.with(context).load(achievements.get(position).getImageId()).into(achievementImage);
        }

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public static class AchievementViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.achievementCardImage)
        public ImageView achievementImage;

        public AchievementViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}