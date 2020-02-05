package in.ac.nitsikkim.abhiyantran2020.ui.home;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;

public class HomeAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private Context context;
    ArrayList<PostModal> list;

    public HomeAdapter(Context context, ArrayList<PostModal> list){
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
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.commentCount.setText(list.get(position).commentCount);
        holder.likeCount.setText(list.get(position).likeCount);
        holder.profileName.setText(list.get(position).profileName);
        holder.postText.setText(list.get(position).postText);
        holder.postTime.setText(list.get(position).time);

        int image = list.get(position).postImage;
        if(image != 0){
            holder.postImage.setImageResource(image);
            holder.postImage.requestLayout();
            holder.postImage.getLayoutParams().height = 550;
        }else{
            holder.postImage.requestLayout();
            holder.postImage.getLayoutParams().height = 0;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
