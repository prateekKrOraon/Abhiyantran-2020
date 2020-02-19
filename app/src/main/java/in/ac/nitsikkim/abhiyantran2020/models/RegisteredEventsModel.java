package in.ac.nitsikkim.abhiyantran2020.models;

import com.google.firebase.Timestamp;

public class RegisteredEventsModel {

    public String title;
    public com.google.firebase.Timestamp timestamp;
    public String venue;
    public String description;

    public RegisteredEventsModel(String title, Timestamp timestamp, String venue,String description){
        this.timestamp = timestamp;
        this.title = title;
        this.venue = venue;
        this.description = description;
    }
}
