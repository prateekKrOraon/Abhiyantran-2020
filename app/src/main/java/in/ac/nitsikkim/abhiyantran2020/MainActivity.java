package in.ac.nitsikkim.abhiyantran2020;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import in.ac.nitsikkim.abhiyantran2020.ui.fragments.EventFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.GalleryFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.GuestFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.HomeFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.AboutFragment;

public class MainActivity extends AppCompatActivity {

    HomeFragment mHomeFragment;
    EventFragment mEventFragment;
    AboutFragment mAboutFragment;
    GuestFragment mGuestFragment;
    GalleryFragment mGalleryFragment;

    private TextView eventText;
    private ImageView eventImage;
    private TextView galleryText;
    private ImageView galleryImage;
    private TextView homeText;
    private TextView guestsText;
    private ImageView guestsImage;
    private TextView aboutText;
    private ImageView aboutImage;
    private ImageView home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHomeFragment = new HomeFragment();
        mEventFragment = new EventFragment();
        mAboutFragment = new AboutFragment();
        mGalleryFragment = new GalleryFragment();
        mGuestFragment = new GuestFragment();

        eventText = findViewById(R.id.bottom_nav_events_new_text);
        eventImage = findViewById(R.id.bottom_nav_events_new_image);
        galleryText = findViewById(R.id.bottom_nav_gallery_new_text);
        galleryImage = findViewById(R.id.bottom_nav_gallery_new_image);
        homeText = findViewById(R.id.bottom_nav_home_new_text);
        guestsText = findViewById(R.id.bottom_nav_guest_new_text);
        guestsImage = findViewById(R.id.bottom_nav_guest_new_image);
        aboutText = findViewById(R.id.bottom_nav_about_new_text);
        aboutImage = findViewById(R.id.bottom_nav_about_new_image);

        LinearLayout event = findViewById(R.id.bottom_nav_events_new);
        LinearLayout gallery= findViewById(R.id.bottom_nav_gallery_new);
        LinearLayout guests = findViewById(R.id.bottom_nav_guests_new);
        LinearLayout about = findViewById(R.id.bottom_nav_about_new);
        home = findViewById(R.id.bottom_nav_home_new);

        switchFragment(mHomeFragment,homeText,home);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(mEventFragment,eventText,eventImage);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(mHomeFragment,homeText,home);
            }
        });

        guests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(mGuestFragment,guestsText,guestsImage);
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(mGalleryFragment,galleryText,galleryImage);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(mAboutFragment,aboutText,aboutImage);
            }
        });

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        try {
            Objects.requireNonNull(getSupportActionBar()).setTitle("");
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }


//        Intent intent = new Intent(this, BottomNavBar.class);
//        startActivity(intent);

    }

    private void switchFragment(Fragment fragment,TextView textView, ImageView imageView) {
        resetColor(textView);
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        imageView.setColorFilter(getResources().getColor(R.color.colorAccent));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    private void resetColor(TextView textView) {

        if(textView.getId() != R.id.bottom_nav_events_new_text){
            eventText.setTextColor(getResources().getColor(R.color.white));
            eventImage.setColorFilter(getResources().getColor(R.color.white));
        }
        if(textView.getId() != R.id.bottom_nav_gallery_new_text){
            galleryText.setTextColor(getResources().getColor(R.color.white));
            galleryImage.setColorFilter(getResources().getColor(R.color.white));
        }
        if(textView.getId() != R.id.bottom_nav_home_new_text){
            homeText.setTextColor(getResources().getColor(R.color.white));
            home.setColorFilter(getResources().getColor(R.color.white));
        }
        if(textView.getId() != R.id.bottom_nav_guest_new_text){
            guestsText.setTextColor(getResources().getColor(R.color.white));
            guestsImage.setColorFilter(getResources().getColor(R.color.white));
        }
        if(textView.getId() != R.id.bottom_nav_about_new_text){
            aboutText.setTextColor(getResources().getColor(R.color.white));
            aboutImage.setColorFilter(getResources().getColor(R.color.white));
        }
    }

}
