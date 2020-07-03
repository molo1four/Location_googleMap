package com.test.location_googlemap;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Maps> mapsArrayList;


    public RecyclerViewAdapter(Context context, ArrayList<Maps> mapsArrayList) {
        this.context = context;
        this.mapsArrayList = mapsArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Maps maps = mapsArrayList.get(position);
        String name = maps.getName();
        holder.txtName.setText(name);

        String vicinity = maps.getVicinity();
        holder.txtVicinity.setText(vicinity);

        String iconUrl = maps.getIconUrl();
        GlideUrl glideUrl = new GlideUrl(iconUrl,new LazyHeaders.Builder().addHeader("User-Agent","Your-User-Agent").build());
        Glide.with(context).load(glideUrl).into(holder.imgIcon);



    }

    @Override
    public int getItemCount() {
        return mapsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       public TextView txtName;
       public TextView txtVicinity;
       public ImageView imgDelete;
       public ImageView imgIcon;
       public CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtVicinity = itemView.findViewById(R.id.txtVicinity);
            cardView = itemView.findViewById(R.id.cardView);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgIcon = itemView.findViewById(R.id.imgIcon);

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder deleteAlert = new AlertDialog.Builder(context);
                    deleteAlert.setTitle("기록 삭제");
                    deleteAlert.setMessage("완료하겠습니까?");
                    deleteAlert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mapsArrayList.remove(getAdapterPosition());
                            notifyDataSetChanged();

                            Toast.makeText(context,"삭제되었습니다.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                    deleteAlert.setNegativeButton("NO",null);
                    deleteAlert.setCancelable(false);
                    deleteAlert.show();
                }
            });



        }
    }
}
