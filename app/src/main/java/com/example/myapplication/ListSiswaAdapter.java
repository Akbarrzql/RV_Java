package com.example.myapplication;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListSiswaAdapter extends RecyclerView.Adapter<ListSiswaAdapter.ViewHolder> {

    private ArrayList<Siswa> listSiswa;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ListSiswaAdapter(ArrayList<Siswa> listSiswa) {
        this.listSiswa = listSiswa;
    }

    @NonNull
    @Override
    public ListSiswaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        return new ListSiswaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListSiswaAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        Siswa siswa = listSiswa.get(position);

        holder.imgPhoto.setImageResource(siswa.getFoto());
        holder.tvName.setText(siswa.getNama());
        // add this below
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setPosition(position);
                holder.itemView.showContextMenu();
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return listSiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName;

        public ViewHolder(@NonNull View itemview) {
            super(itemview);
            imgPhoto = itemview.findViewById(R.id.img_list_image);
            tvName = itemview.findViewById(R.id.tv_item_name);

        }
    }


}
