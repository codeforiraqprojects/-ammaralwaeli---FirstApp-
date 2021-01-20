package com.example.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=(TextView)findViewById(R.id.textView2);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            int age=extras.getInt("Age");
            double salary=extras.getDouble("Salary");
            StringBuilder result=new StringBuilder();
            result.append("\n"+extras.getString("First name"));
            result.append("\n"+String.valueOf(salary));
            result.append("\n"+extras.getString("Department"));
            result.append("\n"+String.valueOf(age));

            textView.setText(result);
            Log.d("TAG","Hello Ammar Alwaeli");
        }

    }
}
