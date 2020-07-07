package com.example.maps_rumah_sakit.adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maps_rumah_sakit.R;
import com.example.maps_rumah_sakit.holder.PuskesmasHolder;
import com.example.maps_rumah_sakit.model.ModelPuskesmas;

import java.util.List;

public class PuskesmasAdapter extends RecyclerView.Adapter<PuskesmasHolder> {

    public List<ModelPuskesmas> rumahSakitList;
    private PuskesmasAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(ModelPuskesmas modelRumahSakit);
    }

    public PuskesmasAdapter(Context context, List<ModelPuskesmas> modelRumahSakitList,
                            PuskesmasAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.rumahSakitList = modelRumahSakitList;
        this.onSelectData = xSelectData;
    }

    @Override
    public PuskesmasHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data_puskesmas, viewGroup, false);
        return new PuskesmasHolder(view);
    }

    @Override
    public void onBindViewHolder(PuskesmasHolder viewHolder, int position) {

        final ModelPuskesmas data = rumahSakitList.get(position);
        viewHolder.tv_nama_puskesmas.setText(data.getNamaPuskesmas());
        viewHolder.tv_location.setText(data.getLocation());
        viewHolder.cvPuskesmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rumahSakitList.size();
    }
}
