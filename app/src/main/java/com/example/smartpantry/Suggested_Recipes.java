package com.example.smartpantry;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Suggested_Recipes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suggested_recipes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        DataBaseAdapter databaseAdapter = new DataBaseAdapter(this);

            ListView lvrecipe = findViewById(R.id.lvRecipe);

            SimpleCursorAdapter simpleCursorAdapter = databaseAdapter.populateListViewFromDB();
            lvrecipe.setAdapter(simpleCursorAdapter);
            lvrecipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                    String name = cursor.getString(0);
                    Toast.makeText(Suggested_Recipes.this, name, Toast.LENGTH_LONG).show();
            }
        });
    }
}