package com.example.archaezoo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAddItem,buttonListItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddItem = (Button)findViewById(R.id.b1);
        buttonAddItem.setOnClickListener(this);

//        buttonListItem = (Button)findViewById(R.id.btn_list_items);
//        buttonListItem.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v==buttonAddItem){

            Intent intent = new Intent(getApplicationContext(),add_item.class);
            startActivity(intent);
        }

//        if(v==buttonListItem){
//
//            Intent intent = new Intent(getApplicationContext(),listItem.class);
//            startActivity(intent);
//        }

    }
}
