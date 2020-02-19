package in.ac.nitsikkim.abhiyantran2020.view_holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import in.ac.nitsikkim.abhiyantran2020.R;

public class RegisteredEventsViewHolder extends RecyclerView.ViewHolder {

    public TextView date;
    public TextView title;
    public TextView venue;
    public TextView description;

    public RegisteredEventsViewHolder(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.reg_event_date);
        title = itemView.findViewById(R.id.reg_event_title);
        venue = itemView.findViewById(R.id.reg_event_venue);
        description = itemView.findViewById(R.id.reg_event_description);
    }
}
