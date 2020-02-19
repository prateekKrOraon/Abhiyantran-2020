package in.ac.nitsikkim.abhiyantran2020.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.adapters.HomeAdapter;
import in.ac.nitsikkim.abhiyantran2020.models.PostModel;
import in.ac.nitsikkim.abhiyantran2020.utility.User;


public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private HomeAdapter mHomeAdapter;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private Query collectionReference = firestore.collection("home").orderBy("timestamp", Query.Direction.DESCENDING);
    private int postsLength = 0;
    private boolean newPosts = false;
    public static String id = "home_fragment";
    private boolean down;

    @Override
    public void onDestroyView() {
        postsLength = 0;
        super.onDestroyView();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, final Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = root.findViewById(R.id.post_recycler_view);
        final ArrayList<PostModel> posts = new ArrayList<>();
        final RelativeLayout newPostButton = root.findViewById(R.id.new_post_button);
        newPostButton.setVisibility(View.INVISIBLE);
        loadPosts(root,posts);

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                try{

                    posts.clear();

                    assert queryDocumentSnapshots != null;
                    for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){


                        Timestamp timestamp = (Timestamp) documentSnapshot.get("timestamp");
                        assert timestamp != null;
                        posts.add(
                                new PostModel(
                                        documentSnapshot.getId(),
                                        0,
                                        documentSnapshot.getString("profile_name"),
                                        timestamp.toDate().toString().substring(0,16),
                                        documentSnapshot.getString("post_text"),
                                        documentSnapshot.getString("post_image"),
                                        documentSnapshot.get("likes").toString(),
                                        "10"
                                )
                        );
                    }

                    if(postsLength == 0 && !newPosts){
                        mHomeAdapter.notifyDataSetChanged();
                    }else if(posts.size() > postsLength){
                        newPosts = true;
                        newPostButton.setVisibility(View.VISIBLE);
                    }else if(postsLength != posts.size()){
                        mHomeAdapter.notifyDataSetChanged();
                    }
                    postsLength = posts.size();
                }catch(NullPointerException ex){
                    Toast.makeText(getContext(),"Null Pointer Exception",Toast.LENGTH_SHORT).show();
                }

            }
        });

        newPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomeAdapter.notifyDataSetChanged();
                newPosts = false;
                newPostButton.setVisibility(View.INVISIBLE);
                newPostButton.animate().translationYBy(300);
            }
        });

        final Animation navAnimationDown = AnimationUtils.loadAnimation(getContext(),R.anim.translate_bottom_nav_down);
        final Animation navAnimationUp = AnimationUtils.loadAnimation(getContext(),R.anim.translate_bottom_nav_up);
        final CoordinatorLayout bottomNavBar = getActivity().findViewById(R.id.bottom_nav_bar);
        down = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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



    private void loadPosts(View root,ArrayList<PostModel> posts){

        mLayoutManager = new LinearLayoutManager(getContext());
        mHomeAdapter = new HomeAdapter(getContext(),posts);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mHomeAdapter);
    }
}