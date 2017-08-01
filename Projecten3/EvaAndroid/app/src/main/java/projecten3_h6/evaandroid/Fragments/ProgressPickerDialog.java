package projecten3_h6.evaandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.List;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Adapters.ProgressPickerAdapter;
import projecten3_h6.evaandroid.Domain.Day;
import projecten3_h6.evaandroid.Domain.EvaApplication;
import projecten3_h6.evaandroid.Domain.Ingredient;
import projecten3_h6.evaandroid.R;
import butterknife.BindView;

public class ProgressPickerDialog extends DialogFragment {

    public ProgressPickerOnclickListener progressPickerOnclickListener;
    protected RecyclerView.LayoutManager mLayoutManager;
    public ProgressPickerAdapter adapter;
    @BindView(R.id.progressPickerRecyclerView)
    RecyclerView mRecyclerView;
    DialogFragment df;
    DialogListener mListener;

    // Achievement
    EvaApplication app;
    LayoutInflater inflater;
    ViewGroup container;

    // Segment size
    private int segmentSize;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_progress_picker, container,false);
        this.inflater = inflater;
        this.container = container;
        ButterKnife.bind(this,v);

        Context context = getContext();
        app = (EvaApplication)context.getApplicationContext();
        segmentSize = app.getSegmentSize();

        df = this;
        progressPickerOnclickListener = new ProgressPickerOnclickListener(getContext());

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new ProgressPickerAdapter(ProgressFragment.choices,progressPickerOnclickListener);
        mRecyclerView.setAdapter(adapter);
        return v;
    }

    public interface DialogListener {
        void onDialogClick(DialogFragment dialog);
    }

    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        // Instantiate the DialogListener so we can send events to the host
        mListener = (DialogListener) getActivity().getSupportFragmentManager().findFragmentById(R.id.content_frame) ;

    }

    public class ProgressPickerOnclickListener implements View.OnClickListener {

        private final Context context;

        public ProgressPickerOnclickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            List<Day> days = ProgressFragment.app.getUser().getDays();
            int posOfDish;
            posOfDish = mRecyclerView.getChildAdapterPosition(v);

            // Set correct Dish
            Day clickedDay = days.get(days.size() - segmentSize + ProgressFragment.pos);
            clickedDay.setDish(ProgressFragment.choices.get(posOfDish));

            // Add ingredients to ShoppingList
            if(app.getUser().isAutomaticShoppingEnabled()) {
                List<Ingredient> shoppingListIngredients = app.getUser().getShoppingList().getIngredients();
                for (Ingredient i : clickedDay.getDish().getIngredients()) {
                    shoppingListIngredients.add(i);
                }
                Toast.makeText(getActivity(), "The ingredients for " + "\"" + clickedDay.getDish().getName() + "\"" + " have been added to the shopping list.", Toast.LENGTH_LONG).show();
            }
            // Achievement
            if(days.get(days.size() - segmentSize).getDish() != null &&
                    days.get(days.size() - segmentSize + 1).getDish() != null &&
                    days.get(days.size() - segmentSize + 2).getDish() != null) {
                app.earnAchievement(getContext(), inflater, container, "Planning Ahead");
            }
            mListener.onDialogClick(df);
            dismiss();
        }
    }
}