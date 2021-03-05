package com.codewithgolap.hindishayari;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Shayari_List extends Fragment {

    RecyclerView recyclerView;

    String data[];
    String category_name;
    public Shayari_List() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_shayari__list, container, false);

        recyclerView = v.findViewById(R.id.shayarirecylerview);

       // recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));

        category_name=getArguments().getString("name_key").toString();

        if (category_name.equalsIgnoreCase("0")){
           data=getResources().getStringArray(R.array.dard_shayari);
        }
        else if(category_name.equalsIgnoreCase("1")){
            data=getResources().getStringArray(R.array.funny_shayari);
        }
        else if (category_name.equalsIgnoreCase("2")){
            data=getResources().getStringArray(R.array.love_shayari);
        }
        else if (category_name.equalsIgnoreCase("3")){
            data=getResources().getStringArray(R.array.birthday_shayari);
        }
        else if (category_name.equalsIgnoreCase("4")){
            data=getResources().getStringArray(R.array.inspired_shayari);
        }
        else if (category_name.equalsIgnoreCase("5")){
            data=getResources().getStringArray(R.array.motivational_shayari);
        }
        else {
            data=new String[2];
            data[0]="data not";
            data[1]="found";

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ShayariListAdapter(data,getContext()));

        return v;
    }

}
