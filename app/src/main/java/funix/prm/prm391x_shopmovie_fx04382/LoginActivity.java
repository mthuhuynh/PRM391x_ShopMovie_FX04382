package funix.prm.prm391x_shopmovie_fx04382;

import android.content.Intent;
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

    private String firstName, lastName, userEmail, birthday, userGender;
    private URL profilePicture;
    private String userId;
    private String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);

        btnLogin = (LoginButton) findViewById(R.id.login_button);
        email = (TextView) findViewById(R.id.email);
        facebookName = (TextView) findViewById(R.id.name);
        gender = (TextView) findViewById(R.id.gender);
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
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
            }
        });
    }

    private void RequestData() {

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object,GraphResponse response) {

                final JSONObject json = response.getJSONObject();



                try {
                    if(json != null){
                        Toast.makeText(LoginActivity.this, json.getString("name"), Toast.LENGTH_SHORT).show();
//                        text = "<b>Name :</b> "+json.getString("name")+"<br><br><b>Email :</b> "+json.getString("email")+"<br><br><b>Profile link :</b> "+json.getString("link");
//                /*details_txt.setText(Html.fromHtml(text));
//                profile.setProfileId(json.getString("id"));*/
//
//                        Log.e(TAG, json.getString("name"));
//                        Log.e(TAG, json.getString("email"));
//                        Log.e(TAG, json.getString("id"));
//                        //web.loadData(text, "text/html", "UTF-8");

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
    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}


//
//        btnLogin.setReadPermissions(Arrays.asList("public_profile", "email"));

//
//        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        Log.e(TAG, object.toString());
//                        Log.e(TAG, response.toString());
//
//                        try {
//                            Toast.makeText(LoginActivity.this, "try", Toast.LENGTH_SHORT).show();
//                            userId = object.getString("id");
//                            profilePicture = new URL("https://graph.facebook.com/" + userId + "/picture?width=500&height=500");
//                            if (object.has("first_name"))
//                                firstName = object.getString("first_name");
//                            Toast.makeText(LoginActivity.this, firstName, Toast.LENGTH_SHORT).show();
//                                facebookName.setText(firstName);
//                            if (object.has("last_name"))
////                                lastName = object.getString("last_name");
//                            if (object.has("email"))
//                                userEmail = object.getString("email");
//                                email.setText(userEmail);
//                            if (object.has("birthday"))
////                                birthday = object.getString("birthday");
//                            if (object.has("gender"))
//                                userGender = object.getString("gender");
//                                gender.setText(userGender );
//
//
////                            Intent main = new Intent(LoginActivity.this, MainActivity.class);
////                            main.putExtra("name", firstName);
////                            main.putExtra("surname", lastName);
////                            main.putExtra("imageUrl", profilePicture.toString());
////                            startActivity(main);
////                            finish();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id, first_name, last_name, email, birthday, gender , location");
//                request.setParameters(parameters);
//                request.executeAsync();
//            }
//
//            @Override
//            public void onCancel() {
//            }
//
//            @Override
//            public void onError(FacebookException e) {
//                e.printStackTrace();
//            }
//        });
//    }
////        // Callback registration
////        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//
////            @Override
////            public void onSuccess(LoginResult loginResult) {
////                GraphRequest request = GraphRequest.newMeRequest(
////                        loginResult.getAccessToken(),
////                        new GraphRequest.GraphJSONObjectCallback() {
////
////                            @Override
////                            public void onCompleted(JSONObject object, GraphResponse response) {
////                                Log.v("Main", response.toString());
////                                Toast.makeText(LoginActivity.this, "json to Login Facebook", Toast.LENGTH_SHORT).show();
////                                try {
////                                    Toast.makeText(LoginActivity.this, object.getString("email"), Toast.LENGTH_SHORT).show();
////                                } catch (JSONException e) {
////                                    e.printStackTrace();
////                                }
//////                                setProfileToView(object);
////                                try {
////                                email.setText(object.getString("email"));
////                                gender.setText(object.getString("gender"));
////                                facebookName.setText(object.getString("name"));
////
////                                profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
////                                profilePictureView.setProfileId(object.getString("id"));
////                                infoLayout.setVisibility(View.VISIBLE);
////                                } catch (JSONException e) {
////                                    e.printStackTrace();
////                                }
////
////                                Intent intentMain = new Intent(getApplicationContext(), MainActivity.class);
////                                startActivity(intentMain);
////                            }
////                        });
////                Bundle parameters = new Bundle();
////                parameters.putString("fields", "id,name,email,gender");
////                request.setParameters(parameters);
////                request.executeAsync();
////            }
////
////            @Override
////            public void onCancel() {
////
////            }
////
////            @Override
////            public void onError(FacebookException exception) {
////                Toast.makeText(LoginActivity.this, "error to Login Facebook", Toast.LENGTH_SHORT).show();
////            }
////        });
////    }
//

//
////    private void setProfileToView(JSONObject jsonObject) {
////        try {
////            Toast.makeText(LoginActivity.this, "error to Login Facebook", Toast.LENGTH_SHORT).show();
////            email.setText(jsonObject.getString("email"));
////            gender.setText(jsonObject.getString("gender"));
////            facebookName.setText(jsonObject.getString("name"));
////
////            profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
////            profilePictureView.setProfileId(jsonObject.getString("id"));
////            infoLayout.setVisibility(View.VISIBLE);
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
////    }
//}