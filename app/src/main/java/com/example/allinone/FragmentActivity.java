package com.example.allinone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TimePicker;

public class FragmentActivity extends AppCompatActivity {

    FrameLayout frameLayoutRoot=null;
    FrameLayout frameLayoutActionSheet=null;

    FrameLayout mBackScreen=null;


    Fragment cctvFragment=null;
    Button btnAction=null;


    Context context=null;


    private ImageView mBackgroundBlurImageView=null;

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            Log.d("test", "onTimeSet() i="+i+", i1="+i1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        context = this;

        frameLayoutRoot = findViewById(R.id.idMainFrame);
        btnAction = findViewById(R.id.idBtnAction);

        frameLayoutActionSheet = new FrameLayout(this);
        FrameLayout.LayoutParams pr=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        frameLayoutActionSheet.setLayoutParams(pr);
        frameLayoutRoot.addView(frameLayoutActionSheet);

        //addBlurBackGroundImage(frameLayoutRoot);
        //cctvFragment = new CctvFragment();

        //home screen clip
        //scr.setClipChildren(false);//default root view clip
        //scr.setClipToPadding(false);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make base screen for app icons.
                //LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                //mBackScreen=(FrameLayout) inflater.inflate(R.layout.layout_fragment_cctv, frameLayoutActionSheet, false);

                TimePickerDialog timePickerDialog = new TimePickerDialog(context, 2, onTimeSetListener,11,30,true);
                timePickerDialog.show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        //startFragment();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void startFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.idMainFrame, cctvFragment);
        //replace
        //fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }



    private void addBlurBackGroundImage(FrameLayout parentLayout)
    {
        killBlurBackGroundImage();
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mBackgroundBlurImageView=new ImageView(parentLayout.getContext());
        mBackgroundBlurImageView.setLayoutParams(lp);
        mBackgroundBlurImageView.setBackgroundColor(Color.parseColor("#77000000"));
        parentLayout.addView(mBackgroundBlurImageView);
    }

    private void killBlurBackGroundImage()
    {
        //ImageView view=mBackScreen.findViewWithTag("TAG_BLUR_IMAGE");

        try {
            if(mBackgroundBlurImageView!=null) {
                ((ViewGroup)mBackgroundBlurImageView.getParent()).removeView(mBackgroundBlurImageView);
                mBackgroundBlurImageView=null;
            }
        } catch (NullPointerException e) {
            //Log.e(TAG, "killBlurBackGroundImage() "+e);
        }
    }

}