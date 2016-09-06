package alivemind.sociallogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import alivemind.sociallogin.Instagram.Insta;

public class MainActivity extends AppCompatActivity {


    String CLIENT_ID = "d4f1cb6d2eaa47ceb07ec6bc2a9d5ed2";
    String SECRET_KEY = "2025b41554f946f7847d7037d254c508";
    String REDIRECT_URL = "http://test.alive-mind.com";

    Insta insta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insta = new Insta(this, CLIENT_ID, SECRET_KEY, REDIRECT_URL);
        insta.login();

    }

    public void fetch(View view) {
        Log.d("Insta>>", insta.InstaUserData().toString());

    }

}
