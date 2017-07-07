package projecten3_h6.evaandroid.Fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Domain.Day;
import projecten3_h6.evaandroid.R;

/**
 * Created by jensleirens on 07/07/2017.
 */

public class ProgressEmptyAdapter  extends RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder>{

    private int itemCount;
    private ArrayList<Day> days;

    public ProgressEmptyAdapter(ArrayList<Day> days) {
        this.days = days;
        this.itemCount = days.size();
    }

    @Override
    public ProgressAdapter.ProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_progress_empty,parent,false);
        return new ProgressAdapter.ProgressViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProgressAdapter.ProgressViewHolder holder, int position) {
        TextView progressDayOfTheWeek = holder.progressDayOfTheWeek;
        TextView progressDishTitle = holder.progressDishTitle;
        ImageView progressCardImage = holder.progressCardImage;

        //the logic will be implemented on the fragment itself




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

        @BindView(R.id.progresscardImage)
        public ImageView progressCardImage;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
