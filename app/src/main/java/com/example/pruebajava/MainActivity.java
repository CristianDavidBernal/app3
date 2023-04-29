package com.example.pruebajava;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos la dirección IP de localhost
        String localhost = "http://127.0.0.1:8080/order/list?response_type=code&client_id=&redirect_uri=%2F";

        try {
            // Creamos una URL para conectarnos al servidor
            URL url = new URL(localhost);

            // Abrimos una conexión HTTP
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Configuramos la conexión
            conn.setRequestMethod("GET");

            // Leemos la respuesta del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Mostramos la respuesta en un TextView
            TextView textView = findViewById(R.id.tvVer);
            textView.setText(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
