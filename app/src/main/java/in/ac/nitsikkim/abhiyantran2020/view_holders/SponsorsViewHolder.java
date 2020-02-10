package in.ac.nitsikkim.abhiyantran2020.view_holders;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;
import in.ac.nitsikkim.abhiyantran2020.R;

public class SponsorsViewHolder extends RecyclerView.ViewHolder {

    public CircleImageView imageView;
    public TextView name;
    public TextView description;
    public LinearLayout link;

    public SponsorsViewHolder(@NonNull View itemView) {
        super(itemView);

        this.imageView = itemView.findViewById(R.id.sponsors_image);
        this.description = itemView.findViewById(R.id.sponsors_des);
        this.name = itemView.findViewById(R.id.sponsors_name);
        this.link = itemView.findViewById(R.id.sponsors_link);

    }
}
