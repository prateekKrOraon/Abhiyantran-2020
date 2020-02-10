package in.ac.nitsikkim.abhiyantran2020.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.models.ProfileOptionsModel;
import in.ac.nitsikkim.abhiyantran2020.ui.activities.RegisteredEventsActivity;
import in.ac.nitsikkim.abhiyantran2020.ui.activities.SponsorsActivity;
import in.ac.nitsikkim.abhiyantran2020.view_holders.ProfileOptionsViewHolder;

public class AboutAbhiyantranAdapter extends RecyclerView.Adapter<ProfileOptionsViewHolder> {

    private Context context;
    private ArrayList<ProfileOptionsModel> list;

    public AboutAbhiyantranAdapter(Context context, ArrayList<ProfileOptionsModel> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProfileOptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_options,parent,false);
        return new ProfileOptionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileOptionsViewHolder holder, final int position) {
        holder.title.setText(list.get(position).title);
        holder.icon.setImageResource(list.get(position).icon);

        holder.listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).title, Toast.LENGTH_SHORT).show();
                if (list.get(position).title.equals("Registered Events")){
                    context.startActivity(new Intent(context,RegisteredEventsActivity.class));
                }

                if (list.get(position).title.equals("Sponsors")){
                    context.startActivity(new Intent(context, SponsorsActivity.class));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
