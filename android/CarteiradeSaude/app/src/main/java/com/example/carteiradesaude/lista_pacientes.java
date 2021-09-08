package com.example.carteiradesaude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import java.util.jar.JarEntry;

public class lista_pacientes extends AppCompatActivity implements adaptadorArrayLista.ClickItem{

    // puxa os campos de tela para programação
    EditText     txtNome, txtUsername;
    RecyclerView lista;
    int posicao = -1;

    // variáveis que preenchem a lista do RecyclerView
    adaptadorArrayLista adaptador; // transformar o array num meio que o RecyclerView possa usar
    ArrayList<baseArrayLista> arrayLista = new ArrayList<baseArrayLista>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);


        // puxar os campos da tela MainActivity pelo ID

        lista     = findViewById(R.id.listaDados);


        // para que possamos chamar uma URL (site) no Android, precisamos dar permissão para a chamada externa
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // chamar a função que preenche o RecyclerView
        preencheLista();
    }



    public void preencheLista(){
        // esta função preenche o RecyclerView
        try {
            // URL do site
            URL site = new URL("http:/192.168.0.3/login7/lista_dados/buscaDados.php");

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
            arrayLista = new ArrayList<baseArrayLista>();

            // adiciona a primeira linha do RecyclerView
            baseArrayLista cabecalho = new baseArrayLista();
            cabecalho.id    = "ID";
            cabecalho.nome  = "Nome";
            cabecalho.username= "Username";
            arrayLista.add(cabecalho);

            // adiciona as linhas de dados do RecyclerView
            for(int contador = 0; contador < saidaJSON.length(); contador++){
                JSONObject linhaJSON = saidaJSON.getJSONObject(contador);

                baseArrayLista registro = new baseArrayLista();
                registro.id    = linhaJSON.getString("id");
                registro.nome  = linhaJSON.getString("nome");
                registro.username = linhaJSON.getString("username");
                arrayLista.add(registro);
            }


            // vamos programar o RecyclerView
            lista.setLayoutManager(new LinearLayoutManager(this));

            // colocar o adaptador
            adaptador = new adaptadorArrayLista(this, arrayLista);
            adaptador.setClickListener(this);

            lista.setAdapter(adaptador);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // tratar o que vai acontecer quando clicar no item
    public void onItemClick(View v, int position){
        if(position > 0)
        {
            posicao = Integer.valueOf(arrayLista.get(position).id);
            //Toast.makeText(this, "ID:"+posicao, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), dados_saude_paciente.class);
            String sposicao = String.valueOf(posicao);
            intent.putExtra("id", sposicao);
            startActivity(intent);
            finish();




        }
    }



}
