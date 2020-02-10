package in.ac.nitsikkim.abhiyantran2020.view_holders;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import in.ac.nitsikkim.abhiyantran2020.R;

public class DevelopersViewHolder extends RecyclerView.ViewHolder {

    public CircleImageView imageView;
    public TextView name;
    public TextView title;
    public ImageView call;
    public ImageView email;
    public ImageView git;
    public ImageView linkedIn;

    public DevelopersViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.dev_image);
        name = itemView.findViewById(R.id.dev_name);
        title = itemView.findViewById(R.id.dev_position);
        call = itemView.findViewById(R.id.dev_call);
        email = itemView.findViewById(R.id.dev_mail);
        git = itemView.findViewById(R.id.dev_git);
        linkedIn = itemView.findViewById(R.id.dev_linked_in);
    }
}
