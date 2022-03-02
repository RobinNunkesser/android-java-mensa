package de.hshl.isd.mensa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import de.hshl.isd.mensa.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    private NavController mNavController;
    private BottomNavigationView mNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        mNavView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(mNavView, mNavController);
    }
}