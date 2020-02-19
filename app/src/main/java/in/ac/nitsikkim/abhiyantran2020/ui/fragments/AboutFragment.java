package in.ac.nitsikkim.abhiyantran2020.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.adapters.ProfilePagerAdapter;
import in.ac.nitsikkim.abhiyantran2020.models.ProfilePagerModel;
import in.ac.nitsikkim.abhiyantran2020.utility.User;

public class AboutFragment extends Fragment {

    private ArrayList<ProfilePagerModel> profilePages;
    private TextView name;
    private TextView phoneNo;
    private User user = User.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        name = root.findViewById(R.id.profile_name);
        phoneNo = root.findViewById(R.id.profile_phone_no);
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
        setUserDetails();
        setViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        return root;
    }

    private void setUserDetails() {
        name.setText(user.name);
        phoneNo.setText("+91-"+user.phoneNo);
    }

    private void setViewPager(ViewPager viewPager){

        ProfilePagerAdapter profilePagerAdapter = new ProfilePagerAdapter(
                getChildFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );
        profilePagerAdapter.addProfilePages(profilePages);
        viewPager.setAdapter(profilePagerAdapter);
    }

}