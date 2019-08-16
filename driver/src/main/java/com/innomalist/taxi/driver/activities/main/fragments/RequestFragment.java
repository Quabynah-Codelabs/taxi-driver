package com.innomalist.taxi.driver.activities.main.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.type.LatLng;
import com.innomalist.taxi.common.models.Travel;
import com.innomalist.taxi.common.utils.CommonUtils;
import com.innomalist.taxi.common.utils.LatLngDeserializer;
import com.innomalist.taxi.common.utils.LocationHelper;
import com.innomalist.taxi.driver.R;
import com.innomalist.taxi.driver.databinding.FragmentRequestBinding;

import java.lang.reflect.Type;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {
    private Travel travel;
    FragmentRequestBinding binding;
    private CountDownTimer countDownTimer;
    private RequestFragment.OnFragmentInteractionListener mListener;
    private static final String ARG_REQUEST = "request";

    public RequestFragment() {

    }

    public static RequestFragment newInstance(Travel travel) {
        RequestFragment fragment = new RequestFragment();
        Bundle args = new Bundle();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LatLng.class, new LatLngDeserializer());
        Gson customGson = gsonBuilder.create();
        args.putString(ARG_REQUEST, customGson.toJson(travel));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String travelString = getArguments().getString(ARG_REQUEST);
            Type type = new TypeToken<Travel>() {}.getType();
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LatLng.class, new LatLngDeserializer());
            Gson customGson = gsonBuilder.create();
            travel = customGson.fromJson(travelString, type);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_request, container,false);
        binding.setTravel(travel);
        if(CommonUtils.currentLocation != null)
            locationChanged(CommonUtils.currentLocation);
        countDownTimer = new CountDownTimer(5 * 60000, 50) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                if(mListener != null && travel != null) {
                    mListener.onDecline(travel);
                    mListener.onInvisible(travel);
                }
            }
        };
        countDownTimer.start();
        if(getResources().getBoolean(R.bool.use_miles)) {
            binding.textUserDestinationDistance.setText(getString(R.string.unit_distance_miles, travel.getDistanceBest() / 1609.344f));
        } else {
            binding.textUserDestinationDistance.setText(getString(R.string.unit_distance, travel.getDistanceBest() / 1000f));
        }
        binding.buttonAccept.setOnClickListener(view -> {
            countDownTimer.cancel();
            mListener.onAccept(travel);
        });
        binding.buttonDecline.setOnClickListener(view -> {
            countDownTimer.cancel();
            mListener.onDecline(travel);
            mListener.onInvisible(travel);
        });
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        // mListener.onVisible(this.travel);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onInvisible(this.travel);
        mListener = null;
    }

    public void locationChanged(com.google.android.gms.maps.model.LatLng latLng) {
        int distanceDriver = LocationHelper.distFrom(travel.getPickupPoint(), latLng);
        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) binding.getRoot().findViewById(R.id.guideline_start).getLayoutParams();
        lp.guidePercent = ((float)distanceDriver / (travel.getDistanceBest() + distanceDriver));
        binding.getRoot().findViewById(R.id.guideline_start).setLayoutParams(lp);
        if(getResources().getBoolean(R.bool.use_miles)) {
            binding.textDriverUserDistance.setText(getString(R.string.unit_distance_miles,distanceDriver / 1609.344f));
        } else {
            binding.textDriverUserDistance.setText(getString(R.string.unit_distance,distanceDriver / 1000f));

        }
    }

    public interface OnFragmentInteractionListener {
        void onAccept(Travel travel);
        void onDecline(Travel travel);
        void onVisible(Travel travel);
        void onInvisible(Travel travel);
    }
}
