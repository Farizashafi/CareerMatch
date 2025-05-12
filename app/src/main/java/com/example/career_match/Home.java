package com.example.career_match;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.career_match.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);
//        binding.appBarHome.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_view_profile){
            Intent i = new Intent(getApplicationContext(),View_Profile.class);
            startActivity(i);
        }
        if(id == R.id.nav_view_qualifications){
            Intent i = new Intent(getApplicationContext(),View_Qualifications.class);
            startActivity(i);
        }
        if(id == R.id.nav_view_preferences){
            Intent i = new Intent(getApplicationContext(),View_Pref.class);
            startActivity(i);
        }
        if(id == R.id.nav_view_companies){
            Intent i = new Intent(getApplicationContext(),View_Companies.class);
            startActivity(i);
        }
//        if(id == R.id.nav_view_vacancies){
//            Intent i = new Intent(getApplicationContext(),View_Vacancy.class);
//            startActivity(i);
//        }
        if(id == R.id.nav_send_suggestions){
            Intent i = new Intent(getApplicationContext(),Send_Suggestions.class);
            startActivity(i);
        }
        if(id == R.id.nav_job_rec){
            Intent i = new Intent(getApplicationContext(),get_recommendation.class);
            startActivity(i);
        } if(id == R.id.logout){
            Intent i = new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        }
        return false;
    }
}