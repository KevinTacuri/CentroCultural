package com.example.centrocultural.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centrocultural.Painting;
import com.example.centrocultural.R;
import com.example.centrocultural.Room;
import com.example.centrocultural.RoomAdapter;

import java.util.ArrayList;
import java.util.List;

public class CuadrosFragment extends Fragment {

    private static final String ARG_ROOM_INDEX = "arg_room_index";
    private static final String ARG_PAINTING_INDEX = "painting_index";

    private int roomIndex;
    private int paintingIndex;
    public static CuadrosFragment newInstance(int roomIndex, int paintingIndex) {
        CuadrosFragment fragment = new CuadrosFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ROOM_INDEX, roomIndex);
        args.putInt(ARG_PAINTING_INDEX, paintingIndex);
        fragment.setArguments(args);
        return fragment;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            roomIndex = getArguments().getInt(ARG_ROOM_INDEX);
            paintingIndex = getArguments().getInt(ARG_PAINTING_INDEX);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuadros, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.roomsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Room> rooms = createRooms();
        Room selectedRoom = rooms.get(roomIndex);
        List<Painting> paintings = selectedRoom.getPaintings();


        if (roomIndex >= 0 && roomIndex < rooms.size()) {
            Room room = rooms.get(roomIndex);
            RoomAdapter adapter = new RoomAdapter(rooms, getContext());
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    private List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();

        List<Painting> paintingsSala1 = new ArrayList<>();
        paintingsSala1.add(new Painting(
                "ARCÁNGEL DE LA PUSKA DE PITUMARCA",
                R.drawable.image1,
                "Plateado en alto relieve policromado 120 x 90 cm.",
                "Noé Mamani Ccajiavilca",
                "2022",
                "image1.mp3"
        ));
        paintingsSala1.add(new Painting(
                "ARCÁNGEL DE LA PUSKA DE PITUMARCA",
                R.drawable.image2,
                "Plateado en alto relieve policromado 120 x 90 cm.",
                "Noé Mamani Ccajiavilca",
                "2022",
                "image2.mp3"
        ));
        paintingsSala1.add(new Painting(
                "ARCÁNGEL MIGUEL",
                R.drawable.image3,
                "Plateado en alto relieve 120 x 80 cm.",
                "Noé Mamani Ccajiavilca",
                "2022",
                "image3.mp3"
        ));
        paintingsSala1.add(new Painting(
                "ARCÁNGEL MIGUEL",
                R.drawable.image4,
                "Plateado en alto relieve 120 x 80 cm.",
                "Noé Mamani Ccajiavilca",
                "2022",
                "image4.mp3"
        ));
        rooms.add(new Room("Galería I Exposición Colectiva de Pintura “Arequipa, luz, color y expresión” a cargo de la Unidad de Promoción y Desarrollo Cultural.", paintingsSala1));

        List<Painting> paintingsSala2 = new ArrayList<>();
        paintingsSala2.add(new Painting(
                "RESIDUO B: La memoria es un refugio en ruinas #1",
                R.drawable.image5,
                "Mixta (Collage) 50 x 33 cm.",
                "Giancarlo Melgar",
                "2024",
                "image5.mp3"
        ));
        paintingsSala2.add(new Painting(
                "RESIDUO B: Miles de diagnósticos una misma imposibilidad",
                R.drawable.image6,
                "Mixta (Collage) 100 x 70 cm.",
                "Giancarlo Melgar",
                "2024",
                "image6.mp3"
        ));
        paintingsSala2.add(new Painting(
                "RESIDUO B: Miles de diagnósticos una misma imposibilidad",
                R.drawable.image7,
                "Mixta (Collage) 100 x 70 cm.",
                "Giancarlo Melgar",
                "2024",
                "image7.mp3"
        ));
        rooms.add(new Room("Galería II Exposición Individual de Pintura “Arraigo, de los hijos que se van” del artista Víctor Sanjinés García.", paintingsSala2));

        List<Painting> paintingsSala3 = new ArrayList<>();
        paintingsSala3.add(new Painting(
                "RESIDUO #11",
                R.drawable.image8,
                "Collage 31 x 24 cm.",
                "Paul Lazarte Velásquez",
                "2024",
                "image8.mp3"
        ));
        paintingsSala3.add(new Painting(
                "RESIDUO #8",
                R.drawable.image8,
                "Collage 42 x 29.5 cm.",
                "Paul Lazarte Velásquez",
                "2024",
                "image8.mp3"
        ));
        rooms.add(new Room("Galería III Exposición Individual de Pintura “Al otro lado de lo real, geometría en vertical” del artista Adolf Ancasi Batallanos.", paintingsSala3));

        List<Painting> paintingsSala4 = new ArrayList<>();
        paintingsSala4.add(new Painting(
                "CAMINO A LA LUNA Y A VENUS",
                R.drawable.image10,
                "Fotografia 45 x 30 cm.",
                "Moisés Calizaya Alvarado",
                "2017",
                "image10.mp3"
        ));
        paintingsSala4.add(new Painting(
                "RODEANDO LA TORRE",
                R.drawable.image11,
                "Fotografia 39 x 39 cm.",
                "Moisés Calizaya Alvarado",
                "2017",
                "image11.mp3"
        ));
        paintingsSala4.add(new Painting(
                "ATALAYA",
                R.drawable.image12,
                "Fotografía 30 x 52 cm.",
                "Moisés Calizaya Alvarado",
                "2017",
                "image12.mp3"
        ));
        paintingsSala4.add(new Painting(
                "CASTILLO BLANCO, CASTILLO NEGRO",
                R.drawable.image13,
                "Fotografía 30 x 65 cm.",
                "Moisés Calizaya Alvarado",
                "2017",
                "image13.mp3"
        ));
        paintingsSala4.add(new Painting(
                "TORRE ALBA",
                R.drawable.image14,
                "Fotografía 39 x 39 cm.",
                "Moisés Calizaya Alvarado",
                "2017",
                "image14.mp3"
        ));
        paintingsSala4.add(new Painting(
                "GIGANTES GUARDIANES",
                R.drawable.image15,
                "Fotografía 30 x 45 cm.",
                "Moisés Calizaya Alvarado",
                "2017",
                "image15.mp3"
        ));
        rooms.add(new Room("Galería IV Exposición Colectiva de Pintura “Blanca ciudad” a cargo de la Asociación “Luces del Sol”", paintingsSala4));

        List<Painting> paintingsSala5 = new ArrayList<>();
        paintingsSala5.add(new Painting(
                "Zapping: conexión compartida",
                R.drawable.image16,
                "Óleo sobre lienzo 45 x 60 cm.",
                "Cristal Luque Castro",
                "2024",
                "image16.mp3"
        ));
        paintingsSala5.add(new Painting(
                "A solas: Distención alquímica",
                R.drawable.image17,
                "Óleo sobre lienzo 100 x 120 cm.",
                "Cristal Luque Castro",
                "2024",
                "image17.mp3"
        ));
        rooms.add(new Room("Galería V Exposición Colectiva de Pintura “Arequipa, luz, color y expresión” a cargo de la Unidad de Promoción y Desarrollo Cultural.", paintingsSala5));

        List<Painting> paintingsSala6 = new ArrayList<>();
        paintingsSala6.add(new Painting(
                "Diálogo perenne en la sala de estar",
                R.drawable.image18,
                "Óleo sobre lienzo 120 x 200 cm.",
                "Cristal Luque Castro",
                "2024",
                "image18.mp3"
        ));
        paintingsSala6.add(new Painting(
                "Picnic: Reminiscencias bajo la mesa",
                R.drawable.image19,
                "Óleo sobre lienzo 100 x 160 cm.",
                "Cristal Luque Castro",
                "2024",
                "image19.mp3"
        ));
        rooms.add(new Room("Galería VI Exposición individual de Fotografía “Estados Naturales” del artista Paul Miguel Pérez Pastor.", paintingsSala6));

        List<Painting> paintingsSala7 = new ArrayList<>();
        paintingsSala7.add(new Painting(
                "S/T",
                R.drawable.image20,
                "Óleo sobre lienzo 80 x 100 cm.",
                "Cristal Luque Castro",
                "2019",
                "image20.mp3"

        ));
        paintingsSala7.add(new Painting(
                "Picnic: Reminiscencias sobre la mesa",
                R.drawable.image21,
                "Oleo sobre lienzo 100 x 120 cm.",
                "Cristal Luque Castro",
                "2024",
                "image21.mp3"
        ));
        rooms.add(new Room("Galería VII (La Sala) Exposición Individual de Acuarela “Rostros Andinos” de Elías Condori Condori.", paintingsSala7));

        List<Painting> paintingsSala8 = new ArrayList<>();
        paintingsSala8.add(new Painting(
                "Sol de Mayo: Cada prenda en este cordel es un disparador de estímulos",
                R.drawable.image22,
                "Óleo sobre lienzo 100 x 130 cm.",
                "Cristal Luque Castro",
                "1498",
                "image22.mp3"
        ));
        paintingsSala8.add(new Painting(
                "Hogar: Me niego a ser oculto",
                R.drawable.image23,
                "Óleo sobre lienzo 40 x 60 cm.",
                "Cristal Luque Castro",
                "2023",
                "image23.mp3"
        ));
        rooms.add(new Room("Galería VIII Exposición Individual de Acuarela “Visiones de Confinamiento” a cargo de la artista Deyvi Coaquila Coaguila.", paintingsSala8));

        return rooms;
    }
}
