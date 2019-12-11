package com.example.tododaily.database;

import android.app.slice.Slice;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.tododaily.model.Task;

import java.util.ArrayList;

public class DBHelper  extends SQLiteOpenHelper {
    Context context;
    private static final String DATABASE_NAME ="task_db";
    private static final String TASKS = "tasks";
    private static final String ID ="id";
    private static final String TASK_NAME = "task_name",
            CAT_NAME = "cat_name",
            DUE_DATE = "dua_date",
            IS_CHECKED ="is_checked";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_task_table = "create table " + TASKS + " ( " +
                ID + " integer primary key, " +
                TASK_NAME + " text, " +
                CAT_NAME + " text, " +
                DUE_DATE + " date, " +
                IS_CHECKED + " integer )";
        sqLiteDatabase.execSQL(create_task_table);
        Log.e( "created", "created success");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TASKS);
        onCreate(sqLiteDatabase);
    }
    public void addTask(Task task){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_NAME, task.getNameTask());
        contentValues.put(CAT_NAME, task.getCateName());
        contentValues.put(DUE_DATE, task.getTime());
        int isChecked = 0;
        if(task.isChecked()){
            isChecked = 1;
        }
        contentValues.put(IS_CHECKED, isChecked);
        database.insert(TASKS, null, contentValues);
        database.close();
    }
    public Task getTaskById(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(TASKS, new String[]{
                ID, TASK_NAME, CAT_NAME, DUE_DATE, IS_CHECKED
        }, ID + "=?", new String[]{String.valueOf(id)}, null,null,null,null);
        if(cursor !=null) cursor.moveToFirst();
        Task task = new Task();
        task.setId(String.valueOf(cursor.getInt(0)));
        task.setNameTask(cursor.getString(1));
        task.setCateName(cursor.getString(2));
        task.setTime(cursor.getString(3));
        int isChecked = cursor.getInt(4);
        task.setChecked(isChecked == 0);
        cursor.close();
        return task;
    }
    public int update(Task task){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_NAME, task.getNameTask());
        values.put(CAT_NAME, task.getCateName());
        values.put(DUE_DATE, task.getTime());
        int isChecked = task.isChecked() ? 1: 0;
        values.put(IS_CHECKED, isChecked);
        int result  = sqLiteDatabase.update(TASKS, values, ID + "=?", new String[]{String.valueOf(task.getId())});
        return result;
    }
    public ArrayList<Task> getTasksOfCat(String catName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String selectQuery = " select * from " + TASKS  + " where " + CAT_NAME + "=?";
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, new String[]{catName});
        ArrayList<Task> taskList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                Task task  =  new Task();
                task.setId(String.valueOf(cursor.getInt(0)));
                task.setNameTask(cursor.getString(1));
                task.setCateName(cursor.getString(2));
                task.setTime(cursor.getString(3));
                boolean isChecked = cursor.getInt(4) == 1 ? true : false;
                task.setChecked(isChecked);
                taskList.add(task);
            }while (cursor.moveToNext());
        }
        return taskList;
    }
    public ArrayList<Task> getAllTask(){
        ArrayList<Task> tasks = new ArrayList<>();
        String selectQuery = "select * from " + TASKS;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Task task = new Task();
                task.setId(String.valueOf(cursor.getInt(0)));
                task.setNameTask(cursor.getString(1));
                task.setCateName(cursor.getString(2));
                task.setTime(cursor.getString(3));
                task.setChecked(cursor.getInt(4)==1);
                tasks.add(task);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return tasks;

    }
    public int delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        int result = database.delete(TASKS, ID + "=?", new String[]{String.valueOf(id)});
        database.close();
        return result;
    }
    public int getTasksCount(){
        String countQuery  = " select * from " + TASKS;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor.getCount();

    }

}
