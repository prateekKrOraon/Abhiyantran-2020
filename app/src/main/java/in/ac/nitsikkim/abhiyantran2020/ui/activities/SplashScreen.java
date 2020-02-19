package in.ac.nitsikkim.abhiyantran2020.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Map;
import java.util.Timer;

import in.ac.nitsikkim.abhiyantran2020.MainActivity;
import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.utility.Constants;
import in.ac.nitsikkim.abhiyantran2020.utility.User;

import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.likedPosts;
import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.name;
import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.phoneNo;
import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.regEvents;
import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.rollNo;
import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.userColl;

public class SplashScreen extends AppCompatActivity {

    TimerThread timerThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView logo = findViewById(R.id.splash_logo);
        ImageView title = findViewById(R.id.splash_title);

        timerThread = new TimerThread();
        timerThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerThread.interrupt();
    }

    private class TimerThread extends Thread{

        int sec = 1000;
        @Override
        public void run() {
            super.run();

            while(sec != 0){
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sec -= 10;
            }

            finish();

        }
    }
}
