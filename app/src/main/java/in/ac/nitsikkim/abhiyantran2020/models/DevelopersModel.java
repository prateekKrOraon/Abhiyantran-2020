package in.ac.nitsikkim.abhiyantran2020.models;

public class DevelopersModel {

    public int image;
    public String name;
    public String title;
    public String call;
    public String email;
    public String git;
    public String linkedIn;

    public DevelopersModel(int image, String name, String title, String call, String email,String git,String linkedIn){
        this.image = image;
        this.title = title;
        this.name = name;
        this.call = call;
        this.email = email;
        this.git = git;
        this.linkedIn = linkedIn;
    }

}
