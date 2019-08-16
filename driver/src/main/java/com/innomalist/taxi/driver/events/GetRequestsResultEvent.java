package com.innomalist.taxi.driver.events;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.innomalist.taxi.common.events.BaseResultEvent;
import com.innomalist.taxi.common.models.Travel;
import com.innomalist.taxi.common.utils.LatLngDeserializer;
import com.innomalist.taxi.common.utils.ServerResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetRequestsResultEvent extends BaseResultEvent {
    public ArrayList<Travel> travels;

    public GetRequestsResultEvent(){
        super(ServerResponse.REQUEST_TIMEOUT);
    }

    public GetRequestsResultEvent(Object... args) {
        super(args);
        if(response != ServerResponse.OK)
            return;
        Type type = new TypeToken<List<Travel>>() {}.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LatLng.class, new LatLngDeserializer());

        Gson customGson = gsonBuilder.create();
        this.travels = customGson.fromJson(args[1].toString(),type);
    }
}
