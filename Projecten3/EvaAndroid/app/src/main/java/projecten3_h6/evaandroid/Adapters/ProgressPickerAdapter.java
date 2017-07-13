package projecten3_h6.evaandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Domain.Dish;
import projecten3_h6.evaandroid.Fragments.ProgressPickerDialog;
import projecten3_h6.evaandroid.R;

/**
 * Created by jensleirens on 11/07/2017.
 */

public class ProgressPickerAdapter extends RecyclerView.Adapter<ProgressPickerAdapter.ProgressPickerViewHolder>{

    private int itemCount;
    private List<Dish> choices;

    public ProgressPickerAdapter(List<Dish> choices) {
        this.choices = choices;
        this.itemCount = choices.size();
    }

    @Override
    public ProgressPickerAdapter.ProgressPickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_picker,parent,false);
        v.setOnClickListener(ProgressPickerDialog.progressPickerOnclickListener);
        return new ProgressPickerAdapter.ProgressPickerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProgressPickerAdapter.ProgressPickerViewHolder holder, int position) {
        TextView pickerDishTitle = holder.pickerDishTitle;
        ImageView pickerDishImage = holder.pickerDishImage;

        pickerDishTitle.setText(choices.get(position).getName());
        Context context = holder.pickerDishImage.getContext();
        Picasso.with(context).load(choices.get(position).getImageId()).into(pickerDishImage);
        pickerDishImage.setImageResource(choices.get(position).getImageId());

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public static class ProgressPickerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.progressPickerCardDishTitle)
        public TextView pickerDishTitle;


        @BindView(R.id.pickerDishImage)
        public ImageView pickerDishImage;

        public ProgressPickerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
