package alivemind.sociallogin.Twitter;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Alm on 9/6/2016.
 */
public class TwitterClass {

    public String TWITTER_KEY;
    public String TWITTER_SECRET;

    public TwitterClass(String TWITTER_KEY, String TWITTER_SECRET) {
        this.TWITTER_KEY = TWITTER_KEY;
        this.TWITTER_SECRET = TWITTER_SECRET;

        TwitterActivity.setTwitter(TWITTER_KEY, TWITTER_SECRET);

    }

    public ArrayList<String> fetchTwitterData() {
        return TwitterActivity.list;
    }

    public void login(Context context) {
        context.startActivity(new Intent(context, TwitterActivity.class));
    }
}
