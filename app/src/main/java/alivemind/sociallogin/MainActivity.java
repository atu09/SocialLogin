package alivemind.sociallogin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import alivemind.sociallogin.Instagram.Insta;
import alivemind.sociallogin.Twitter.TwitterActivity;
import alivemind.sociallogin.Twitter.TwitterClass;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "fFEoAWP2W3ooex3I5SzyVB8jm";
    private static final String TWITTER_SECRET = "w1fYPksl0op1PWYT4aUpt6x4vGNgwxSuxUANzZEUAN38nBS3Gd";

    TwitterClass twitterClass;

    boolean aBoolean = false;

    String LINKEDIN_CLIENT_ID = "819cfy4b95ckxh";
    String LINKEDIN_SECRET_KEY = "7OlMoyvJwlaKCGLv";

    String INSTAGRAM_CLIENT_ID = "d4f1cb6d2eaa47ceb07ec6bc2a9d5ed2";
    String INSTAGRAM_SECRET_KEY = "2025b41554f946f7847d7037d254c508";
    String INSTAGRAM_REDIRECT_URL = "http://test.alive-mind.com";

    Insta insta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateSHAKey();

/*
        insta = new Insta(this, INSTAGRAM_CLIENT_ID, INSTAGRAM_SECRET_KEY, INSTAGRAM_REDIRECT_URL);
        insta.login();
*/
        twitterClass = new TwitterClass(TWITTER_KEY, TWITTER_SECRET);
    }

    public void fetch(View view) {
        //Log.d("Insta>>", insta.InstaUserData().toString());
        if (aBoolean) {
            aBoolean = false;
            Log.d("Twitter>>>", twitterClass.fetchTwitterData().toString());
        } else {
            aBoolean = true;
            twitterClass.login(MainActivity.this);
        }
    }

    public void generateSHAKey() {
        try {
            Context context = getApplicationContext();
            PackageInfo info = context.getPackageManager().getPackageInfo("alivemind.sociallogin", PackageManager.GET_SIGNATURES); //package name here
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


}