package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SelectPlanet extends AppCompatActivity {

    private int planetId =0;
    private RecyclerView recyclerView;
    private ArrayList<Planet> planetArrayList;
    private MyPlanetAdapter myPlanetAdapter;
    Button button;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_planet);

        recyclerView = findViewById(R.id.planet_select_recyclerview);
        planetArrayList = new ArrayList<>();
        button = findViewById(R.id.planet_selected_button);
        button.setEnabled(false);

        planetArrayList.add(new Planet("CARAT","http://cafefiles.naver.net/20131211_79/iamtol_1386727016861TC1zi_PNG/glazed_anwansoon.png",01));
        planetArrayList.add(new Planet("ARMY","http://cafefiles.naver.net/MjAyMDAxMjhfMjg2/MDAxNTgwMTg0MDk5NzM4.suLiYGoryykVVzvOcFVlZE9gQ8MWSIUeGQda6LJM2r8g.cmukzFF_zX0jutUNCU1GP61QkQrcZsRGv5p2ng6nH9Mg.PNG/28929-7-venus-clipart.png",02));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        myPlanetAdapter = new MyPlanetAdapter(planetArrayList);
        recyclerView.setAdapter(myPlanetAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = getApplicationContext();
                Intent intent = new Intent(context,PlaceSearchActivity.class);
                intent.putExtra("planetKey",planetId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });



    }

    class MyPlanetAdapter extends RecyclerView.Adapter<MyPlanetAdapter.ViewHolder>{

        private ArrayList<Planet> arrayList;


        public MyPlanetAdapter(ArrayList<Planet> arrayList) {
            this.arrayList = arrayList;
        }

        @NonNull
        @Override
        public MyPlanetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int position) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.planet_list_item_view,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyPlanetAdapter.ViewHolder holder, final int position) {
            holder.textView.setText(arrayList.get(position).getName());
            Glide.with(SelectPlanet.this).load(arrayList.get(position).getImage()).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    planetId = arrayList.get(position).getId();
                    button.setEnabled(true);
                    button.setBackgroundColor(getColor(R.color.black));
                    //Log.d("testt","planetId : " + planetId);
                    if(planetId == arrayList.get(position).getId()) {
                        view.setBackground(getDrawable(R.drawable.image_selected));
                    }
                    else{
                        view.setBackground(null);
                    }
                }

            });
            //notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            ImageView imageView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.planet_text_view);
                imageView = itemView.findViewById(R.id.planet_image_view);
            }
        }




    }
}
