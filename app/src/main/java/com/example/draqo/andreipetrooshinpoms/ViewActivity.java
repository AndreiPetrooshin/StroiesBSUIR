package com.example.draqo.andreipetrooshinpoms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by draqo on 01.06.2017.
 */

public class ViewActivity extends AppCompatActivity {

    private LinearLayout linearLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);
        linearLayout  = (LinearLayout) findViewById(R.id.linear_stories);
        fillLayout();


    }


    private void fillLayout(){
        ArrayList<String> arrayList =  MainActivity.stories;
        LinearLayout.LayoutParams layoutParams =  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                for (int i = 0; i < arrayList.size(); i++) {
            if(!arrayList.get(i).equals("")){
//                TableRow tableRow  = new TableRow(getApplicationContext());
                TextView textView = new TextView(getApplicationContext());
                textView.setLayoutParams(layoutParams);
                textView.setText(arrayList.get(i));
//                tableRow.addView(textView);
                linearLayout.addView(textView);
            }
        }
    }



}
