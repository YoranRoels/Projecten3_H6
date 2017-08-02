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
import projecten3_h6.evaandroid.domain.Dish;
import projecten3_h6.evaandroid.fragments.ProgressPickerDialog;
import projecten3_h6.evaandroid.R;

public class ProgressPickerAdapter extends RecyclerView.Adapter<ProgressPickerAdapter.ProgressPickerViewHolder> {

    private int itemCount;
    private List<Dish> choices;
    private ProgressPickerDialog.ProgressPickerOnclickListener onclickListener;

    public ProgressPickerAdapter(List<Dish> choices, ProgressPickerDialog.ProgressPickerOnclickListener onclickListener) {
        this.choices = choices;
        this.itemCount = choices.size();
        this.onclickListener = onclickListener;
    }

    @Override
    public ProgressPickerAdapter.ProgressPickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_picker, parent, false);
        v.setOnClickListener(onclickListener);
        return new ProgressPickerAdapter.ProgressPickerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProgressPickerAdapter.ProgressPickerViewHolder holder, int position) {
        TextView pickerDishTitle = holder.pickerDishTitle;
        ImageView pickerDishImage = holder.pickerDishImage;

        if (choices.get(position).getImageId() == 0) {
            choices.get(position).setImageId(R.drawable.dish_placeholder);
        }
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
            ButterKnife.bind(this, itemView);
        }
    }
}
