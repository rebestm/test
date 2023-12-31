package com.example.allinone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.allinone.testframgent.CctvFragment;
import com.example.callback.JniCallBack;
import com.example.basic.InstanceClass;
import com.example.ndk.NdkTest;


public class MainActivity extends AppCompatActivity implements JniCallBack {
    final static private String TAG = "MainActivity";

    TextView txtMain=null;
    Button buttonMain=null;
    Button buttonGo=null;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getBaseContext();
        txtMain = findViewById(R.id.idTxtMain);
        buttonMain = findViewById(R.id.idButtonMain);
        buttonGo = findViewById(R.id.idBtnGo);

        // test "Module"
        txtMain.setText(InstanceClass.getIns().makeMainString());


        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // test ndk
                //txtMain.setText(NdkTest.getInstance().getString());
                // Jni > java
                NdkTest.getInstance().callConnectAndChangeUiJNI();
            }
        });

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FragmentActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        NdkTest.setJniCallBack(this);
    }

    @Override
    public void jniHelloCallBack() {
        Log.d(TAG, "jniHelloCallBack()");
        // Jni > java > CB > Main App
        txtMain.setText("jniHelloCallBack");
    }

    @Override
    public void jniTestCallBack() {
        Log.d(TAG, "jniTestCallBack()");
    }

    public void startFragment() {
        /*Fragment fragment = new FrontDoorFragment();


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentBorC, fragment);
        fragmentTransaction.commit();*/
    }
}