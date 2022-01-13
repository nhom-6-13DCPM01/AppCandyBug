package com.example.appcandybug.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcandybug.R;
import com.example.appcandybug.model.Mycart;
import com.example.appcandybug.model.Order;
import com.example.appcandybug.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MyOrderAdapter extends BaseAdapter {
    Context context;
    ArrayList<Mycart> listOrder;

    public MyOrderAdapter(Context context, ArrayList<Mycart> listOrder) {
        this.context = context;
        this.listOrder = listOrder;
    }

    @Override
    public int getCount() {
        return listOrder.size();
    }

    @Override
    public Object getItem(int position) {
        return listOrder.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyOrderAdapter.ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new MyOrderAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_list_myorder,null);
            viewHolder.my_madonhang = convertView.findViewById(R.id.my_madonhang);
            viewHolder.my_ngaydat = convertView.findViewById(R.id.my_ngaydat);
            viewHolder.my_diachi = convertView.findViewById(R.id.my_diachi);
            viewHolder.my_SDT = convertView.findViewById(R.id.my_SDT);
            viewHolder.my_tongtien = convertView.findViewById(R.id.my_tongtien);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyOrderAdapter.ViewHolder) convertView.getTag();
        }
        Mycart order = (Mycart) getItem(position);
        viewHolder.my_madonhang.setText(""+order.getId());
        viewHolder.my_ngaydat.setText(order.getDateCreate().toString());
        viewHolder.my_diachi.setText(order.getAddress());
        viewHolder.my_SDT.setText(""+order.getSDT());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.my_tongtien.setText(decimalFormat.format(order.getTongTien())+ " Đ");
        return convertView;
    }

    public class ViewHolder{
        public TextView my_madonhang,my_ngaydat,my_diachi,my_SDT,my_tongtien;
    }
}
