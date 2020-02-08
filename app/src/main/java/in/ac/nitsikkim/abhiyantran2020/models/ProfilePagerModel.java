package in.ac.nitsikkim.abhiyantran2020.models;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ProfilePagerModel {

    public Fragment fragment;
    public String title;

    public ProfilePagerModel(Fragment fragment, String title){
        this.fragment = fragment;
        this.title = title;
    }

}
