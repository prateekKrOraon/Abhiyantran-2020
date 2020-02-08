package in.ac.nitsikkim.abhiyantran2020.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.models.ProfilePagerModel;

public class ProfilePagerAdapter extends FragmentPagerAdapter {

    private ArrayList<ProfilePagerModel> profilePages;

    public ProfilePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return profilePages.get(position).fragment;
    }

    @Override
    public int getCount() {
        return profilePages.size();
    }

    public void addProfilePages(ArrayList<ProfilePagerModel> profilePages){
        this.profilePages = profilePages;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return profilePages.get(position).title;
    }
}
