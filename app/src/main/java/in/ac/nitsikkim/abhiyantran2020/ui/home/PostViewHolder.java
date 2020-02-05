package in.ac.nitsikkim.abhiyantran2020.ui.home;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import in.ac.nitsikkim.abhiyantran2020.R;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public CircleImageView profileImage;
    public TextView profileName;
    public TextView postTime;
    public TextView postText;
    public ImageView postImage;
    public ImageButton likeButton;
    public ImageButton commentButton;
    public TextView likeCount;
    public TextView commentCount;


    public PostViewHolder(@NonNull View view) {
        super(view);
        profileImage = view.findViewById(R.id.post_profile_image);
        profileName = view.findViewById(R.id.post_profile_name);
        postTime = view.findViewById(R.id.post_time);
        postText = view.findViewById(R.id.post_textView);
        postImage = view.findViewById(R.id.post_image);
        likeButton = view.findViewById(R.id.post_like);
        commentButton = view.findViewById(R.id.post_comment);
        likeCount = view.findViewById(R.id.like_count);
        commentCount = view.findViewById(R.id.comment_count);
    }
}
