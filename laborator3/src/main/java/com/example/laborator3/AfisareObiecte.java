package com.example.laborator3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AfisareObiecte extends AppCompatActivity {

    private  MarmuraAdapter adapter;
    private ArrayList<Marmura> lista;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2 && resultCode==RESULT_OK && data !=null)
        {
            int position=data.getIntExtra("position",-1);

            if(position>=0)
            {
                Marmura marmura=lista.get(position);

                marmura.setCuloare(data.getStringExtra("culoare"));
                marmura.setTipMarmura(data.getStringExtra("tipMarmura"));
                marmura.setPretPeMetruPatrat(data.getDoubleExtra("pret", 0));
                marmura.setFinisaj(data.getStringExtra("finisaj"));
                marmura.setEsteInStoc(data.getBooleanExtra("esteInStoc", false));

                adapter.notifyDataSetChanged();
                Toast.makeText(
                        this,
                        "Marmura modificata cu succes!",
                        Toast.LENGTH_SHORT
                ).show();


            }
        }

    }

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


        Intent it = getIntent();

        /*ArrayList<String> lista =
                it.getStringArrayListExtra("Lista");
*/
         lista=it.getParcelableArrayListExtra("listaMarmura");
        if (lista == null) {
            lista = new ArrayList<>();
        }

       /* ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                lista
        );*/

        ListView lv = findViewById(R.id.Afisare_LV);
        /*lv.setAdapter(adapter);*/
         adapter=new MarmuraAdapter(this, lista);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(
                    android.widget.AdapterView<?> parent,
                    android.view.View view,
                    int position,
                    long id) {
/*
                String marmuraSelectata = (String)adapter.getItem(position);

                if (marmuraSelectata != null) {
                    Toast.makeText(
                            AfisareObiecte.this,
                            marmuraSelectata,
                            Toast.LENGTH_SHORT
                    ).show();
                }*/

                Marmura marmuraSelectata=(Marmura) adapter.getItem(position);

                Intent it=new Intent(AfisareObiecte.this, IntroducereDate.class);
               it.putExtra("marmuraSelectata",marmuraSelectata);
               it.putExtra("position",position);
               startActivityForResult(it,2);

            }
        });




        lv.setOnItemLongClickListener(
                new ListView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(
                            android.widget.AdapterView<?> parent,
                            android.view.View view,
                            int position,
                            long id) {

                 //       String marmuraStearsa =(String) adapter.getItem(position);
                        Marmura marmuraStearsa = (Marmura) adapter.getItem(position);

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
