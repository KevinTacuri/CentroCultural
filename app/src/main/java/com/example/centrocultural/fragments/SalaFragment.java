package com.example.centrocultural.fragments;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.centrocultural.R;
import com.example.centrocultural.fragments.PaintingDetailFragment;
import com.example.centrocultural.Painting;

import java.util.ArrayList;
import java.util.List;

public class SalaFragment extends Fragment {

    private static final String ARG_SALA_ID = "sala_id";

    public static SalaFragment newInstance(int salaId) {
        SalaFragment fragment = new SalaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SALA_ID, salaId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sala1, container, false);

        // Obtener el salaId desde los argumentos
        int salaId = getArguments() != null ? getArguments().getInt(ARG_SALA_ID) : -1;

        RelativeLayout relativeLayout = rootView.findViewById(R.id.mapContainer);
        relativeLayout.removeAllViews();

        // Mostrar el contenido basado en salaId
        switch (salaId) {
            case 1:
                setupSala(relativeLayout, 600, 500, getIconDataForSala1(), getPaintingsForSala1()); // 4 íconos para sala 1
                break;
            case 2:
                setupSala(relativeLayout, 600, 500, getIconDataForSala2(), getPaintingsForSala2()); // 3 íconos para sala 2
                break;
            case 3:
                setupSala(relativeLayout, 600, 500, getIconDataForSala3(), getPaintingsForSala3()); // 2 íconos para sala 3
                break;
            case 4:
                setupSala(relativeLayout, 600, 800, getIconDataForSala4(), getPaintingsForSala4()); // 6 íconos para sala 4
                break;
            case 5:
                setupSala(relativeLayout, 600, 700, getIconDataForSala5(), getPaintingsForSala5()); // 2 íconos para sala 5
                break;
            case 6:
                setupSala(relativeLayout, 700, 500, getIconDataForSala6(), getPaintingsForSala6()); // 2 íconos para sala 6
                break;
            case 7:
                setupSala(relativeLayout, 700, 500, getIconDataForSala7(), getPaintingsForSala7()); // 2 íconos para sala 7
                break;
            case 8:
                setupSala(relativeLayout, 600, 800, getIconDataForSala8(), getPaintingsForSala8()); // 2 íconos para sala 8
                break;
            default:
                // Sala por defecto si no se encuentra el ID
                break;
        }

        return rootView;
    }

    private void setupSala(RelativeLayout relativeLayout, int width, int height, List<IconData> iconDataList, List<Painting> paintings) {
        // Crear una vista personalizada para dibujar el rectángulo
        View salaView = new View(getContext()) {
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);

                int viewWidth = getWidth();
                int viewHeight = getHeight();

                float scaleX = (float) viewWidth / 1000;
                float scaleY = (float) viewHeight / 1000;

                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(5);

                Rect rect = new Rect(
                        (int) (100 * scaleX),
                        (int) (100 * scaleY),
                        (int) (100 * scaleX + width * scaleX),
                        (int) (100 * scaleY + height * scaleY)
                );

                canvas.drawRect(rect, paint);
            }
        };

        // Configurar los parámetros de diseño de la vista
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        salaView.setLayoutParams(layoutParams);
        relativeLayout.addView(salaView);

        // Añadir íconos
        addIconsToLayout(relativeLayout, width, height, iconDataList, paintings);
    }

    private void addIconsToLayout(RelativeLayout layout, int rectWidth, int rectHeight, List<IconData> iconDataList, List<Painting> paintings) {
        for (int i = 0; i < iconDataList.size(); i++) {
            IconData iconData = iconDataList.get(i);
            Painting painting = paintings.get(i);
            ImageView icon = new ImageView(getContext());
            icon.setImageResource(iconData.getIconResource());

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            // Calcula las posiciones absolutas basadas en las posiciones relativas
            int xPosition = (int) (iconData.getRelativeX() * rectWidth);
            int yPosition = (int) (iconData.getRelativeY() * rectHeight);

            // Ajusta el margen para la posición del ícono
            params.leftMargin = xPosition;
            params.topMargin = yPosition;

            layout.addView(icon, params);

            // Añadir listener para abrir PaintingDetailFragment al hacer clic
            icon.setOnClickListener(v -> loadPaintingDetail(painting.getName(), painting.getImageResId(), painting.getDescription(), painting.getArtist(), painting.getYear(), painting.getAudioFileName()));
        }
    }
    private List<IconData> getIconDataForSala1() {
        List<IconData> icons = new ArrayList<>();
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.45f, 0.3f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.7f, 0.8f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.2f, 0.8f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.45f, 1.3f));
        return icons;
    }

    private List<Painting> getPaintingsForSala1() {
        List<Painting> paintings = new ArrayList<>();
        paintings.add(new Painting(
                "ARCÁNGEL DE LA PUSKA DE PITUMARCA",
                R.drawable.image1,
                "El Arcángel de la Puska de Pitumarca es una figura religiosa y cultural del distrito de Pitumarca, Cusco, Perú. Representa un ángel armado con una puska (rifle), simbolizando la protección de la fe y la comunidad. Es destacado en festividades locales, como el Corpus Christi, y es conocido por sus elaboradas vestimentas andinas. " +
                        "\nPlateado en alto relieve policromado 120 x 90 cm.",
                "Noé Mamani Ccajiavilca",
                "2022",
                "image1.mp3"
        ));
        paintings.add(new Painting(
                "ARCÁNGEL DE LA PUSKA DE PITUMARCA",
                R.drawable.image2,
                "El Arcángel de la Puska de Pitumarca es una figura religiosa de Pitumarca, Cusco, Perú. Representa un ángel armado con una puska (rifle), simbolizando la defensa de la fe y la protección comunitaria. Se destaca en festividades como el Corpus Christi y es conocido por sus vistosos trajes andinos. " +
                        "\nPlateado en alto relieve policromado 120 x 90 cm.",
                "Noé Mamani Ccajiavilca",
                "2022",
                "image2.mp3"

        ));
        paintings.add(new Painting(
                "ARCÁNGEL RAFAEL",
                R.drawable.image3,
                "San Rafael es considerado un ángel de la curación tanto en el ámbito físico como espiritual, su festividad se celebra cada año el 24 de octubre. " +
                        "\nPintura plateada en alto relieve 120 x 80 cm.",
                "Noé Mamani Ccajiavilca",
                "2022",
                "image3.mp3"

        ));
        paintings.add(new Painting(
                "ARCÁNGEL MIGUEL",
                R.drawable.image4,
                "La festividad del Arcángel Miguel en Perú, especialmente en Carhuaz, Áncash, se celebra el 29 de septiembre. Incluye procesiones, danzas, música y ferias, destacando la imagen del arcángel como protector contra el mal. Esta celebración combina tradiciones religiosas y andinas, reflejando la diversidad cultural del país. " +
                        "\nPlateado en alto relieve 120 x 80 cm.",
                "Noé Mamani Ccajiavilca",
                "2022",
                "image4.mp3"

        ));
        return paintings;
    }
    private List<IconData> getIconDataForSala2() {
        List<IconData> icons = new ArrayList<>();
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.7f, 0.8f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.2f, 0.8f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.45f, 1.3f));
        return icons;
    }

    private List<Painting> getPaintingsForSala2() {
        List<Painting> paintings = new ArrayList<>();
        paintings.add(new Painting(
                "RESIDUO B: La memoria es un refugio en ruinas #1",
                R.drawable.image5,
                "Muestra una tomografía lateral de un cráneo con un gráfico de A hasta E y líneas que cruzan entre estas letras, simbolizando la fragmentación y complejidad de la memoria humana. " +
                        "\nMixta (Collage) 50 x 33 cm.",
                "Giancarlo Melgar",
                "2024",
                "image5.mp3"

        ));
        paintings.add(new Painting(
                "RESIDUO B: Miles de diagnósticos una misma imposibilidad",
                R.drawable.image6,
                "Presenta una tomografía frontal del cráneo en un plano cartesiano, con ejes XY y la inscripción TEM en la parte inferior, destacando la multiplicidad de diagnósticos y la incertidumbre en la interpretación médica. " +
                        "\nMixta (Collage) 100 x 70 cm.",
                "Giancarlo Melgar",
                "2024",
                "image6.mp3"

        ));
        paintings.add(new Painting(
                "RESIDUO B: Miles de diagnósticos una misma imposibilidad",
                R.drawable.image7,
                "Muestra una tomografía del pecho que revela los pulmones, costillas y ojos, ilustrando la repetitiva lucha contra la enfermedad y la constante búsqueda de respuestas en la medicina. " +
                        "\nMixta (Collage) 100 x 70 cm.",
                "Giancarlo Melgar",
                "2024",
                "image7.mp3"

        ));
        return paintings;
    }

    private List<IconData> getIconDataForSala3() {
        List<IconData> icons = new ArrayList<>();
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.7f, 0.8f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.2f, 0.8f));
        return icons;
    }

    private List<Painting> getPaintingsForSala3() {
        List<Painting> paintings = new ArrayList<>();
        paintings.add(new Painting(
                "RESIDUO #11",
                R.drawable.image8,
                "Muestra un mamífero con patas delanteras y traseras con franjas de cebra, mientras que su cuerpo y cabeza son marrones con una cara de tapir, destacando su trompa. Debajo de la imagen, se ven hojas con su catalogación, combinando la observación científica con el arte. " +
                        "\nCollage 31 x 24 cm.",
                "Paul Lazarte Velásquez",
                "2024",
                "image8.mp3"

        ));
        paintings.add(new Painting(
                "RESIDUO #8",
                R.drawable.image9,
                "Se muestra un auquénido en la sierra, compuesto por fotos del animal y del paisaje. Esta obra resalta la conexión entre la criatura y su entorno, fusionando la naturaleza y la fotografía en una única representación visual. " +
                        "\nCollage 42 x 29.5 cm.",
                "Paul Lazarte Velásquez",
                "2024",
                "image9.mp3"

        ));
        return paintings;
    }
    private List<IconData> getIconDataForSala4() {
        List<IconData> icons = new ArrayList<>();
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.45f, 0.2f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.7f, 1f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.2f, 0.5f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.7f, 0.5f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.2f, 1f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.45f, 1.25f));
        return icons;
    }

    private List<Painting> getPaintingsForSala4() {
        List<Painting> paintings = new ArrayList<>();
        paintings.add(new Painting(
                "CAMINO A LA LUNA Y A VENUS",
                R.drawable.image10,
                "Muestra un paisaje rocoso con la luna visible en el fondo, creando una atmósfera mística y evocando un viaje celestial. " +
                        "\nFotografia 45 x 30 cm.",
                "Moisés Calizaya Alvarado",
                "2017",
                "image10.mp3"

        ));
        paintings.add(new Painting(
                "RODEANDO LA TORRE",
                R.drawable.image11,
                "Destaca una gran estructura rocosa solitaria rodeada de pequeños arbustos, simbolizando la majestuosidad y la soledad de la naturaleza. " +
                        "\nFotografia 39 x 39 cm.",
                "Moisés Calizaya Alvarado",
                "2017",
                "image11.mp3"

        ));
        paintings.add(new Painting(
                "ATALAYA",
                R.drawable.image12,
                "Muestra una estructura rocosa vista de lejos, con detalles de rocas grandes circundantes y un paisaje rocoso, sugiriendo una atalaya natural en un entorno agreste. " +
                        "\nFotografía 30 x 52 cm.",
                "Moisés Calizaya Alvarado",
                "2017",
                "image12.mp3"

        ));
        paintings.add(new Painting(
                "CASTILLO BLANCO, CASTILLO NEGRO",
                R.drawable.image13,
                "Presenta dos estructuras rocosas en un paisaje andino, una iluminada por el sol y la otra en sombra, reflejando el contraste entre luz y oscuridad. " +
                        "\nFotografía 30 x 65 cm.",
                "Moisés Calizaya Alvarado",
                "2017",
                "image13.mp3"

        ));
        paintings.add(new Painting(
                "TORRE ALBA",
                R.drawable.image14,
                "Presenta una estructura rocosa brillante iluminada por el sol, rodeada de un suelo rocoso y liso, resaltando la belleza y la imponencia del amanecer. " +
                        "\nFotografía 39 x 39 cm.",
                "Moisés Calizaya Alvarado",
                "2017",
                "image14.mp3"

        ));
        paintings.add(new Painting(
                "GIGANTES GUARDIANES",
                R.drawable.image15,
                "Muestra grandes rocas alargadas una junto a otra, evocando la imagen de guardianes protectores del paisaje. " +
                        "\nFotografía 30 x 45 cm.",
                "Moisés Calizaya Alvarado",
                "2017",
                "image15.mp3"

        ));
        return paintings;
    }
    private List<IconData> getIconDataForSala5() {
        List<IconData> icons = new ArrayList<>();
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.45f, 0.2f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.15f, 0.8f));
        return icons;
    }


    private List<Painting> getPaintingsForSala5() {
        List<Painting> paintings = new ArrayList<>();
        paintings.add(new Painting(
                "Zapping: conexión compartida",
                R.drawable.image16,
                "Captura un momento de introspección y autocuidado, donde una joven se sumerge en el arte de Vermeer, simbolizando la transformación personal a través de la quietud y la reflexión. " +
                        "\nÓleo sobre lienzo 45 x 60 cm.",
                "Cristal Luque Castro",
                "2024",
                "image16.mp3"

        ));
        paintings.add(new Painting(
                "A solas: Distención alquímica",
                R.drawable.image17,
                "Refleja la calidez y la unión familiar en la era digital, mostrando a una madre y su hija disfrutando juntas, simbolizando la armonía y el amor en los pequeños momentos cotidianos. " +
                        "\nÓleo sobre lienzo 100 x 120 cm.",
                "Cristal Luque Castro",
                "2024",
                "image18.mp3"

        ));
        return paintings;
    }
    private List<IconData> getIconDataForSala6() {
        List<IconData> icons = new ArrayList<>();
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.45f, 0.3f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.7f, 0.8f));
        return icons;
    }

    private List<Painting> getPaintingsForSala6() {
        List<Painting> paintings = new ArrayList<>();
        paintings.add(new Painting(
                "Diálogo perenne en la sala de estar",
                R.drawable.image18,
                "Explora la intimidad y la conexión en la cotidianidad, mostrando a una pareja en un momento de complicidad. La mujer teje y el hombre sostiene el hilado, simbolizando la creación conjunta y el diálogo continuo que une sus vidas. " +
                        "\nÓleo sobre lienzo 120 x 200 cm.",
                "Cristal Luque Castro",
                "2024",
                "image19.mp3"

        ));
        paintings.add(new Painting(
                "Picnic: Reminiscencias bajo la mesa",
                R.drawable.image19,
                "Captura la inocencia y el placer simple del niño en su escondite, rodeado de bocadillos y juguetes. La escena también revela una conexión tierna con un gato, destacando la alegría compartida y la imaginación infantil en un entorno íntimo. " +
                        "\nÓleo sobre lienzo 100 x 160 cm.",
                "Cristal Luque Castro",
                "2024",
                "image19.mp3"

        ));
        return paintings;
    }
    private List<IconData> getIconDataForSala7() {
        List<IconData> icons = new ArrayList<>();
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.45f, 0.3f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.45f, 1.3f));
        return icons;
    }

    private List<Painting> getPaintingsForSala7() {
        List<Painting> paintings = new ArrayList<>();
        paintings.add(new Painting(
                "S/T",
                R.drawable.image20,
                "Presenta una escena de calma y compañía, donde una chica y su perro descansan juntos en la cama. La imagen evoca una sensación de paz y cercanía, subrayando la conexión emocional y la tranquilidad compartida en un espacio privado. " +
                        "\nÓleo sobre lienzo 80 x 100 cm.",
                "Cristal Luque Castro",
                "2019",
                "image20.mp3"

        ));
        paintings.add(new Painting(
                "Picnic: Reminiscencias sobre la mesa",
                R.drawable.image21,
                "Muestra a una señora sentada junto a una mesa repleta de frutas, frascos y galletas. La pintura celebra la abundancia y la gratitud en los pequeños placeres, evocando la nostalgia y el confort de un picnic casero. " +
                        "\nÓleo sobre lienzo 100 x 120 cm.",
                "Cristal Luque Castro",
                "2024",
                "image21.mp3"

        ));
        return paintings;
    }
    private List<IconData> getIconDataForSala8() {
        List<IconData> icons = new ArrayList<>();
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.15f, 0.7f));
        icons.add(new IconData(R.drawable.sharp_aspect_ratio_24, 0.45f, 1.3f));
        return icons;
    }

    private List<Painting> getPaintingsForSala8() {
        List<Painting> paintings = new ArrayList<>();
        paintings.add(new Painting(
                "Sol de Mayo: Cada prenda en este cordel es un disparador de estímulos",
                R.drawable.image22,
                "Presenta una escena de introspección y conexión personal. La figura solitaria en el techo, rodeada de ropa tendida y elementos domésticos, transforma la rutina diaria en un acto poético, donde cada prenda se convierte en un catalizador de recuerdos y sensaciones, entrelazando lo cotidiano con lo introspectivo. " +
                        "\nÓleo sobre lienzo 100 x 130 cm.",
                "Cristal Luque Castro",
                "1498",
                "image22.mp3"

        ));
        paintings.add(new Painting(
                "Hogar: Me niego a ser oculto",
                R.drawable.image23,
                "Captura la tensión entre la invisibilidad y la presencia en un entorno natural. La figura encapuchada en el naranjo, mirando fijamente desde entre las hojas, representa una búsqueda intensa de identidad y visibilidad, fusionando la naturaleza y el individuo en una declaración poderosa de existencia y deseo de ser visto. " +
                        "\nÓleo sobre lienzo 40 x 60 cm.",
                "Cristal Luque Castro",
                "2023",
                "image23.mp3"

        ));
        return paintings;
    }

    private void loadPaintingDetail(String name, int imageResId, String description, String artist, String year, String audioFileName) {
        PaintingDetailFragment fragment = PaintingDetailFragment.newInstance(name, imageResId, description, artist, year, audioFileName);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .addToBackStack(null)
                .commit();
    }

    // Clase interna para almacenar datos de íconos
    private static class IconData {
        private final int iconResource;
        private final float relativeX;
        private final float relativeY;

        public IconData(int iconResource, float relativeX, float relativeY) {
            this.iconResource = iconResource;
            this.relativeX = relativeX;
            this.relativeY = relativeY;
        }

        public int getIconResource() {
            return iconResource;
        }

        public float getRelativeX() {
            return relativeX;
        }

        public float getRelativeY() {
            return relativeY;
        }
    }
}
