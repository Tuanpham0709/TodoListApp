package com.example.tododaily.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tododaily.R;
import com.example.tododaily.interfaces.TaskList;
import com.example.tododaily.model.DialogAddTask;
import com.example.tododaily.model.Task;
import com.example.tododaily.presenter.MainPresenter;
import com.example.tododaily.presenter.TaskListPresenter;

import java.util.ArrayList;

public class TaskAdapter  extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    ArrayList<Task> data;
    Context context;
    RecyclerView recyclerView;
    LinearLayout emptyTask;
    FragmentManager fragmentManager;
    public TaskAdapter(ArrayList<Task> data, Context context, View view,String type, FragmentManager fragmentManager) {
        this.data = data;
        this.context = context;
        this.fragmentManager = fragmentManager;
        if(type.equals("activity")){
            recyclerView= view.findViewById(R.id.rv_task_of_cat);
            emptyTask= view.findViewById(R.id.empty_task);
        }else{
            recyclerView = view.findViewById(R.id.rv_task);
            emptyTask= view.findViewById(R.id.empty_task);
        }

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
   public void deletedItem(boolean isRemoved, int position){
        if(isRemoved){
            data.remove(position);
            notifyDataSetChanged();
            if (data.size() <1){
                recyclerView.setVisibility(View.GONE);
                emptyTask.setVisibility(View.VISIBLE);
            }
        }
    }
    public class TaskViewHolder extends RecyclerView.ViewHolder implements TaskList {
        TaskListPresenter taskListPresenter;
        View vCat;
        TextView tvTime, tvTaskName;
        ImageView imgCheck;
        CardView cardItem;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }


        void init(View itemView){
            taskListPresenter = new TaskListPresenter(this, context);
            vCat = itemView.findViewById(R.id.v_cat);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvTaskName = itemView.findViewById(R.id.tv_task_name);
            imgCheck = itemView.findViewById(R.id.img_check_done);
            cardItem = itemView.findViewById(R.id.card_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    taskListPresenter.toggleTask(!data.get(getAdapterPosition()).isChecked(),data.get(getAdapterPosition()));

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    DialogAddTask editNameDialogFragment = DialogAddTask.editInstance("Edit task", "Edit task", data.get(getAdapterPosition()));
                    editNameDialogFragment.show(fragmentManager, "fragment_edit_name");
                    return true;
                }
            });
        }
        void configView(int positon){
            switch (data.get(positon).getCateName()){
                case "Personal":
                    vCat.setBackgroundColor(context.getResources().getColor(R.color.yellow));
                    break;
                case "Work":
                    vCat.setBackgroundColor(context.getResources().getColor(R.color.green));
                    break;
                case "Meeting":
                    vCat.setBackgroundColor(context.getResources().getColor(R.color.pink));
                    break;
                case "Shopping":
                    vCat.setBackgroundColor(context.getResources().getColor(R.color.orange));
                    break;
                case "Party":
                    vCat.setBackgroundColor(context.getResources().getColor(R.color.blue));
                    break;
                case "Study":
                    vCat.setBackgroundColor(context.getResources().getColor(R.color.violet));
                    break;
                    default:vCat.setBackgroundColor(Color.BLACK);

            }

            tvTaskName.setText(data.get(positon).getNameTask());
            tvTime.setText(data.get(positon).getTime());
            cardItem.setMaxCardElevation(0);
            cardItem.setCardBackgroundColor(Color.parseColor("#E6E6E6"));
            if(data.get(getAdapterPosition()).isChecked()){
                imgCheck.setImageResource(R.drawable.ic_tick);
                return;
            }
            imgCheck.setImageResource(R.drawable.bell_small);
        }


        @Override
        public void checkTask(boolean checked) {
            data.get(getAdapterPosition()).setChecked(checked);
            notifyDataSetChanged();
        }

        @Override
        public void editTask() {

        }

        @Override
        public void removeTask(int position,boolean isRemoved, String message ) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void getAllTask(ArrayList<Task> tasks) {

        }
    }
}
