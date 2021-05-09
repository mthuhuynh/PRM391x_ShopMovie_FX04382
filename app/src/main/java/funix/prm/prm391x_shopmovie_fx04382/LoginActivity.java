package funix.prm.prm391x_shopmovie_fx04382;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
//    // Declaring Variables.
////    TextView FacebookDataTextView;
//    AccessTokenTracker accessTokenTracker;
//
//    //FBmain
//    LoginButton loginButton;
//    CallbackManager callbackManager;
//
//    //test
//    private String TAG = MainActivity.class.getSimpleName();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // Passing LoginActivity in Facebook SDK.
//        FacebookSdk.sdkInitialize(getApplicationContext());
//
//        // Adding Callback Manager.
//        callbackManager = CallbackManager.Factory.create();
//
//        setContentView(R.layout.activity_login);
//
//        // Assign TextView ID.
////        FacebookDataTextView = (TextView)findViewById(R.id.TextView1);
//
//        // Assign Facebook Login button ID.
//        loginButton = (LoginButton)findViewById(R.id.login_button);
//
//        // Giving permission to Login Button.
//        loginButton.setReadPermissions("email");
//
//        // Checking the Access Token.
//        if(AccessToken.getCurrentAccessToken()!=null){
//
//            GraphLoginRequest(AccessToken.getCurrentAccessToken());
//
//            // If already login in then show the Toast.
//            Toast.makeText(LoginActivity.this,"Already logged in",Toast.LENGTH_SHORT).show();
//
//        }else {
//
//            // If not login in then show the Toast.
//            Toast.makeText(LoginActivity.this,"User not logged in",Toast.LENGTH_SHORT).show();
//        }
//
//        // Adding Click listener to Facebook login button.
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){
//            @Override
//            public void onSuccess(LoginResult loginResult){
//                Toast.makeText(LoginActivity.this,"Login Successed",Toast.LENGTH_SHORT).show();
//                // Calling method to access User Data After successfully login.
//                GraphLoginRequest(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel(){
//
//                Toast.makeText(LoginActivity.this,"Login Canceled",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(FacebookException exception){
//
//                Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//        // Detect user is login or not. If logout then clear the TextView and delete all the user info from TextView.
//        accessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2) {
//                if (accessToken2 == null) {
//
//                    // Clear the TextView after logout.
////                    FacebookDataTextView.setText("");
//
//                }
//            }
//        };
//    }
//
//    // Method to access Facebook User Data.
//    protected void GraphLoginRequest(AccessToken accessToken){
////        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken,
////                new GraphRequest.GraphJSONObjectCallback() {
////                    @Override
////                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
////
////                        try {
////
////                            // Adding all user info one by one into TextView.
////                            FacebookDataTextView.setText("ID: " + jsonObject.getString("id"));
////
////                            FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nName : " + jsonObject.getString("name"));
////
////                            FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nFirst name : " + jsonObject.getString("first_name"));
////
////                            FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nLast name : " + jsonObject.getString("last_name"));
////
////                            FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nEmail : " + jsonObject.getString("email"));
////
////                            FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nGender : " + jsonObject.getString("gender"));
////
////                            FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nLink : " + jsonObject.getString("link"));
////
////                            FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nTime zone : " + jsonObject.getString("timezone"));
////
////                            FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nLocale : " + jsonObject.getString("locale"));
////
////                            FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nUpdated time : " + jsonObject.getString("updated_time"));
////
////                            FacebookDataTextView.setText(FacebookDataTextView.getText() + "\nVerified : " + jsonObject.getString("verified"));
////                        }
////                        catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////                    }
////                });
////
////        Bundle bundle = new Bundle();
////        bundle.putString(
////                "fields",
////                "id,name,link,email,gender,last_name,first_name,locale,timezone,updated_time,verified"
////        );
////        graphRequest.setParameters(bundle);
////        graphRequest.executeAsync();
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        AppEventsLogger.activateApp(getApplication());
//
//    }
//
////    @Override
////    protected void onPause() {
////        super.onPause();
////        AppEventsLogger.deactivateApp(LoginActivity.this);
////
////    }
//}

    private LoginButton btnLogin;
    private CallbackManager callbackManager;
    private ProfilePictureView profilePictureView;
    private LinearLayout infoLayout;
    private TextView email;
    private TextView gender;
    private TextView facebookName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);

        btnLogin = (LoginButton)findViewById(R.id.login_button);
        email = (TextView)findViewById(R.id.email);
        facebookName = (TextView)findViewById(R.id.name);
        gender = (TextView)findViewById(R.id.gender);
        infoLayout = (LinearLayout)findViewById(R.id.layout_info);
        profilePictureView = (ProfilePictureView)findViewById(R.id.image);

        btnLogin.setReadPermissions(Arrays.asList("public_profile, email"));
        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("Main", response.toString());
                                Toast.makeText(LoginActivity.this, "json to Login Facebook", Toast.LENGTH_SHORT).show();
                                try {
                                    Toast.makeText(LoginActivity.this, object.getString("email"), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
//                                setProfileToView(object);
                                try {
                                email.setText(object.getString("email"));
                                gender.setText(object.getString("gender"));
                                facebookName.setText(object.getString("name"));

                                profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
                                profilePictureView.setProfileId(object.getString("id"));
                                infoLayout.setVisibility(View.VISIBLE);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, "error to Login Facebook", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setProfileToView(JSONObject jsonObject) {
        try {
            Toast.makeText(LoginActivity.this, "error to Login Facebook", Toast.LENGTH_SHORT).show();
            email.setText(jsonObject.getString("email"));
            gender.setText(jsonObject.getString("gender"));
            facebookName.setText(jsonObject.getString("name"));

            profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
            profilePictureView.setProfileId(jsonObject.getString("id"));
            infoLayout.setVisibility(View.VISIBLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}