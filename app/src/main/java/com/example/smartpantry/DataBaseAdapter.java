package com.example.smartpantry;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DataBaseAdapter {

    DatabaseHelper helper;
    SQLiteDatabase db;
    Context context;

    public DataBaseAdapter(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
        this.context = context;
    }

    public SimpleCursorAdapter populateListViewFromDB(){
        String columns[] = {DatabaseHelper.KEY_RECIPE, DatabaseHelper.KEY_INGREDIENTS, DatabaseHelper.KEY_STEPS};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns,null, null,null, null, null, null);
        String[] fromFieldNames = new String[]{
                DatabaseHelper.KEY_RECIPE, DatabaseHelper.KEY_INGREDIENTS, DatabaseHelper.KEY_STEPS
        };
        int[] toViewIDs = new int[]{R.id.recipe_name, R.id.recipe_ingredients};
        SimpleCursorAdapter contactAdapter = new SimpleCursorAdapter(
                context,
                R.layout.Single_item,
                cursor,
                fromFieldNames,
                toViewIDs
        );
        return contactAdapter;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "smart_pantry.db";
        private static final String TABLE_NAME = "recipes";
        // When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = 1;
        private static final String KEY_RECIPE = "recipe_name";
        private static final String KEY_INGREDIENTS="ingredients";
        private static final String KEY_STEPS = "simple_preparations";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_RECIPE+" text primary key, "+ KEY_INGREDIENTS + " text, "+ KEY_STEPS+ " text)";
        private static final String DROP_TABLE = "drop table if exists "+ TABLE_NAME;
        private Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Toast.makeText(context, "constructor called", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                //db.execSQL(CREATE_TABLE);
                Toast.makeText(context, "onCreate called", Toast.LENGTH_SHORT).show();
            }catch (SQLException e){
                Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                Toast.makeText(context, "onUpgrade called", Toast.LENGTH_SHORT).show();
            }catch (SQLException e){
                Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
