package com.zofa.mastermind;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class scoreadapter extends RecyclerView.Adapter<scoreadapter.myviewholder>
{
    scoredata data[];

    public scoreadapter(scoredata[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scorelist,parent,false);
        return new scoreadapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView t1 , t2;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.textView1);
            t2 = itemView.findViewById(R.id.textView2);

        }
    }
}
