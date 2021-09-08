package com.example.carteiradesaude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
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

public class home_agendamento extends AppCompatActivity {

    SearchView mySearchView;
    ListView myList;
    ArrayList<String> arrayLista = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Button btn_Buscar;
    String selecionado, id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_agendamento);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        mySearchView = (SearchView) findViewById(R.id.mySearchView);
        myList = (ListView) findViewById(R.id.myList);
        btn_Buscar = findViewById(R.id.btn_Buscar);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            // URL do site
            URL site = new URL("http:/192.168.0.3/login7/lista_especialidade.php");

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
            while((linha = leitor.readLine()) != null){
                decodificador.append(linha);
            }

            // agora vamos ler o JSON
            String stringJSON = decodificador.toString();

            JSONArray saidaJSON = new JSONArray(stringJSON);
            arrayLista = new ArrayList<String>();



            // adiciona as linhas de dados do RecyclerView
            for(int contador = 0; contador < saidaJSON.length(); contador++){
                JSONObject linhaJSON = saidaJSON.getJSONObject(contador);

                String registro;
                //baseArrayLista2 registro = new baseArrayLista2();
                registro = linhaJSON.getString("especialidade");
                //registro.especialidade  = linhaJSON.getString("especialidade");

                //esp[contador] = linhaJSON.getString("especialidade");

                arrayLista.add(registro);
            }


            // colocar o adaptador
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayLista);

            myList.setAdapter(adapter);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                myList.setVisibility(View.VISIBLE);

                adapter.getFilter().filter(s);
                myList.setAdapter(adapter);


                return false;
            }
        });

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                selecionado = myList.getItemAtPosition(i).toString();

                //Toast.makeText(getApplicationContext(),selecionado,Toast.LENGTH_SHORT).show();

               // mySearchView.setIconifiedByDefault(false);// Expand it.isIconified = false;// Expand it
                mySearchView.setQuery(selecionado, true);// true if you want to submit, otherwise false
                mySearchView.clearFocus(); // so the keyboard is not show up.
                myList.setVisibility(View.INVISIBLE);


            }
        });
        btn_Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selecionado==null||selecionado=="")
                {
                    Toast.makeText(getApplicationContext(),"Selecione alguma especialidade!",Toast.LENGTH_SHORT).show();

                }
                else
                {
                   // Toast.makeText(getApplicationContext(),selecionado,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), lista_medicos.class);
                   intent.putExtra("especialidade", selecionado);
                   intent.putExtra("id",id);
                   startActivity(intent);
                    finish();
                }

            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), home_paciente.class);
        intent.putExtra("id",id);
        startActivity(intent);

        finish();
    }
}