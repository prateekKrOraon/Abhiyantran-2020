package in.ac.nitsikkim.abhiyantran2020.view_holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.ac.nitsikkim.abhiyantran2020.R;

public class ProfileOptionsViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon;
    public TextView title;
    public LinearLayout listView;

    public ProfileOptionsViewHolder(@NonNull View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.profile_options_icon);
        title = itemView.findViewById(R.id.profile_options_title);
        listView = itemView.findViewById(R.id.profile_option);
    }
}
