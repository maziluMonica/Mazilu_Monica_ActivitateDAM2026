package com.example.laborator3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AfisareObiecte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afisare_obiecte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it=getIntent();
        Bundle bundle=it.getExtras();
        ArrayList<String> lista = bundle.getStringArrayList("Lista");
        if (lista == null) {
            lista = new ArrayList<>();
        }

        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                lista
        );
        ListView lv=findViewById(R.id.Afisare_LV);
        lv.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(
                    android.widget.AdapterView<?> parent,
                    android.view.View view,
                    int position,
                    long id) {

                Marmura marmuraSelectata = (Marmura)adapter.getItem(position);

                if (marmuraSelectata != null) {
                    Toast.makeText(
                            AfisareObiecte.this,
                            marmuraSelectata.toString(),
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

// 4. La apăsarea lungă, obiectul este șters din listă.
        lv.setOnItemLongClickListener(
                new ListView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(
                            android.widget.AdapterView<?> parent,
                            android.view.View view,
                            int position,
                            long id) {

                        Marmura marmuraStearsa =(Marmura) adapter.getItem(position);

                        if (marmuraStearsa != null) {
                            adapter.remove(marmuraStearsa);


                            Toast.makeText(
                                    AfisareObiecte.this,
                                    "Obiect șters!",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }

                        return true;
                    }
                }
        );
        }
    }
