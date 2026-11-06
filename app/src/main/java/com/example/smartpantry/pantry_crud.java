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
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class pantry_crud extends AppCompatActivity {

    Button btnPanCreate, btnPanRead, btnPanEdit, btnPanDelete;
    EditText edtPanIng, edtPanAmount, edtPanMeas, edtPanExp;


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

        ListView lvRec = findViewById(R.id.lstPanCrud);

        SimpleCursorAdapter simpleCursorAdapter = databaseAdapter.populateListViewFromDB();
        lvRec.setAdapter(simpleCursorAdapter);
        lvRec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String name = cursor.getString(0);
                Toast.makeText(pantry_crud.this, name, Toast.LENGTH_LONG).show();
            }
        });


        btnPanCreate = findViewById(R.id.btnPanCreate);
        btnPanRead = findViewById(R.id.btnPanRead);
        btnPanEdit = findViewById(R.id.btnPanUpdate);
        btnPanDelete = findViewById(R.id.btnPanDelete);

        edtPanIng = findViewById(R.id.edtPanIng);
        edtPanAmount = findViewById(R.id.edtPanAmount);
        edtPanMeas = findViewById(R.id.edtPanMeas);
        edtPanExp = findViewById(R.id.edtPanExp);

            btnPanCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ing = edtPanIng.getText().toString();
                    int amount = Integer.parseInt(edtPanAmount.getText().toString());
                    String meas = edtPanMeas.getText().toString();
                    String expiration = edtPanExp.getText().toString();
                    try {
                        conn = DriverManager.getConnection(db_url);
                        String create = "INSERT INTO pantry (ingredient, amount, measurement, expiration)" +
                            " VALUES ('"+ ing +"', "+ amount +", '"+ meas +"', '"+ expiration +"')";

                        stmt = conn.createStatement();
                        stmt.executeUpdate(create);

                    } catch (SQLException e) {
                        System.out.println("Unable to insert Data.");
                    }
                }
            });
            btnPanRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListView lvRec = findViewById(R.id.lstPanCrud);

                    SimpleCursorAdapter simpleCursorAdapter = databaseAdapter.populateListViewFromDB();
                    lvRec.setAdapter(simpleCursorAdapter);
                    lvRec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                            String name = cursor.getString(0);
                            Toast.makeText(pantry_crud.this, name, Toast.LENGTH_LONG).show();
                        }
                    });

                }
            });
            btnPanEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ing = edtPanIng.getText().toString();
                    int amount = Integer.parseInt(edtPanAmount.getText().toString());
                    String meas = edtPanMeas.getText().toString();
                    String expiration = edtPanExp.getText().toString();

                    try {
                        conn = DriverManager.getConnection(db_url);
                        String edit = "UPDATE pantry SET amount = "+ amount +", measurement = '" + meas + "', expiration = '" + expiration + "') "+
                                "WHERE ingredient = '" + ing + "'";

                        stmt = conn.createStatement();
                        stmt.executeUpdate(edit);

                    } catch (SQLException e) {
                        System.out.println("Unable to edit Data.");
                    }

                }
            });
            btnPanDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ing = edtPanIng.getText().toString();

                    try {
                        conn = DriverManager.getConnection(db_url);
                        String delete = "DELETE FROM pantry WHERE ingredient = '" + ing + "'";

                        stmt = conn.createStatement();
                        stmt.executeUpdate(delete);

                    } catch (SQLException e) {
                        System.out.println("Unable to delete Data.");
                    }


                }

            });


    }
}