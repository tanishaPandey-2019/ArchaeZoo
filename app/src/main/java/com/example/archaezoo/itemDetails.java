package com.example.archaezoo;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class itemDetails extends AppCompatActivity  {


    TextView textViewName, textViewGender, textViewClass,textViewId;
    Button buttonUpdateItem, buttonDeleteItem;
    String itemId, name, gender, classStudent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item_details);

        Intent intent = getIntent();
        itemId = intent.getStringExtra("itemId");
        name = intent.getStringExtra("name");
        gender = intent.getStringExtra("gender");
        classStudent = intent.getStringExtra("classStudent");

        textViewId = (TextView)findViewById(R.id.tv_id);
        textViewName = (TextView) findViewById(R.id.tv_item_name);
        textViewGender = (TextView) findViewById(R.id.tv_brand);
        textViewClass = (TextView) findViewById(R.id.tv_price);

        textViewId.setText(itemId);
        textViewName.setText(name);
        textViewGender.setText(gender);
        textViewClass.setText(classStudent);


    }
}
