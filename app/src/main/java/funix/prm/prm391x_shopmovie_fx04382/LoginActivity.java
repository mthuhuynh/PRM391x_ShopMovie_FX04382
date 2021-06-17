package funix.prm.prm391x_shopmovie_fx04382;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private LoginButton btnLogin;
    private CallbackManager callbackManager;
    private ProfilePictureView profilePictureView;
    private LinearLayout infoLayout;
    private TextView email;
    private TextView gender;
    private TextView facebookName;

    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    private String userName = "";
    private String userEmail = "";
    private String userId = "";
    private String userPicURL = "";
    private String TAG = "LoginActivity";

    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private SignInButton btnSignInGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);

        //Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        btnLogin = (LoginButton) findViewById(R.id.login_button);
        email = (TextView) findViewById(R.id.email);
        facebookName = (TextView) findViewById(R.id.name);

        infoLayout = (LinearLayout) findViewById(R.id.layout_info);
        profilePictureView = (ProfilePictureView) findViewById(R.id.image);
        callbackManager = CallbackManager.Factory.create();
        btnLogin.setReadPermissions(Arrays.asList("public_profile", "email", "user_friends"));

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

        //Google

        btnSignInGoogle = findViewById(R.id.btnSignIn);
        btnSignInGoogle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signIn();
            }
        });

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
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // [END build_client]


    }

    @Override
    public void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);

//        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
//        if (opr.isDone()) {
//            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
//            // and the GoogleSignInResult will be available instantly.
//            Log.d(TAG, "Got cached sign-in");
//            GoogleSignInResult result = opr.get();
//            handleSignInResult(result);
//        } else {
//            // If the user has not previously signed in on this device or the sign-in has expired,
//            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
//            // single sign-on will occur in this branch.
//            showProgressDialog();
//            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                @Override
//                public void onResult(GoogleSignInResult googleSignInResult) {
//                    hideProgressDialog();
//                    handleSignInResult(googleSignInResult);
//                }
//            });
//        }
    }

//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnSignIn:
//                signIn();
//                break;
//            // ...
//        }
//    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void RequestData() {


        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object,GraphResponse response) {
                final JSONObject json = response.getJSONObject();

                try {
                    if(json != null){
                        Log.d(TAG, "json name:" + json.getString("name"));
//                        if (json.has("id")) {
//                            userId = json.getString("id");
//                        }
                        if (json.has("name")) {
                            userName = json.getString("name");
                            Log.d(TAG, "user name:" + userName);
                        }
                        if (json.has("email")) {
                            userEmail = json.getString("email");
                        }
                        if (json.has("picture")) {
                            userPicURL = json.getJSONObject("picture").getJSONObject("data").getString("url");
                            Log.d(TAG, "user PicURL:" + userPicURL);
                        }

                    }

//                    Uri photoUrl = Profile.getCurrentProfile().getProfilePictureUri(200, 200);

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

        //fb
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "extra name:" + userName);

        //google
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
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
            // Signed in successfully, show authenticated UI.
//            updateUI(account);
            Intent main = new Intent(LoginActivity.this, MainActivity.class);
            main.putExtra("from", "Google");
            main.putExtra("name", personName);
            main.putExtra("picURL", personPhoto);
            main.putExtra("email", personEmail);
            startActivity(main);
            finish();

            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.

//            updateUI(null);
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.btnSignIn).setVisibility(View.GONE);
        } else {
            findViewById(R.id.btnSignIn).setVisibility(View.VISIBLE);
        }
    }

    public boolean isLoginFB() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return  (accessToken != null && !accessToken.isExpired());
    }
}

