package in.ac.nitsikkim.abhiyantran2020.models;

import com.google.firebase.storage.StorageReference;

public class PostModel {

    public String postId;
    public int profileImage;
    public String profileName;
    public String time;
    public String postText;
    public String postImage;
    public String likeCount;
    public String commentCount;

    public PostModel(String postId,int profileImage, String profileName, String time, String postText, String postImage, String likeCount, String commentCount){
        this.postId = postId;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.postImage = postImage;
        this.postText = postText;
        this.profileImage = profileImage;
        this.profileName = profileName;
        this.time = time;
    }

}
