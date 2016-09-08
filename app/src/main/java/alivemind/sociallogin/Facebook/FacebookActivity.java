package alivemind.sociallogin.Facebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Alm on 9/8/2016.
 */
public class FacebookActivity extends AppCompatActivity {

    public LoginButton loginButton;
    public CallbackManager callbackManager;
    public static ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        FacebookSdk.clearLoggingBehaviors();

        loginButton = new LoginButton(this);
        loginButton.setReadPermissions("public_profile, email");

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try {

                            JSONObject fbResponse = new JSONObject(String.valueOf(response.getJSONObject()));

                            list.clear();
                            list.add(fbResponse.getString("id"));
                            list.add(fbResponse.getString("name"));
                            list.add("https://graph.facebook.com/" + fbResponse.getString("id") + "/picture?type=large");
                            list.add(fbResponse.getString("email"));

                        } catch (JSONException e) {

                            e.printStackTrace();

                        }

                        LoginManager.getInstance().logOut();

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,link");
                request.setParameters(parameters);
                request.executeAsync();

                finish();

            }

            @Override
            public void onCancel() {
                finish();
            }

            @Override
            public void onError(FacebookException e) {
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginButton.callOnClick();
    }
}
