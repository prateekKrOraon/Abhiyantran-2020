package in.ac.nitsikkim.abhiyantran2020.adapters;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.models.GalleryModel;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {



    private Activity context;
    private ArrayList<ArrayList<GalleryModel>> galleryModels;
    private int i=0;

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

    public GalleryAdapter(Activity context,ArrayList<ArrayList<GalleryModel>> galleryModels)
    {
        this.context=context;
        this.galleryModels=galleryModels;
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
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {

        if(position%6==0 || position%6==3)
        {


            ViewGroup.LayoutParams largeImageParams = holder.imageView1.getLayoutParams();
            ViewGroup.LayoutParams smallImagesParams1 = holder.imageView2.getLayoutParams();
            ViewGroup.LayoutParams smallImagesParams2 = holder.imageView3.getLayoutParams();

            //Getting screen size
            DisplayMetrics metrics = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            float width = metrics.widthPixels;

            smallImagesParams1.height = (int) (width/3);
            smallImagesParams2.height = (int) (width/3);
            largeImageParams.height = (int) (width - (width/3));
            holder.imageView1.setLayoutParams(largeImageParams);
            holder.imageView2.setLayoutParams(smallImagesParams1);
            holder.imageView3.setLayoutParams(smallImagesParams2);
        }

    }

    @Override
    public int getItemCount() {
        return galleryModels.size();
    }


}
