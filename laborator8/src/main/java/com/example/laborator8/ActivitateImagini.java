package com.example.laborator8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivitateImagini extends AppCompatActivity {

    private List<Bitmap> imagini=new ArrayList<>();
    private List<ImaginiMarmura> lista=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   WindowCompat.enableEdgeToEdge(getWindow());
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_activitate_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        List<String> linkuriImagini = new ArrayList<>();

        linkuriImagini.add(
                "https://www.decorama.ro/16-large_default/marmura-alb-negru.jpg"
        );

        linkuriImagini.add(
                "https://travertinonline.ro/wp-content/uploads/2019/04/Rainbow-marmura-1cmx7.5x15-antichizat-e1745872374607.jpg"
        );

        linkuriImagini.add(
                "https://cdn.dedeman.ro/media/catalog/product/7/0/7059364_1_1.jpg?optimize=low&fit=bounds&height=700&width=700&canvas=700:700"
        );

        linkuriImagini.add(
                "https://stonexpert.ro/wp-content/uploads/2022/08/sunny_menia_marmura.jpeg"
        );

        linkuriImagini.add(
                "https://rocasdecor.ro/wp-content/uploads/2023/04/CULORI-MARMURA.jpg"
        );


        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        ListView lv = findViewById(R.id.imagini_LV);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                imagini.clear();

                for (String link : linkuriImagini) {
                    HttpURLConnection con = null;
                    Bitmap bitmap = null;

                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();


                        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                            try (InputStream is = con.getInputStream()) {
                                bitmap = BitmapFactory.decodeStream(is);
                            }
                        } else {
                            Log.e("ActivitateImagini",
                                    "Raspuns HTTP invalid: " + con.getResponseCode());
                        }

                        if (bitmap == null) {
                            Log.e("ActivitateImagini",
                                    "Imaginea nu a putut fi decodificata: " + link);
                        }

                    } catch (IOException e) {
                        Log.e("ActivitateImagini",
                                "Eroare la descarcarea imaginii: " + link, e);
                    } finally {
                        if (con != null) {
                            con.disconnect();
                        }
                    }

                    imagini.add(bitmap);
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        lista.clear();

                        String[] denumiri = {
                                "Marmura alb-negru",
                                "Marmura Rainbow",
                                "Marmura decorativa",
                                "Marmura Royal pavaj",
                                "Culori marmura"
                        };

                        for (int i = 0; i < imagini.size(); i++) {
                            Bitmap bitmap = imagini.get(i);

                            if (bitmap != null) {
                                lista.add(new ImaginiMarmura(
                                        denumiri[i],
                                        linkuriImagini.get(i),
                                        bitmap
                                ));
                            }
                        }


                        ImagineAdapter adapter = new ImagineAdapter(
                                ActivitateImagini.this,
                                R.layout.img_layout,
                                lista
                        );

                        lv.setAdapter(adapter);

                    }
                });
            }
        });


        List<String> linkuriDetalii = new ArrayList<>();

        linkuriDetalii.add(
                "https://www.decorama.ro/autocolant-imitatie-marmura/28-marmura-alb-negru.html"
        );

        linkuriDetalii.add(
                "https://travertinonline.ro/produs/placi-marmura-rainbow-antichizat/"
        );

        linkuriDetalii.add(
                "https://www.dedeman.ro/ro/marmura-decorativa-naturala-sparta-decolex-interior-exterior-alb-15-25-mm-20-kg/p/7059364"
        );

        linkuriDetalii.add(
                "https://stonexpert.ro/product/marmura-royal-pavaj/"
        );

        linkuriDetalii.add(
                "https://rocasdecor.ro/culori-marmura/"
        );


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent it = new Intent(
                        ActivitateImagini.this,
                        ActivitateWebView.class
                );

                it.putExtra("link", linkuriDetalii.get(position));
                startActivity(it);
            }
        });}}


