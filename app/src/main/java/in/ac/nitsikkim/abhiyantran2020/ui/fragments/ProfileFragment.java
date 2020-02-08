package in.ac.nitsikkim.abhiyantran2020.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.adapters.ProfilePagerAdapter;
import in.ac.nitsikkim.abhiyantran2020.models.ProfilePagerModel;

public class ProfileFragment extends Fragment {

    private ArrayList<ProfilePagerModel> profilePages;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        profilePages = new ArrayList<>();
        TabLayout tabLayout = root.findViewById(R.id.profile_tab_layout);
        ViewPager viewPager = root.findViewById(R.id.profile_view_pager);
        AboutAbhiyantranFragment abhiyantranFragment = new AboutAbhiyantranFragment();
        AboutAppFragment aboutAppFragment = new AboutAppFragment();
        profilePages.add(
                new ProfilePagerModel(
                        abhiyantranFragment,
                        "Abhiyantran"
                )
        );
        profilePages.add(
                new ProfilePagerModel(
                        aboutAppFragment,
                        "Application"
                )
        );

        setViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        return root;
    }

    private void setViewPager(ViewPager viewPager){

        ProfilePagerAdapter profilePagerAdapter = new ProfilePagerAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        profilePagerAdapter.addProfilePages(profilePages);
        viewPager.setAdapter(profilePagerAdapter);
    }

}