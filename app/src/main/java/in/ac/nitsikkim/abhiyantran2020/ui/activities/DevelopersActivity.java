package in.ac.nitsikkim.abhiyantran2020.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.adapters.DevelopersAdapter;
import in.ac.nitsikkim.abhiyantran2020.models.DevelopersModel;

public class DevelopersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);

        Toolbar toolbar = findViewById(R.id.developers_toolbar);
        setSupportActionBar(toolbar);

        ArrayList<DevelopersModel> devs = new ArrayList<>();

        devs.add(
                new DevelopersModel(
                        R.drawable.outline_person_black_48,
                        "Prateek Kumar Oraon",
                        "Developer",
                        "9931905946",
                        "b170078@nitsikkim.ac.in",
                        "github.com/prateekKrOraon",
                        "linkedin.com/in/prateekKumarOraon"
                )
        );

        devs.add(
                new DevelopersModel(
                        R.drawable.outline_person_black_48,
                        "Dhrubajit Sarkar",
                        "Developer",
                        "9931905946",
                        "b1700177@nitsikkim.ac.in",
                        "github.com/prateekKrOraon",
                        "linkedin.com/in/prateekKumarOraon"
                )
        );

        devs.add(
                new DevelopersModel(
                        R.drawable.outline_person_black_48,
                        "Kamal Kumar",
                        "Developer",
                        "9931905946",
                        "b170034@nitsikkim.ac.in",
                        "github.com/prateekKrOraon",
                        "linkedin.com/in/prateekKumarOraon"
                )
        );

        RecyclerView recyclerView = findViewById(R.id.developers_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        DevelopersAdapter adapter = new DevelopersAdapter(this,devs);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
