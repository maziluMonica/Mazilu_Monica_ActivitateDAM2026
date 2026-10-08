package com.example.laborator2;
//cand adaugam un nou modul,lucram in el, in fisierele de dedesubt laborator2, nu la cele din app
//cream noi module, laborator 2, 3, etc , task suplimentar 1, 2, etc

//1. Creați o aplicație în Android Studio cu o activitate Empty, în java, cu API 24.
//
//2. Implementați metodele pentru prezentarea ciclului de viață al unei activități: onStart() = activitatea de vine vizibila, onResume() = utilizatorul poate interactiona cu activ, onPause()=apare alt dialog (un apel) si activitatea ramane vizibila, dar inactiva, onStop()= utilizatorul deschide o alta aplicatie, activitatea se duce in background, nu mai e nici vizibila.
//onDestroy=aplicatia e stearsa se inchide de tot
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate =  creeaza resursele pt activitate si le pune in memorie
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Seminar","OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Seminar","OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Seminar","OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Seminar","OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("Seminar","OnDestroy");
    }
    //3. In cadrul fiecărei metode salvați un log de tipul error,
    // warning, debug, info: Log.e()=error, Log.w()=warning, Log.d()=debug, Log.i()=information, Log.v()=ceva ca information.
    //loguri=fisiere cu info despre erorile care apar

    //daca pun pe landscape, se apeleaza on destroy so on start iar (se sterge aplicatia si se recreaza cu onCreate)

    //4. salvam logurilor in modulul laborator2

}