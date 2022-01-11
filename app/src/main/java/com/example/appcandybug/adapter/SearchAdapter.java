package com.example.appcandybug.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcandybug.R;
import com.example.appcandybug.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ItemsearchHolder>{
    Context context;
    List<Product> products;

    public SearchAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }
    @NonNull
    @Override
    public ItemsearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_new_product,null);
        ItemsearchHolder itemHolder = new ItemsearchHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsearchHolder holder, int position) {
        Product product = products.get(position);
        holder.txtNameProduct.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPriceProduct.setText("Giá : " + decimalFormat.format(product.getPrice())+ " Đ");
        Picasso.with(context).load(product.getImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageProduct);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public static class ItemsearchHolder extends RecyclerView.ViewHolder{
        public ImageView imageProduct;
        public TextView txtNameProduct, txtPriceProduct;

        public ItemsearchHolder(View itemView){
            super(itemView);
            imageProduct = (ImageView) itemView.findViewById(R.id.imageview_Product);
            txtNameProduct = (TextView) itemView.findViewById(R.id.txt_NameProduct);
            txtPriceProduct = (TextView) itemView.findViewById(R.id.txt_PriceProduct);
        }
    }
}