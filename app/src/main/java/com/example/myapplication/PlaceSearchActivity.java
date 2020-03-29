package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class PlaceSearchActivity extends AppCompatActivity {


    private int planetData;
    private ImageView search_icon;
    private EditText editSearch;
    private RecyclerView recyclerView;
    private ArrayList<Place> searchList;
    private ArrayList<Place> placeArrayList;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_search);

        search_icon = findViewById(R.id.search_btn);
        recyclerView = findViewById(R.id.recycler_view);
        placeArrayList = new ArrayList<>();
        Intent intent = getIntent();
        planetData = intent.getIntExtra("planetKey",0);
        Log.d("testt",""+planetData);

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editSearch.getVisibility() == view.INVISIBLE){

                    Animation animation = new AlphaAnimation(0,1);
                    animation.setDuration(1000);
                    editSearch.setVisibility(view.VISIBLE);
                    editSearch.setAnimation(animation);

                }
                else{
                    editSearch.setVisibility(view.INVISIBLE);
                }
            }
        });


        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAxOTEyMTFfMjk1/MDAxNTc2MDQxMjM5MzIz.uCh8-YpBGq0mbMWeyrgTqmuGSWq4MuemWOWhJymSamkg.Ha0eDUL_9UHaDCYggy17P39HKNVVYWFdMZfRpDbJOtQg.JPEG.crypearl/" +
                "P20191205_123216007_CD3DED03-371F-4826-9EC9-C5329F44CFF7.JPG","유정식당",02,"식당"));
        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAxOTA0MDVfMjIg/MDAxNTU0NDI5MjIyNjcx.PBkBlb-N7sfxOSqivS3zB" +
                "6ZCgoDFYuUBspc-ViQSBdkg.HxvEgsNdbFM0yGYyqfBEJBqjzc0ZpqNKxAwOw3aVT9wg.JPEG.wntjr8/20190405_105659.jpg","엔게더",02,"카페"));
        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAyMDAyMDZfNDkg/MDAxNTgwOTU4NDIyODkz.FU-8NHfdkwTwls-hCeBhysOdHObkb8mu6QajnC662Zog.Fs9UGzyL" +
                "IuT-oHZGcOFw7Ns6jSWQXWb8QogbpPQ7RpAg.JPEG.dreammer91/KakaoTalk_20200206_110454896.jpg","금돼지식당",02,"식당"));
        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAyMDAxMjFfMjAx/MDAxNTc5NjA3Mjc0Nzc2.RNcYu_yLRaW51fuNdkKgU-Qm2w0zYYrQp6C" +
                "aAE8LIMIg.Uf_ykW95ncqPag9f-N3SE4-sR9CGlYJJZVqszc1W1Agg.JPEG.oneandonly___/IMG_5583.jpg","고수포차",01,"식당"));
        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAxOTA3MjlfMTgy/MDAxNTY0NDA5NjQ0MzIx.l4y7GvGH4oaN0Gg1EvEiyWlAdSHtZb31Zp" +
                "8cIB17PW4g.wHCHkBDA-gLchekNuXKL156s2bIDIKZLZMJ4SNi8IcUg.JPEG.gamjaaaaa/IMG_0951.JPG","용곱창",01,"식당"));
        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAxNzEwMDZfMjA5/MDAxNTA3MjY1NzExMTgy.m6-SYW53BAHB7copRqHdeL3nY_sasdQduHv" +
                "xS-FMm9wg.M3DgVDG8L4qsdY0JXAxiD7fUmQHXelUgf3aU3q4nNUIg.JPEG.hyeonpeach/20171006_132628.jpg","애월해",01,"식당"));

        searchList = new ArrayList<>();

        for (int i = 0; i < placeArrayList.size(); i++){
            if(placeArrayList.get(i).getPlanetId() == planetData){
                searchList.add(placeArrayList.get(i));
            }
        }

        //searchList.addAll(placeArrayList);



        editSearch = findViewById(R.id.edit_search);


//        LayoutInflater layoutInflater = LayoutInflater.from(this);
////        View header = layoutInflater.inflate(R.layout.place_list_header,null,false);
////        myAdapter = new MyAdapter(PlaceSearchActivity.this,searchList);
////        listView.addHeaderView(header);
////        listView.setAdapter(myAdapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(searchList);
        recyclerView.setAdapter(myAdapter);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString();
                search(text);
            }
        });



    }


    public void search(String charText){
        searchList.clear();
        if(charText.length() == 0){
            searchList.addAll(placeArrayList);
        }else{
            for(int i = 0; i <placeArrayList.size(); i++){

                if(placeArrayList.get(i).getName().contains(charText)){
                    searchList.add(placeArrayList.get(i));
                }
            }
        }
        myAdapter.notifyDataSetChanged();

    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        private ArrayList<Place> arrayList;

        public MyAdapter(ArrayList<Place> arrayList) {
            this.arrayList = arrayList;
        }

        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.place_list_tem_view,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
            holder.textView.setText(arrayList.get(position).getName());
            RequestOptions centerOption = new RequestOptions().centerCrop();
            Glide.with(PlaceSearchActivity.this).load(arrayList.get(position).getImage()).apply(centerOption)
                   .into(holder.imageView);
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
                textView = itemView.findViewById(R.id.place_text_view);
                imageView = itemView.findViewById(R.id.place_image_view);
            }
        }

    }
//    class MyAdapter extends BaseAdapter{
//        private ViewHolder viewHolder;
//        private LayoutInflater layoutInflater;
//        private ArrayList<Place> itemList;
//
//        public MyAdapter(Context context,ArrayList<Place> itemList) {
//            this.itemList = itemList;
//            this.layoutInflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public int getCount() {
//            return itemList.size();
//        }
//
//        @Override
//        public Place getItem(int i) {
//            return itemList.get(i);
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int i, View convertView, ViewGroup viewGroup) {
//            View view = convertView;
//            if(view == null){
//                viewHolder = new ViewHolder();
//                view = layoutInflater.inflate(R.layout.place_list_tem_view,null);
//                viewHolder.textView = view.findViewById(R.id.place_text_view);
//                viewHolder.imageView = view.findViewById(R.id.place_image_view);
//                view.setTag(viewHolder);
//            }else{
//                viewHolder = (ViewHolder)view.getTag();
//            }
//
//            viewHolder.textView.setText(getItem(i).getName());
//            RequestOptions centerOption = new RequestOptions().centerCrop();
//            Glide.with(PlaceSearchActivity.this).load(getItem(i).getImage()).apply(centerOption)
//                   .into(viewHolder.imageView);
//            return view;
//        }
//
//        class ViewHolder{
//            public TextView textView = null;
//            public ImageView imageView = null;
//        }
//
//    }


}
