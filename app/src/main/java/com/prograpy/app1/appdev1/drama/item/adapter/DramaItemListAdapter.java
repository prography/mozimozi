package com.prograpy.app1.appdev1.drama.item.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prograpy.app1.appdev1.R;
import com.prograpy.app1.appdev1.db.DbController;
import com.prograpy.app1.appdev1.utils.Utils;
import com.prograpy.app1.appdev1.vo.ProductVO;

import java.util.ArrayList;
import java.util.List;

public class DramaItemListAdapter extends RecyclerView.Adapter<DramaItemListAdapter.DramaItemViewHolder> {

    private Context context;
    private List<ProductVO> dramaProductData = new ArrayList<ProductVO>();
    private int item_layout = 0;

    private View.OnClickListener onClickListener;
    private View.OnClickListener onHeartClickListener;

    public DramaItemListAdapter(Context context, View.OnClickListener listener, View.OnClickListener heartListener) {
        this.context = context;
        this.onClickListener = listener;
        onHeartClickListener = heartListener;
    }

    public void setDramaProductData(List<ProductVO> dramaProductData){
        this.dramaProductData = dramaProductData;
    }

    @Override
    public DramaItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_drama_item_child, parent,false);

        return new DramaItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DramaItemViewHolder holder, int position) {

        ProductVO item = dramaProductData.get(position);

        Glide.with(context).load(item.getP_img()).into( ((DramaItemViewHolder)holder).itemImg);
        ((DramaItemViewHolder)holder).itemName.setText(item.getP_name());
        ((DramaItemViewHolder)holder).itemPrice.setText(Utils.moneyFormatToWon(item.getP_price()));
        ((DramaItemViewHolder)holder).itemDrama.setText(item.getD_name());
        ((DramaItemViewHolder)holder).itemHeart.setOnClickListener(onHeartClickListener);
        ((DramaItemViewHolder)holder).itemZzim.setOnClickListener(onHeartClickListener);
        ((DramaItemViewHolder)holder).itemHeart.bringToFront();
        ((DramaItemViewHolder)holder).itemHeart.setTag(item);
        ((DramaItemViewHolder)holder).itemZzim.setTag(item);

        if(DbController.isOverlapData(context, item.getP_id())){
            ((DramaItemViewHolder)holder).itemHeart.setSelected(true);
            ((DramaItemViewHolder)holder).itemZzim.setText("찜 해제하기");
        }else{
            ((DramaItemViewHolder)holder).itemHeart.setSelected(false);
            ((DramaItemViewHolder)holder).itemZzim.setText("찜하기");
        }


        ((DramaItemViewHolder)holder).itemDetail.setTag(item);
        ((DramaItemViewHolder)holder).itemDetail.setOnClickListener(onClickListener);

    }

    @Override
    public int getItemCount() {
        return this.dramaProductData.size();
    }


    public class DramaItemViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout parent;
        private ImageView itemImg;
        private TextView itemPrice;
        private TextView itemName;
        private ImageView itemHeart;
        private TextView itemZzim;
        private TextView itemDetail;
        private TextView itemDrama;

        public DramaItemViewHolder(View itemView) {
            super(itemView);

            parent = (LinearLayout) itemView.findViewById(R.id.view_parent);
            itemDrama = (TextView) itemView.findViewById(R.id.item_drama_name);
            itemImg = (ImageView) itemView.findViewById(R.id.item_image);
            itemPrice = (TextView) itemView.findViewById(R.id.item_price);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemHeart = (ImageView) itemView.findViewById(R.id.heart_btn);
            itemZzim = (TextView) itemView.findViewById(R.id.item_zzim);
            itemDetail = (TextView) itemView.findViewById(R.id.item_url);

        }

        public void setOnItemClick(View.OnClickListener listener){
            parent.setOnClickListener(listener);
        }
    }

}
