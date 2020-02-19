package in.ac.nitsikkim.abhiyantran2020.ui.fragments;


import android.app.Activity;
import android.content.Context;
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
import in.ac.nitsikkim.abhiyantran2020.adapters.GalleryAdapter;
import in.ac.nitsikkim.abhiyantran2020.models.GalleryModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    ArrayList<ArrayList<GalleryModel>> galleryModels;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    GalleryAdapter galleryAdapter;

    public Activity context;

    public GalleryFragment() {
        // Required empty public constructor
    }

    public GalleryFragment(Activity context)
    {
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_gallery, container, false);

        //fetch Data

        //setRecycler View
        setRecyclerView(root);


        return root;
    }


    public void setRecyclerView(View root)
    {


        galleryModels = new ArrayList<>();
        ArrayList<GalleryModel> insideItemAL = new ArrayList<>();
        insideItemAL.add(new GalleryModel("khbsdf","vfdvsf"));
        insideItemAL.add(new GalleryModel("khbsdf","vfdvsf"));
        insideItemAL.add(new GalleryModel("khbsdf","vfdvsf"));

        galleryModels.add(insideItemAL);
        galleryModels.add(insideItemAL);
        galleryModels.add(insideItemAL);
        galleryModels.add(insideItemAL);
        galleryModels.add(insideItemAL);

        recyclerView = root.findViewById(R.id.gallery_recycler_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(root.getContext(),RecyclerView.VERTICAL,false);
        galleryAdapter = new GalleryAdapter(context,galleryModels);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(galleryAdapter);


    }


}
