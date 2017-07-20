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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import projecten3_h6.evaandroid.Adapters.ProgressAdapter;
import projecten3_h6.evaandroid.Domain.CookingTime;
import projecten3_h6.evaandroid.Domain.Day;
import projecten3_h6.evaandroid.Domain.Dish;
import projecten3_h6.evaandroid.Domain.EvaApplication;
import projecten3_h6.evaandroid.Domain.Ingredient;
import projecten3_h6.evaandroid.Domain.DishType;
import projecten3_h6.evaandroid.Domain.User;
import projecten3_h6.evaandroid.Network.Calls;
import projecten3_h6.evaandroid.Network.Config;
import projecten3_h6.evaandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Yoran on 07/07/2017.
 */

public class ProgressFragment extends Fragment implements ProgressPickerDialog.DialogListener{
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

    public static ImageView progressBoxes[];

    @BindView(R.id.progressTextView) TextView progressTextView;
    @BindView(R.id.progressMotivationTextView) TextView progressMotivationTextView;
    @BindView(R.id.progressRecyclerView) RecyclerView mRecyclerView;

    @BindView(R.id.finishSegment) Button finishSegment;

    public static ProgressOnclickListener progressOnclickListener;
    protected RecyclerView.LayoutManager mLayoutManager;
    private ProgressAdapter adapter;
    public static List<Dish> choices;
    public static int pos;
    public static User user;
    private List<Day> days;
    public static List<Day> lastThreeDays = new ArrayList<>();
    public static int segmentSize = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_progress, container, false);
        ButterKnife.bind(this,v);



        Context context = getContext();
        EvaApplication app = (EvaApplication)context.getApplicationContext();
        user = app.getUser();
        days = user.getDays();

        progressBoxes = new ImageView[]{progressBox1, progressBox2, progressBox3, progressBox4, progressBox5, progressBox6,
                progressBox7, progressBox8, progressBox9, progressBox10, progressBox11, progressBox12, progressBox13,
                progressBox14, progressBox15, progressBox16, progressBox17, progressBox18, progressBox19,
                progressBox20, progressBox21};

        if(user.getDays().isEmpty()) {
            progressTextView.setText("Welcome to Carrot!");
            progressMotivationTextView.setText("Start tapping the cards to select dishes.");
            startFirstSegment();
        } else {
            checkSegmentButtonEnabled();
            setMotivationTextViews();
        }

        if(days.isEmpty()){
            initChoices();
        }

        recheckCheckboxes();

        progressOnclickListener = new ProgressOnclickListener(getContext());
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        reloadCards();
        return v;
    }

    public void reloadCards(){
        initChoices();
        refreshLastThreeDays();
        adapter = new ProgressAdapter(lastThreeDays);
        mRecyclerView.setAdapter(adapter);
    }

    private void refreshLastThreeDays() {
        lastThreeDays.clear();
        lastThreeDays.add(days.get(days.size()-3));
        lastThreeDays.add(days.get(days.size()-2));
        lastThreeDays.add(days.get(days.size()-1));
    }

    public static void recheckCheckboxes() {
        for(int i = 0; i < user.getDays().size(); i++) {
            if(user.getDays().get(i).isCompleted())
            {
                progressBoxes[i].setImageResource(R.drawable.checkbox_completed);
            } else {
                progressBoxes[i].setImageResource(R.drawable.checkbox);
            }
        }
    }

    public void setMotivationTextViews() {
        progressTextView.setText("You've completed " + user.getTotalVeganDays() + " days this season.");
        if(user.getTotalVeganDays() == user.getLongestStreak()) {
            progressMotivationTextView.setText("You didn't skip a single day. We admire you.");
        } else {
            progressMotivationTextView.setText("Don't give up, keep the vegan streaks coming!");
        }
    }

    private void startFirstSegment(){
        Calendar cal = Calendar.getInstance();
        cal.getTime();

        for(int i = 0; i < segmentSize; i++) {
            user.getDays().add(new Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_WEEK),cal.get(Calendar.DAY_OF_YEAR)));
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }
    }

    private void startNewSegment() {
        int lastDayIndex = user.getDays().size()-1;
        Day lastDay = user.getDays().get(lastDayIndex);

        Calendar cal = Calendar.getInstance();
        cal.set(lastDay.getYear(), lastDay.getMonth(), lastDay.getDayOfTheMonth());

        for(int i = 0; i < segmentSize; i++) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            user.getDays().add(new Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_WEEK), cal.get(Calendar.DAY_OF_YEAR)));
        }
    }

    private void checkSegmentButtonEnabled() {
        // Now
        Calendar c1 = Calendar.getInstance();
        // Last Date
        int daysLength = days.size();
        Day lastDay = days.get(daysLength-1);
        // Check
        if(c1.get(Calendar.DAY_OF_YEAR) >= lastDay.getDayOfTheYear()){
            finishSegment.setVisibility(View.VISIBLE);
        } else {
            finishSegment.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick(R.id.finishSegment)
    public void finishSegment() {
        startNewSegment();
        reloadCards();
        checkSegmentButtonEnabled();
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
                Log.e("BackendCall", " call successful three random dishes");
            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t) {
                if(choices.isEmpty()){
                    choices.add(new Dish(R.drawable.winterovenschotel, "failed api call", CookingTime.LONG, "moeilijk", DishType.APPETIZER,
                            null, ""));
                }
                Log.e("BackendCAll", "failed to call three random dishes "+ t.getMessage());
            }
        });

    }

    @Override
    public void onDialogClick(DialogFragment dialog) {
        reloadCards();
    }

    private class ProgressOnclickListener implements View.OnClickListener{

        private final Context context;

        public ProgressOnclickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            pos = mRecyclerView.getChildAdapterPosition(v) ;
            FragmentManager fm = getActivity().getSupportFragmentManager();
            Calendar c = Calendar.getInstance();
            Day tappedDay = user.getDays().get(days.size() - segmentSize + pos);
            if(tappedDay.getDish() == null && tappedDay.getDayOfTheYear() >= c.get(Calendar.DAY_OF_YEAR)) {
                ProgressPickerDialog ppd = new ProgressPickerDialog();
                ppd.show(fm, "food picker");
            }

        }


    }
}
