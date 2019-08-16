package com.innomalist.taxi.driver.events;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.android.gms.maps.model.LatLng;
import com.innomalist.taxi.common.models.Travel;
import com.innomalist.taxi.common.utils.LatLngDeserializer;

import java.lang.reflect.Type;
import java.util.List;

public class RequestReceivedEvent {
    public Travel travel;

    public RequestReceivedEvent(Object... args) {
        Type type = new TypeToken<Travel>() {}.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LatLng.class, new LatLngDeserializer());
        Gson customGson = gsonBuilder.create();
        this.travel = customGson.fromJson(args[0].toString(),type);
    }
}