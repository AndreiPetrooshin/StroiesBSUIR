package com.example.draqo.andreipetrooshinpoms;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ViewActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);
        linearLayout = (LinearLayout) findViewById(R.id.linear_stories);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        fillLayout();



    }


    //Заполнение view_activity.xml элементами.
    private void fillLayout() {
        ArrayList<String> arrayList = MainActivity.stories;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 50);
        for (int i = 0; i < arrayList.size(); i++) {
            if (!arrayList.get(i).equals("")) {
                TextView textView = new TextView(getApplicationContext());
                textView.setTextSize(20f);
                textView.setTextColor(Color.WHITE);
                textView.setShadowLayer(5f, 0f, 0f, Color.BLACK);
                textView.setLayoutParams(layoutParams);
                textView.setText(arrayList.get(i));
                linearLayout.addView(textView);
            }
        }
    }
}
