package projecten3_h6.evaandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projecten3_h6.evaandroid.Fragments.AchievementFragment;
import projecten3_h6.evaandroid.Fragments.ProgressFragment;
import projecten3_h6.evaandroid.Fragments.ShoppinglistFragment;
import projecten3_h6.evaandroid.Fragments.TodayFragment;

import projecten3_h6.evaandroid.Domain.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view)NavigationView navigationView;

    public static EvaApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        app = (EvaApplication)getApplicationContext();
        //getting the user from the saved file
        app.setUser(getUserOutOfFile());

        displaySelectedScreen(R.id.nav_progress);

        navigationView.setNavigationItemSelectedListener(this);

    }

    private User getUserOutOfFile(){
        User user = new User();
        try {
            FileInputStream fis = getApplicationContext().openFileInput("EvaApplicationUserStorage");
            ObjectInputStream is = new ObjectInputStream(fis);
            user = (User) is.readObject();
            is.close();
            fis.close();

        } catch (Exception e) {
            Log.e("FileInputStream " , e.toString());
        }

        return user;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        displaySelectedScreen(item.getItemId());
        return true;
    }

    public void displaySelectedScreen(int itemId) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;

        if (itemId == R.id.nav_progress) {
            fragment = new ProgressFragment();
            toolbar.setTitle("Progress");
        } else if (itemId == R.id.nav_today) {
            fragment = new TodayFragment();
            toolbar.setTitle("Today");
        } else if (itemId == R.id.nav_shoppinglist) {
            fragment = new ShoppinglistFragment();
            toolbar.setTitle("Shopping List");
        } else if (itemId == R.id.nav_achievements) {
            fragment = new AchievementFragment();
            toolbar.setTitle("Achievements");
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    protected void onStop(){
        super.onStop();

        // save the user to a file
        try {
            FileOutputStream fos = getApplicationContext().openFileOutput("EvaApplicationUserStorage",Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(app.getUser());
            os.close();
            fos.close();
        }
        catch (Exception e){
            Log.e("FileOutputStream", e.toString());
        }

    }
}
