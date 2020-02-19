package in.ac.nitsikkim.abhiyantran2020.adapters;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.security.AllPermission;
import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.models.GalleryModel;

import static in.ac.nitsikkim.abhiyantran2020.utility.Constants.gallery;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {



    private Context context;
    private Activity activity;
    private ArrayList<GalleryModel> images;
    private int i=0;
    private int j=0;

    StorageReference storage = FirebaseStorage.getInstance().getReference();

    public class GalleryViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1,imageView2,imageView3;
        LinearLayout twoSmall;

        public GalleryViewHolder(@NonNull View view) {
            super(view);
            imageView1 = view.findViewById(R.id.image1);
            //twoSmall = view.findViewById(R.id.two_small_layout);
            imageView2 = view.findViewById(R.id.image2);
            imageView3 = view.findViewById(R.id.image3);


        }
    }

    public GalleryAdapter(Context context, ArrayList<GalleryModel> images, Activity activity)
    {
        this.context=context;
        this.images = images;
        this.activity = activity;
    }


    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(i%6==0)
        {

            view = LayoutInflater.from(context).inflate(R.layout.gallery_large_one_small_two_item,parent,false);
        }
        else if(i%6==3)
        {
            view = LayoutInflater.from(context).inflate(R.layout.gallery_two_small_one_large_item,parent,false);
        }
        else
        {

            view = LayoutInflater.from(context).inflate(R.layout.gallery_small_three_item_1,parent,false);
        }

       i++; //incrementing for next layout item


        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GalleryViewHolder holder, final int position) {

        ViewGroup.LayoutParams largeImageParams = holder.imageView1.getLayoutParams();
        ViewGroup.LayoutParams smallImagesParams1 = holder.imageView2.getLayoutParams();
        ViewGroup.LayoutParams smallImagesParams2 = holder.imageView3.getLayoutParams();

        //Getting screen size
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        float width = metrics.widthPixels;
        smallImagesParams1.height = (int) (width/3);
        smallImagesParams2.height = (int) (width/3);

        if(position%6==0 || position%6==3) {

            largeImageParams.height = (int) (width - (width/3));
        }else{

            largeImageParams.height = (int) ((width/3));
        }

        holder.imageView1.setLayoutParams(largeImageParams);
        holder.imageView2.setLayoutParams(smallImagesParams1);
        holder.imageView3.setLayoutParams(smallImagesParams2);

        StorageReference image_1 = storage.child(gallery+"/"+images.get(j).getGalleryImage());
        StorageReference image_2 = storage.child(gallery+"/"+images.get(j+1).getGalleryImage());
        StorageReference image_3 = storage.child(gallery+"/"+images.get(j+2).getGalleryImage());

        image_1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                    Glide.with(context).load(uri).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView1);
            }
        });

        image_2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Glide.with(context).load(uri).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView2);
            }
        });

        image_3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView3);
            }
        });

        j+=3;

    }

    @Override
    public int getItemCount() {
        return images.size()/3;
    }


}
