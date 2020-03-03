package in.ac.nitsikkim.abhiyantran2020.models;

public class WorkshopModel {

    public String topic,des,date;
    public int album;

    public WorkshopModel()
    {

    }

    public WorkshopModel(String topic, String des, String date, int album) {
        this.topic = topic;
        this.des = des;
        this.date = date;
        this.album = album;
    }

    public String getTopic() {
        return topic;
    }

    public String getDes() {
        return des;
    }

    public String getDate() {
        return date;
    }

    public int getAlbum() {
        return album;
    }
}
