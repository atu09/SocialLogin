package alivemind.sociallogin.Twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;

import alivemind.sociallogin.R;
import io.fabric.sdk.android.Fabric;

/**
 * Created by Alm on 9/6/2016.
 */
public class TwitterActivity extends AppCompatActivity {

    public TwitterLoginButton loginButton;
    public static ArrayList<String> list = new ArrayList<>();

    TwitterSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.twitter_client_key), getString(R.string.twitter_client_secret));
        Fabric.with(this, new Twitter(authConfig));

        loginButton = new TwitterLoginButton(this);

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                session = result.data;

                list.clear();
                list.add(String.valueOf(session.getUserId()));
                list.add(session.getUserName());

                TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
                twitterApiClient.getAccountService().verifyCredentials(true, false, new Callback<User>() {
                    @Override
                    public void success(Result<User> userResult) {

                        list.add(userResult.data.name);
                        list.add(userResult.data.profileImageUrl);

                        TwitterAuthClient authClient = new TwitterAuthClient();
                        authClient.requestEmail(session, new Callback<String>() {
                            @Override
                            public void success(Result<String> result) {
                                list.add(result.data.toString());
                                TwitterActivity.this.finish();
                            }

                            @Override
                            public void failure(TwitterException exception) {
                                TwitterActivity.this.finish();
                            }
                        });

                    }

                    @Override
                    public void failure(TwitterException e) {
                        TwitterActivity.this.finish();
                    }
                });

                TwitterActivity.this.finish();

            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
                TwitterActivity.this.finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        loginButton.callOnClick();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

}
