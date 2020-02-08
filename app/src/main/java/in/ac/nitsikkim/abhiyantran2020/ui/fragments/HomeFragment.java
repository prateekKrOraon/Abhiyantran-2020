package in.ac.nitsikkim.abhiyantran2020.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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


public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private HomeAdapter mHomeAdapter;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private Query collectionReference = firestore.collection("home").orderBy("timestamp", Query.Direction.DESCENDING);
    int postsLength = 0;
    boolean newPosts = false;
    public static String id = "home_fragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = root.findViewById(R.id.post_recycler_view);
        final ArrayList<PostModel> posts = new ArrayList<>();
        final RelativeLayout newPostButton = root.findViewById(R.id.new_post_button);
        loadPosts(root,posts);

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                try{

                    posts.clear();
                    assert queryDocumentSnapshots != null;
                    for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){

                        System.out.println();
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
                    }else{
                        mHomeAdapter.notifyDataSetChanged();
                    }
                    postsLength = posts.size();

                }catch(NullPointerException ex){
                    Toast.makeText(getContext(),"Null Pointer Exception",Toast.LENGTH_SHORT).show();
                }

            }
        });

        newPostButton.setVisibility(View.INVISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if(oldScrollY > 5 && newPosts){
                        newPostButton.setVisibility(View.VISIBLE);
                    }else if(oldScrollY < 0){
                        newPostButton.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

        newPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomeAdapter.notifyDataSetChanged();
                newPosts = false;
                newPostButton.setVisibility(View.INVISIBLE);
            }
        });
        return root;
    }

    private void activateNewPostButton() {

    }

    private void loadPosts(View root,ArrayList<PostModel> posts){

        mLayoutManager = new LinearLayoutManager(getContext());
        mHomeAdapter = new HomeAdapter(getContext(),posts);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mHomeAdapter);
    }
}