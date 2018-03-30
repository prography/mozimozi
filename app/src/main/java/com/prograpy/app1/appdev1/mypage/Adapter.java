package com.prograpy.app1.appdev1.mypage;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prograpy.app1.appdev1.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<ItemData> itemData;
    int item_layout;

    public Adapter(Context context, List<ItemData> itemData, int item_layout) {
        this.context = context;
        this.itemData = itemData;
        this.item_layout = item_layout;
    }

    Adapter(List data) {
        if (data == null) {
            throw new IllegalArgumentException (
                    "data must not be null");
        }
        this.itemData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mypage_item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemData item = itemData.get(position);
       // Drawable drawable = context.getResources().getDrawable(item.getImage());
        holder.listImageInfo.setImageResource(itemData.get(position).image);
        holder.listTitleInfo.setText(item.getTitle());
        holder.listBrand.setText(item.getActor());
        holder.listPrice.setText(item.getTag());


    }

    @Override
    public int getItemCount() {
        return this.itemData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView listTitleInfo;
        TextView listBrand;
        TextView listPrice;
        ImageView listImageInfo;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            listTitleInfo = (TextView) itemView.findViewById(R.id.list_title_info);
            listBrand = (TextView) itemView.findViewById(R.id.list_brand_info);
            listPrice =  (TextView) itemView.findViewById(R.id.list_price_info);
            listImageInfo = (ImageView) itemView.findViewById(R.id.list_image_info);
            cardView = (CardView) itemView.findViewById(R.id.cardview_info);
        }

        public void setText(String text) {
            listTitleInfo.setText(text);
            listBrand.setText(text);
            listPrice.setText(text);
        }
    }


}