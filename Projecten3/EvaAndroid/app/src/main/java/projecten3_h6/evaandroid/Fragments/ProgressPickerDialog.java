package projecten3_h6.evaandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Adapters.ProgressPickerAdapter;
import projecten3_h6.evaandroid.R;
import butterknife.BindView;

/**
 * Created by jensleirens on 11/07/2017.
 */

public class ProgressPickerDialog extends DialogFragment {

    public static ProgressPickerOnclickListener progressPickerOnclickListener;
    protected RecyclerView.LayoutManager mLayoutManager;
    public ProgressPickerAdapter adapter ;
    @BindView(R.id.progressPickerRecyclerView)
    RecyclerView mRecyclerView;
    DialogFragment df;
    DialogListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_progress_picker, container,false);
        ButterKnife.bind(this,v);

        df = this;
        progressPickerOnclickListener = new ProgressPickerOnclickListener(getContext());

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new ProgressPickerAdapter(ProgressFragment.choices);
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

    private class ProgressPickerOnclickListener implements View.OnClickListener {

        private final Context context;

        public ProgressPickerOnclickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int posOfDish;
            posOfDish = mRecyclerView.getChildAdapterPosition(v);

            ProgressFragment.days.get(ProgressFragment.pos).setDish(ProgressFragment.choices.get(posOfDish));
            mListener.onDialogClick(df);
            dismiss();

        }
    }
}