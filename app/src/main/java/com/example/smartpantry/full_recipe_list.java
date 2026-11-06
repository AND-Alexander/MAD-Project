package com.example.smartpantry;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class full_recipe_list extends AppCompatActivity {

    Button btnCrud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_full_recipe_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DBRecipeHelper databaseAdapter = new DBRecipeHelper(this);

        ListView lvRec = findViewById(R.id.lvRecipe);

        SimpleCursorAdapter simpleCursorAdapter = databaseAdapter.populateListViewFromDB();
        lvRec.setAdapter(simpleCursorAdapter);
        lvRec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String name = cursor.getString(0);
                Toast.makeText(full_recipe_list.this, name, Toast.LENGTH_LONG).show();
            }
        });

        btnCrud = findViewById(R.id.btnCrud);

        btnCrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(full_recipe_list.this, recipe_crud.class);
                startActivity(i);
            }
        });

    }
}