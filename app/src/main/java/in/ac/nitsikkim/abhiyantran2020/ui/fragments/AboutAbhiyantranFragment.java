package in.ac.nitsikkim.abhiyantran2020.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.adapters.AboutAbhiyantranAdapter;
import in.ac.nitsikkim.abhiyantran2020.models.ProfileOptionsModel;

public class AboutAbhiyantranFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_abhiyantran, container, false);

        ArrayList<ProfileOptionsModel> options = new ArrayList<>();
        options.add(
                new ProfileOptionsModel(
                        R.drawable.outline_info_black_48,
                        "About Abhiyantran"
                )
        );
        options.add(
                new ProfileOptionsModel(
                        R.drawable.rupee,
                        "Sponsors"
                )
        );
        options.add(
                new ProfileOptionsModel(
                        R.drawable.outline_people_black_48,
                        "Abhiyantran Team"
                )
        );

        RecyclerView recyclerView = view.findViewById(R.id.about_abhiyantran_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        AboutAbhiyantranAdapter aboutAbhiyantranAdapter = new AboutAbhiyantranAdapter(getContext(), options);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(aboutAbhiyantranAdapter);

        return view;
    }

}
