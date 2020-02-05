package in.ac.nitsikkim.abhiyantran2020.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import in.ac.nitsikkim.abhiyantran2020.R;

public class HomeFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    HomeAdapter mHomeAdapter;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = firestore.collection("home");

    public static String id = "home_fragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        final ArrayList<PostModal> posts = new ArrayList<>();

        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){

                    Timestamp timestamp = (Timestamp) documentSnapshot.get("timestamp");
                    System.out.println(timestamp.toDate());

                    posts.add(
                            new PostModal(
                                    0,
                                    documentSnapshot.getString("profile_name").toString(),
                                    timestamp.toDate().toString().substring(0,16),
                                    documentSnapshot.getString("post_text").toString(),
                                    0,
                                    documentSnapshot.get("likes").toString(),
                                    "10"
                            )
                    );


                }

                for(int i =0; i<3 ;i++){
                    if(i == 1){
                        posts.add(
                                new PostModal(
                                        0,
                                        "Prateek Oraon",
                                        "09:50 PM",
                                        "BB8 from star wars",
                                        R.drawable.bb8_4,
                                        "30",
                                        "10"
                                )
                        );
                    }else{
                        posts.add(
                                new PostModal(
                                        0,
                                        "Prateek Oraon",
                                        "09:50 PM",
                                        "This is a demo text only post... thiahfdhasgd... ahiuodfhass... ahgiuodsfgh",
                                        0,
                                        "30",
                                        "10"
                                )
                        );
                    }
                }
                loadPosts(root,posts);
            }
        });


        return root;
    }

    private void loadPosts(View root,ArrayList<PostModal> posts){
        mRecyclerView = root.findViewById(R.id.post_recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        mHomeAdapter = new HomeAdapter(getContext(),posts);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mHomeAdapter);
    }
}