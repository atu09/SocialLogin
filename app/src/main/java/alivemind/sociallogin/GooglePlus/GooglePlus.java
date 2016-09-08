package alivemind.sociallogin.GooglePlus;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Alm on 9/8/2016.
 */
public class GooglePlus {
    Context context;

    public GooglePlus(Context context) {
        this.context = context;
    }

    public ArrayList<String> googlePlusUserData() {
        return GooglePlusActivity.list;
    }

    public void login() {
        context.startActivity(new Intent(context, GooglePlusActivity.class));
    }
}
