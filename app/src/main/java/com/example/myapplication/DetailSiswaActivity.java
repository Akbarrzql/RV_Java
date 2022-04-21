package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailSiswaActivity extends AppCompatActivity {
    TextView textDefault;
    ImageView imageDefault;

    //Cara ke 2
    public static final String Nama = "NAMA_SISWA";
    public static final String Foto = "FOTO_SISWA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_siswa);

        textDefault = findViewById(R.id.tv_data_received);
        imageDefault = findViewById(R.id.iv_data_siswa);

        Intent intent = getIntent();
//      cara ke 1
        String nama = intent.getStringExtra("NAMA_SISWA");
        int image = intent.getIntExtra("FOTO_SISWA", 0);
        textDefault.setText(nama);
        imageDefault.setImageResource(image);


        /*

        //Cara KE 2
        String namaSiswa = intent.getStringExtra(Nama);
        int fotoSiswa = intent.getIntExtra(Foto, 0);

        textDefault.setText(namaSiswa);
        imageDefault.setImageResource(fotoSiswa);

        */
    }
}