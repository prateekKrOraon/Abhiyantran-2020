package in.ac.nitsikkim.abhiyantran2020.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.models.DevelopersModel;
import in.ac.nitsikkim.abhiyantran2020.view_holders.DevelopersViewHolder;

public class DevelopersAdapter extends RecyclerView.Adapter<DevelopersViewHolder> {

    private ArrayList<DevelopersModel> list;
    private Context context;

    public DevelopersAdapter(Context context, ArrayList<DevelopersModel> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DevelopersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.developers,parent,false);
        return new DevelopersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DevelopersViewHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.outline_person_black_48);
        holder.name.setText(list.get(position).name);
        holder.title.setText(list.get(position).title);
        setListeners(holder,position);
    }

    private void setListeners(DevelopersViewHolder holder, final int position) {

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,list.get(position).call,Toast.LENGTH_SHORT).show();
            }
        });

        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,list.get(position).email,Toast.LENGTH_SHORT).show();
            }
        });

        holder.git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,list.get(position).git,Toast.LENGTH_SHORT).show();
            }
        });

        holder.linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,list.get(position).linkedIn,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
