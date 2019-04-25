package com.example.flyanimation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.List;

/**
 * Created by coderzlab on 20/5/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<FileContainer> mList;
    private Context mContext;

    private ProductItemActionListener actionListener;
    public ProductAdapter(Context mContext, List<FileContainer> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setActionListener(ProductItemActionListener actionListener) {
        this.actionListener = actionListener;
    }


    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ProductHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ProductHolder holder, int position) {


        FileContainer product=mList.get(position);

        holder.itemIV.setImageDrawable(product.getAppIcon());
        holder.itemCopyIV.setImageDrawable(product.getAppIcon());

        holder.itemCopyIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actionListener!=null)
                    actionListener.onItemTap(holder.itemCopyIV);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addItems(List<FileContainer> fileContainers) {
    }


    class  ProductHolder extends RecyclerView.ViewHolder{


        protected ImageView itemIV;
        protected ImageView itemCopyIV;


        public ProductHolder(View itemView) {
            super(itemView);
            itemIV=(ImageView) itemView.findViewById(R.id.itemIV);
            itemCopyIV=(ImageView) itemView.findViewById(R.id.itemCopyIV);

        }

    }


    public interface ProductItemActionListener{
        void onItemTap(ImageView imageView);
    }
}
