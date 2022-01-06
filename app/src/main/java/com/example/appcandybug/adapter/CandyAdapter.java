package com.example.appcandybug.adapter;

import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcandybug.R;
import com.example.appcandybug.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CandyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> listCandy;

    public CandyAdapter(Context context, ArrayList<Product> listCandy) {
        this.context = context;
        this.listCandy = listCandy;
    }

    @Override
    public int getCount() {
        return listCandy.size();
    }

    @Override
    public Object getItem(int position) {
        return listCandy.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_list_candy,null);
            viewHolder.txt_NameCandy = convertView.findViewById(R.id.txt_NameCandy);
            viewHolder.txt_PriceCandy = convertView.findViewById(R.id.txt_PriceCandy);
            viewHolder.txt_DiscriptionCandy = convertView.findViewById(R.id.txt_DiscriptionCandy);
            viewHolder.imageView_Candy = convertView.findViewById(R.id.imageview_Candy);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Product product = (Product) getItem(position);
        viewHolder.txt_NameCandy.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txt_PriceCandy.setText("Giá : " + decimalFormat.format(product.getPrice())+ " Đ");
        viewHolder.txt_DiscriptionCandy.setMaxLines(2);
        viewHolder.txt_DiscriptionCandy.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txt_DiscriptionCandy.setText(product.getDescription());
        Picasso.with(context).load(product.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(viewHolder.imageView_Candy);
        return convertView;
    }

    public class ViewHolder{
        public TextView txt_NameCandy,txt_PriceCandy,txt_DiscriptionCandy;
        public ImageView imageView_Candy;
    }
}
