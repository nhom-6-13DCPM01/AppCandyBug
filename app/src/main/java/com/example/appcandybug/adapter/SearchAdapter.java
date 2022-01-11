package com.example.appcandybug.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcandybug.R;
import com.example.appcandybug.model.Product;
import com.example.appcandybug.my_interface.IClickItemListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ItemsearchHolder> {
    Context context;
    List<Product> products;
    private IClickItemListener iClickItemListener;

    public SearchAdapter(Context context, List<Product> products,IClickItemListener iClickItemListener) {
        this.context = context;
        this.products = products;
        this.iClickItemListener = iClickItemListener;
    }
    @NonNull
    @Override
    public ItemsearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search_product,parent,false);
        ItemsearchHolder itemHolder = new ItemsearchHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsearchHolder holder, int position) {
        Product product = products.get(position);
        holder.product_name.setMaxLines(1);
        holder.product_name.setEllipsize(TextUtils.TruncateAt.END);
        holder.product_name.setText(product.getName());
        holder.qty.setText(product.getQuantity()+"");
        holder.description.setText(product.getCategory());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.price.setText("Giá : " + decimalFormat.format(product.getPrice())+ " Đ");
        Picasso.with(context).load(product.getImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.discountberry)
                .into(holder.imageview_search);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
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


    public static class ItemsearchHolder extends RecyclerView.ViewHolder {

        public TextView product_name, price , qty , unit ,description;
        public ImageView imageview_search;
        public ConstraintLayout constraintLayout;

        public ItemsearchHolder(View itemView){
            super(itemView);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            price = (TextView) itemView.findViewById(R.id.price);
            qty = (TextView) itemView.findViewById(R.id.qty);
            unit = (TextView) itemView.findViewById(R.id.unit);
            description = (TextView) itemView.findViewById(R.id.description);
            constraintLayout = itemView.findViewById(R.id.recently_layout);
            imageview_search = itemView.findViewById(R.id.imageview_search);
        }


    }
}