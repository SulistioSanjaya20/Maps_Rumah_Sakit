package com.example.maps_rumah_sakit.holder;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maps_rumah_sakit.R;

public class PuskesmasHolder extends RecyclerView.ViewHolder {

    public TextView tv_nama_puskesmas, tv_location;
    public CardView cvPuskesmas;

    public PuskesmasHolder(View view) {
        super(view);

        cvPuskesmas = view.findViewById(R.id.cvPuskesmas);
        tv_nama_puskesmas = view.findViewById(R.id.tv_nama_puskesmas);
        tv_location = view.findViewById(R.id.tv_location);

    }
}
