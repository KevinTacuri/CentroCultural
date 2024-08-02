package com.example.centrocultural.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.centrocultural.MapView;
import com.example.centrocultural.R;

public class MapFragment extends Fragment {

    private FrameLayout mapContainer;
    private MapView mapView;
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mapContainer = view.findViewById(R.id.mapContainer);
        mapView = new MapView(getContext(), null);

        // Se agrega el MapView al contenedor
        mapContainer.addView(mapView);

        return view;
    }


}
