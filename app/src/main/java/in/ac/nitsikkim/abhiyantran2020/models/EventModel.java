package in.ac.nitsikkim.abhiyantran2020.models;

import android.net.Uri;

public class EventModel {

    public int id;
    public String name,time,venue;
    public Uri album;
    public String description,coordinatorName1,coordinatorPhNo1,coordinatorName2,coordinatorPhNo2;
    public boolean isOver;

    public EventModel()
    {

    }
    public EventModel(int id, String name, String time, String venue, Uri album, String description, String coordinatorName1, String coordinatorPhNo1, String coordinatorName2, String coordinatorPhNo2, boolean isOver) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.venue = venue;
        this.album = album;
        this.description = description;
        this.coordinatorName1 = coordinatorName1;
        this.coordinatorPhNo1 = coordinatorPhNo1;
        this.coordinatorName2 = coordinatorName2;
        this.coordinatorPhNo2 = coordinatorPhNo2;
        this.isOver = isOver;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public Uri getAlbum() {
        return album;
    }

    public String getDescription() {
        return description;
    }

    public String getCoordinatorName1() {
        return coordinatorName1;
    }

    public String getCoordinatorPhNo1() {
        return coordinatorPhNo1;
    }

    public String getCoordinatorName2() {
        return coordinatorName2;
    }

    public String getCoordinatorPhNo2() {
        return coordinatorPhNo2;
    }

    public boolean isOver() {
        return isOver;
    }
}
