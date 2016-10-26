package alivemind.sociallogin.Instagram;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;

import alivemind.sociallogin.R;

/**
 * Created by Alm on 9/5/2016.
 */
public class Insta {

    final Activity activity;
    final ArrayList<String> list = new ArrayList<String>();

    final InstagramSession instagramSession;
    final Instagram instagram;

    public Insta(Activity activity) {
        this.activity = activity;

        instagram = new Instagram(activity, activity.getString(R.string.instagram_client_key), activity.getString(R.string.instagram_client_secret), activity.getString(R.string.instagram_redirect_url));
        instagramSession = instagram.getSession();
        instagramSession.reset();

    }

    public void login() {
        instagram.authorize(mAuthListener);
    }

    public ArrayList<String> instaUserData() {
        return list;
    }


    private Instagram.InstagramAuthListener mAuthListener = new Instagram.InstagramAuthListener() {
        @Override
        public void onSuccess(alivemind.sociallogin.Instagram.InstagramUser user) {

            if (instagramSession.isActive()) {

                alivemind.sociallogin.Instagram.InstagramUser instagramUser = instagramSession.getUser();

                list.clear();
                list.add(instagramUser.id);
                list.add(instagramUser.username);
                list.add(instagramUser.fullName);
                list.add(instagramUser.profilPicture);

            }
        }

        @Override
        public void onError(String error) {
            list.clear();
        }

        @Override
        public void onCancel() {
            list.clear();
        }
    };

}
