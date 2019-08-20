package com.innomalist.taxi.rider.custom;

import com.google.firebase.firestore.FirebaseFirestore;
import com.innomalist.taxi.common.components.BaseActivity;
import com.innomalist.taxi.common.custom.PreferenceType;
import com.innomalist.taxi.common.custom.UserSharedPreferences;

public final class CustomRiderService {
    private FirebaseFirestore firestore;
    private final BaseActivity host;
    private final UserSharedPreferences prefs;


    public CustomRiderService(BaseActivity host) {
        this.host = host;
        this.prefs = UserSharedPreferences.get(host, PreferenceType.RIDER);
        this.firestore = FirebaseFirestore.getInstance();
    }


}
