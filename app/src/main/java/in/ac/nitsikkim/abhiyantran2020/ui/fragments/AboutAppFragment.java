package in.ac.nitsikkim.abhiyantran2020.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.adapters.AboutApplicationAdapter;
import in.ac.nitsikkim.abhiyantran2020.models.ProfileOptionsModel;

public class AboutAppFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_app, container, false);

        ArrayList<ProfileOptionsModel> options = new ArrayList<>();

        options.add(
                new ProfileOptionsModel(
                        R.drawable.outline_build_black_48,
                        "Developers"
                )
        );

        options.add(
                new ProfileOptionsModel(
                        R.drawable.baseline_share_black_48,
                        "Share Application"
                )
        );

        options.add(
                new ProfileOptionsModel(
                        R.drawable.outline_bug_report_black_48,
                        "Report Bug"
                )
        );

        options.add(
                new ProfileOptionsModel(
                        R.drawable.outline_star_border_black_48,
                        "Rate Application"
                )
        );

        options.add(
                new ProfileOptionsModel(
                        R.drawable.outline_info_black_48,
                        "About Application"
                )
        );

        RecyclerView recyclerView = view.findViewById(R.id.about_app_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        AboutApplicationAdapter applicationAdapter = new AboutApplicationAdapter(getContext(),options);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(applicationAdapter);

        return view;
    }

}
