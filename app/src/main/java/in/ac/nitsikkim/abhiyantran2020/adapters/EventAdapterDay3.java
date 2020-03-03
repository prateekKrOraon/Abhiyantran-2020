package in.ac.nitsikkim.abhiyantran2020.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.models.EventModel;


public class EventAdapterDay3 extends RecyclerView.Adapter<EventAdapterDay3.EventViewHolder> {


    private ArrayList<EventModel> eventItems;
    private Context context;
    public static class EventViewHolder extends RecyclerView.ViewHolder{


        public ImageView imageViewAbhi;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAbhi = itemView.findViewById(R.id.event_abhi_image);


        }

    }

    public EventAdapterDay3(ArrayList<EventModel> arrayList, Context context)
    {
        this.eventItems = arrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item,parent,false);
        return new EventViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final EventViewHolder holder, final int position) {
        Glide.with(context)
                .load(context.getResources().getDrawable(R.drawable.abhi))
                .into(holder.imageViewAbhi);

    }

    @Override
    public int getItemCount() {
        return eventItems.size();
    }




}