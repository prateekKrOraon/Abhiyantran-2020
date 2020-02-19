package in.ac.nitsikkim.abhiyantran2020.utility;

import androidx.annotation.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class User {

    public static User user = null;

    public String phoneNo;
    public String name;
    public String rollNo;
    public Map likedPosts;
    public Map regEvents;
    private static CountDownLatch done = new CountDownLatch(1);

    public User(String name,String phoneNo,String rollNo,Map likedPosts, Map regEvents){
        this.likedPosts = likedPosts;
        this.name = name;
        this.phoneNo = phoneNo;
        this.regEvents = regEvents;
        this.rollNo = rollNo;
    }

    public static User getInstance(){
        if(user == null){
            throw new NullPointerException("null user");
        }
        return user;

    }
}
