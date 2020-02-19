package in.ac.nitsikkim.abhiyantran2020.adapters;
import android.content.Context;
import android.content.res.ColorStateList;
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
import in.ac.nitsikkim.abhiyantran2020.utility.User;
import in.ac.nitsikkim.abhiyantran2020.view_holders.PostViewHolder;

public class HomeAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private Context context;
    ArrayList<PostModel> list;
    User user;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private final CollectionReference postsRef = firestore.collection("home");

    public HomeAdapter(Context context, ArrayList<PostModel> list){
        this.context = context;
        this.list = list;
        user = User.getInstance();
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
        if(!imageRef.equals("")){
            storageReference.child("posts/"+imageRef).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(context).load(uri).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.postImage);

                }
            });
            holder.postImage.requestLayout();
            holder.postImage.getLayoutParams().height = 600;
        }else{
            holder.postImage.requestLayout();
            holder.postImage.getLayoutParams().height = 0;
        }

        if (user.likedPosts.containsKey(list.get(position).postId)){
            holder.likeButton.setImageResource(R.drawable.baseline_thumb_up_black_48);
        }else{
            holder.likeButton.setImageResource(R.drawable.outline_thumb_up_black_48);
        }

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int likes = 0;
                if (user.likedPosts.containsKey(list.get(position).postId)){
                    likes = Integer.parseInt(list.get(position).likeCount);
                    likes--;
                    user.likedPosts.remove(list.get(position).postId);
                    Map<String, Map> update = new HashMap<>();
                    update.put("liked_posts",user.likedPosts);

                    holder.likeButton.setImageResource(R.drawable.outline_thumb_up_black_48);
                }else{
                    likes = Integer.parseInt(list.get(position).likeCount);
                    likes++;
                    user.likedPosts.put(list.get(position).postId,true);
                    Map<String, Map> update = new HashMap<>();
                    update.put("liked_posts",user.likedPosts);
                    //firestore.collection("users").document(user.phoneNo).set(User.user);
                    holder.likeButton.setImageResource(R.drawable.baseline_thumb_up_black_48);
                }
                holder.likeCount.setText(""+likes);
                list.get(position).likeCount = ""+likes;
                Map<String,String> update = new HashMap<>();
                update.put("likes",""+likes);
                firestore.collection("users").document(user.phoneNo).set(user);
                postsRef.document(list.get(position).postId).set(update, SetOptions.merge());
            }
        });

        holder.post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
