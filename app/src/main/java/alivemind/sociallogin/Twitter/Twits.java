package alivemind.sociallogin.Twitter;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Alm on 9/6/2016.
 */
public class Twits {

    Context context;

    public Twits(Context context) {
        this.context = context;
    }

    public ArrayList<String> twitterUserData() {
        return TwitterActivity.list;
    }

    public void login() {
        context.startActivity(new Intent(context, TwitterActivity.class));
    }
}
