package com.example.carteiradesaude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import javax.xml.transform.sax.SAXResult;

public class lista_medicos extends AppCompatActivity {

    String especialidade;
    ListView myList;
    ArrayList<String> arrayLista = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayList <String> array_id = new ArrayList<String>();
    String selecionado, id;
    String nome_medico, cfm_medico,divisao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);

        Intent intent = getIntent();
        especialidade = intent.getStringExtra("especialidade");
        id = intent.getStringExtra("id");
        myList = (ListView) findViewById(R.id.myList);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            // URL do site

            array_id = new ArrayList<String>();

            URL site = new URL("http:/192.168.0.3/login7/filtra_medico.php?especialidade=" + especialidade);


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

                String registro;
                String registro2;
                //baseArrayLista2 registro = new baseArrayLista2();
                registro = linhaJSON.getString("nome");
                registro = registro + "  -  CRM: " + linhaJSON.getString("cfm");

                registro2 = linhaJSON.getString("id");

                //registro.especialidade  = linhaJSON.getString("especialidade");

                //esp[contador] = linhaJSON.getString("especialidade");

                arrayLista.add(registro);
                array_id.add(registro2);
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
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                selecionado = array_id.get(i);

                divisao = arrayLista.get(i);

                //divisao = divisao.replace(" ", ""); //remove UM ponto
                divisao = divisao.replace(":", ""); //remove UM ponto
                divisao = divisao.replace("CRM", ""); //remove UM ponto
                divisao = divisao.replace("MED", ""); //remove UM ponto
                divisao = divisao.replace("Nome", ""); //remove UM ponto
                divisao = divisao.replace("medico", ""); //remove UM ponto



                String[] separated = divisao.split("-");



                nome_medico = separated[0];
                cfm_medico = separated[1];
                cfm_medico = cfm_medico.replaceAll(" ","");



                Intent intent = new Intent(getApplicationContext(), calendario.class);
                intent.putExtra("especialidade", especialidade);
                intent.putExtra("id_usuario",id);
                intent.putExtra("id_medico",selecionado);
                intent.putExtra("cfm",cfm_medico);
                intent.putExtra("nome",nome_medico);

                startActivity(intent);
                finish();

                //String mensagem = "ID user: " + id + " ID_MEDICO: " + selecionado + " Especialidade: " + especialidade+
                   //     " CFM: "+cfm_medico+ "Nome: "+nome_medico;

               // Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();


            }
        });





    }
}