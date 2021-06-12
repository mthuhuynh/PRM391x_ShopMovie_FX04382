package funix.prm.prm391x_shopmovie_fx04382;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String name = "";
//        String id = getIntent().getStringExtra("id");
//        String email = getIntent().getStringExtra("email");
        Intent intent = getIntent();

        if (intent.hasExtra("name")) {
            name = getIntent().getStringExtra("name");
//            Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
        }
        Log.d(TAG, "name:" + name);
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
//        dashboardViewModel = new ViewModelProvider(getActivity()).get(LocationFragmentViewModel.class);
        dashboardViewModel.setText(name);

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

}