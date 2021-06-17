package funix.prm.prm391x_shopmovie_fx04382;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import funix.prm.prm391x_shopmovie_fx04382.ui.dashboard.DashboardViewModel;

public class MainActivity extends AppCompatActivity {

    private String loginFrom;
    private String name = "";
    private String email = "";
    private String picURL = "";

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //[Facebook]
        FacebookSdk.sdkInitialize(getApplicationContext());

        //[Google]
        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // [END build_client]

        //Get Extras from Intent
        Intent intent = getIntent();
        if (intent.hasExtra("from")) {
            loginFrom = getIntent().getStringExtra("from");
        }
        if (intent.hasExtra("name")) {
            name = getIntent().getStringExtra("name");
        }
        if (intent.hasExtra("email")) {
            email = getIntent().getStringExtra("email");
        }
        if (intent.hasExtra("picURL")) {
            picURL = getIntent().getStringExtra("picURL");
        }

        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
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
        if (item.getItemId() == R.id.logout) {
            if (loginFrom.equals("Facebook")) {
                logoutFB();
            } else if (loginFrom.equals("Google")) {
                signoutGoogle();
            }

            switchActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void switchActivity() {
        Intent logout = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(logout);
        finish();
    }

    private void signoutGoogle() {
        mGoogleSignInClient.signOut();
    }

    private void logoutFB() {
        LoginManager.getInstance().logOut();
    }
}