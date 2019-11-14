package com.example.tododaily.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tododaily.R;
import com.example.tododaily.model.Task;

import java.util.ArrayList;

public class TaskAdapter  extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    ArrayList<Task> data;
    Context context;

    public TaskAdapter(ArrayList<Task> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
    holder.configView(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{
        View vCat;
        TextView tvTime, tvTaskName;
        ImageView imgCheck;
        CardView cardItem;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }
        void init(View itemView){
            vCat = itemView.findViewById(R.id.v_cat);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvTaskName = itemView.findViewById(R.id.tv_task_name);
            imgCheck = itemView.findViewById(R.id.img_check_done);
            cardItem = itemView.findViewById(R.id.card_item);

        }
        void configView(int positon){
            switch (data.get(positon).getGroupColor()){
                case "yellow":
                    vCat.setBackgroundColor(context.getResources().getColor(R.color.yellow));
                    break;
                case "green":
                    vCat.setBackgroundColor(context.getResources().getColor(R.color.green));
                    break;
                case "pink":
                    vCat.setBackgroundColor(context.getResources().getColor(R.color.pink));
                    break;
                case "orange":
                    vCat.setBackgroundColor(context.getResources().getColor(R.color.orange));
                    break;
                case "blue":
                    vCat.setBackgroundColor(context.getResources().getColor(R.color.blue));
                    break;
                case "violet":
                    vCat.setBackgroundColor(context.getResources().getColor(R.color.violet));
                    break;
                    default:vCat.setBackgroundColor(Color.BLACK);

            }
            tvTaskName.setText(data.get(positon).getNameTask());
            tvTime.setText(data.get(positon).getTime());
            cardItem.setMaxCardElevation(0);
            cardItem.setCardBackgroundColor(Color.parseColor("#E6E6E6"));
        }
    }
}
