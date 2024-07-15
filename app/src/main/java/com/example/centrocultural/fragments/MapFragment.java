package com.example.centrocultural.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.centrocultural.MapView;
import com.example.centrocultural.R;

public class MapFragment extends Fragment {

    private FrameLayout mapContainer;
    private MapView mapView;
    private Button sala1Button;

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
        mapView = new MapView(getContext(),null);

        // Se agrega el MapView al contenedor
        mapContainer.addView(mapView);

        // ViewTreeObserver para medir la vista
        mapContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Se quita el listener para evitar llamadas repetidas
                mapContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                addButtons();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        addButtons();
    }

    @Override
    public void onPause() {
        super.onPause();
        removeButtons();
    }

    private void addButtons() {
        float unitWidth = mapView.getWidth() / 10f;
        float unitHeight = mapView.getHeight() / 10f;

        sala1Button = createButton("Sala 1", unitWidth, 7 * unitHeight, 2 * unitWidth, 2 * unitHeight, SalaFragment.class);

        mapContainer.addView(sala1Button);
    }

    private void removeButtons() {
        mapContainer.removeView(sala1Button);
    }

    private Button createButton(String text, float left, float top, float width, float height, final Class<? extends Fragment> fragmentClass) {
        Button button = new Button(getContext());
        button.setText(text);
        button.setBackgroundColor(Color.TRANSPARENT);
        button.setTextColor(Color.BLACK);

        // Crear parámetros de diseño para el botón
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) width, (int) height);
        layoutParams.leftMargin = (int) left;
        layoutParams.topMargin = (int) top;

        // Asignar parámetros de diseño al botón
        button.setLayoutParams(layoutParams);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentActivity) getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView, fragmentClass, null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return button;
    }
}
