package com.zofa.mastermindadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    List<com.zofa.mastermindadmin.ModelMind> data;
    Context context;

    public MyAdapter(List<com.zofa.mastermindadmin.ModelMind> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View root = LayoutInflater.from(context).inflate(R.layout.listview_content,null);

        TextView tv = root.findViewById(R.id.txtscore);
        TextView tv1 = root.findViewById(R.id.txtscore1);
        tv.setText("Name : "+data.get(position).getScore());
        tv1.setText("Id : "+data.get(position).getName());
        return root;
    }
}
