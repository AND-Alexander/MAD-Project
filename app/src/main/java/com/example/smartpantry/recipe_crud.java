package com.example.smartpantry;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class recipe_crud extends AppCompatActivity {

    Button btnCreate, btnUpdate, btnEdit, btnDelete;
    EditText edtRecipe, edtIngredient, edtSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_crud);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        DataBaseAdapter databaseAdapter = new DataBaseAdapter(this);

        ListView lvRec = findViewById(R.id.lstRecpipeCRUD);

        SimpleCursorAdapter simpleCursorAdapter = databaseAdapter.populateListViewFromDB();
        lvRec.setAdapter(simpleCursorAdapter);
        lvRec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String name = cursor.getString(0);
                Toast.makeText(recipe_crud.this, name, Toast.LENGTH_LONG).show();
            }
        });

        btnCreate = findViewById(R.id.btnCreate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        edtRecipe = findViewById(R.id.edtRecipe);
        edtIngredient = findViewById(R.id.edtIngredients);
        edtSteps = findViewById(R.id.edtSteps);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipe = edtRecipe.getText().toString();
                String ing = edtIngredient.getText().toString();
                String step = edtSteps.getText().toString();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipe = edtRecipe.getText().toString();
                String ing = edtIngredient.getText().toString();
                String step = edtSteps.getText().toString();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipe = edtRecipe.getText().toString();
                String ing = edtIngredient.getText().toString();
                String step = edtSteps.getText().toString();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipe = edtRecipe.getText().toString();
            }
        });

    }
}