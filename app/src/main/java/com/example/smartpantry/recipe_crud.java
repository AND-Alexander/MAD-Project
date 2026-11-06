package com.example.smartpantry;

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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class recipe_crud extends AppCompatActivity {

    Button btnCreate, btnRead, btnEdit, btnDelete;
    EditText edtRecipe, edtIngredient, edtSteps;


    String db_url = "jdbc:sqlite:smart_pantry.db";
    Connection conn = null;
    Statement stmt = null;

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

        DBRecipeHelper databaseAdapter = new DBRecipeHelper(this);

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
        btnRead = findViewById(R.id.btnRead);
        btnEdit = findViewById(R.id.btnUpdate);
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

                try {
                    conn = DriverManager.getConnection(db_url);

                    String create = "INSERT INTO recipes (recipe_name, ingredient, simple_preparations)" +
                            " VALUES ('"+ recipe +"', '"+ ing +"', '"+ step +"')";

                    stmt = conn.createStatement();
                    stmt.executeUpdate(create);

                } catch (SQLException e) {
                    System.out.println("Unable to insert Data.");
                }
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipe = edtRecipe.getText().toString();
                String ing = edtIngredient.getText().toString();
                String step = edtSteps.getText().toString();

                try {
                    conn = DriverManager.getConnection(db_url);
                    String edit = "UPDATE recipes SET amount = '"+ ing +"', simple_preparations = '" + step + "') "+
                            "WHERE recipe_name = '" + recipe + "'";

                    stmt = conn.createStatement();
                    stmt.executeUpdate(edit);

                } catch (SQLException e) {
                    System.out.println("Unable to edit Data.");
                }

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipe = edtRecipe.getText().toString();

                try {
                    conn = DriverManager.getConnection(db_url);
                    String delete = "DELETE FROM pantry WHERE recipe_name = '" + recipe + "'";

                    stmt = conn.createStatement();
                    stmt.executeUpdate(delete);

                } catch (SQLException e) {
                    System.out.println("Unable to delete Data.");
                }
            }

        });

    }
}