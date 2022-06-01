package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView  recyclerViewSiwa;
    private TextView textViewrv;
    private Button btnAdd;
    private final ArrayList<Siswa> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewSiwa = findViewById(R.id.recyclerView);
        textViewrv = findViewById(R.id.textRv);
        btnAdd = findViewById(R.id.btn_data);
        recyclerViewSiwa.setHasFixedSize(true);
        registerForContextMenu(recyclerViewSiwa);




        list.addAll(SiswaData.getListData());
        showRecyclerList();
        addData();

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


        recyclerViewSiwa.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    btnAdd.setVisibility(View.VISIBLE);
                }else {
                    btnAdd.setVisibility(View.INVISIBLE);
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

        });

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

    public void addData(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.diaolag_add_data);

                EditText editText = dialog.findViewById(R.id.edt_nama);
                Button button = dialog.findViewById(R.id.button_save);
                Button button1 = dialog.findViewById(R.id.button_cancel);

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nama = editText.getText().toString();
                        if (nama.isEmpty()){
                            Toast.makeText(MainActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                        }else {
                            list.add(new Siswa());
                            list.get(list.size()-1).setNama(nama);
                            list.get(list.size()-1).setFoto(R.drawable.android);
                            recyclerViewSiwa.getAdapter().notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });
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