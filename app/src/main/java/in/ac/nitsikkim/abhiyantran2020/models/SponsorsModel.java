package in.ac.nitsikkim.abhiyantran2020.models;

public class SponsorsModel {

    public int image;
    public String name;
    public String description;
    public String link;

    public SponsorsModel(int image, String name, String description, String link){
        this.description = description;
        this.name = name;
        this.image = image;
        this.link = link;
    }

}
