package alivemind.sociallogin.Facebook;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Alm on 9/8/2016.
 */
public class Faces {

    Context context;

    public Faces(Context context) {
        this.context = context;
    }

    public ArrayList<String> facebookUserData() {
        return FacebookActivity.list;
    }

    public void login() {
        context.startActivity(new Intent(context, FacebookActivity.class));
    }


}
