package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSiwa;
    private ArrayList<Siswa> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerViewSiwa = findViewById(R.id.recyclerView);
        recyclerViewSiwa.setHasFixedSize(true);


        list.addAll(SiswaData.getListData());
        showRecyclerList();

//        Cara Ke 1
        recyclerViewSiwa.addOnItemTouchListener( new RecyclerItemClickListener(this, recyclerViewSiwa ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Toast.makeText(MainActivity.this, "Anda memilih " + list.get(position).getNama(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(view.getContext(), DetailSiswaActivity.class);
                        intent.putExtra("NAMA_SISWA", list.get(position).getNama());
                        intent.putExtra("FOTO_SISWA", list.get(position).getFoto());
                        view.getContext().startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        /*
        Cara ke 2
        recyclerViewSiwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = (Activity) view.getContext();
                Intent intent = new Intent(activity, DetailSiswaActivity.class);
//                intent.putExtra("NAMA_SISWA", list.get(0).getNama());
//                intent.putExtra("FOTO_SISWA", list.get(0).getFoto());
                intent.putExtra("NAMA_SISWA", list.get(recyclerViewSiwa.getChildAdapterPosition(view)).getNama());
                intent.putExtra("FOTO_SISWA", list.get(recyclerViewSiwa.getChildAdapterPosition(view)).getFoto());
                activity.startActivity(intent);
            }
        });

        */

    }

    private void showRecyclerList() {
        recyclerViewSiwa.setLayoutManager(new LinearLayoutManager(this));
        ListSiswaAdapter listSiswaAdapter = new ListSiswaAdapter(list);
        recyclerViewSiwa.setAdapter(listSiswaAdapter);
    }
}