package com.example.productsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText e1,e2,e3,e4,e5;
    Button btnAdd,btnView,btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.editTextTextPersonName);
        e2 = findViewById(R.id.editTextTextPersonName2);
        e3 = findViewById(R.id.editTextTextPersonName3);
        e4 = findViewById(R.id.editTextTextPersonName4);
        e5 = findViewById(R.id.editTextTextPersonName5);
        btnAdd = findViewById(R.id.button);
        btnView = findViewById(R.id.button2);
        btnUpdate = findViewById(R.id.button3);
        btnDelete = findViewById(R.id.button4);
        // initialize the databasehelper
        db = new DatabaseHelper(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             boolean isInserted =   db.addItems(e2.getText().toString(),
                     e3.getText().toString(),
                     e4.getText().toString(),
                     e5.getText().toString());
             if(isInserted == true) {
               Toast.makeText(getApplicationContext(),"Item added ",
                       Toast.LENGTH_SHORT).show();
             } else {
                 Toast.makeText(getApplicationContext(),"Item not added ",
                         Toast.LENGTH_SHORT).show();
             }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated =   db.updateProduct(e1.getText().toString(),e2.getText().toString(),
                        e3.getText().toString(),
                        e4.getText().toString(),
                        e5.getText().toString());
                if(isUpdated == true) {
                    Toast.makeText(getApplicationContext(),"Item updated ",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Item not updated ",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer isDeleted = db.deleteProduct(e1.getText().toString());
                if (isDeleted > 0) {
                    Toast.makeText(getApplicationContext(),"Item deleted ",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Item not deleted ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.viewAll();
                if (res.getCount() == 0) {
                    showMessage ("error", "No products found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("id " + res.getString(0) + "\n" );
                    buffer.append("name " + res.getString(1) + "\n" );
                    buffer.append("brand " + res.getString(2) + "\n" );
                    buffer.append("cost " + res.getString(3) + "\n" );
                    buffer.append("qty " + res.getString(4) + "\n" );

                }
                showMessage ("Data", buffer.toString());

            }
        });


    }
    public void showMessage (String error, String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(error);
        builder.setMessage(s);
        builder.show();

    }

}