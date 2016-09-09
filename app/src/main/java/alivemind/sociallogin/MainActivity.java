package alivemind.sociallogin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import alivemind.sociallogin.Facebook.Faces;
import alivemind.sociallogin.GooglePlus.GooglePlus;
import io.fabric.sdk.android.Fabric;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import alivemind.sociallogin.Instagram.Insta;
import alivemind.sociallogin.Twitter.Twits;

public class MainActivity extends AppCompatActivity {

    Insta insta;
    Twits twits;
    Faces faces;
    GooglePlus googlePlus;

    boolean instaCheck = false;
    boolean twitsCheck = false;
    boolean facesCheck = false;
    boolean googlePlusCheck = false;

    String LINKEDIN_CLIENT_ID = "819cfy4b95ckxh";
    String LINKEDIN_SECRET_KEY = "7OlMoyvJwlaKCGLv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateSHAKey(this);

        insta = new Insta(this);
        twits = new Twits(this);
        faces = new Faces(this);
        googlePlus = new GooglePlus(this);
    }

    public void fetch(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_insta:
                if (instaCheck) {
                    instaCheck = false;
                    Log.d("CheckInsta>>", insta.instaUserData().toString());
                } else {
                    instaCheck = true;
                    insta.login();
                }
                break;
            case R.id.btn_twitter:
                if (twitsCheck) {
                    twitsCheck = false;
                    Log.d("CheckTwitter>>>", twits.twitterUserData().toString());
                } else {
                    twitsCheck = true;
                    twits.login();
                }
                break;
            case R.id.btn_facebook:
                if (facesCheck) {
                    facesCheck = false;
                    Log.d("CheckFacebook>>>", faces.facebookUserData().toString());
                } else {
                    facesCheck = true;
                    faces.login();
                }
                break;
            case R.id.btn_googlePlus:
                if (googlePlusCheck) {
                    googlePlusCheck = false;
                    Log.d("CheckGooglePlus>>>", googlePlus.googlePlusUserData().toString());
                } else {
                    googlePlusCheck = true;
                    googlePlus.login();
                }
                break;
        }
    }

    public void generateSHAKey(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
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