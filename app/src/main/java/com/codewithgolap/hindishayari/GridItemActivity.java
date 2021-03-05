package com.codewithgolap.hindishayari;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class GridItemActivity extends AppCompatActivity {

    TextView name;
    ImageView image;
    FragmentManager fm;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);
//        name=findViewById(R.id.griddata);
//        image=findViewById(R.id.imageView);

        //get the info from main activity such as which button is clicked
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");


        //set the data to transfer info from fragment to activity
        Bundle bundle=new Bundle();
        bundle.putString("name_key",name);
        Shayari_List shayari_list=new Shayari_List();
        shayari_list.setArguments(bundle);

        //attach  the fragment to activity

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.second_layout, shayari_list);
        ft.commit();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
