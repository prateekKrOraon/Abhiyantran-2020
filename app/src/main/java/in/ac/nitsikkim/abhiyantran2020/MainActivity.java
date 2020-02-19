package in.ac.nitsikkim.abhiyantran2020;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.util.Listener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Map;
import java.util.Objects;

import in.ac.nitsikkim.abhiyantran2020.ui.activities.LoginActivity;
import in.ac.nitsikkim.abhiyantran2020.ui.activities.SplashScreen;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.EventFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.GalleryFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.GuestFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.HomeFragment;
import in.ac.nitsikkim.abhiyantran2020.ui.fragments.AboutFragment;
import in.ac.nitsikkim.abhiyantran2020.utility.User;

import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.likedPosts;
import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.name;
import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.phoneNo;
import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.regEvents;
import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.rollNo;
import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.userColl;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private HomeFragment mHomeFragment;
    private EventFragment mEventFragment;
    private AboutFragment mAboutFragment;
    private GuestFragment mGuestFragment;
    private GalleryFragment mGalleryFragment;

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

    private LinearLayout event;
    private LinearLayout gallery;
    private LinearLayout guests;
    private LinearLayout about;
    private boolean first = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(User.user == null)
            startActivity(new Intent(this,SplashScreen.class));
        else
            switchFragment(mHomeFragment,homeText,home);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestore.collection(userColl).document("9931905946");

        CollectionReference reference = firestore.collection(userColl);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                User.user = new User(
                        documentSnapshot.getString(name),
                        documentSnapshot.getString(phoneNo),
                        documentSnapshot.getString(rollNo),
                        (Map) documentSnapshot.get(likedPosts),
                        (Map) documentSnapshot.get(regEvents)
                );

                if(User.user != null && !first){
                    setContentView(R.layout.activity_main);
                    setLayout();
                    first = true;
                }
            }
        });


    }

    private void setLayout(){
        mHomeFragment = new HomeFragment();
        mEventFragment = new EventFragment();
        mAboutFragment = new AboutFragment();
        mGalleryFragment = new GalleryFragment(this);
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

        event = findViewById(R.id.bottom_nav_events_new);
        gallery = findViewById(R.id.bottom_nav_gallery_new);
        guests = findViewById(R.id.bottom_nav_guests_new);
        about = findViewById(R.id.bottom_nav_about_new);
        home = findViewById(R.id.bottom_nav_home_new);

        switchFragment(mHomeFragment,homeText,home);

        addBottomNavListeners();

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        try {
            Objects.requireNonNull(getSupportActionBar()).setTitle("");
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    private void addBottomNavListeners() {

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
