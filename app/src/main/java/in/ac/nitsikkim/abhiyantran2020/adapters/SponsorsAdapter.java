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
import in.ac.nitsikkim.abhiyantran2020.models.SponsorsModel;
import in.ac.nitsikkim.abhiyantran2020.view_holders.SponsorsViewHolder;

public class SponsorsAdapter extends RecyclerView.Adapter<SponsorsViewHolder> {

    private ArrayList<SponsorsModel> list;
    private Context context;

    public SponsorsAdapter(Context context,ArrayList<SponsorsModel> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SponsorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sponsors,parent,false);
        return new SponsorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SponsorsViewHolder holder, final int position) {
        holder.name.setText(list.get(position).name);
        holder.imageView.setImageResource(list.get(position).image);
        holder.description.setText(list.get(position).description);
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).link, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
