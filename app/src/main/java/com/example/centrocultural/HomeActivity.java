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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


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
    }

    private void loadFragment(Fragment fragment){
        Log.d(TAG, "loadFragment: Cargando fragmento " + fragment.getClass().getSimpleName());
        if(fragmentManager!=null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
            fragmentTransaction.commit();
        }
    }
}


