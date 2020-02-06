package in.ac.nitsikkim.abhiyantran2020;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import in.ac.nitsikkim.abhiyantran2020.ui.fragments.EventFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.GalleryFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.GuestFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.HomeFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.NotificationsFragment;

public class MainActivity extends AppCompatActivity {

    HomeFragment mHomeFragment;
    EventFragment mEventFragment;
    NotificationsFragment mNotificationFragment;
    GuestFragment mGuestFragment;
    GalleryFragment mGalleryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        mHomeFragment = new HomeFragment();
        mEventFragment = new EventFragment();
        mNotificationFragment = new NotificationsFragment();
        mGalleryFragment = new GalleryFragment();
        mGuestFragment = new GuestFragment();

        switchFragment(mHomeFragment);

        bottomNavigationViewListener(bottomNavigationView);
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
                    case R.id.bottom_nav_notification:
                        switchFragment(mNotificationFragment);
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
