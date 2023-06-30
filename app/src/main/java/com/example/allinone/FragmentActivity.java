package com.example.allinone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.allinone.testframgent.CctvFragment;

public class FragmentActivity extends AppCompatActivity {
    FrameLayout frameLayoutRoot=null;
    Fragment cctvFragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        frameLayoutRoot = findViewById(R.id.idMainFrame);

        cctvFragment = new CctvFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startFragment();
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

}