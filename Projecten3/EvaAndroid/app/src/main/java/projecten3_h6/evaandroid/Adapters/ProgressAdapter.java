package projecten3_h6.evaandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.squareup.picasso.Picasso;
import java.util.Calendar;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Domain.Day;
import projecten3_h6.evaandroid.Fragments.ProgressFragment;
import projecten3_h6.evaandroid.R;

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder> {

    private int itemCount;
    private List<Day> currentDays;
    private ViewGroup parent;
    private ProgressFragment.ProgressOnclickListener progressOnclickListener;

    public ProgressAdapter(List<Day> currentDays, ProgressFragment.ProgressOnclickListener progressOnclickListener) {
        this.currentDays = currentDays;
        this.progressOnclickListener = progressOnclickListener;
        itemCount = currentDays.size();
    }

    @Override
    public ProgressAdapter.ProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_progress, parent, false);
        this.parent = parent;
        v.setOnClickListener(progressOnclickListener);
        return new ProgressAdapter.ProgressViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ProgressAdapter.ProgressViewHolder holder, final int position) {
        TextView progressDayOfTheWeek = holder.progressDayOfTheWeek;
        TextView progressDishTitle = holder.progressDishTitle;
        ToggleButton toggleComplete = holder.toggleComplete;
        ImageView dayDishImage = holder.dayDishImage;
        Calendar today = Calendar.getInstance();
        final int holderPosition = holder.getAdapterPosition();
        progressDayOfTheWeek.setText(currentDays.get(holderPosition).getDayOfTheWeekString());

        if (currentDays.get(holderPosition).getDish() != null) {
            progressDishTitle.setText(currentDays.get(holderPosition).getDish().getName());
            Context context = holder.dayDishImage.getContext();
            Picasso.with(context).load(currentDays.get(holderPosition).getDish().getImageId()).into(dayDishImage);
            dayDishImage.setImageResource(currentDays.get(holderPosition).getDish().getImageId());
            toggleComplete.setChecked(currentDays.get(holderPosition).isCompleted());
            if (today.get(Calendar.DAY_OF_YEAR) >= currentDays.get(holderPosition).getDayOfTheYear()
                    || today.get(Calendar.YEAR) > currentDays.get(holderPosition).getYear()) {
                toggleComplete.setVisibility(View.VISIBLE);
            }
        } else if (today.get(Calendar.DAY_OF_YEAR) > currentDays.get(holderPosition).getDayOfTheYear()
                || today.get(Calendar.YEAR) > currentDays.get(holderPosition).getYear()) {
            progressDishTitle.setText(R.string.forgot_dish);
        }

        toggleComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int daysLength = ProgressFragment.app.getUser().getDays().size();
                ProgressFragment.app.getUser().getDays().get(daysLength - itemCount + holderPosition).setCompleted(isChecked);
                ProgressFragment.app.getUser().calculateStatistics();
                ProgressFragment.recheckCheckboxes();
                ProgressFragment.setMotivationTextViews();
                // Achievement earned
                ProgressFragment.app.earnAchievement(parent.getContext(), LayoutInflater.from(parent.getContext()), parent, "Vegan Rookie");
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.progressCardDay)
        public TextView progressDayOfTheWeek;

        @BindView(R.id.progressCardDishTitle)
        public TextView progressDishTitle;

        @BindView(R.id.toggleComplete)
        public ToggleButton toggleComplete;


        @BindView(R.id.dayDishImage)
        public ImageView dayDishImage;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
