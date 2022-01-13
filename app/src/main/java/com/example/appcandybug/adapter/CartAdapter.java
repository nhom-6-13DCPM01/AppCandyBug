package com.example.appcandybug.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcandybug.R;
import com.example.appcandybug.activity.CartActivity;
import com.example.appcandybug.activity.Index;
import com.example.appcandybug.model.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context context;
    List<Cart> listCart;

    public CartAdapter(Context context, List<Cart> listCart) {
        this.context = context;
        this.listCart = listCart;
    }

    @Override
    public int getCount() {
        return listCart.size();
    }

    @Override
    public Object getItem(int position) {
        return listCart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txtTenCart, txtGiaCart;
        public ImageView imgCart;
        public Button btnMinus, btnValue, btnPlus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_item_cart, null);
            viewHolder.txtTenCart = (TextView) convertView.findViewById(R.id.textViewTenCart);
            viewHolder.txtGiaCart = (TextView) convertView.findViewById(R.id.textViewGiaCart);
            viewHolder.imgCart = (ImageView) convertView.findViewById(R.id.imageViewCart);
            viewHolder.btnMinus = (Button) convertView.findViewById(R.id.buttonSoLuongMinus);
            viewHolder.btnValue = (Button) convertView.findViewById(R.id.buttonSoLuongValue);
            viewHolder.btnPlus = (Button) convertView.findViewById(R.id.buttonSoLuongPlus);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Cart cart = (Cart) getItem(position);
        viewHolder.txtTenCart.setText(cart.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaCart.setText(decimalFormat.format(cart.getGiaSP()) + " Đ");
        Picasso.with(context).load(cart.getHinhSP())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(viewHolder.imgCart);
        viewHolder.btnValue.setText(cart.getSoLuongSP() + "");
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuongMoiNhat = Integer.parseInt(finalViewHolder.btnValue.getText().toString()) + 1;
                int soLuongHienTai = Index.mangCart.get(position).getSoLuongSP();
                double giaHienTai = Index.mangCart.get(position).getGiaSP();
                Index.mangCart.get(position).setSoLuongSP(soLuongMoiNhat);
                double giaMoiNhat = (giaHienTai * soLuongMoiNhat) / soLuongHienTai;
                Index.mangCart.get(position).setGiaSP(giaMoiNhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiaCart.setText(decimalFormat.format(giaMoiNhat) + " Đ");
                CartActivity.eventUntil();
                finalViewHolder.btnValue.setText(String.valueOf(soLuongMoiNhat));
            }
        });
        viewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuongMoiNhat = Integer.parseInt(finalViewHolder.btnValue.getText().toString()) - 1;
                int soLuongHienTai = Index.mangCart.get(position).getSoLuongSP();
                double giaHienTai = Index.mangCart.get(position).getGiaSP();
                Index.mangCart.get(position).setSoLuongSP(soLuongMoiNhat);
                double giaMoiNhat = (giaHienTai * soLuongMoiNhat) / soLuongHienTai;
                Index.mangCart.get(position).setGiaSP(giaMoiNhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiaCart.setText(decimalFormat.format(giaMoiNhat) + " Đ");
                CartActivity.eventUntil();
                if(soLuongMoiNhat < 2){
                    finalViewHolder.btnMinus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnValue.setText(String.valueOf(soLuongMoiNhat));
                }else{
                    finalViewHolder.btnMinus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnValue.setText(String.valueOf(soLuongMoiNhat));
                }
            }
        });
        return convertView;
    }
}
