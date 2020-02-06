package in.ac.nitsikkim.abhiyantran2020.ui.fragments;

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

    public static String id = "home_fragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        final ArrayList<PostModel> posts = new ArrayList<>();
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

                        System.out.println(documentSnapshot.getString("post_image"));
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

                        mHomeAdapter.notifyDataSetChanged();

                    }

                }catch(NullPointerException ex){
                    Toast.makeText(getContext(),"Null Pointer Exception",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return root;
    }

    private void loadPosts(View root,ArrayList<PostModel> posts){
        mRecyclerView = root.findViewById(R.id.post_recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        mHomeAdapter = new HomeAdapter(getContext(),posts);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mHomeAdapter);
    }
}