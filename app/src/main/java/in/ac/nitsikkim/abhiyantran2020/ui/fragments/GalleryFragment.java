package in.ac.nitsikkim.abhiyantran2020.ui.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.adapters.GalleryAdapter;
import in.ac.nitsikkim.abhiyantran2020.models.GalleryModel;

import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.gallery;
import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.image;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    ArrayList<ArrayList<GalleryModel>> galleryModels;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    GalleryAdapter galleryAdapter;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private Query collectionRef;

    private boolean down;
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

        final ArrayList<GalleryModel> images = new ArrayList<>();
        collectionRef = firestore.collection(gallery);
        collectionRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                images.clear();
                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    images.add(
                            new GalleryModel(
                                    documentSnapshot.getString(image),
                                    documentSnapshot.getString(image)
                            )
                    );
                    galleryAdapter.notifyDataSetChanged();
                }
            }
        });

        setRecyclerView(root,images);

        final Animation navAnimationDown = AnimationUtils.loadAnimation(getContext(),R.anim.translate_bottom_nav_down);
        final Animation navAnimationUp = AnimationUtils.loadAnimation(getContext(),R.anim.translate_bottom_nav_up);
        final CoordinatorLayout bottomNavBar = getActivity().findViewById(R.id.bottom_nav_bar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(dy < -10){
                        if(down){
                            bottomNavBar.setVisibility(View.VISIBLE);
                            bottomNavBar.startAnimation(navAnimationUp);
                            down = false;
                        }
                    }else if(dy > 15){
                        if(!down){
                            down = true;
                            bottomNavBar.startAnimation(navAnimationDown);
                            bottomNavBar.setVisibility(View.INVISIBLE);
                        }
                    }

                }
            });
        }


        return root;
    }


    public void setRecyclerView(View root,ArrayList<GalleryModel> images)
    {


        recyclerView = root.findViewById(R.id.gallery_recycler_view);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        Activity activity = getActivity();
        galleryAdapter = new GalleryAdapter(context,images,activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(galleryAdapter);


    }


}
