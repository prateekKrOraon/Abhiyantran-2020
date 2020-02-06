package in.ac.nitsikkim.abhiyantran2020.adapters;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.models.PostModel;
import in.ac.nitsikkim.abhiyantran2020.view_holders.PostViewHolder;

public class HomeAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private Context context;
    ArrayList<PostModel> list;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private final CollectionReference postsRef = firestore.collection("home");

    public HomeAdapter(Context context, ArrayList<PostModel> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_layout,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, final int position) {
        holder.commentCount.setText(list.get(position).commentCount);
        holder.likeCount.setText(list.get(position).likeCount);
        holder.profileName.setText(list.get(position).profileName);
        holder.postText.setText(list.get(position).postText);
        holder.postTime.setText(list.get(position).time);

        final String imageRef = list.get(position).postImage;

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        if(imageRef != ""){
            storageReference.child("posts/"+imageRef).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    System.out.println(uri);
                    Glide.with(context).load(uri).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.postImage);

                }
            });
            holder.postImage.requestLayout();
            holder.postImage.getLayoutParams().height = 550;
        }else{
            holder.postImage.requestLayout();
            holder.postImage.getLayoutParams().height = 0;
        }

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int likes = Integer.parseInt(list.get(position).likeCount);
                likes++;
                holder.likeCount.setText(""+likes);
                list.get(position).likeCount = ""+likes;
                Map<String,String> update = new HashMap<>();
                update.put("likes",""+likes);
                postsRef.document(list.get(position).postId).set(update, SetOptions.merge());
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
