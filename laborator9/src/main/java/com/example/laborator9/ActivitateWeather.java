
package com.example.laborator9;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivitateWeather extends AppCompatActivity {

    private String cheieOras;
    private Spinner perioada_SP;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_activitate_weather);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                });

        perioada_SP = findViewById(R.id.perioada_SP);
    }

    public void cautareOras(View view) {
        String apikey = "";

        EditText oras_ET = findViewById(R.id.oras_ET);
        String oras = oras_ET.getText().toString().trim();
        TextView oras_TV = findViewById(R.id.oras_TV);

        if (oras.isEmpty()) {
            Toast.makeText(
                    this,
                    "Introdu numele orasului!",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        String perioada = perioada_SP.getSelectedItem().toString();

        Executor executor = Executors.newSingleThreadExecutor();



        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String orasCodificat = URLEncoder.encode(
                            oras, StandardCharsets.UTF_8.name());

                    String link =
                            "https://dataservice.accuweather.com/locations/v1/cities/search"
                                    + "?apikey=" + apikey
                                    + "&q=" + orasCodificat;

                    URL url = new URL(link);
                    HttpURLConnection conn =
                            (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(10000);
                    conn.setReadTimeout(10000);

                    int codRaspuns = conn.getResponseCode();

                    if (codRaspuns != HttpURLConnection.HTTP_OK) {
                        conn.disconnect();
                        throw new IOException(
                                "Eroare la cautarea orasului: HTTP "
                                        + codRaspuns);
                    }

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    conn.getInputStream(),
                                    StandardCharsets.UTF_8));

                    StringBuilder builder = new StringBuilder();
                    String linie;

                    while ((linie = reader.readLine()) != null) {
                        builder.append(linie);
                    }

                    reader.close();
                    conn.disconnect();

                    JSONArray vector =
                            new JSONArray(builder.toString());

                    if (vector.length() == 0) {
                        throw new IOException(
                                "Orasul nu a fost gasit!");
                    }

                    JSONObject obiect = vector.getJSONObject(0);
                    cheieOras = obiect.getString("Key");
                    String numeOras = obiect.getString("LocalizedName");

                    String metoda = perioada.equals("1 zi")
                            ? "1day"
                            : "5day";

                    String linkVreme =
                            "https://dataservice.accuweather.com/forecasts/v1/daily/"
                                    + metoda + "/" + cheieOras
                                    + "?apikey=" + apikey
                                    + "&language=ro-ro"
                                    + "&metric=true";

                    URL urlVreme = new URL(linkVreme);

                    HttpURLConnection connVreme =
                            (HttpURLConnection) urlVreme.openConnection();

                    connVreme.setRequestMethod("GET");
                    connVreme.setConnectTimeout(10000);
                    connVreme.setReadTimeout(10000);

                    int codVreme = connVreme.getResponseCode();

                    if (codVreme != HttpURLConnection.HTTP_OK) {
                        connVreme.disconnect();
                        throw new IOException(
                                "Eroare la obtinerea vremii: HTTP "
                                        + codVreme);
                    }

                    BufferedReader readerVreme = new BufferedReader(
                            new InputStreamReader(
                                    connVreme.getInputStream(),
                                    StandardCharsets.UTF_8));

                    StringBuilder builderVreme = new StringBuilder();

                    while ((linie = readerVreme.readLine()) != null) {
                        builderVreme.append(linie);
                    }

                    readerVreme.close();
                    connVreme.disconnect();

                    JSONObject obiectVreme =
                            new JSONObject(builderVreme.toString());

                    JSONArray prognoze =
                            obiectVreme.getJSONArray("DailyForecasts");

                    StringBuilder rezultat = new StringBuilder();

                    rezultat.append("Prognoza pentru ")
                            .append(numeOras)
                            .append("\n\n");

                    for (int i = 0; i < prognoze.length(); i++) {
                        JSONObject prognoza =
                                prognoze.getJSONObject(i);

                        JSONObject temperatura =
                                prognoza.getJSONObject("Temperature");

                        JSONObject minima =
                                temperatura.getJSONObject("Minimum");

                        JSONObject maxima =
                                temperatura.getJSONObject("Maximum");

                        String data =
                                prognoza.getString("Date").substring(0, 10);

                        double tempMin = minima.getDouble("Value");
                        double tempMax = maxima.getDouble("Value");

                        rezultat.append("Data: ")
                                .append(data)
                                .append("\n");

                        rezultat.append("Temperatura minima: ")
                                .append(tempMin)
                                .append(" °C\n");

                        rezultat.append("Temperatura maxima: ")
                                .append(tempMax)
                                .append(" °C\n\n");
                    }

                    String textRezultat = rezultat.toString();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            oras_TV.setText(textRezultat);

                            Toast.makeText(
                                    ActivitateWeather.this,
                                    "Cheia orasului: " + cheieOras,
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    });

                } catch (MalformedURLException e) {
                    afisareEroare(oras_TV, "Adresa URL nu este valida.");

                } catch (IOException e) {
                    afisareEroare(
                            oras_TV,
                            "Eroare de conexiune: " + e.getMessage());

                } catch (JSONException e) {
                    afisareEroare(
                            oras_TV,
                            "Eroare la citirea raspunsului JSON.");
                }
            }
        });
    }

    private void afisareEroare(TextView oras_TV, String mesaj) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                oras_TV.setText(mesaj);

                Toast.makeText(
                        ActivitateWeather.this,
                        mesaj,
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
