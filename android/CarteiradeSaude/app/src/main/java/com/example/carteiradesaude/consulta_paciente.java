package com.example.carteiradesaude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class consulta_paciente extends AppCompatActivity {

    ArrayList<String> arrayLista = new ArrayList<String>();
    String id;
    ArrayAdapter<String> adapter;
    ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_paciente);

        myList = (ListView) findViewById(R.id.myList);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");


        try {
            // URL do site

            //array_id = new ArrayList<String>();

            URL site = new URL("http:/192.168.0.3/login7/agenda/consulta_paciente.php?id=" + id);


            // abrir a conexão com o site
            HttpURLConnection conexao = (HttpURLConnection) site.openConnection();
            conexao.connect();

            // ler os dados do site
            InputStream entradaDados = conexao.getInputStream();
            BufferedReader leitor = new BufferedReader(new InputStreamReader(entradaDados));

            // decodificar = ler o InputStream linha a linha
            String linha;
            StringBuilder decodificador = new StringBuilder();

            // este laço lê linha a linha o InputStream
            while ((linha = leitor.readLine()) != null) {
                decodificador.append(linha);
            }

            // agora vamos ler o JSON
            String stringJSON = decodificador.toString();

            JSONArray saidaJSON = new JSONArray(stringJSON);
            arrayLista = new ArrayList<String>();


            // adiciona as linhas de dados do RecyclerView
            for (int contador = 0; contador < saidaJSON.length(); contador++) {
                JSONObject linhaJSON = saidaJSON.getJSONObject(contador);

                //String registro = linhaJSON.getString("id");

               String registro= "Id da consulta: ";

                registro = registro + linhaJSON.getString("id");

                registro = registro + " -  ID Medico: " + linhaJSON.getString("cod_medico");

                registro = registro + "\n";

                registro = registro + "Data: " + linhaJSON.getString("data");

                registro = registro + " (" + linhaJSON.getString("dia_semana") + ")";

                registro = registro + "\n";

                registro = registro + "Horário: " + linhaJSON.getString("hora");




                arrayLista.add(registro);

            }


            // colocar o adaptador
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayLista);

            myList.setAdapter(adapter);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



        //Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), home_paciente.class);
        intent.putExtra("id",id);
        startActivity(intent);

        finish();
    }
}