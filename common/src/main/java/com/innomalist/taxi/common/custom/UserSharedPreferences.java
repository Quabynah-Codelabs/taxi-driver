package com.innomalist.taxi.common.custom;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.innomalist.taxi.common.models.Driver;
import com.innomalist.taxi.common.models.Rider;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link SharedPreferences} class for keeping user's login state persisted
 */
public final class UserSharedPreferences {
    private static final String KEY_LAT = "lat";
    private static final String KEY_LNG = "lng";
    private final Context context;
    private final SharedPreferences prefs;
    private final FirebaseAuth auth;
    private List<LoginStateListener> listeners;
    private static volatile UserSharedPreferences instance;

    private static final String KEY_UID = "user_id";

    private String uid;
    private boolean isLoggedIn;

    private UserSharedPreferences(Context context, PreferenceType type) {
        this.context = context;
        this.prefs = context.getApplicationContext().getSharedPreferences(type == PreferenceType.DRIVER ? "DRIVER_PREFS" : "RIDER_PREFS", Context.MODE_PRIVATE);
        this.auth = FirebaseAuth.getInstance();

        // Initialize user login unique id
        this.uid = prefs.getString(KEY_UID, null);
        this.isLoggedIn = uid != null && !TextUtils.isEmpty(uid);
        if (isLoggedIn) {
            uid = prefs.getString(KEY_UID, null);
        }
    }

    public static UserSharedPreferences get(Context context, PreferenceType type) {
        if (instance == null) {
            synchronized (UserSharedPreferences.class) {
                instance = new UserSharedPreferences(context, type);
            }
        }
        return instance;
    }


    public void addLoginStatusListener(LoginStateListener listener) {
        if (listeners == null) {
            listeners = new ArrayList<>(0);
        }
        listeners.add(listener);
    }

    public void removeLoginStatusListener(LoginStateListener listener) {
        if (listeners != null) {
            listeners.remove(listener);
        }
    }

    private void dispatchLoginEvent() {
        if (listeners != null && !listeners.isEmpty()) {
            for (LoginStateListener l : listeners) {
                l.onLogin();
            }
        }
    }

    private void dispatchLogoutEvent() {
        if (listeners != null && !listeners.isEmpty()) {
            for (LoginStateListener l : listeners) {
                l.onLogout();
            }
        }
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getUID() {
        return uid;
    }

    public void setLoggedIn(BaseUser user) {
        if (user instanceof Rider) {
            String id = ((Rider) user).getUid();
            isLoggedIn = id != null && !TextUtils.isEmpty(id);
            this.uid = id;
        } else if (user instanceof Driver) {
            String id = ((Driver) user).getUid();
            isLoggedIn = id != null && !TextUtils.isEmpty(id);
            this.uid = id;
        }
        prefs.edit().putString(KEY_UID, uid).apply();
        dispatchLoginEvent();

        // todo: store in database
    }

    public void logout() {
        auth.signOut();
        AuthUI.getInstance().signOut(context).addOnCompleteListener(task -> {
            // User logged out successfully
        }).addOnFailureListener(e -> {
            // Failed to logout user
        });
        prefs.edit().clear().apply();
        dispatchLogoutEvent();

        // todo: remove from database
    }

    public void setLastLocation(LatLng location) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(KEY_LAT, (float) location.latitude);
        editor.putFloat(KEY_LNG, (float) location.longitude);
        editor.apply();
    }

    public LatLng getLastLocation() {
        return new LatLng(prefs.getFloat(KEY_LAT, 5.53218389f), prefs.getFloat(KEY_LNG, -0.25942889f));
    }

    public interface LoginStateListener {
        void onLogin();

        void onLogout();
    }
}
