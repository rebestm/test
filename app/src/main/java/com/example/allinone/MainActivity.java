package com.example.allinone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.basic.InstanceClass;
import com.example.ndk.NdkTest;


public class MainActivity extends AppCompatActivity {
    final static private String TAG = "MainActivity";

    TextView txtMain;
    Button buttonMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMain = findViewById(R.id.idTxtMain);
        buttonMain = findViewById(R.id.idButtonMain);

        // test "Module"
        txtMain.setText(InstanceClass.getIns().makeMainString());

        // ndk test
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //txtMain.setText(NdkTest.getInstance().hello());
                txtMain.setText(NdkTest.getInstance().getString());
            }
        });


    }
}