package com.example.mazilu_monica_activitatedam2026;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntroducereDate extends AppCompatActivity {

    private EditText tipMarmura_ET;
    private EditText pretMarmura_ET;
    private RadioGroup finisaj_RG;
    private CheckBox inStoc_CB;
    private Button buttonAdauga;
    private Spinner spinnerCuloare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_introducere_date);

        tipMarmura_ET = findViewById(R.id.tipMarmura_ET);
        pretMarmura_ET = findViewById(R.id.pretMarmura_ET);
        finisaj_RG = findViewById(R.id.finisaj_RG);
        inStoc_CB = findViewById(R.id.inStoc_CB);
        buttonAdauga = findViewById(R.id.buttonAdauga);
        spinnerCuloare = findViewById(R.id.spinnerCuloare);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.culori_marmura,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCuloare.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void adaugaMarmura(View view) {
        String culoare = spinnerCuloare.getSelectedItem().toString();
        String tipMarmura = tipMarmura_ET.getText().toString();
        String pretMarmura = pretMarmura_ET.getText().toString();
        int idFinisaj = finisaj_RG.getCheckedRadioButtonId();

        if (tipMarmura.isEmpty() || pretMarmura.isEmpty() || idFinisaj == -1) {
            Toast.makeText(this, "Completeaza toate campurile", Toast.LENGTH_LONG).show();
            return;
        }

        double pret;
        try {
            pret = Double.parseDouble(pretMarmura);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Introduceti un pret valid", Toast.LENGTH_LONG).show();
            return;
        }

        RadioButton radioSelectat = findViewById(idFinisaj);
        String finisaj = radioSelectat.getText().toString();
        boolean esteInStoc = inStoc_CB.isChecked();
        Marmura marmura = new Marmura(culoare, tipMarmura, pret, finisaj, esteInStoc);

        Toast.makeText(this, "Obiectul Marmura a fost creat!", Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putString("culoare", marmura.getCuloare());
        bundle.putString("tipMarmura", marmura.getTipMarmura());
        bundle.putDouble("pret", marmura.getPretPeMetruPatrat());
        bundle.putString("finisaj", marmura.getFinisaj());
        bundle.putBoolean("esteInStoc", marmura.isEsteInStoc());

        Intent it = new Intent();
        it.putExtras(bundle);
        setResult(RESULT_OK, it);
        finish();
    }
}