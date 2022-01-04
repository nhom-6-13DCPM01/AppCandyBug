package com.example.appcandybug.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.appcandybug.model.Product;

import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {
    private ArrayList<Product> list;
    private int layout;
    private Context context;

    public OrderAdapter(ArrayList<Product> list, int layout, Context context) {
        this.list = list;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() { return list.size(); }

    @Override
    public Object getItem(int position) { return list.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    public class ViewHolder{

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
