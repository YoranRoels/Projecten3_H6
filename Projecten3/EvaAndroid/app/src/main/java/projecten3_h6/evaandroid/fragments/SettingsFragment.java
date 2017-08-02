package projecten3_h6.evaandroid.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import projecten3_h6.evaandroid.domain.EvaApplication;
import projecten3_h6.evaandroid.R;

public class SettingsFragment extends Fragment {

    @BindView(R.id.toggle_automatic_shoppinglist) ToggleButton toggleAutomaticShoppinglist;

    EvaApplication app;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this,v);

        Context context = getContext();
        app = (EvaApplication)context.getApplicationContext();

        // Set ToggleButton(s)
        toggleAutomaticShoppinglist.setChecked(app.getUser().isAutomaticShoppingEnabled());

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.settings_reset_button)
    public void resetProgress() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Add the buttons
        builder.setTitle("Are you sure you want to delete your progress?");
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                app.setUser(app.createNewUser());
                Toast.makeText(getContext(), R.string.progress_reset, Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
    }

    @OnCheckedChanged(R.id.toggle_automatic_shoppinglist)
    public void changeAutomaticShopping() {
        app.getUser().setAutomaticShoppingEnabled(toggleAutomaticShoppinglist.isChecked());
    }
}