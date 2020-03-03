package in.ac.nitsikkim.abhiyantran2020.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.ac.nitsikkim.abhiyantran2020.R;
import in.ac.nitsikkim.abhiyantran2020.adapters.EventAdapterDay1;
import in.ac.nitsikkim.abhiyantran2020.adapters.EventAdapterDay2;
import in.ac.nitsikkim.abhiyantran2020.adapters.EventAdapterDay3;
import in.ac.nitsikkim.abhiyantran2020.adapters.TalksSliderAdapter;
import in.ac.nitsikkim.abhiyantran2020.adapters.WorkshopAdapter;
import in.ac.nitsikkim.abhiyantran2020.models.EventModel;
import in.ac.nitsikkim.abhiyantran2020.models.EventViewModel;
import in.ac.nitsikkim.abhiyantran2020.models.TalksModel;
import in.ac.nitsikkim.abhiyantran2020.models.WorkshopModel;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class EventFragment extends Fragment {

    private EventViewModel eventViewModel;

    private TextView eventLODay1,eventLODay2,eventLODay3;
    private TextView eventLODay1Num,eventLODay2Num,eventLODay3Num;
    private CircularSeekBar eventLODay1seek,eventLODay2seek,eventLODay3seek;
    private final int ETDay1=24,ETDay2=20,ETDay3=23; //Total no of event
    private final int ELDay1=5,ELDay2=10,ELDay3=20; //no of events left
    private boolean down=false;

    private RecyclerView recyclerViewDay1,recyclerViewDay2,recyclerViewDay3;
    private LinearLayoutManager linearLayoutManagerDay1,linearLayoutManagerDay2,linearLayoutManagerDay3;
    EventAdapterDay1 eventAdapterDay1;
    EventAdapterDay2 eventAdapterDay2;
    EventAdapterDay3 eventAdapterDay3;
    private ArrayList<EventModel> eventModelsDay1,eventModelsDay2,eventModelsDay3;

    private ArrayList<TalksModel> talksModels;
    private SliderView talksSliderView;

    private ArrayList<WorkshopModel> workshopModels;
    private RecyclerView recyclerViewWorkshop;
    private LinearLayoutManager linearLayoutManagerWorkshop;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        View root = inflater.inflate(R.layout.fragment_event, container, false);


        //Fetch data here


        //set Talks Slider Adapter
        setTalksAdapter(root);

        //set Events left out layout
        setEventsLeftOut(root);

        //set Day1 adapter
        setDay1Adapter(root);

        //set Day2 adapter
        setDay2Adapter(root);

        //set Day3 adapter
        setDay3Adapter(root);

        //set Workshop adapter
        setWorkshopAdapter(root);

        //set exhibition layout
        ImageView imageViewAbhi = root.findViewById(R.id.tech_exbtn_abhi_image);
        Glide.with(root)
                .load(root.getResources().getDrawable(R.drawable.abhi))
                .into(imageViewAbhi);


        final Animation navAnimationDown = AnimationUtils.loadAnimation(getContext(),R.anim.translate_bottom_nav_down);
        final Animation navAnimationUp = AnimationUtils.loadAnimation(getContext(),R.anim.translate_bottom_nav_up);
        final CoordinatorLayout bottomNavBar = getActivity().findViewById(R.id.bottom_nav_bar);
        final NestedScrollView nestedScrollView = root.findViewById(R.id.nested_scroll_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    if(scrollY-oldScrollY < 10){
                        if(down){
                            bottomNavBar.setVisibility(View.VISIBLE);
                            bottomNavBar.startAnimation(navAnimationUp);
                            down = false;
                        }
                    }else if(scrollY-oldScrollY > 15){
                        if(!down){
                            down = true;
                            bottomNavBar.startAnimation(navAnimationDown);
                            bottomNavBar.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            });
        }



        return root;
    }

    private void setEventsLeftOut(View root)
    {
        eventLODay1 = root.findViewById(R.id.events_l_o_day1).findViewById(R.id.events_l_o_day);
        eventLODay2 = root.findViewById(R.id.events_l_o_day2).findViewById(R.id.events_l_o_day);
        eventLODay3 = root.findViewById(R.id.events_l_o_day3).findViewById(R.id.events_l_o_day);

        eventLODay1Num = root.findViewById(R.id.events_l_o_day1).findViewById(R.id.events_l_o_no);
        eventLODay2Num = root.findViewById(R.id.events_l_o_day2).findViewById(R.id.events_l_o_no);
        eventLODay3Num = root.findViewById(R.id.events_l_o_day3).findViewById(R.id.events_l_o_no);

        eventLODay1seek = root.findViewById(R.id.events_l_o_day1).findViewById(R.id.events_l_o_seekbar);
        eventLODay2seek = root.findViewById(R.id.events_l_o_day2).findViewById(R.id.events_l_o_seekbar);
        eventLODay3seek = root.findViewById(R.id.events_l_o_day3).findViewById(R.id.events_l_o_seekbar);

        eventLODay1.setText("Day 1");
        eventLODay2.setText("Day 2");
        eventLODay3.setText("Day 3");

        eventLODay1Num.setText(""+ELDay1);
        eventLODay2Num.setText(""+ELDay2);
        eventLODay3Num.setText(""+ELDay3);

        eventLODay1seek.setMax(0);
        eventLODay1seek.setMax(ETDay1);
        eventLODay1seek.setProgress(ETDay1-ELDay1);

        eventLODay2seek.setMax(0);
        eventLODay2seek.setMax(ETDay2);
        eventLODay2seek.setProgress(ETDay2-ELDay2);

        eventLODay3seek.setMax(0);
        eventLODay3seek.setMax(ETDay3);
        eventLODay3seek.setProgress(ETDay3-ELDay3);


    }

    private void setDay1Adapter(View root)
    {
        eventModelsDay1 = new ArrayList<>();
        eventModelsDay1.add(new EventModel());
        eventModelsDay1.add(new EventModel());
        eventModelsDay1.add(new EventModel());
        eventModelsDay1.add(new EventModel());
        eventModelsDay1.add(new EventModel());
        recyclerViewDay1 = root.findViewById(R.id.recycler_view_day1);
        recyclerViewDay1.hasFixedSize();
        linearLayoutManagerDay1 = new LinearLayoutManager(root.getContext(),RecyclerView.HORIZONTAL,false);
        eventAdapterDay1 = new EventAdapterDay1(eventModelsDay1,root.getContext());
        recyclerViewDay1.setLayoutManager(linearLayoutManagerDay1);
        recyclerViewDay1.setAdapter(eventAdapterDay1);


    }

    private void setDay2Adapter(View root)
    {
        eventModelsDay2 = new ArrayList<>();
        eventModelsDay2.add(new EventModel());
        eventModelsDay2.add(new EventModel());
        eventModelsDay2.add(new EventModel());
        eventModelsDay2.add(new EventModel());
        recyclerViewDay2 = root.findViewById(R.id.recycler_view_day2);
        recyclerViewDay2.hasFixedSize();
        linearLayoutManagerDay2 = new LinearLayoutManager(root.getContext(),RecyclerView.HORIZONTAL,false);
        eventAdapterDay2 = new EventAdapterDay2(eventModelsDay2,root.getContext());
        recyclerViewDay2.setLayoutManager(linearLayoutManagerDay2);
        recyclerViewDay2.setAdapter(eventAdapterDay2);

    }

    private void setDay3Adapter(View root)
    {
        eventModelsDay3 = new ArrayList<>();
        eventModelsDay3.add(new EventModel());
        eventModelsDay3.add(new EventModel());
        recyclerViewDay3 = root.findViewById(R.id.recycler_view_day3);
        recyclerViewDay3.hasFixedSize();
        linearLayoutManagerDay3 = new LinearLayoutManager(root.getContext(),RecyclerView.HORIZONTAL,false);
        eventAdapterDay3 = new EventAdapterDay3(eventModelsDay3,root.getContext());
        recyclerViewDay3.setLayoutManager(linearLayoutManagerDay3);
        recyclerViewDay3.setAdapter(eventAdapterDay3);

    }
    private void setTalksAdapter(View root)
    {
        talksModels = new ArrayList<>();
        talksModels.add(new TalksModel(R.drawable.app_logo));
        talksModels.add(new TalksModel(R.drawable.app_logo));

        talksModels.add(new TalksModel(R.drawable.app_logo));

        talksSliderView = root.findViewById(R.id.imageSlider_talks);
        talksSliderView.setSliderAdapter(new TalksSliderAdapter(root.getContext(),talksModels));
        talksSliderView.startAutoCycle();
        talksSliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        talksSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    private void setWorkshopAdapter(View root)
    {

        workshopModels=new ArrayList<>();
        workshopModels.add(new WorkshopModel());
        workshopModels.add(new WorkshopModel());
        workshopModels.add(new WorkshopModel());

        recyclerViewWorkshop = root.findViewById(R.id.recycler_view_work_shop);
        recyclerViewWorkshop.setHasFixedSize(true);
        linearLayoutManagerWorkshop = new LinearLayoutManager(root.getContext(),RecyclerView.HORIZONTAL,false);
        recyclerViewWorkshop.setLayoutManager(linearLayoutManagerWorkshop);
        recyclerViewWorkshop.setAdapter(new WorkshopAdapter(workshopModels,root.getContext()));

    }
}
