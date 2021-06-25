package funix.prm.prm391x_shopmovie_fx04382;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private String userName = "";
    private String userEmail = "";
    private String userPicURL = "";
    private final String TAG = "LoginActivity";

    private CallbackManager callbackManager;

    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);

        //[START - Facebook Integration]
        FacebookSdk.sdkInitialize(getApplicationContext());
        LoginButton btnLogin = findViewById(R.id.login_button);

        callbackManager = CallbackManager.Factory.create();
        btnLogin.setReadPermissions(Arrays.asList("public_profile", "email"));

        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (AccessToken.getCurrentAccessToken() != null) {
                    RequestData();
                }
            }

            @Override
            public void onCancel() {}

            @Override
            public void onError(FacebookException exception) {}
        });
        //[END - Facebook Integration]

        //[START - Google Integration]
        SignInButton btnSignInGoogle = findViewById(R.id.btnSignIn);
        btnSignInGoogle.setOnClickListener(v -> signIn());

        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // [END build_client]
        //[END - Google Integration]
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void RequestData() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                (object, response) -> {
                    final JSONObject json = response.getJSONObject();

                    try {
                        if(json != null){
                            if (json.has("name")) {
                                userName = json.getString("name");
                            }
                            if (json.has("email")) {
                                userEmail = json.getString("email");
                            }
                            if (json.has("picture")) {
                                userPicURL = json.getJSONObject("picture").getJSONObject("data").getString("url");
                            }
                        }

                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                        Log.d(TAG, "extra name:" + userName);
                        main.putExtra("from", "Facebook");
                        main.putExtra("name", userName);
                        main.putExtra("picURL", userPicURL);
                        main.putExtra("email", userEmail);
                        startActivity(main);
                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture.type(large)");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //[Facebook]
        callbackManager.onActivityResult(requestCode, resultCode, data);

        //[Google]
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                handleSignInResult(task);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) throws ApiException {
            GoogleSignInAccount acct = completedTask.getResult(ApiException.class);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personEmail = acct.getEmail();
                Uri personPhoto = acct.getPhotoUrl();

            Intent main = new Intent(LoginActivity.this, MainActivity.class);
            main.putExtra("from", "Google");
            main.putExtra("name", personName);
            main.putExtra("picURL", personPhoto);
            main.putExtra("email", personEmail);
            startActivity(main);
            finish();
        }
    }
}

