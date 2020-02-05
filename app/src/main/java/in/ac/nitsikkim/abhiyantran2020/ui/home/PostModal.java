package in.ac.nitsikkim.abhiyantran2020.ui.home;

public class PostModal {

    public int profileImage;
    public String profileName;
    public String time;
    public String postText;
    public int postImage;
    public String likeCount;
    public String commentCount;

    public PostModal(int profileImage,String profileName,String time,String postText,int postImage, String likeCount,String commentCount){
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.postImage = postImage;
        this.postText = postText;
        this.profileImage = profileImage;
        this.profileName = profileName;
        this.time = time;
    }

}
