package com.example.centrocultural.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;

import com.example.centrocultural.R;

public class SalaFragment extends Fragment {

    public static SalaFragment newInstance() {
        return new SalaFragment();
    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sala1, container, false);

        RelativeLayout relativeLayout = rootView.findViewById(R.id.customViewContainer);

        // Dibujo de la sala (rectángulo)
        View sala1View = new View(getContext()) {
            protected void onDraw(android.graphics.Canvas canvas) {
                super.onDraw(canvas);

                int width = getWidth();
                int height = getHeight();
                int padding = 50; // Para los bordes

                android.graphics.Paint paint = new android.graphics.Paint();
                paint.setColor(android.graphics.Color.BLACK);
                paint.setStyle(android.graphics.Paint.Style.STROKE); // Solo bordes
                paint.setStrokeWidth(5); // Grosor
                canvas.drawRect(padding, padding, width - padding, height - padding, paint);
            }
        };

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        sala1View.setLayoutParams(layoutParams);
        relativeLayout.addView(sala1View);

        // Se obtienen las dimensiones
        int rectangleWidth = getResources().getDisplayMetrics().widthPixels - 2 * 50;
        int rectangleHeight = getResources().getDisplayMetrics().heightPixels - 2 * 50;

        int iconSize = 100;
        int padding = 50;

        // Icono en la centro superior
        ImageView topIcon = new ImageView(getContext());
        topIcon.setImageResource(R.drawable.sharp_aspect_ratio_24);
        RelativeLayout.LayoutParams topParams = new RelativeLayout.LayoutParams(iconSize, iconSize);
        topParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        topParams.topMargin = padding;
        topIcon.setLayoutParams(topParams);
        relativeLayout.addView(topIcon);

        // Icono en la centro inferior
        ImageView bottomIcon = new ImageView(getContext());
        bottomIcon.setImageResource(R.drawable.sharp_aspect_ratio_24);
        RelativeLayout.LayoutParams bottomParams = new RelativeLayout.LayoutParams(
                iconSize,
                iconSize
        );
        bottomParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bottomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bottomParams.bottomMargin = padding;
        bottomIcon.setLayoutParams(bottomParams);
        relativeLayout.addView(bottomIcon);

        // Icono en la centro izquierdo
        ImageView leftIcon = new ImageView(getContext());
        leftIcon.setImageResource(R.drawable.sharp_aspect_ratio_24);
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(
                iconSize,
                iconSize
        );
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        leftParams.leftMargin = padding;
        leftIcon.setLayoutParams(leftParams);
        relativeLayout.addView(leftIcon);

        // Icono en el centro derecho
        ImageView rightIcon = new ImageView(getContext());
        rightIcon.setImageResource(R.drawable.sharp_aspect_ratio_24);
        RelativeLayout.LayoutParams rightParams = new RelativeLayout.LayoutParams(
                iconSize,
                iconSize
        );
        rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        rightParams.rightMargin = padding;
        rightIcon.setLayoutParams(rightParams);
        relativeLayout.addView(rightIcon);

        // Clic listener para el ícono superior
        topIcon.setOnClickListener(v -> loadPaintingDetail(
                "ARCÁNGEL DE LA PUSKA DE PITUMARCA",
                R.drawable.image1,

                "Plateado en alto relieve policromado 120 x 90 cm.",
                "Noé Mamani Ccajiavilca",
                "2022"
        ));
        // Clic listener para el ícono inferior
        bottomIcon.setOnClickListener(v -> loadPaintingDetail(
                "ARCÁNGEL DE LA PUSKA DE PITUMARCA",
                R.drawable.image2,
                "Plateado en alto relieve policromado 120 x 90 cm.",
                "Noé Mamani Ccajiavilca",
                "2022"
        ));
        // Clic listener para el ícono izquierdo
        leftIcon.setOnClickListener(v -> loadPaintingDetail(
                "ARCÁNGEL MIGUEL",
                R.drawable.image3,

                "Plateado en alto relieve 120 x 80 cm.",
                "Noé Mamani Ccajiavilca",
                "2022"
        ));
        // Clic listener para el ícono derecho
        rightIcon.setOnClickListener(v -> loadPaintingDetail(
                "ARCÁNGEL RAFAEL",
                R.drawable.image4,
                "San Rafael es considerado un ángel de la curación, tanto en el ámbito físico como espiritual. " +
                          "Su festividad se celebra cada año el 24 de octubre. " +
                          "Plateado en alto relieve 120 x 80 cm.",
                "Noé Mamani Ccajiavilca",
                "2022"
        ));
        return rootView;
    }
    private void loadPaintingDetail(String name, int imageResId, String description, String artist, String year) {
        PaintingDetailFragment fragment = PaintingDetailFragment.newInstance(name, imageResId, description, artist, year);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .addToBackStack(null)
                .commit();
    }
}
