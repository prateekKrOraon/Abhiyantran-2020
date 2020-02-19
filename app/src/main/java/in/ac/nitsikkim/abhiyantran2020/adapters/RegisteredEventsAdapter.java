package in.ac.nitsikkim.abhiyantran2020.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.models.RegisteredEventsModel;
import in.ac.nitsikkim.abhiyantran2020.view_holders.RegisteredEventsViewHolder;

public class RegisteredEventsAdapter extends RecyclerView.Adapter<RegisteredEventsViewHolder> {

    private ArrayList<RegisteredEventsModel> list;
    private Context context;

    public RegisteredEventsAdapter(Context context, ArrayList<RegisteredEventsModel> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RegisteredEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reg_event,parent,false);
        return new RegisteredEventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegisteredEventsViewHolder holder, int position) {

        holder.title.setText(list.get(position).title);
        holder.date.setText(list.get(position).timestamp.toDate().toString());
        holder.venue.setText(list.get(position).venue);
        holder.description.setText(list.get(position).description);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
