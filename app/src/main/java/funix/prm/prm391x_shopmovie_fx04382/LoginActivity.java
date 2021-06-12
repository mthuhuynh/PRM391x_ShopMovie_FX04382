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
    private URL profilePicture;
    private String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);

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
    }

    private void RequestData() {
        Intent main = new Intent(LoginActivity.this, MainActivity.class);

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object,GraphResponse response) {
                final JSONObject json = response.getJSONObject();

                try {
                    if(json != null){
//                        Toast.makeText(LoginActivity.this, json.getString("name"), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "json name:" + json.getString("name"));
                        if (json.has("id")) {
                            userId = json.getString("id");
                        }
                        if (json.has("name")) {
                            userName = json.getString("name");
                            Log.d(TAG, "user name:" + userName);
                        }
                        if (json.has("email")) {
                            userEmail = json.getString("email");
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();

//        Toast.makeText(LoginActivity.this, userName, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "extra name:" + userName);
        main.putExtra("name", userName);
        main.putExtra("id", userId);
        main.putExtra("email", userEmail);
        startActivity(main);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

