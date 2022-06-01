package com.example.myapplication;

import androidx.annotation.NonNull;
import java.util.ArrayList;

public class SiswaData {
    private static String[] siswaNames = {
            "Ilyas",
            "Wira",
            "Akbar",
            "Alwan",
            "Amri",
            "Jati",
            "Andhika",
            "Antariksa",
            "Bayu",
            "Bhre Nabil",
            "Bimo",
            "Daffa",
            "Danar",
            "Dimas",
            "Firdaus",
            "Hegel",
            "Hibatullah",
            "Mikhail"
    };

    private static int [] siswaImages = {
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android,
            R.drawable.android
    };




    @NonNull
    static ArrayList<Siswa> getListData(){
        ArrayList<Siswa> list = new ArrayList<>();
        for (int position = 0; position < siswaNames.length; position++){
            Siswa siswa = new Siswa();
            siswa.setNama(siswaNames[position]);
            siswa.setFoto(siswaImages[position]);
            list.add(siswa);
        }
        return list;
    }
}
