package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView  recyclerViewSiwa;
    private TextView textViewrv;
    private final ArrayList<Siswa> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewSiwa = findViewById(R.id.recyclerView);
        textViewrv = findViewById(R.id.textRv);
        recyclerViewSiwa.setHasFixedSize(true);
        registerForContextMenu(recyclerViewSiwa);




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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        int position = ((ListSiswaAdapter)recyclerViewSiwa.getAdapter()).getPosition();
        switch (item.getItemId()) {
            case R.id.delete:
                showDialog();
                return true;
            case R.id.sms:
                Toast.makeText(this, "SMS", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onContextItemSelected(item);
    }



    private void showRecyclerList() {
        recyclerViewSiwa.setLayoutManager(new LinearLayoutManager(this));
        ListSiswaAdapter listSiswaAdapter = new ListSiswaAdapter(list);

        recyclerViewSiwa.setAdapter(listSiswaAdapter);
    }

    private void showDialog() {
        int position = ((ListSiswaAdapter)recyclerViewSiwa.getAdapter()).getPosition();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Konfirmasi");
        builder.setMessage("Apakah kamu sudah yakin ingin menghapus data ini?");

        builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                removeItem(position);
                if (list.isEmpty()) {
                    textViewrv.setVisibility(View.VISIBLE);
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void removeItem(int position) {
        list.remove(position);
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
        showRecyclerList();
    }

    public void restoreItem(Siswa siswa, int position) {
        list.add(position, siswa);
        showRecyclerList();
    }

}