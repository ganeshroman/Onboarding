package com.example.onboarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OnbaordingAdapter extends RecyclerView.Adapter<OnbaordingAdapter.MyViewHolder>{


    private ArrayList<OnboardingItem> list=new ArrayList<>();


    public OnbaordingAdapter(ArrayList<OnboardingItem> list){
        this.list=list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_onboarding,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setOnboardingItem(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitle, txtDescription;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle=(TextView) itemView.findViewById(R.id.txtTitle);
            txtDescription=(TextView) itemView.findViewById(R.id.txtDescription);
            img=(ImageView) itemView.findViewById(R.id.imgOnboarding);

        }

        public void setOnboardingItem(OnboardingItem onboardingItem){
            txtTitle.setText(""+onboardingItem.getTitle());
            txtDescription.setText(""+onboardingItem.getDescription());
            img.setImageResource(onboardingItem.getImage());

        }



    }
}
