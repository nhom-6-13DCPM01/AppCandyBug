package com.example.appcandybug.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcandybug.R;
import com.example.appcandybug.model.Product;
import com.example.appcandybug.my_interface.IClickItemListener;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {
    Context context;
    List<Product> products;
    IClickItemListener iClickItemListener;

    public ProductAdapter(Context context, List<Product> products, IClickItemListener iClickItemListener) {
        this.context = context;
        this.products = products;
        this.iClickItemListener = iClickItemListener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_new_product,null);
        ItemHolder itemHolder = new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Product product = products.get(position);
        holder.txtNameProduct.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPriceProduct.setText("Giá : " + decimalFormat.format(product.getPrice())+ " Đ");
        Picasso.with(context).load(product.getImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageProduct);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListener.onClickItemProduct(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;
        public ImageView imageProduct;
        public TextView txtNameProduct, txtPriceProduct;

        public ItemHolder(View itemView){
            super(itemView);
            imageProduct = (ImageView) itemView.findViewById(R.id.imageview_Product);
            txtNameProduct = (TextView) itemView.findViewById(R.id.txt_NameProduct);
            txtPriceProduct = (TextView) itemView.findViewById(R.id.txt_PriceProduct);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutProduct);
        }
    }


}
