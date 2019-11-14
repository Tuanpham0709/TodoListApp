package com.example.tododaily.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tododaily.R;
import com.example.tododaily.model.Category;
import com.mikhaellopez.circleview.CircleView;

import java.util.ArrayList;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    Context context;
    ArrayList<Category> data;

    public CategoryAdapter(Context context, ArrayList<Category> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.configView(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        CircleView circleView;
        TextView tvCat;
        LinearLayout lCat;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            circleView = itemView.findViewById(R.id.circleView);
            tvCat = itemView.findViewById(R.id.tv_cat);
            lCat = itemView.findViewById(R.id.l_cat);
            onPressItem(itemView);
        }
        void onPressItem(View itemView){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (data.get(getAdapterPosition()).isSelected()){
                        data.get(getAdapterPosition()).setSelected(false);
                        notifyDataSetChanged();
                     return;
                    }
                    for (Category cat : data){
                        cat.setSelected(false);
                    }
                    data.get(getAdapterPosition()).setSelected(true);
                    notifyDataSetChanged();
                }
            });
        }
        void configView(int position){
            circleView.setCircleColor(Color.parseColor(data.get(position).getColor()));
            tvCat.setText(data.get(position).getCategory());
            if(!data.get(position).isSelected()){
                lCat.setBackgroundColor(Color.WHITE);
                tvCat.setTextColor(Color.parseColor("gray"));
                return;
            }
            lCat.setBackgroundColor(Color.parseColor(data.get(getAdapterPosition()).getColor()));
            tvCat.setTextColor(Color.parseColor("#ffffff"));

        }

    }
}
