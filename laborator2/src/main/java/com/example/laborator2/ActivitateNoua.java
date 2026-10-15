package com.example.laborator2;

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

public class ActivitateNoua extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_noua);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void deschidereActivitate(View view) {
        Intent it = new Intent(this, ActivitateRezultat.class);
        //8. creez bundle
        Bundle bundle=new Bundle();
        bundle.putString("mesaj","Mesaj din prima activitate");
        bundle.putInt("numar1", 5);
        bundle.putInt("numar2",5);
        it.putExtras(bundle);
        startActivityForResult(it,52);//de obicei creez constanta cu codul si o folosesc, nu hardcodez
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==52)
        {
            if(resultCode==RESULT_OK)
            {
                String mesaj=data.getStringExtra("altMesaj");
                int suma=data.getIntExtra("suma",0);
               // Toast.makeText(this,mesaj+" "+suma, Toast.LENGTH_LONG).show(); nu facem mesaj+suma ca string e immutable si nu e bine se fac prea multe copii => folosim string builder
                StringBuilder builder=new StringBuilder();
                builder.append(mesaj);
                builder.append(" ");
                builder.append(suma);
                Toast.makeText(this,builder.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }
}
