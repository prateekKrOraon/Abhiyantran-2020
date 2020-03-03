package in.ac.nitsikkim.abhiyantran2020.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.models.TalksModel;

public class TalksSliderAdapter extends
        SliderViewAdapter<TalksSliderAdapter.SliderAdapterVH> {

    private Context context;
    private ArrayList<TalksModel> talksModels;

    public TalksSliderAdapter(Context context,ArrayList<TalksModel> talksModels) {
        this.context = context;
        this.talksModels=talksModels;
    }

    public void renewItems(ArrayList<TalksModel> talksModels) {
        this.talksModels=talksModels;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.talksModels.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(TalksModel sliderItem) {
        this.talksModels.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.talks_slider_layout, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {


        Glide.with(context)
                .load(context.getResources().getDrawable(talksModels.get(position).getImage()))
                .into(viewHolder.album);
        Glide.with(context)
                .load(context.getResources().getDrawable(R.drawable.abhi))
                .into(viewHolder.abhiImage);

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return talksModels.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView album;
        ImageView abhiImage;


        public SliderAdapterVH(View itemView) {
            super(itemView);
            this.itemView = itemView;
            album = itemView.findViewById(R.id.talks_album);
            abhiImage = itemView.findViewById(R.id.talks_abhi_image);
        }
    }

}