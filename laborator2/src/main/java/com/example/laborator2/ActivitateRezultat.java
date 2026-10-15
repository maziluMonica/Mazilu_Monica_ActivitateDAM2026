package com.example.laborator2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//8. La deschiderea activității trimiteți un mesaj și două valori de tip întreg către activitatea deschisă printr-un Bundle.
//
//9. În activitatea nou deschisă preluați informațiile trimise din activitatea precedentă și le afișați într-un Toast.
//toast=mesaj scurt 1-2 sec de confirmare
//10. În activitatea nou deschisa trebuie sa aveți un buton care să trimită către activitatea precedentă un alt mesaj si suma celor două valori primite.
//
//11. În activitatea inițială vor fi afișate printr-un Toast mesajul primit și valoarea calculată.
public class ActivitateRezultat extends AppCompatActivity {

    private int nr1;
    private int nr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_rezultat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //aflam intentul prin care activitatea s a lansat
        Intent it=getIntent();
        Bundle bundle=it.getExtras();
        String mesaj=bundle.getString("mesaj","");//daca nu gaseste mesaj, imi da ""
        this.nr1=bundle.getInt("numar1",0);
        this.nr2=bundle.getInt("numar2",0);

        Toast.makeText(this,mesaj,Toast.LENGTH_SHORT).show();
    }

    public void intoarceInActivitate(View view) {
        String altMesaj="Mesaj nou";
        int suma=this.nr1 + this.nr2;

        Intent it=new Intent();
        it.putExtra("altMesaj", altMesaj);
        it.putExtra("suma",suma);
        setResult(RESULT_OK, it);

        finish();
    }
}
