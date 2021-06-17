package funix.prm.prm391x_shopmovie_fx04382;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import funix.prm.prm391x_shopmovie_fx04382.ui.dashboard.DashboardViewModel;

public class MainActivity extends AppCompatActivity {

    private DashboardViewModel dashboardViewModel;
    private String TAG = "MainActivity";
    private String loginFrom = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());


        String name = "";
        String email = "";
        String picURL = "";

//        String id = getIntent().getStringExtra("id");
//        String email = getIntent().getStringExtra("email");
        Intent intent = getIntent();

        if (intent.hasExtra("from")) {
            loginFrom = getIntent().getStringExtra("from");
            Log.d(TAG, "from:" + loginFrom);
//            Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
        }

        if (intent.hasExtra("name")) {
            name = getIntent().getStringExtra("name");
            Log.d(TAG, "n:" + name);
//            Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
        }

        if (intent.hasExtra("email")) {
            email = getIntent().getStringExtra("email");
            Log.d(TAG, "e:" + email);
//            Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
        }

        if (intent.hasExtra("picURL")) {
            picURL = getIntent().getStringExtra("picURL");
            Log.d(TAG, "u:" + picURL);
//            Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "MainAc name:" + name);
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
//        dashboardViewModel = new ViewModelProvider(getActivity()).get(LocationFragmentViewModel.class);
        dashboardViewModel.setName(name);
        dashboardViewModel.setEmail(email);
        dashboardViewModel.setPicURL(picURL);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                if(loginFrom.equals("Facebook")) {
                    Toast.makeText(this, "Search button selected", Toast.LENGTH_SHORT).show();
                    logoutFB();
                } else if (loginFrom.equals("Google")) {

                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logoutFB() {
        LoginManager.getInstance().logOut();

        Intent logout = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(logout);
        finish();
    }
}