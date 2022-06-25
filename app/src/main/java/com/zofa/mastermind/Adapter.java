package com.zofa.mastermind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {

    List<Mind> data;
    Context context;

    public Adapter(List<Mind> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View root = LayoutInflater.from(context).inflate(R.layout.listview_content,null);

        TextView tv = root.findViewById(R.id.txtscore);
        TextView tv1 = root.findViewById(R.id.txtscore1);
        tv.setText("Scores : "+data.get(position).getScore());
        tv1.setText("Name : "+data.get(position).getName());
        return root;
    }
}
