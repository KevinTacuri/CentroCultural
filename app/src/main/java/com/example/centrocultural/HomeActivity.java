package com.example.centrocultural;


import static android.content.ContentValues.TAG;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;

import android.view.MenuItem;

import com.example.centrocultural.fragments.CuadrosFragment;
import com.example.centrocultural.fragments.HomeFragment;
import com.example.centrocultural.fragments.MapFragment;
import com.example.centrocultural.fragments.PaintingDetailFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/*import com.example.centrocultural.AppDatabase;
import com.example.centrocultural.DatabaseClient;
import com.example.centrocultural.entities.Autor;
import com.example.centrocultural.entities.Pintura;
import com.example.centrocultural.entities.Sala;*/

public class HomeActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private CuadrosFragment cuadrosFragment;
    private MapFragment mapaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Inicio de HomeActivity");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fragmentManager = getSupportFragmentManager();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.menu_home) {
                    homeFragment = HomeFragment.newInstance("", "");
                    loadFragment(homeFragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.menu_cuadros) {
                    cuadrosFragment = CuadrosFragment.newInstance(1, 0);
                    loadFragment(cuadrosFragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.menu_mapa) {
                    mapaFragment = MapFragment.newInstance("", "");
                    loadFragment(mapaFragment);
                    return true;
                } else {
                    return false;
                }
            }
        });

        homeFragment = HomeFragment.newInstance("", "");
        loadFragment(homeFragment);

        if (getIntent() != null && getIntent().hasExtra("fragment")) {
            String fragmentName = getIntent().getStringExtra("fragment");
            if ("PaintingDetailFragment".equals(fragmentName)) {
                String filename = getIntent().getStringExtra(AudioplayService.FILENAME);
                showPaintingDetailFragment(filename);
            }
        }
        //AppDatabase db = DatabaseClient.getInstance(getApplicationContext()).getDatabase();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Sala sala1 = new Sala();
                sala1.setNombre("Sala 1");
                sala1.setDescripcion("Galería para Maestros");

                Autor autor1 = new Autor();
                autor1.setNombre("Noé");
                autor1.setApellido("Mamani Ccajiavilca");

                Pintura pintura1 = new Pintura();
                pintura1.setTitulo("ARCÁNGEL DE LA PUSKA DE PITUMARCA");
                pintura1.setAutorId(1); //
                pintura1.setSalaId(1); //
                pintura1.setTecnica("Plateado en alto relieve policromado 120 x 90 cm.");
                pintura1.setCategoria("Relieve");
                pintura1.setDescripcion("Descripción de la pintura");
                pintura1.setAnio("2022");
                pintura1.setEnlace("enlace_a_la_imagen.jpg");

                // Insertar los datos
                db.salaDao().insert(sala1);
                db.autorDao().insert(autor1);
                db.pinturaDao().insert(pintura1);
            }
        }).start();*/
    }

    private void loadFragment(Fragment fragment){
        Log.d(TAG, "loadFragment: Cargando fragmento " + fragment.getClass().getSimpleName());
        if(fragmentManager!=null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
            fragmentTransaction.commit();
        }
    }
    private void showPaintingDetailFragment(String filename) {
        PaintingDetailFragment fragment = new PaintingDetailFragment();

        Bundle args = new Bundle();
        args.putString(AudioplayService.FILENAME, filename);
        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit();
    }
}