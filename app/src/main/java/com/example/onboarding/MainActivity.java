package com.example.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private OnbaordingAdapter adapter;
    private ViewPager2 pager;
    private MaterialButton btnNavigate;
    private ArrayList<OnboardingItem> items=new ArrayList<>();
    private LinearLayout layoutIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager=findViewById(R.id.onboardingViewpager);
        layoutIndicator=(LinearLayout) findViewById(R.id.layoutOnboardingIndicators);
        btnNavigate=(MaterialButton) findViewById(R.id.btnOnboardingAction);

        items=getList();
        adapter=new OnbaordingAdapter(items);

        pager.setAdapter(adapter);

        setupIndicator();
        setupCurrentIndicator(0);

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                setupCurrentIndicator(position);

                if((position+1)==adapter.getItemCount()){
                    btnNavigate.setText("Start");
                }else{
                    btnNavigate.setText("Next");
                }
            }
        });

        btnNavigate.setOnClickListener(this);
    }


    private void setupCurrentIndicator(int index){
        int childCount = layoutIndicator.getChildCount();

        for(int i=0;i<childCount;i++){
            ImageView imageView=(ImageView) layoutIndicator.getChildAt(i);

            if(i==index){
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),R.drawable.onboarding_indicator_active
                ));
            }
            else{
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),R.drawable.onboarding_indicator_inactive
                ));
            }

        }
    }

    private void setupIndicator(){
        ImageView[] indicaors=new ImageView[adapter.getItemCount()];

        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(8,0,8,0);

        for(int i=0;i<indicaors.length;i++){
            indicaors[i]=new ImageView(getApplicationContext());
            indicaors[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),R.drawable.onboarding_indicator_inactive
            ));

            indicaors[i].setLayoutParams(layoutParams);
            layoutIndicator.addView(indicaors[i]);


        }

    }



    private ArrayList<OnboardingItem> getList(){

        ArrayList<OnboardingItem> list=new ArrayList<>();

        OnboardingItem item1=new OnboardingItem();
        item1.setTitle("Title 1");
        item1.setDescription("Description 1");
        item1.setImage(R.drawable.onboarding_first);


        OnboardingItem item2=new OnboardingItem();
        item2.setTitle("Title2");
        item2.setDescription("Description 2");
        item2.setImage(R.drawable.onboarding_second);

        OnboardingItem item3=new OnboardingItem();
        item3.setTitle("Title 3");
        item3.setDescription("Description 3");
        item3.setImage(R.drawable.onboarding_third);


        list.add(item1);
        list.add(item2);
        list.add(item3);

        return list;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnOnboardingAction:

                if((pager.getCurrentItem()+1)==adapter.getItemCount()){
                    Intent intent=new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    pager.setCurrentItem(pager.getCurrentItem()+1);
                }

                break;
        }

    }
}
