package com.example.appcandybug.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcandybug.R;
import com.example.appcandybug.model.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {
    private ArrayList<Category> list;
    private int layout;
    private Context context;

    public CategoryAdapter(ArrayList<Category> list, int layout, Context context) {
        this.list = list;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView txt_NameCate;
        ImageView icon_Cate;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewHolder.txt_NameCate = view.findViewById(R.id.txt_NameCate);
            viewHolder.icon_Cate = view.findViewById(R.id.icon_Cate);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Category category = (Category) getItem(i);
        viewHolder.txt_NameCate.setText(category.getName());
        Picasso.with(context).load(category.getIcon())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(viewHolder.icon_Cate);
        return view;
    }
}
