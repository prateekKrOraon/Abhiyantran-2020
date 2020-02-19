package in.ac.nitsikkim.abhiyantran2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import in.ac.nitsikkim.abhiyantran2020.ui.activities.LoginActivity;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.EventFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.GalleryFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.GuestFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.HomeFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    HomeFragment mHomeFragment;
    EventFragment mEventFragment;
    ProfileFragment mProfileFragment;
    GuestFragment mGuestFragment;
    GalleryFragment mGalleryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setTitle("");
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }


        mHomeFragment = new HomeFragment();
        mEventFragment = new EventFragment();
        mProfileFragment = new ProfileFragment();
        mGalleryFragment = new GalleryFragment(this);
        mGuestFragment = new GuestFragment();

        switchFragment(mHomeFragment);

        bottomNavigationViewListener(bottomNavigationView);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    private void bottomNavigationViewListener(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.bottom_nav_home:
                        switchFragment(mHomeFragment);
                        break;
                    case R.id.bottom_nav_events:
                        switchFragment(mEventFragment);
                        break;
                    case R.id.bottom_nav_profile:
                        switchFragment(mProfileFragment);
                        break;
                    case R.id.bottom_nav_guests:
                        switchFragment(mGuestFragment);
                        break;
                    case R.id.bottom_nav_gallery:
                        switchFragment(mGalleryFragment);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

}
