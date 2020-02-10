package in.ac.nitsikkim.abhiyantran2020.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.Timestamp;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.adapters.RegisteredEventsAdapter;
import in.ac.nitsikkim.abhiyantran2020.models.RegisteredEventsModel;

public class RegisteredEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_events);

        Toolbar toolbar = findViewById(R.id.registered_events_toolbar);
        setSupportActionBar(toolbar);
        try {

            getSupportActionBar().setTitle("Registered Events");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }catch (NullPointerException ex){
            ex.printStackTrace();
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ArrayList<RegisteredEventsModel> events = new ArrayList<>();

        events.add(
                new RegisteredEventsModel(
                        "Call of Duty",
                        Timestamp.now(),
                        "Computer Lab 3",
                        "In Game Rules and constraints:\n" +
                                " Match type will be Domination (Capture the flag), maps will be randomly\n" +
                                "picked at the time of the event.\n" +
                                "Each round will be a best of three encounter, with each sub round lasting for\n" +
                                "10 minutes. Team winning two out of the three will be declared as winner.\n" +
                                "Players are not allowed to use airstrike, helicopter, Gun-grenade Launcher, 3\n" +
                                "frag grenade perk, RPG, C4, Claymore, martyrdom.\n" +
                                "(Any team violating these conditions will be disqualified immediately)"
                )
        );

        events.add(
                new RegisteredEventsModel(
                        "Scratch Your Brain",
                        Timestamp.now(),
                        "Multi-Purpose Hall",
                        "Single participation event.\n" +
                                "2. It will include abstract/conceptual reasoning, verbal reasoning and\n" +
                                "numerical Reasoning."
                )
        );

        events.add(
                new RegisteredEventsModel(
                        "Just a Minute",
                        Timestamp.now(),
                        "Hall 1",
                        "Just-A-Minute (or JAM) is an all round-fun event that is all about the control of\n" +
                                "the mind over the mouth. Can you make it through sixty seconds of non-stop\n" +
                                "talking without hesitation, repetition, or deviation? Or will the sheer pressure\n" +
                                "make you crumble and have your competitors pounce on you in an instant?"
                )
        );

        RecyclerView recyclerView = findViewById(R.id.reg_events_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RegisteredEventsAdapter adapter = new RegisteredEventsAdapter(this,events);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
