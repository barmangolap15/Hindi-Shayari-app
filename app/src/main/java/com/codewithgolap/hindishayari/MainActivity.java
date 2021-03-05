package com.codewithgolap.hindishayari;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    GridView gridView;
    String[] fruitNames = {"Sad","Funny","Love",
            "Birthday","Inspired","Motivational"};
    int[] fruitImages = {R.drawable.sad_emoji_removebg_preview,R.drawable.funny_emoji_removebg_preview,
            R.drawable.love_emoji_removebg_preview,R.drawable.birthday_emoji_removebg_preview,
            R.drawable.inspire_emoji_removebg_preview,
            R.drawable.motivational_emoji_removebg_preview};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager2 = findViewById(R.id.viewpagerImageSlider);

        List<SliderItems> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.sad_image)); //use your images
        sliderItems.add(new SliderItems(R.drawable.funny_image));
        sliderItems.add(new SliderItems(R.drawable.love_image));
        sliderItems.add(new SliderItems(R.drawable.birtha_image));
        sliderItems.add(new SliderItems(R.drawable.inspire_image));
        sliderItems.add(new SliderItems(R.drawable.motivational_image));

        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));

        //now add some changes and add infinite auto image slide

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r =1 - Math.abs(position);
                page.setScaleY(0.85f + r*0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,2000); //slide duration 2 seconds
            }
        });

        gridView=findViewById(R.id.gridview);

        CustomAdapter customAdapter=new CustomAdapter();

        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),GridItemActivity.class);
                intent.putExtra("name",String.valueOf(position));
//                intent.putExtra("name",fruitNames[position]);
//                intent.putExtra("image",fruitImages[position]);
                startActivity(intent);
            }
        });
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    private class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return fruitImages.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v= getLayoutInflater().inflate(R.layout.row_data,null);
            TextView name=v.findViewById(R.id.fruits);
            ImageView image=v.findViewById(R.id.images);

            name.setText(fruitNames[position]);
            image.setImageResource(fruitImages[position]);
            return v;
        }
    }
}
