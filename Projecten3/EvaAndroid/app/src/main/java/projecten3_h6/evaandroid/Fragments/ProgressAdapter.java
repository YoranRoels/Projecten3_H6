package projecten3_h6.evaandroid.Fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import projecten3_h6.evaandroid.Domain.Day;
import projecten3_h6.evaandroid.Domain.Ingredient;
import projecten3_h6.evaandroid.R;

/**
 * Created by jensleirens on 07/07/2017.
 */

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder>{

    private int itemCount;
    private ArrayList<Day> days;

    public ProgressAdapter(ArrayList<Day> days) {
        this.days = days;
        this.itemCount = days.size();
    }

    @Override
    public ProgressAdapter.ProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_progress,parent,false);
        return new ProgressAdapter.ProgressViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProgressAdapter.ProgressViewHolder holder, int position) {
        TextView progressDayOfTheWeek = holder.progressDayOfTheWeek;
        TextView progressDishTitle = holder.progressDishTitle;
        Button toggleComplete = holder.toggleComplete; // todo implement button
        ImageView progressCardImage = holder.progressCardImage;


        if(days.get(position).getCompleted() == false){
            progressDayOfTheWeek.setText(days.get(position).getDateOfTheWeek());
            progressDishTitle.setText(days.get(position).getDish().getName());
            Context context = holder.progressCardImage.getContext();
            Picasso.with(context).load(days.get(position).getDish().getImageId()).into(progressCardImage);
            progressCardImage.setImageResource(days.get(position).getDish().getImageId());
        } else {
            // do something
        }



        // todo setlistener for "complete" button

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
        public Button toggleComplete;


        @BindView(R.id.progresscardImage)
        public ImageView progressCardImage;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
