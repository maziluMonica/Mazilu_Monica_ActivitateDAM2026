package com.example.laborator3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void deschidereActivitate(View view) {
        Intent it = new Intent(this, IntroducereDate.class);
        startActivityForResult(it, 52);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 52) {
            if (resultCode == RESULT_OK && data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    String culoare = bundle.getString("culoare");
                    String tipMarmura = bundle.getString("tipMarmura");
                    double pret = bundle.getDouble("pret");
                    String finisaj = bundle.getString("finisaj");
                    boolean esteInStoc = bundle.getBoolean("esteInStoc");

                    Marmura marmura = new Marmura(culoare, tipMarmura, pret, finisaj, esteInStoc);
                    StringBuilder builder = new StringBuilder();
                    builder.append("Culoare: ").append(marmura.getCuloare());
                    builder.append("\nTip marmura: ").append(marmura.getTipMarmura());
                    builder.append("\nPret: ").append(marmura.getPretPeMetruPatrat());
                    builder.append("\nFinisaj: ").append(marmura.getFinisaj());
                    builder.append("\nEste in stoc: ").append(marmura.isEsteInStoc());

                    Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}