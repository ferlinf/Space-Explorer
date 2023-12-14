package com.example.spaceexplorer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.text.BreakIterator;
import java.util.ArrayList;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ItemData> values;
    private LayoutInflater inflater;

    public PlanetAdapter (Context context, ArrayList<ItemData> values){
        this.context=context;
        this.values=values;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item,parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemData data = values.get(position);
        holder.titleText.setText(data.itemTitle);
        holder.descText.setText(data.itemDesc);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image);

        Glide.with(context)
                .load(data.itemImage)  // Pastikan data.itemImage tidak null
                .apply(requestOptions)
                .into(holder.imageView);

        Log.d("Adapter", "Binding item at position: " + position);
    }

    @Override
    public int getItemCount() {return values.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleText;
        ImageView imageView;
        TextView descText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title);
            imageView=itemView.findViewById(R.id.img);
            descText=itemView.findViewById(R.id.desc);
        }
    }
}
