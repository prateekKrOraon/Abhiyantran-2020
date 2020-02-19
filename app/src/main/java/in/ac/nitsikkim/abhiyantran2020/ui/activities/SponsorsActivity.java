package in.ac.nitsikkim.abhiyantran2020.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.adapters.SponsorsAdapter;
import in.ac.nitsikkim.abhiyantran2020.models.SponsorsModel;

public class SponsorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        Toolbar toolbar = findViewById(R.id.sponsors_toolbar);
        setSupportActionBar(toolbar);

        ArrayList<SponsorsModel> sponsors = new ArrayList<>();

        sponsors.add(
                new SponsorsModel(
                        R.drawable.outline_people_black_48,
                        "Roxent",
                        "Previous Sponsors",
                        "Roxent link"
                )
        );

        sponsors.add(
                new SponsorsModel(
                        R.drawable.outline_people_black_48,
                        "Ravangla Star",
                        "Previous Sponsors",
                        "Ravangla Star link"
                )
        );

        sponsors.add(
                new SponsorsModel(
                        R.drawable.outline_people_black_48,
                        "CAD Desk",
                        "Previous Sponsors",
                        "CAD Desk link"
                )
        );

        RecyclerView recyclerView = findViewById(R.id.sponsors_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        SponsorsAdapter adapter = new SponsorsAdapter(this,sponsors);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
