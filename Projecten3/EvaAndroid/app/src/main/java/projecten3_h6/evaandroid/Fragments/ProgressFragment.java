package projecten3_h6.evaandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import projecten3_h6.evaandroid.Adapters.ProgressAdapter;
import projecten3_h6.evaandroid.Domain.Day;
import projecten3_h6.evaandroid.Domain.Dish;
import projecten3_h6.evaandroid.Domain.EvaApplication;
import projecten3_h6.evaandroid.Network.Calls;
import projecten3_h6.evaandroid.Network.Config;
import projecten3_h6.evaandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgressFragment extends Fragment implements ProgressPickerDialog.DialogListener{
    // Progress checkboxes
    @BindView(R.id.progressBox1) ImageView progressBox1;
    @BindView(R.id.progressBox2) ImageView progressBox2;
    @BindView(R.id.progressBox3) ImageView progressBox3;
    @BindView(R.id.progressBox4) ImageView progressBox4;
    @BindView(R.id.progressBox5) ImageView progressBox5;
    @BindView(R.id.progressBox6) ImageView progressBox6;
    @BindView(R.id.progressBox7) ImageView progressBox7;
    @BindView(R.id.progressBox8) ImageView progressBox8;
    @BindView(R.id.progressBox9) ImageView progressBox9;
    @BindView(R.id.progressBox10) ImageView progressBox10;
    @BindView(R.id.progressBox11) ImageView progressBox11;
    @BindView(R.id.progressBox12) ImageView progressBox12;
    @BindView(R.id.progressBox13) ImageView progressBox13;
    @BindView(R.id.progressBox14) ImageView progressBox14;
    @BindView(R.id.progressBox15) ImageView progressBox15;
    @BindView(R.id.progressBox16) ImageView progressBox16;
    @BindView(R.id.progressBox17) ImageView progressBox17;
    @BindView(R.id.progressBox18) ImageView progressBox18;
    @BindView(R.id.progressBox19) ImageView progressBox19;
    @BindView(R.id.progressBox20) ImageView progressBox20;
    @BindView(R.id.progressBox21) ImageView progressBox21;
    @BindView(R.id.progressBox22) ImageView progressBox22;
    @BindView(R.id.progressBox23) ImageView progressBox23;
    @BindView(R.id.progressBox24) ImageView progressBox24;
    public static ImageView progressBoxes[];

    // Top Textviews
    @BindView(R.id.progressTextView) TextView progressTextView;
    @BindView(R.id.progressMotivationTextView) TextView progressMotivationTextView;
    public static TextView progressTextViews[];

    // Recyclerview
    @BindView(R.id.progressRecyclerView) RecyclerView mRecyclerView;

    // Bottom Button
    @BindView(R.id.finishSegment) Button finishSegment;

    // Segment size
    private int segmentSize;

    // Objects
    public static List<Dish> choices;
    private List<Day> days;

    // Boolean defining the ability to click on a progress-card depending on your internet connection
    public boolean internetConnection = false;

    // Achievements
    public static EvaApplication app;
    private LayoutInflater inflater;
    private ViewGroup container;

    // Rest
    private ProgressOnclickListener progressOnclickListener;
    protected RecyclerView.LayoutManager mLayoutManager;
    public static int pos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_progress, container, false);
        this.inflater = inflater;
        this.container = container;
        ButterKnife.bind(this,v);

        Context context = getContext();
        app = (EvaApplication)context.getApplicationContext();
        days = app.getUser().getDays();
        segmentSize  = app.getSegmentSize();

        progressBoxes = new ImageView[]{progressBox1, progressBox2, progressBox3, progressBox4, progressBox5, progressBox6,
                progressBox7, progressBox8, progressBox9, progressBox10, progressBox11, progressBox12, progressBox13,
                progressBox14, progressBox15, progressBox16, progressBox17, progressBox18, progressBox19,
                progressBox20, progressBox21, progressBox22, progressBox23, progressBox24};

        progressTextViews = new TextView[]{progressTextView, progressMotivationTextView};

        if(days.isEmpty()) {
            progressTextView.setText(R.string.welcome);
            progressMotivationTextView.setText(R.string.welcome_advice);
            startFirstSegment();
            initChoices();
        } else {
            checkSegmentButtonEnabled();
            setMotivationTextViews();
        }

        recheckCheckboxes();

        progressOnclickListener = new ProgressOnclickListener(getContext());
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        reloadCards();

        // Achievement earned
        app.earnAchievement(getContext(), inflater, container, "I’m On a Regime");
        return v;
    }

    public void reloadCards(){
        initChoices();
        checkSegmentButtonEnabled();
        mRecyclerView.setAdapter(new ProgressAdapter(app.getUser().getLastThreeDays(), progressOnclickListener));
    }

    public static void recheckCheckboxes() {
        int checkBoxCount = progressBoxes.length;
        int daysCount = app.getUser().getDays().size();
        int startingDayIndex;
        int sizeToCheck;
        if(daysCount > checkBoxCount) {
            startingDayIndex = daysCount - checkBoxCount;
            sizeToCheck = checkBoxCount;
        } else {
            startingDayIndex = 0;
            sizeToCheck = daysCount;
        }
        for(int i = 0; i < sizeToCheck; i++) {
            if(app.getUser().getDays().get(i + startingDayIndex).isCompleted())
            {
                progressBoxes[i].setImageResource(R.drawable.checkbox_completed);
            } else {
                progressBoxes[i].setImageResource(R.drawable.checkbox);
            }
        }
    }

    public static void setMotivationTextViews() {
        progressTextViews[0].setText("You've had " + app.getUser().getTotalVeganDays() + " vegan days.");
        if(app.getUser().getTotalVeganDays() == app.getUser().getLongestStreak()) {
            progressTextViews[1].setText(R.string.no_skipped_days);
        } else {
            progressTextViews[1].setText(R.string.skipped_days);
        }
    }

    private void startFirstSegment(){
        Calendar cal = Calendar.getInstance();
        cal.getTime();

        for(int i = 0; i < segmentSize; i++) {
            days.add(new Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_WEEK),cal.get(Calendar.DAY_OF_YEAR)));
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }
    }

    private void startNewSegment() {
        int lastDayIndex = days.size()-1;
        Day lastDay = days.get(lastDayIndex);

        Calendar cal = Calendar.getInstance();
        cal.set(lastDay.getYear(), lastDay.getMonth(), lastDay.getDayOfTheMonth());

        for(int i = 0; i < segmentSize; i++) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            days.add(new Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_WEEK), cal.get(Calendar.DAY_OF_YEAR)));
        }
    }

    private void checkSegmentButtonEnabled() {
        // Now
        Calendar today = Calendar.getInstance();
        // Last Date
        int daysLength = days.size();
        Day lastDay = days.get(daysLength-1);
        // Check
        if(today.get(Calendar.DAY_OF_YEAR) >= lastDay.getDayOfTheYear()
                || today.get(Calendar.YEAR) > lastDay.getYear()){
            finishSegment.setVisibility(View.VISIBLE);
        } else {
            finishSegment.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick(R.id.finishSegment)
    public void finishSegment() {
        // Achievements
        if(days.get(days.size()-segmentSize).isCompleted()
                && days.get(days.size()-segmentSize + 1).isCompleted() &&
                days.get(days.size()-segmentSize + 2).isCompleted()) {
            app.earnAchievement(getContext(), inflater, container, "Making Progress");
        } else {
            app.earnAchievement(getContext(), inflater, container, "Cheat Day");
        }
        if(app.getUser().getLongestStreak() >= 25) {
            app.earnAchievement(getContext(), inflater, container, "Vegan Pro Streak");
        } else if(app.getUser().getLongestStreak() >= 10) {
            app.earnAchievement(getContext(), inflater, container, "Vegan Master Streak");
        }
        if(app.getUser().getTotalVeganDays() >= 100) {
            app.earnAchievement(getContext(), inflater, container, "Vegan Master");
        }

        startNewSegment();
        reloadCards();
        recheckCheckboxes();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void initChoices(){
        Calls caller = Config.getRetrofit().create(Calls.class);
        Call<List<Dish>> call = caller.getThreeRandomDishes();
        call.enqueue(new Callback<List<Dish>>() {
            @Override
            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                choices = response.body();
                internetConnection = true;
                Log.e("Backend call: ", "call successful (three random dishes)");
            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t) {
                internetConnection = false;
                Log.e("Backend call: ", "call failed (three random dishes) "+ t.getMessage());
            }
        });

    }

    @Override
    public void onDialogClick(DialogFragment dialog) {
        reloadCards();
    }

    public class ProgressOnclickListener implements View.OnClickListener{

        private final Context context;

        public ProgressOnclickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            if(internetConnection) {
                pos = mRecyclerView.getChildAdapterPosition(v);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Calendar c = Calendar.getInstance();
                Day tappedDay = days.get(days.size() - segmentSize + pos);
                if (tappedDay.getDish() == null
                        && (tappedDay.getDayOfTheYear() >= c.get(Calendar.DAY_OF_YEAR)
                        || tappedDay.getYear() > c.get(Calendar.DAY_OF_YEAR))) {
                    ProgressPickerDialog ppd = new ProgressPickerDialog();
                    ppd.show(fm, "food picker");
                }

            } else
            {
                Toast.makeText(getContext(),"No internet connection",Toast.LENGTH_LONG).show();
            }
        }


    }
}
