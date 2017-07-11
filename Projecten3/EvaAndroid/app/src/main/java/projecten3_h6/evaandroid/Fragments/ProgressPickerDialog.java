package projecten3_h6.evaandroid.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import java.util.List;

import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Domain.Dish;
import projecten3_h6.evaandroid.R;
import butterknife.BindView;

/**
 * Created by jensleirens on 11/07/2017.
 */

public class ProgressPickerDialog extends Dialog implements android.view.View.OnClickListener{

    public Activity a;
    public Dialog d;
    public List<Dish> choices;
    protected RecyclerView.LayoutManager mLayoutManager;
    public ShoppinglistAdapter adapter ;

    @BindView(R.id.shoppingListRecyclerView)
    RecyclerView mRecyclerView;



    public ProgressPickerDialog(Activity a,List<Dish> choices){
        super(a);
        this.a = a;
        this.choices = choices;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_progress_picker);
        ButterKnife.bind(this);

    }

    @Override
    public void onClick(View v) {

    }
}
